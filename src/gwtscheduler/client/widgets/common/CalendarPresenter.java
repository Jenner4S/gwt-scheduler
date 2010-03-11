package gwtscheduler.client.widgets.common;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.CalendarDropHandler;
import gwtscheduler.client.CalendarMoveHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * Defines a calendar controller. Responsible for mediating the view and the
 * listener. For most cases, the implementing class will be the listener itself.
 *
 * @author malp
 */
public interface CalendarPresenter {
  public interface Display extends GenericCalendarDisplay{

    CalendarHeader.Display getCalendarHeaderDisplay();

    CalendarContent.Display getCalendarContentDisplay();
  }

  public void bindDisplay(Display display);

  public void setTittle(String tabLabel);

  public Display getDisplay();

  /**
   * Gets the label for the view.
   *
   * @return the label
   */
  String getTitle();

  /**
   * Forces the layout of the display.
   */
  void forceLayout();

  /**
   * Gets the correspondent time interval for a given cell range
   *
   * @param start the starting cell
   * @param end the end cell
   * @return the time interval
   */
  Interval getIntervalForRange(int[] start, int[] end);

  /**
   * Gets the correspondent instant for a cell
   *
   * @param start the starting cell
   */
  Instant getInstantForCell(int[] start);

  void deleteColumn(CalendarColumn column);

  void addColumn(CalendarColumn column);

  HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler);

  HandlerRegistration addCalendarMoveHandler(CalendarMoveHandler handler);

  void setCalendarType(CalendarType type);

  CalendarType getCalendarType();

}
