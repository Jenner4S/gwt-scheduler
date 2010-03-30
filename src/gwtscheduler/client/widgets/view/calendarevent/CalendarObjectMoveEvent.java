package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.Instant;

/**
 * Fired when something already attached to calendar is moved to another place. Contains information about Calendar type
 * where something is moved, calendar title, object that is moved, column from where something is moved, column to where
 * something is moved, old time for moved object and new time where object is dropped.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarObjectMoveEvent extends GwtEvent<CalendarObjectMoveHandler>{
  public static final Type<CalendarObjectMoveHandler> TYPE = new Type<CalendarObjectMoveHandler>();
  private final CalendarType calendarType;
  private final String calendarTitle;
  private final Object droppedObject;
  private final CalendarColumn oldColumn;
  private final Instant oldTime;
  private final CalendarColumn newColumn;
  private final Instant newTime;

  public CalendarObjectMoveEvent(CalendarType calendarType, String calendarTitle, Object droppedObject, CalendarColumn oldColumn, Instant oldTime, CalendarColumn newColumn, Instant newTime) {
    this.calendarType = calendarType;
    this.calendarTitle = calendarTitle;
    this.droppedObject = droppedObject;
    this.oldColumn = oldColumn;
    this.oldTime = oldTime;
    this.newColumn = newColumn;
    this.newTime = newTime;
  }

  @Override
  public Type<CalendarObjectMoveHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarObjectMoveHandler handler) {
    handler.onCalendarObjectMove(this);
  }

  public CalendarType getCalendarType() {
    return calendarType;
  }

  public String getCalendarTitle() {
    return calendarTitle;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }

  public CalendarColumn getOldColumn() {
    return oldColumn;
  }

  public long getOldTime() {
    return oldTime.getMillis();
  }

  public CalendarColumn getNewColumn() {
    return newColumn;
  }

  public long getNewTime() {
    return newTime.getMillis();
  }
}
