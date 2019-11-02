package tv.codely.mooc.video.domain

import tv.codely.shared.domain.actionlogger.Action

object VideoActionMother {
  def apply(source: String, video: Video): Action = 
    Action(source, s"Video created: $video")
}