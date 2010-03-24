package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.common.events.CellDropHandler;
import org.goda.time.Instant;

/**
 * Fired when something is dropped on the cell.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CellDropEvent extends GwtEvent<CellDropHandler> {
  public static Type<CellDropHandler> TYPE = new Type<CellDropHandler>();
  // contains two elements. First element is row number and second element is column number.
  private final int[] newCell;
  private final Instant newTime;
  private final Object droppedObject;

  public CellDropEvent(int[] cell, Instant time, Object droppedObject) {
    this.newCell = cell;
    this.newTime = time;
    this.droppedObject = droppedObject;
  }

  @Override
  public Type<CellDropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CellDropHandler handler) {
    handler.onCellDrop(this);
  }

  public int[] getCell() {
    return newCell;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }

  public Instant getTime() {
    return newTime;
  }
}