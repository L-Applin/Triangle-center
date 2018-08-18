package sample.app;

import javafx.geometry.Pos;
import lombok.Getter;
import lombok.Setter;
import sample.region.DisplayInfo;

public class Controller {

    private static volatile Controller instance;
    private static final Object mutex = new Object();

    private Controller() {}

    public static Controller getInstance() {
        Controller result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Controller();
            }
        }
        return result;
    }

    @Getter @Setter
    private DisplayInfo infos;

    public void moveInfos(double height){

        infos.getAllInfos().setAlignment(Pos.BOTTOM_CENTER);
        infos.getAllInfos().setLayoutX(10);
        infos.getAllInfos().setLayoutY(height - infos.getAllInfos().getHeight() - 30);


    }

    public void updateDisplayInfo(){
        infos.updateInfos();
    }


}
