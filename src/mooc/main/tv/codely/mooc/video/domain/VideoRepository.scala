package tv.codely.mooc.video.domain

import scala.concurrent.Future
import tv.codely.mooc.shared.domain.user.UserId

trait VideoRepository {
  def all(): Future[Seq[Video]]

  def find(id: VideoId): Option[Video]
  
  def save(video: Video): Future[Unit]
  
  def lastVideoCreatedByUserWithId(userId: UserId): Option[Video]
}
