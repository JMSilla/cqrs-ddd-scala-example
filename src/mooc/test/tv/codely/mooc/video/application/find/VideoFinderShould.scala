package tv.codely.mooc.video.application.find

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.mooc.video.domain.VideoIdMother
import tv.codely.mooc.video.domain.VideoNotFound

final class VideoFinderShould extends UnitTestCase 
  with VideoRepositoryMock
{
  private val finder = new VideoFinder(repository)
  
  "find an existing video" in {
    val video = VideoMother.random
    val id = video.id
    
    repositoryShouldFindVideoById(video)
    
    finder.find(id)
  }
  
  "throw VideoNotFound when not exists a video with the given id" in {
    val id = VideoIdMother.random
    
    repositoryShouldNotFindVideoById(id)
    
    intercept[VideoNotFound] { 
      finder.find(id)
    }
  }
}