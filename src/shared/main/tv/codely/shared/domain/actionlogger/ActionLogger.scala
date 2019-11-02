package tv.codely.shared.domain.actionlogger

trait ActionLogger {
  def logAction(action: Action)
}