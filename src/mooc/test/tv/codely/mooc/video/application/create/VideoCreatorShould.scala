package tv.codely.mooc.video.application.create

import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller
import tv.codely.mooc.video.domain.VideoActionMother
import tv.codely.mooc.video.domain.VideoCreatedMother
import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.actionlogger.ActionLoggerMock
import tv.codely.shared.infrastructure.rabbitmq.MessagePublisherMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideoCreatorShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock
  with ActionLoggerMock {
  private val creator = new VideoCreator(repository, messagePublisher, actionLogger)

  "save a video" in {
    val video        = VideoMother.random
    val videoCreated = VideoCreatedMother(video)
    val action = VideoActionMother("VideoCreator", video)
    
    repositoryShouldSave(video)
    publisherShouldPublish(videoCreated)(MessageMarshaller)
    actionLoggerShouldLog(action)
    
    creator.create(video.id, video.title, video.duration, video.category, video.creatorId).shouldBe(())
  }
}
