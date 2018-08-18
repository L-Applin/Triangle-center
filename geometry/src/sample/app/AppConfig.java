package sample.app;

import ExtendedMath.struct.Vector2d;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppConfig {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;


    private static volatile AppConfig instance;
    private static final Object mutex = new Object();

    private AppConfig() {}

    public static AppConfig getInstance() {
        AppConfig result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new AppConfig();
            }
        }
        return result;
    }

    private Stage primaryStage;

    public static boolean dark_style = false;
    public final Vector2d p1Start = new Vector2d(150,150);
    public final Vector2d p2Start = new Vector2d(400,100);
    public final Vector2d p3Start = new Vector2d(225,375);

    public void setupWindowProperty(MenuBar menuBar){

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            menuBar.setPrefWidth(newVal.doubleValue());
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            Controller.getInstance().moveInfos(newVal.doubleValue());
        });


    }




}
