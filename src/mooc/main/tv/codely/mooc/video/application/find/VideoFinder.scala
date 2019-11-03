package tv.codely.mooc.video.application.find

import tv.codely.mooc.video.domain.VideoId
import tv.codely.mooc.video.domain.Video
import tv.codely.mooc.video.domain.VideoRepository
import tv.codely.mooc.video.domain.{VideoFinder => DomainVideoFinder}

class VideoFinder (repository: VideoRepository) {
  def find(id: VideoId): Video = {
    val finder = new DomainVideoFinder(repository)
    
    finder.find(id)
  }
}