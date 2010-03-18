package gwtscheduler.client.widgets.view.common.resize;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperProviderImpl implements CalendarEventResizeHelperProvider {
  private final DateGenerator dateGenerator;
  private EventsDashboard.Display display;
  private EventBus eventBus;

  public CalendarEventResizeHelperProviderImpl(DateGenerator dateGenerator, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.eventBus = eventBus;
  }

  @Override
  public void setDashboardDisplay(EventsDashboard.Display display) {
    this.display = display;
  }

  public CalendarEventResizeHelper attachResizeHelper(CalendarEvent calendarEvent) {
    CalendarEventResizeHelperImpl helper = new CalendarEventResizeHelperImpl(calendarEvent, display, dateGenerator, new CalendarEventResizeHelperView());
    return helper;
  }
}