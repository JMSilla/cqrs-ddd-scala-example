package tv.codely.mooc.video.application.create

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller
import tv.codely.mooc.video.domain._
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.domain.actionlogger.ActionLogger
import tv.codely.shared.domain.actionlogger.Action

final class VideoCreator(repository: VideoRepository, publisher: MessagePublisher,
    actionLogger: ActionLogger) {
  def create(
      id: VideoId,
      title: VideoTitle,
      duration: VideoDuration,
      category: VideoCategory,
      creatorId: UserId
  ): Unit = {
    val video = Video(id, title, duration, category, creatorId)

    repository.save(video)

    publisher.publish(VideoCreated(video))(MessageMarshaller)
    actionLogger.logAction(Action("VideoCreator", s"Video created: $video"))
  }
}
