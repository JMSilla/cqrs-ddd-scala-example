package tv.codely.mooc.video.domain

import scala.concurrent.duration.Duration

import tv.codely.mooc.shared.domain.user.UserId

object Video {
  def apply(id: String, title: String, duration: Duration, category: String, creatorId: String): Video = Video(
    VideoId(id),
    VideoTitle(title),
    VideoDuration(duration),
    VideoCategory(category),
    UserId(creatorId)
  )
}

case class Video(id: VideoId, var _title: VideoTitle, duration: VideoDuration, category: VideoCategory, creatorId: UserId) {
  def title = _title
  
  def updateTitle(title: VideoTitle): Unit = {
    this._title = title
  }
}
