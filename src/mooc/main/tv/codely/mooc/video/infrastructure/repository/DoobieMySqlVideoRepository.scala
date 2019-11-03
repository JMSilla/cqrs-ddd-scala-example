package tv.codely.mooc.video.infrastructure.repository

import doobie.implicits._
import tv.codely.mooc.video.domain.{Video, VideoRepository}
import tv.codely.mooc.shared.infrastructure.doobie.TypesConversions._
import tv.codely.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.{ExecutionContext, Future}
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.video.domain.VideoId

final class DoobieMySqlVideoRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends VideoRepository {
  override def all(): Future[Seq[Video]] =
    db.read(sql"SELECT video_id, title, duration_in_seconds, category, creator_id FROM videos".query[Video].to[Seq])

  override def save(video: Video): Future[Unit] =
    sql"INSERT INTO videos(video_id, title, duration_in_seconds, category, creator_id) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category}, ${video.creatorId})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
  
  override def lastVideoCreatedByUserWithId(userId: UserId): Option[Video] = {
    sql"SELECT video_id, title, duration_in_seconds, category, creator_id FROM videos WHERE creator_id = ${userId} ORDER BY updated_at DESC"
    .query[Video].to[List]
    .transact(db.transactor)
    .unsafeRunSync
    .headOption
  }
    
  override def find(id: VideoId): Option[Video] = {
    sql"SELECT video_id, title, duration_in_seconds, category, creator_id FROM videos WHERE video_id = ${id}"
    .query[Video]
    .option
    .transact(db.transactor)
    .unsafeRunSync
  }
}
