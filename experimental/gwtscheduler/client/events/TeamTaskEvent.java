package gwtscheduler.client.events;


import gwtscheduler.client.TestTask;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.DurationInterval;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.HasColors;
import gwtscheduler.common.event.colors.DefaultEventColor;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TeamTaskEvent implements Event, HasColors{
  private TestTask testTask;
  private CalendarColumn column;
  private DefaultEventColor color;
  private String id;

  public TeamTaskEvent(TestTask testTask, CalendarColumn column, DefaultEventColor color) {
    this.testTask = testTask;
    this.column = column;
    this.color = color;
    id = System.currentTimeMillis()+"";
  }

  @Override
  public DurationInterval getDurationInterval() {
    return testTask.getDurationInterval();
  }

  @Override
  public void setDurationInterval(DurationInterval durationInterval) {
    testTask.setDurationInterval(durationInterval); 
  }

  @Override
  public String getTitle() {
    return testTask.getTitle();
  }

  @Override
  public Object getColumnId() {
    return column.getId();
  }

  @Override
  public String getEventId() {
    return id;  
  }

  @Override
  public String getDescription() {
    return testTask.getDescription();  
  }

  @Override
  public boolean isEditable() {
    return true;
  }

  public void setColumn(CalendarColumn column) {
    this.column = column;
  }

  @Override
  public String getHeaderColor() {
    return color.getHeaderColor();
  }

  @Override
  public String getBodyColor() {
    return color.getBodyColor();
  }

  @Override
  public String getTitleColor() {
    return color.getTitleColor();
  }

  @Override
  public String getTextColor() {
    return color.getTextColor();
  }
}
