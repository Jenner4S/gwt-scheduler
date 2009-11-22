package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.view.common.GenericCalendarView;

/**
 * Display class for months.
 * @author malp
 */
public interface MonthDisplay extends GenericCalendarView {

  /**
   * Shows only the supplied rows.
   * @param rows the number of weeks to show
   */
  void showRows(int rows);

  /**
   * Gets the number of visible rows
   * @return
   */
  int getVisibleRows();

}
