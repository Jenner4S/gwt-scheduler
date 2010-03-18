package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.EventIntervalCollisionException;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEndHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.calendar.CalendarFrame;
import gwtscheduler.common.event.Event;

import java.util.List;

/**
 * Represents the calendar content
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContent {
  public interface Display {
    CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay();

    void removeColumn(int calendarColumnIndex);

    void addColumn(String title);

    void fireResizeRedrawEvents();

    EventsDashboard.Display getEventsDashboard();
  }

  private CalendarColumnsFrameGrid calendarColumnsFrameGrid;
//  private boolean collision = false;
  private EventsDashboard eventsDashboard;
  private Display display;
  private List<CalendarColumn> columns;
//  private static final String EVENT_IN_COLLISION = "The dropped event interval is in collision with other already exist event";

  public CalendarContent(CalendarColumnsFrameGrid calendarColumnsFrameGrid, EventsDashboard eventsDashboard) {
    this.calendarColumnsFrameGrid = calendarColumnsFrameGrid;
    this.eventsDashboard = eventsDashboard;
  }

  public void bindDisplay(Display display) {
    this.display = display;

    calendarColumnsFrameGrid.bindDisplay(display.getCalendarColumnsFrameGridDisplay());
    eventsDashboard.bindDisplay(display.getEventsDashboard());
  }

  public List<Cell<Element>> getFrameGridDecorables() {
    return calendarColumnsFrameGrid.getTimeLineDecorables();
  }

  public void removeColumn(int index) {
    display.removeColumn(index);
  }

  public void addColumn(String title) {
    display.addColumn(title);
  }

  public void fireResizeRedrawEvents() {
    display.fireResizeRedrawEvents();
  }

  public void addContentChangeCallback(final ContentChange contentChange) {
    eventsDashboard.addContentChangeCallback(contentChange);
  }

  public void addCalendarEvent(Event event) {
    int rowsCount = display.getCalendarColumnsFrameGridDisplay().getRows();
    int columnIndex = 0;
    for (CalendarColumn column : columns) {
      if (column.isEventForColumn(event)) {
        eventsDashboard.addCalendarEvent(columnIndex, event, rowsCount);
      }
      columnIndex++;
    }
  }

  public void setColumns(List<CalendarColumn> columns) {
    this.columns = columns;
    eventsDashboard.setColumns(columns);
  }

  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
    return eventsDashboard.addEventResizeEndHandler(handler);
  }

  public HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    return eventsDashboard.addEventResizeStartHandler(handler);
  }

  public WidgetResizeHandler getEventsDachboardWidgetResizeHandler() {
    return eventsDashboard.getEventsDachboardWidgetResizeHandler();
  }

  public void updateEvent(Event event) {
    int columnIndex = 0;
    for (CalendarColumn column : columns) {
      if (column.isEventForColumn(event)) {
        eventsDashboard.updateCalendarEvent(columnIndex, event);
      }
      columnIndex++;
    }
  }
}
