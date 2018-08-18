package sample.region;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import lombok.Getter;
import sample.shapes.*;

import static sample.app.AppConfig.dark_style;
import static sample.shapes.ShapeStyle.*;
import static sample.shapes.ShapeStyle.BISECTOR_COLOR;
import static sample.shapes.ShapeStyle.CICUMCENTER_COLOR;

/**
 *
 */
public class TriangleCanvas {

    /**
     *
     */
    @Getter
    private Group allShapes;

    /**
     *
     */
    @Getter
    private InfoTriangle triangle;

    /**
     *
     * @param triangle
     */
    public TriangleCanvas (InfoTriangle triangle){
        this.triangle = triangle;
    }

    /**
     *
     * @param shapes
     */
    public void shapesInitialization(ShapeProvider shapes){


        Line l1 = shapes.l1; Line l2 = shapes.l2; Line l3 = shapes.l3;
        l1.setStroke(dark_style ? LINE_COLOR_DARK : LINE_COLOR_LIGHT);
        l2.setStroke(dark_style ? LINE_COLOR_DARK : LINE_COLOR_LIGHT);
        l3.setStroke(dark_style ? LINE_COLOR_DARK : LINE_COLOR_LIGHT);

        l1.setStrokeWidth(THICK_LINES_WIDTH);
        l2.setStrokeWidth(THICK_LINES_WIDTH);
        l3.setStrokeWidth(THICK_LINES_WIDTH);

        l1.setStrokeType(StrokeType.OUTSIDE);
        l2.setStrokeType(StrokeType.OUTSIDE);
        l3.setStrokeType(StrokeType.OUTSIDE);

        TriangleVertex p1 = shapes.p1; TriangleVertex p2 = shapes.p2; TriangleVertex p3 = shapes.p3;
        p1.setBeginLine(l1);
        p1.setEndLine(l3);
        p2.setBeginLine(l2);
        p2.setEndLine(l1);
        p3.setBeginLine(l3);
        p3.setEndLine(l2);

        Circle outCircle = shapes.circumcircle;
        outCircle.setFill(Color.TRANSPARENT);
        outCircle.setStroke(CIRCUMCIRCLE_COLOR);
        outCircle.setStrokeWidth(SMALL_LINES_WIDTH);

        shapes.circumcenter.setFill(ShapeStyle.CIRCUMCIRCLE_COLOR);
        shapes.circumcenter.setType(CircleCenter.TriangleCenter.CIRCUMCENTER);

        shapes.centerLine1.setStrokeType(StrokeType.OUTSIDE);
        shapes.centerLine2.setStrokeType(StrokeType.OUTSIDE);
        shapes.centerLine3.setStrokeType(StrokeType.OUTSIDE);

        shapes.centerLine1.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.centerLine2.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.centerLine3.setStrokeWidth(SMALL_LINES_WIDTH);

        shapes.centerLine1.setStroke(CICUMCENTER_COLOR);
        shapes.centerLine2.setStroke(CICUMCENTER_COLOR);
        shapes.centerLine3.setStroke(CICUMCENTER_COLOR);

        shapes.bisector1.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.bisector2.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.bisector3.setStrokeWidth(SMALL_LINES_WIDTH);

        shapes.bisector1.setStroke(BISECTOR_COLOR);
        shapes.bisector2.setStroke(BISECTOR_COLOR);
        shapes.bisector3.setStroke(BISECTOR_COLOR);

        shapes.centroid.setFill(BISECTOR_COLOR);
        shapes.centroid.setType(CircleCenter.TriangleCenter.CENTROID);

        shapes.orth1.setStroke(ORTHO_COLOR);
        shapes.orth2.setStroke(ORTHO_COLOR);
        shapes.orth3.setStroke(ORTHO_COLOR);

        shapes.orth1.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.orth2.setStrokeWidth(SMALL_LINES_WIDTH);
        shapes.orth3.setStrokeWidth(SMALL_LINES_WIDTH);

        shapes.orthocenter.setFill(ORTHO_COLOR);
        shapes.orthocenter.setType(CircleCenter.TriangleCenter.ORTHOCENTER);

        shapes.eulerLine.setStroke(EULER_LINE);
        shapes.eulerLine.setStrokeWidth(THICK_LINES_WIDTH);

        shapes.incenter.setFill(INCENTER_COLOR);
        shapes.incenter.setType(CircleCenter.TriangleCenter.INCENTER);

        Circle inCircle = shapes.inCircle;
        inCircle.setFill(Color.TRANSPARENT);
        inCircle.setStroke(INCENTER_COLOR);
        inCircle.setStrokeWidth(SMALL_LINES_WIDTH);

        allShapes = new Group();
        allShapes.getChildren().addAll(
                triangle.getEulerGroup(),
                triangle.getCircumGroup(),
                triangle.getCentroGgroup(),
                triangle.getOrthoGroup(),
                triangle.getIncenterGroup(),
                triangle.getTriangle(),
                triangle.getIncenter(),
                triangle.getCircumcenter(),
                triangle.getCentroid(),
                triangle.getOrthocenter());


    }

}
