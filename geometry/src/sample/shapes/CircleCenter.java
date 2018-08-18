package sample.shapes;

import ExtendedMath.struct.Vector2d;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

public class CircleCenter extends Circle {

    private static final int CIRCLE_RADIUS = 4;

    @Getter @Setter
    private TriangleCenter type;

    public CircleCenter(double x, double y) {
        super(x, y, CIRCLE_RADIUS);
        setOnMouseEntered(pointOnMouseEntered);
        setOnMouseExited(pointOnMouseExited);
        setOnMouseDragged(pointOnMouseDrag);
    }

    public CircleCenter(Vector2d pos){
        this(pos.x, pos.y);
    }


    private EventHandler<MouseEvent> pointOnMouseEntered = event -> {
        setCursor(Cursor.HAND);
        setRadius(CIRCLE_RADIUS * 2);

    };

    private EventHandler<MouseEvent> pointOnMouseExited = event -> {
        setCursor(Cursor.HAND);
        setRadius(CIRCLE_RADIUS);

    };

    private EventHandler<MouseEvent> pointOnMouseDrag =
            event -> ShapeProvider.getInstance().updateShapesOnCenterClick(event, this);


    public enum TriangleCenter {
        CENTROID,
        CIRCUMCENTER,
        ORTHOCENTER,
        INCENTER

    }




}
