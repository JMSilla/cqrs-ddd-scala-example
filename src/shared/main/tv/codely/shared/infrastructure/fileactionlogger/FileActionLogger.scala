package tv.codely.shared.infrastructure.fileactionlogger

import tv.codely.shared.domain.actionlogger.ActionLogger
import tv.codely.shared.domain.actionlogger.Action
import tv.codely.shared.domain.logger.Logger

class FileActionLogger(logger: Logger) extends ActionLogger {
  def logAction(action: Action): Unit = {
    logger.info(s"Action: source=$action.source -> message=$action.message")
  }
}