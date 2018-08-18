package sample.shapes;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

import static sample.shapes.ShapeStyle.OUTER_POINT_COLOR;

/**
 * A trinagle vertex that has some extra informations associated to it.
 */
public class TriangleVertex extends Circle {

    /**
     * The radius of the vertez to be displayed.
     */
    private static final int VERTEX_RADIUS = 4;

    /**
     * The {@link Line} that represent one of the triangle edges that has its begining point anchored to this instance.
     */
    @Getter @Setter
    private Line beginLine;

    /**
     * The {@link Line} that represent one of the triangle edges that has its end point anchored to this instance.
     */
    @Getter @Setter
    private Line endLine;


    /**
     * The event handler associated to a {@link MouseEvent} for the
     * {@link javafx.scene.shape.Shape#setOnMouseDragged(EventHandler)} event. <p></p> When a {@link TriangleVertex} is
     * moved (or dragged) both edges connected to it must have thier end points or begin points updated and evey shape
     * in the {@link InfoTriangle} must be updated.
     */
    protected EventHandler<MouseEvent> pointOnMouseDragged =
            event -> ShapeProvider.getInstance().updateShapes(event, this);


    /**
     * The event handler associated to a {@link MouseEvent} for the
     * {@link javafx.scene.shape.Shape#setOnMouseEntered(EventHandler)} event. <p></p> Upon mouse-over, a simple mouse
     * icon change happens.
     */
    protected EventHandler<MouseEvent> pointOnMouseEntered = event -> {
        setCursor(Cursor.HAND);
        setRadius(VERTEX_RADIUS * 2);

    };

    /**
     * The event handler associated to a {@link MouseEvent} for the
     * {@link javafx.scene.shape.Shape#setOnMouseExited(EventHandler)} event. <p></p> Upon mouse-over exit,
     * a simple mouse icon change happens.
     */
    protected EventHandler<MouseEvent> pointOnMouseExited = event -> {
        setCursor(Cursor.HAND);
        setRadius(VERTEX_RADIUS);

    };


    /**
     * The representation of a
     *
     * @param centerX
     * @param centerY
     */
    public TriangleVertex(double centerX, double centerY){
        super(centerX, centerY, VERTEX_RADIUS);

        setFill(OUTER_POINT_COLOR);

        setCursor(Cursor.MOVE);
        setOnMouseDragged(pointOnMouseDragged);
        setOnMouseEntered(pointOnMouseEntered);
        setOnMouseExited(pointOnMouseExited);


    }


}
