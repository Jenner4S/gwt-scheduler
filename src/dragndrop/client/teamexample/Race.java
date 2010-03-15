package dragndrop.client.teamexample;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.DropHandler;
import dragndrop.client.core.DropEvent;
import dragndrop.client.core.DropZone;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)     
 */
public class Race {
  public interface Display extends DropZone{
    void addNewTeam(String name, String vehicles);
  }

  private Display display;

  public Race(Display display) {
    this.display = display;

    display.addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        Object o = event.getDroppedObject();
        if(o instanceof Team){
          racingTeam((Team)o);
          event.getSourceWidget().removeFromParent();
        }
      }
    });
  }

  public void racingTeam(Team team){
    String vehicles = "";

    for(Car car : team.getCars()){
      vehicles = vehicles + car.getName() + ", ";
    }

    for(Truck truck : team.getTrucks()){
      vehicles = vehicles + truck.getName() + ", ";
    }
    display.addNewTeam(team.getTeamName(), vehicles);
  }

  public void go(HasWidgets widget){
    widget.add((Widget)display);
  }
}