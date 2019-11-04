package tv.codely.mooc.video.application.update

import tv.codely.mooc.video.domain.VideoRepository
import tv.codely.mooc.video.domain.VideoTitle
import tv.codely.mooc.video.domain.VideoId
import tv.codely.mooc.video.domain.VideoFinder

class VideoTitleUpdater (repository: VideoRepository) {
  def updateTitle(id: VideoId, title: VideoTitle): Unit = {
    val videoFinder = new VideoFinder(repository)
    val video = videoFinder.find(id)
    
    video.updateTitle(title)
    
    repository.update(video)
  }
}