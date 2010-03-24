package gwtscheduler.common.event;

import org.goda.time.Interval;

/**
 * Event represents a single Event item in the calendar.
 * <p/>
 * 
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface Event {

  /**
   * Gets the interval of that event.
   * @return the interval value which indicates start/end and date of the current event
   */
  DurationInterval getDurationInterval();

  /**
   * Sets a new interval for the current event.
   *
   * @param interval the new interval to be set
   */
  void setDurationInterval(DurationInterval interval);

  /**
   * Gets the event title.
   * @return the event title
   */
  String getTitle();

  /**
   * Returns the column id as object
   * @return
   */
  Object getColumnId();

  /**
   * Return unique ID for the event, by this id event can be recognized when updating, moving or deleting.
   *
   * @return unique ID of the event.
   */
  String getEventId();

  /**
   * Return description for the event. This description will be showed in the body of the event.
   *
   * @return description for the event.
   */
  String getDescription();

  /**
   * Tels if event can be editable or not. If set to false event can be moved and cant be resized.
   *
   * @return if event can be editable or not.
   */
  boolean isEditable();

}
