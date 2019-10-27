package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.mooc.user.domain.UserMother

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new VideosSearcher(repository)

  "search all existing videos" in {
    val existingVideo        = VideoMother.random
    val anotherExistingVideo = VideoMother.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }
  
  "search last video created by user with a given id" in {
    val existingUser = UserMother.random
    val existingVideo = VideoMother.random.copy(creatorId = existingUser.id)
    
    repositoryShouldFindVideoAsLastFromCreator(existingVideo)
    
    searcher.lastVideoCreatedByUserId(existingUser.id) shouldBe Some(existingVideo)
  }
}
