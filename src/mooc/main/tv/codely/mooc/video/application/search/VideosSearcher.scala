package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoRepository}

import scala.concurrent.Future
import tv.codely.mooc.shared.domain.user.UserId

final class VideosSearcher(repository: VideoRepository) {
  def all(): Future[Seq[Video]] = repository.all()
  def lastVideoCreatedByUserId(userId: UserId): Option[Video] =
    repository.lastVideoCreatedByUserWithId(userId)
}
