package tv.codely.mooc.video.domain

class VideoFinder (repository: VideoRepository) {
  def find(id: VideoId): Video = {
    val video = repository.find(id)
    
    video match {
      case Some(v) => v
      case None => throw new VideoNotFound
    }
  }
}