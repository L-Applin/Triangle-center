package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.app.AppConfig;
import sample.app.Controller;
import sample.region.CustomMenu;
import sample.region.DisplayInfo;
import sample.region.TriangleCanvas;
import sample.shapes.InfoTriangle;
import sample.shapes.ShapeProvider;

import static sample.app.AppConfig.HEIGHT;
import static sample.app.AppConfig.WIDTH;
import static sample.app.AppConfig.dark_style;
import static sample.shapes.ShapeStyle.BCK_COLOR_DARK;
import static sample.shapes.ShapeStyle.BCK_COLOR_LIGHT;

/**
 * This serves as the main entry point of the application.
 *
 */
public class Main extends Application {



    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Triangle centers");
        AppConfig.getInstance().setPrimaryStage(primaryStage);


        InfoTriangle infoTri = new InfoTriangle(ShapeProvider.getInstance());
        TriangleCanvas myCanvas = new TriangleCanvas(infoTri);
        DisplayInfo infos = new DisplayInfo(infoTri);

        myCanvas.shapesInitialization(ShapeProvider.getInstance());
        ShapeProvider.getInstance().updateLinesFromPoint();

        CustomMenu menuBar = new CustomMenu(infos);
        menuBar.setCanvasTriangle(myCanvas);
        AppConfig.getInstance().setupWindowProperty(menuBar.getAppMenuBar());

        Group root = new Group();
        root.getChildren().addAll(myCanvas.getAllShapes());
        root.getChildren().add(menuBar.getAppMenuBar());

        root.getChildren().add(infos.getAllInfos());
        infos.initInfos();
        Controller.getInstance().setInfos(infos);
        menuBar.setDisplayInfo(infos);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(dark_style ? BCK_COLOR_DARK : BCK_COLOR_LIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller.getInstance().moveInfos(HEIGHT + 20);

    }


    public static void main(String[] args) {
        launch(args);
    }




}
