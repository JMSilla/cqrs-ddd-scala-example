package tv.codely.mooc.video.infrastructure.repository

import org.scalamock.scalatest.MockFactory
import tv.codely.mooc.video.domain.{Video, VideoRepository}
import scala.concurrent.Future

import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.mooc.video.domain.VideoId

protected[video] trait VideoRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))
  
  protected def repositoryShouldFindVideoById(video: Video): Unit =
    (repository.find _)
      .expects(video.id)
      .returning(Some(video))
  
  protected def repositoryShouldNotFindVideoById(id: VideoId): Unit =
    (repository.find _)
      .expects(id)
      .returning(None)
      
  protected def repositoryShouldFindVideoAsLastFromCreator(video: Video): Unit =
    (repository.lastVideoCreatedByUserWithId _)
      .expects(video.creatorId)
      .returning(Some(video))
}
