package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwtscheduler.client.dragndrop.DragZone;
import gwtscheduler.client.dragndrop.Zones;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;

import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.DateTimeConstants;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today,deleteColumn,addColumn;
  TextBox textBox = new TextBox();
  private EventBus eventBus = new EventBus();

   GwtScheduler main;
  private TestTeamCalendarColumnProvider testteams1 = new TestTeamCalendarColumnProvider();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    HorizontalPanel ticketsPanel = new HorizontalPanel();
    TicketPresenter ticket1 = new TicketPresenter(new TicketView2(), "Ticket one");
    TicketPresenter ticket2 = new TicketPresenter(new TicketView2(), "Ticket two");
    TicketPresenter ticket3 = new TicketPresenter(new TicketView2(), "Ticket three");

    ticketsPanel.add(ticket1.getDisplay());
    ticketsPanel.add(ticket2.getDisplay());
    ticketsPanel.add(ticket3.getDisplay());
    
    DragZone dragZone = Zones.getDragZone();
    dragZone.add(ticket1);
    dragZone.add(ticket2);
    dragZone.add(ticket3);

    back = new Button("&laquo;", this);
    forward = new Button("&raquo;", this);
    today = new Button("today", this);
    deleteColumn = new Button("delete Column",this);
    addColumn = new Button("makeDraggable Column",this);


    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);
    nav.add(textBox);
    nav.add(deleteColumn);
    nav.add(addColumn);

    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();

    main = schedulerBuilder.addTab(new CalendarsBuilder().newMultiColumn(new TestAppConfiguration(), testteams1,eventBus).named("Teams").build())
            .addTab(new CalendarsBuilder().newWeekColumn(new TestAppConfiguration(),eventBus).named("Team 1 Week Calendar").build()).build();
    main.addCalendarDropHandler(new CalendarDropHandler(){
      @Override
      public void onCalendarDrop(CalendarDropEvent event) {
        Object o = event.getDroppedObject();
        if(o instanceof TicketPresenter){
          GWT.log("Dropped: TicketPresenter", null);
          GWT.log("On calendar type: " + event.getCalendarType().toString(), null);
          GWT.log("On calendar with title: " + event.getCalendarTitle(), null);
          GWT.log("On column with title: " + event.getCalendarColumn().getTitle(), null);
          GWT.log("On time: " + event.getDropTime().toString(), null);
        }
      }
    });

    main.addCalendarMoveHandler(new CalendarMoveHandler(){
      @Override
      public void onCalendarMove(CalendarMoveEvent event){

      }
    });

    dragZone.addDropZoneRoot((HasWidgets)main.asWidget());
//    VerticalPanel dropRoot = new VerticalPanel();
//    dropRoot.makeDraggable(new Panel1());
//    dropRoot.makeDraggable(new Panel1());
//    dropRoot.makeDraggable(new Panel1());
//    dragZone.addDropZoneRoot(dropRoot);

    VerticalPanel mainPanel = new VerticalPanel();
//    mainPanel.makeDraggable(dropRoot);
    mainPanel.add(ticketsPanel);
    mainPanel.add(nav);
    mainPanel.add(main.asWidget());
    dragZone.addWidget(mainPanel);
    dragZone.go(RootPanel.get("nav"));


//    RootPanel.get("nav").makeDraggable(nav);
//    RootPanel.get("main").makeDraggable(main.asWidget());
    main.selectTab(0);
//    registry.fireDateNavigation(getCurrentDate());
   eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));     
  }

  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    return start;
  }

  public void onClick(ClickEvent event) {
//    AppInjector uiResources = AppInjector.GIN.getInjector();
//    UIManager registry = uiResources.getUIRegistry();

    if (event.getSource() == back) {
//      registry.fireBackNavigation();
      eventBus.fireEvent(new NavigatePreviousEvent());
    } else if (event.getSource() == forward) {
//      registry.fireForwardNavigation();
      eventBus.fireEvent(new NavigateNextEvent());
    } else if (event.getSource() == today) {
//      registry.fireDateNavigation(getCurrentDate());
      eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
    }else if(event.getSource() == deleteColumn){
      CalendarColumn  column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
        main.deleteColumn(column);
    }else if(event.getSource() == addColumn){
      if(textBox.getText()!=""){
      CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
        main.addColumn(column);
      }
    }

  }


  public static class TestAppConfiguration implements AppConfiguration {
    public TestAppConfiguration() {
    }

    @Override
    public int startDayOfWeek() {
      return DateTimeConstants.MONDAY;
    }


    @Override
    public int getDayViewTopRows() {
      return 3;
    }


    @Override
    public int daysInWeek() {
      return 7;
    }

    @Override
    public int daysLineHeightEMs() {
      return 2;
    }

    @Override
    public int rowsInDay() {
      return 48;
    }
  }

}
