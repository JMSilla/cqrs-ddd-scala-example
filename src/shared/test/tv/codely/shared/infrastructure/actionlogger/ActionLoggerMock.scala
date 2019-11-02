package tv.codely.shared.infrastructure.actionlogger

import org.scalamock.scalatest.MockFactory
import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.shared.domain.actionlogger.ActionLogger
import tv.codely.shared.domain.actionlogger.Action

trait ActionLoggerMock extends MockFactory {
  this: UnitTestCase =>
  
  protected val actionLogger: ActionLogger = mock[ActionLogger]
  
  protected def actionLoggerShouldLog(action: Action): Unit =
    (actionLogger.logAction _)
      .expects(action)
      .returning(())
}