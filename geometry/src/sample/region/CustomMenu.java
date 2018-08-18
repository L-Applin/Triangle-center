package sample.region;

import ExtendedMath.struct.Vector2d;
import javafx.scene.Group;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;
import sample.app.AppConfig;
import sample.shapes.CircleCenter;
import sample.shapes.InfoTriangle;
import sample.shapes.ShapeProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomMenu {

    @Getter
    private MenuBar appMenuBar = new MenuBar();
    @Setter
    private TriangleCanvas canvasTriangle;
    @Setter
    private DisplayInfo displayInfo;


    private Menu display = new Menu("Display");
    private CheckMenuItem centroid = new CheckMenuItem("centroid");
    private CheckMenuItem orthocenter = new CheckMenuItem("orthocenter");
    private CheckMenuItem circumcenter = new CheckMenuItem("circumcenter");
    private CheckMenuItem incenter = new CheckMenuItem("incenter");
    private CheckMenuItem eulerLine = new CheckMenuItem("Euler line");
    private MenuItem reset = new MenuItem("reset position");
    private CheckMenuItem displayTextInfo = new CheckMenuItem("Text dexcription");

    public CustomMenu( DisplayInfo infos) {
        this.displayInfo = infos;

        appMenuBar.setPrefWidth(AppConfig.getInstance().getPrimaryStage().getMaxWidth());

        // creating themenu : order is important and will reflect order in the menu itself
        display.getItems().addAll(centroid, orthocenter, circumcenter, incenter,
                new SeparatorMenuItem(),
                eulerLine,
                new SeparatorMenuItem(),
                displayTextInfo,
                new SeparatorMenuItem(),
                reset);

        appMenuBar.getMenus().addAll(display);

        centroid.setSelected(true);
        orthocenter.setSelected(true);
        circumcenter.setSelected(true);
        incenter.setSelected(true);
        eulerLine.setSelected(true);
        displayTextInfo.setSelected(true);


        centroid.setOnAction(event -> {

            if (!centroid.isSelected()){
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getCentroGgroup());
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getCentroid());


            } else {
                canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getCentroGgroup());
                makeCenterClickable();

            }

        });

        orthocenter.setOnAction(event -> {

            if (!orthocenter.isSelected()){
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getOrthoGroup());
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getOrthocenter());



            } else {
                canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getOrthoGroup());
                makeCenterClickable();

            }

        });

        circumcenter.setOnAction(event -> {

            if (!circumcenter.isSelected()){
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getCircumGroup());
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getCircumcenter());



            } else {
                canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getCircumGroup());
                makeCenterClickable();

            }

        });

        incenter.setOnAction(event ->{

            if (!incenter.isSelected()){
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getIncenterGroup());
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getIncenter());

            }else {
                canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getIncenterGroup());
                makeCenterClickable();
            }
        });

        eulerLine.setOnAction(event -> {

            if (!eulerLine.isSelected()){
                canvasTriangle.getAllShapes().getChildren().remove(canvasTriangle.getTriangle().getEulerGroup());

            } else {
                canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getEulerGroup());
                makeCenterClickable();

            }

        });

        reset.setOnAction( event -> {

            Vector2d p1 = AppConfig.getInstance().p1Start;
            canvasTriangle.getTriangle().getV1().setCenterX(p1.x);
            canvasTriangle.getTriangle().getV1().setCenterY(p1.y);

            Vector2d p2 = AppConfig.getInstance().p2Start;
            canvasTriangle.getTriangle().getV2().setCenterX(p2.x);
            canvasTriangle.getTriangle().getV2().setCenterY(p2.y);

            Vector2d p3 = AppConfig.getInstance().p3Start;
            canvasTriangle.getTriangle().getV3().setCenterX(p3.x);
            canvasTriangle.getTriangle().getV3().setCenterY(p3.y);

            ShapeProvider.getInstance().updateEverythingFromPoints();

        });

        displayTextInfo.setOnAction( event -> {

            if (!displayTextInfo.isSelected()){
                displayInfo.removeText();
            } else {
                displayInfo.addAllInfo();
            }

        });




    }


    private void makeCenterClickable(){

        InfoTriangle infoTriangle = canvasTriangle.getTriangle();
        Group shapes = canvasTriangle.getAllShapes();
        List<CircleCenter> centers = new ArrayList<>();
        centers.add(infoTriangle.getIncenter());
        centers.add(infoTriangle.getCircumcenter());
        centers.add(infoTriangle.getOrthocenter());
        centers.add(infoTriangle.getCentroid());

        shapes.getChildren().removeAll(centers);


        if(orthocenter.isSelected()){
            canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getOrthocenter());
        }

        if(circumcenter.isSelected()){
            canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getCircumcenter());
        }

        if(centroid.isSelected()){
            canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getCentroid());
        }

        if(incenter.isSelected()){
            canvasTriangle.getAllShapes().getChildren().add(canvasTriangle.getTriangle().getIncenter());
        }


    }


}
