package tv.codely.mooc.video.application.update

import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.domain.VideoTitle
import tv.codely.mooc.video.domain.VideoIdMother
import tv.codely.mooc.video.domain.VideoNotFound

class VideoTitleUpdaterShould extends UnitTestCase 
  with VideoRepositoryMock
{
  private val titleUpdater = new VideoTitleUpdater(repository)
  
  "update the title of an existing video" in {
    val video = VideoMother.random
    val id = video.id
    val title = VideoTitle("New title")
    
    repositoryShouldFindVideoById(video)
    repositoryShouldUpdateVideo(video)
    
    titleUpdater.updateTitle(id, title)
    assert(video.title == title)
  }
  
  "throw VideoNotFound when updating the title of a non existing video" in {
    val id = VideoIdMother.random
    val title = VideoTitle("New title")
    
    repositoryShouldNotFindVideoById(id)
    
    intercept[VideoNotFound] { 
      titleUpdater.updateTitle(id, title)
    }
  }
}