package sample.shapes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import ExtendedMath.GeometricCalculation;
import ExtendedMath.struct.Vector2d;
import sample.app.AppConfig;
import sample.app.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This singleton Class creates all shapes used and manages all the change of position of those shapes.
 */
public class ShapeProvider {

    /**
     * The single instance of this class. Uses the Singleton design Pattern.
     */
    private static volatile ShapeProvider instance;

    /**
     * This nutek is used to lock access to the instance, making sure the instance is thread safe.
     */
    private static Object mutex = new Object();

    private ShapeProvider() {}

    /**
     * Thread safe singleton getter for the instance of the class.
     * @return the unique instace of this class.
     */
    public static ShapeProvider getInstance() {
        ShapeProvider result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new ShapeProvider();
            }
        }
        return result;
    }

    /**
     * One of the {@link TriangleVertex}.
     */
    public final TriangleVertex p1 =
            new TriangleVertex(AppConfig.getInstance().p1Start.x,AppConfig.getInstance().p1Start.y);

    /**
     * One of the {@link TriangleVertex}.
     */
    public final TriangleVertex p2 =
            new TriangleVertex(AppConfig.getInstance().p2Start.x, AppConfig.getInstance().p2Start.y);

    /**
     * One of the {@link TriangleVertex}.
     */
    public final TriangleVertex p3 =
            new TriangleVertex(AppConfig.getInstance().p3Start.x, AppConfig.getInstance().p3Start.y);

    /**
     * One of the triangle line. Basic JavaFx {@link Line}.
     */
    public final Line l1 = new Line(p1.getCenterX(), p1.getCenterY(), p2.getCenterX(), p2.getCenterY());

    /**
     * One of the triangle line. Basic JavaFx {@link Line}.
     */
    public final Line l2 = new Line(p2.getCenterX(), p2.getCenterY(), p3.getCenterX(), p3.getCenterY());

    /**
     * One of the triangle line. Basic JavaFx {@link Line}.
     */
    public final Line l3 = new Line(p3.getCenterX(), p3.getCenterY(), p1.getCenterX(), p1.getCenterY());

    /**
     * The circumcircle is a triangle's circumscribed circle, i.e., the unique circle that
     * passes through each of the triangle's three vertices.
     */
    public final Circle circumcircle = GeometricCalculation.initThreePointCircle(p1, p2, p3);

    /**
     * The circumcenter is the center of a triangle's circumcircle.
     * It can be found as the intersection of the perpendicular bisectors.
     * <p><a href= https://en.wikipedia.org/wiki/Triangle_center#Circumcenter>details</a></p>
     */
    public final CircleCenter circumcenter =
            new CircleCenter(circumcircle.getCenterX(), circumcircle.getCenterY());

    /**
     * A {@link Line} from the {@link ShapeProvider#circumcenter} to of of the {@link TriangleVertex}
     */
    public final Line centerLine1 =
            new Line(p1.getCenterX(), p1.getCenterY(), circumcenter.getCenterX(), circumcenter.getCenterY());

    /**
     * A {@link Line} from the {@link ShapeProvider#circumcenter} to of of the {@link TriangleVertex}
     */
    public final Line centerLine2 =
            new Line(p2.getCenterX(), p2.getCenterY(), circumcenter.getCenterX(), circumcenter.getCenterY());

    /**
     * A {@link Line} from the {@link ShapeProvider#circumcenter} to of of the {@link TriangleVertex}
     */
    public final Line centerLine3 =
            new Line(p3.getCenterX(), p3.getCenterY(), circumcenter.getCenterX(), circumcenter.getCenterY());

    /**
     * Line bisector from a {@link TriangleVertex} to the opposite edge middle point.
     */
    public final Line bisector1 = new Line(p1.getCenterX(), p1.getCenterY(),
            GeometricCalculation.findMiddlePoint(p3, p2).x, GeometricCalculation.findMiddlePoint(p3, p2).y);

    /**
     * Line bisector from a {@link TriangleVertex} to the opposite edge middle point.
     */
    public final Line bisector2 = new Line(p2.getCenterX(), p2.getCenterY(),
            GeometricCalculation.findMiddlePoint(p1, p3).x, GeometricCalculation.findMiddlePoint(p1, p3).y);

    /**
     * Line bisector from a {@link TriangleVertex} to the opposite edge middle point.
     */
    public final Line bisector3 = new Line(p3.getCenterX(), p3.getCenterY(),
            GeometricCalculation.findMiddlePoint(p2, p1).x, GeometricCalculation.findMiddlePoint(p2, p1).y);

    /**
     * The geometric centroid (center of mass) of the polygon vertices of a triangle is the point G
     * (sometimes also denoted M) which is also the intersection of the triangle's three triangle medians.
     * The point is therefore sometimes called the median point.
     * The centroid is always in the interior of the triangle.
     * <p><a href= https://en.wikipedia.org/wiki/Centroid>details</a></p>
     */
    public final CircleCenter centroid =
            new CircleCenter(GeometricCalculation.lineSegmentIntersectionPoint(bisector1, bisector2));

    /**
     * One of the triangle altitude. Connect perpendicularly one of the {@link TriangleVertex}
     * to of it's opposing edge.
     */
    public final Line orth1 = new Line(p1.getCenterX(), p1.getCenterY(),
            GeometricCalculation.perpendicularPointProjectionOnLine(l2, new Vector2d(p1)).x,
            GeometricCalculation.perpendicularPointProjectionOnLine(l2, new Vector2d(p1)).y);

    /**
     * One of the triangle altitude. Connect perpendicularly one of the {@link TriangleVertex}
     * to of it's opposing edge.
     */
    public final Line orth2 = new Line(p2.getCenterX(), p2.getCenterY(),
            GeometricCalculation.perpendicularPointProjectionOnLine(l3, new Vector2d(p2)).x,
            GeometricCalculation.perpendicularPointProjectionOnLine(l3, new Vector2d(p2)).y);

    /**
     * One of the triangle altitude. Connect perpendicularly one of the {@link TriangleVertex}
     * to of it's opposing edge.
     */
    public final Line orth3 = new Line(p3.getCenterX(), p3.getCenterY(),
            GeometricCalculation.perpendicularPointProjectionOnLine(l1, new Vector2d(p3)).x,
            GeometricCalculation.perpendicularPointProjectionOnLine(l1, new Vector2d(p3)).y);

    /**
     * The three (possibly extended) altitudes intersect in a single point, called the orthocenter of the triangle,
     * usually denoted by H.[1][2] The orthocenter lies inside the triangle if and only if the triangle is acute
     * (i.e. does not have an angle greater than or equal to a right angle).
     * If one angle is a right angle, the orthocenter coincides with the vertex at the right angle.
     * <p><a href = https://en.wikipedia.org/wiki/Altitude_(triangle)#Orthocenter>details</a></p>
     */
    public final CircleCenter orthocenter =
            new CircleCenter(GeometricCalculation.lineSegmentIntersectionPoint(orth1, orth2));

    /**
     * is a line determined from any triangle that is not equilateral.
     * It is a central line of the triangle, and it passes through several important points
     * determined from the triangle, including the orthocenter, the circumcenter and the centroid of the triangle.
     * <p><a href=https://en.wikipedia.org/wiki/Euler_line></a></p>
     */
    public final Line eulerLine =
            new Line(centroid.getCenterX(), centroid.getCenterY(), orthocenter.getCenterX(), orthocenter.getCenterY());


    /**
     * A circle for which each of the triangle edges are tangeant to.
     */
    public final Circle inCircle = GeometricCalculation.initInCircle(p1, p2, p3);

    /**
     * The incenter may be equivalently defined as the point where the internal angle bisectors of the triangle cross,
     * as the point equidistant from the triangle's sides, as the junction point of the medial axis and
     * innermost point of the grassfire transform of the triangle, and as the center point
     * of the inscribed circle of the triangle.
     * <p><a href = https://en.wikipedia.org/wiki/Incenter>details</a></p>
     */
    public final CircleCenter incenter = new CircleCenter(inCircle.getCenterX(), inCircle.getCenterY());


    /**
     * Updates every {@link Shape} part of the {@link ShapeProvider} in response to a
     * {@link javafx.scene.input.MouseEvent} moving one of the {@link TriangleVertex}. Every position of every shape is
     * recalculated using the new position of the currently dragged {@link TriangleVertex}.
     *
     * @param event the {@link javafx.scene.input.MouseEvent} that is created as part of a {@link javafx.event.EventHandler}.
     *              Called during the {@link Shape#setOnMouseDragged(EventHandler)} of the {@link TriangleVertex}.
     * @param point The {@link TriangleVertex} the is currently beign dragged.
     */
    public void updateShapes(MouseEvent event, TriangleVertex point){

        Circle current = (Circle) (event.getSource());

        current.setCenterX(event.getSceneX());
        current.setCenterY(event.getSceneY());

        point.getBeginLine().setStartX(event.getSceneX());
        point.getBeginLine().setStartY(event.getSceneY());

        point.getEndLine().setEndX(event.getSceneX());
        point.getEndLine().setEndY(event.getSceneY());

        Circle _tmpCircumircle = GeometricCalculation.initThreePointCircle(p1, p2, p3);
        circumcenter.setCenterX(_tmpCircumircle.getCenterX()); circumcenter.setCenterY(_tmpCircumircle.getCenterY());
        circumcircle.setCenterX(_tmpCircumircle.getCenterX()); circumcircle.setCenterY(_tmpCircumircle.getCenterY());
        circumcircle.setRadius(_tmpCircumircle.getRadius());

        Circle _tempInCircle = GeometricCalculation.initInCircle(p1, p2, p3);
        inCircle.setCenterX(_tempInCircle.getCenterX()); inCircle.setCenterY(_tempInCircle.getCenterY());
        inCircle.setRadius(_tempInCircle.getRadius());
        incenter.setCenterX(_tempInCircle.getCenterX()); incenter.setCenterY(_tempInCircle.getCenterY());


        Vector2d centroidPos = GeometricCalculation.lineSegmentIntersectionPoint(bisector1, bisector2);
        centroid.setCenterX(centroidPos.x); centroid.setCenterY(centroidPos.y);

        updateLinesFromPoint(p1, p2, p3, circumcenter);

        Vector2d orthocenterPoint = GeometricCalculation.lineSegmentIntersectionPoint(orth1, orth3);
        orthocenter.setCenterX(orthocenterPoint.x); orthocenter.setCenterY(orthocenterPoint.y);

        orth1.setEndX(orthocenter.getCenterX()); orth1.setEndY(orthocenter.getCenterY());
        orth2.setEndX(orthocenter.getCenterX()); orth2.setEndY(orthocenter.getCenterY());
        orth3.setEndX(orthocenter.getCenterX()); orth3.setEndY(orthocenter.getCenterY());

        Controller.getInstance().updateDisplayInfo();

    }


    /**
     * Updates every {@link Shape} part of the {@link ShapeProvider} in response to a
     * {@link javafx.scene.input.MouseEvent} moving one of the {@link CircleCenter}. Every position of every shape is
     * recalculated using the new position of the currently dragged {@link CircleCenter}.
     *
     * @param event the {@link javafx.scene.input.MouseEvent} that is created as part of a {@link javafx.event.EventHandler}.
     *              Called during the {@link Shape#setOnMouseDragged(EventHandler)} of the {@link CircleCenter}.
     * @param centerOnFocus The {@link CircleCenter} the is currently beign dragged.
     */
    public void updateShapesOnCenterClick(MouseEvent event, CircleCenter centerOnFocus){

        double dxp1 = p1.getCenterX() - centerOnFocus.getCenterX();
        double dyp1 = p1.getCenterY() - centerOnFocus.getCenterY();
        double dxp2 = p2.getCenterX() - centerOnFocus.getCenterX();
        double dyp2 = p2.getCenterY() - centerOnFocus.getCenterY();
        double dxp3 = p3.getCenterX() - centerOnFocus.getCenterX();
        double dyp3 = p3.getCenterY() - centerOnFocus.getCenterY();

        double dxCircum =      circumcenter.getCenterX() - centerOnFocus.getCenterX();
        double dyCircum =      circumcenter.getCenterY() - centerOnFocus.getCenterY();
        double dxOrtho =       orthocenter.getCenterX() -  centerOnFocus.getCenterX();
        double dyOrtho =       orthocenter.getCenterY() -  centerOnFocus.getCenterY();
        double dxIncenter =    incenter.getCenterX() -     centerOnFocus.getCenterX();
        double dyIncenter =    incenter.getCenterY() -     centerOnFocus.getCenterY();
        double dxCentroid =    centroid.getCenterX() -     centerOnFocus.getCenterX();
        double dyCentroid =    centroid.getCenterY() -     centerOnFocus.getCenterY();

        double eventX = event.getSceneX(); double eventY = event.getSceneY();

        switch (centerOnFocus.getType()){

            case CENTROID:

                centroid.setCenterX(eventX);
                centroid.setCenterY(eventY);

                circumcenter.setCenterX(eventX + dxCircum);
                circumcenter.setCenterY(eventY + dyCircum);

                orthocenter.setCenterX(eventX + dxOrtho);
                orthocenter.setCenterY(eventY + dyOrtho);

                incenter.setCenterX(eventX + dxIncenter);
                incenter.setCenterY(eventY + dyIncenter);
                break;

            case CIRCUMCENTER:

                circumcenter.setCenterX(eventX);
                circumcenter.setCenterY(eventY);

                centroid.setCenterX(eventX + dxCentroid);
                centroid.setCenterY(eventY + dyCentroid);

                orthocenter.setCenterX(eventX + dxOrtho);
                orthocenter.setCenterY(eventY + dyOrtho);

                incenter.setCenterX(eventX + dxIncenter);
                incenter.setCenterY(eventY + dyIncenter);

                break;

            case ORTHOCENTER:

                orthocenter.setCenterX(eventX);
                orthocenter.setCenterY(eventY);

                centroid.setCenterX(eventX + dxCentroid);
                centroid.setCenterY(eventY + dyCentroid);

                circumcenter.setCenterX(eventX + dxCircum);
                circumcenter.setCenterY(eventY + dyCircum);

                incenter.setCenterX(eventX + dxIncenter);
                incenter.setCenterY(eventY + dyIncenter);

                break;

            case INCENTER:

                incenter.setCenterX(eventX);
                incenter.setCenterY(eventY);

                centroid.setCenterX(eventX + dxCentroid);
                centroid.setCenterY(eventY + dyCentroid);

                circumcenter.setCenterX(eventX + dxCircum);
                circumcenter.setCenterY(eventY + dyCircum);

                orthocenter.setCenterX(eventX + dxOrtho);
                orthocenter.setCenterY(eventY + dyOrtho);
                break;

        }


        p1.setCenterX(event.getSceneX() + dxp1);
        p1.setCenterY(event.getSceneY() + dyp1);
        p2.setCenterX(event.getSceneX() + dxp2);
        p2.setCenterY(event.getSceneY() + dyp2);
        p3.setCenterX(event.getSceneX() + dxp3);
        p3.setCenterY(event.getSceneY() + dyp3);

        p1.getBeginLine().setStartX(p1.getCenterX());
        p1.getBeginLine().setStartY(p1.getCenterY());
        p1.getBeginLine().setEndX(p2.getCenterX());
        p1.getBeginLine().setEndY(p2.getCenterY());

        p2.getBeginLine().setStartX(p2.getCenterX());
        p2.getBeginLine().setStartY(p2.getCenterY());
        p2.getBeginLine().setEndX(p3.getCenterX());
        p2.getBeginLine().setEndY(p3.getCenterY());

        p3.getBeginLine().setStartX(p3.getCenterX());
        p3.getBeginLine().setStartY(p3.getCenterY());
        p3.getBeginLine().setEndX(p1.getCenterX());
        p3.getBeginLine().setEndY(p1.getCenterY());

        Circle _tmpCircumcircle = GeometricCalculation.initThreePointCircle(p1, p2, p3);
        circumcircle.setCenterX(_tmpCircumcircle.getCenterX());
        circumcircle.setCenterY(_tmpCircumcircle.getCenterY());

        Circle _tempincircle = GeometricCalculation.initInCircle(p1, p2, p3);
        inCircle.setCenterX(_tempincircle.getCenterX());
        inCircle.setCenterY(_tempincircle.getCenterY());

        updateLinesFromPoint(p1, p2, p3, circumcenter);

        Controller.getInstance().updateDisplayInfo();

    }

    /**
     * Updates all the lines
     * ({@link ShapeProvider#centerLine1}, {@link ShapeProvider#centerLine2}, {@link ShapeProvider#centerLine3},
     * {@link ShapeProvider#bisector1}, {@link ShapeProvider#bisector2}, {@link ShapeProvider#bisector3},
     * {@link ShapeProvider#orth1}, {@link ShapeProvider#orth2}, {@link ShapeProvider#orth3} and
     * {@link ShapeProvider#eulerLine}. Must also recalculated and update {@link ShapeProvider#orth1}.
     */
    public void updateLinesFromPoint(){
        updateLinesFromPoint(p1, p2, p3, circumcenter);
    }

    /**
     * Updates all the lines
     * ({@link ShapeProvider#centerLine1}, {@link ShapeProvider#centerLine2}, {@link ShapeProvider#centerLine3},
     * {@link ShapeProvider#bisector1}, {@link ShapeProvider#bisector2}, {@link ShapeProvider#bisector3},
     * {@link ShapeProvider#orth1}, {@link ShapeProvider#orth2}, {@link ShapeProvider#orth3} and
     * {@link ShapeProvider#eulerLine}. Must also recalculated and update {@link ShapeProvider#orth1}.
     * This update uses the {@link Circle} passed in parameter to do so and does not updates the
     * actual {@link TriangleVertex}. Use with caution.
     */
    private void updateLinesFromPoint(Circle p1, Circle p2, Circle p3, Circle center){

        centerLine1.setStartX(p1.getCenterX());
        centerLine2.setStartX(p2.getCenterX());
        centerLine3.setStartX(p3.getCenterX());
        centerLine1.setStartY(p1.getCenterY());
        centerLine2.setStartY(p2.getCenterY());
        centerLine3.setStartY(p3.getCenterY());

        centerLine1.setEndX(center.getCenterX());
        centerLine2.setEndX(center.getCenterX());
        centerLine3.setEndX(center.getCenterX());
        centerLine1.setEndY(center.getCenterY());
        centerLine2.setEndY(center.getCenterY());
        centerLine3.setEndY(center.getCenterY());

        Vector2d l1Middle = GeometricCalculation.findMiddlePoint(p2, p3);
        Vector2d l2Middle = GeometricCalculation.findMiddlePoint(p1, p3);
        Vector2d l3Middle = GeometricCalculation.findMiddlePoint(p2, p1);

        bisector1.setStartX(p1.getCenterX()); bisector1.setStartY(p1.getCenterY());
        bisector2.setStartX(p2.getCenterX()); bisector2.setStartY(p2.getCenterY());
        bisector3.setStartX(p3.getCenterX()); bisector3.setStartY(p3.getCenterY());
        bisector1.setEndX(l1Middle.x); bisector1.setEndY(l1Middle.y);
        bisector2.setEndX(l2Middle.x); bisector2.setEndY(l2Middle.y);
        bisector3.setEndX(l3Middle.x); bisector3.setEndY(l3Middle.y);


        Vector2d orth1point = GeometricCalculation.perpendicularPointProjectionOnLine(l2, new Vector2d(p1));
        Vector2d orth2point = GeometricCalculation.perpendicularPointProjectionOnLine(l3, new Vector2d(p2));
        Vector2d orth3point = GeometricCalculation.perpendicularPointProjectionOnLine(l1, new Vector2d(p3));

        orth1.setStartX(p1.getCenterX()); orth1.setStartY(p1.getCenterY());
        orth2.setStartX(p2.getCenterX()); orth2.setStartY(p2.getCenterY());
        orth3.setStartX(p3.getCenterX()); orth3.setStartY(p3.getCenterY());
        orth1.setEndX(orth1point.x); orth1.setEndY(orth1point.y);
        orth2.setEndX(orth2point.x); orth2.setEndY(orth2point.y);
        orth3.setEndX(orth3point.x); orth3.setEndY(orth3point.y);

        Vector2d orthocenterPoint = GeometricCalculation.lineSegmentIntersectionPoint(orth1, orth3);
        orthocenter.setCenterX(orthocenterPoint.x); orthocenter.setCenterY(orthocenterPoint.y);

        orth1.setEndX(orthocenter.getCenterX()); orth1.setEndY(orthocenter.getCenterY());
        orth2.setEndX(orthocenter.getCenterX()); orth2.setEndY(orthocenter.getCenterY());
        orth3.setEndX(orthocenter.getCenterX()); orth3.setEndY(orthocenter.getCenterY());

        eulerLine.setStartX(orthocenter.getCenterX()); eulerLine.setStartY(orthocenter.getCenterY());
        eulerLine.setEndX(circumcenter.getCenterX()); eulerLine.setEndY(circumcenter.getCenterY());

    }

    /**
     * Resets and recalculates the position of every shape of the triangle
     * base one the position of the three {@link TriangleVertex}
     * {@link ShapeProvider#p1}m {@link ShapeProvider#p2} and {@link ShapeProvider#p3}.
     */
    public void updateEverythingFromPoints(){

        p1.getBeginLine().setStartX(p1.getCenterX());
        p1.getBeginLine().setStartY(p1.getCenterY());
        p1.getBeginLine().setEndX(p2.getCenterX());
        p1.getBeginLine().setEndY(p2.getCenterY());

        p2.getBeginLine().setStartX(p2.getCenterX());
        p2.getBeginLine().setStartY(p2.getCenterY());
        p2.getBeginLine().setEndX(p3.getCenterX());
        p2.getBeginLine().setEndY(p3.getCenterY());

        p3.getBeginLine().setStartX(p3.getCenterX());
        p3.getBeginLine().setStartY(p3.getCenterY());
        p3.getBeginLine().setEndX(p1.getCenterX());
        p3.getBeginLine().setEndY(p1.getCenterY());

        Circle _tempCircumcircle = GeometricCalculation.initThreePointCircle(p1, p2, p3);
        circumcenter.setCenterX(_tempCircumcircle.getCenterX()); circumcenter.setCenterY(_tempCircumcircle.getCenterY());
        circumcircle.setCenterX(_tempCircumcircle.getCenterX()); circumcircle.setCenterY(_tempCircumcircle.getCenterY());
        circumcircle.setRadius(_tempCircumcircle.getRadius());

        Circle _tempIncircle = GeometricCalculation.initInCircle(p1, p2, p3);
        incenter.setCenterX(_tempIncircle.getCenterX()); incenter.setCenterY(_tempIncircle.getCenterY());
        inCircle.setCenterX(_tempIncircle.getCenterX()); inCircle.setCenterY(_tempIncircle.getCenterY());
        inCircle.setRadius(_tempIncircle.getRadius());


        updateLinesFromPoint(p1, p2, p3, circumcenter);

        Vector2d centroidPos = GeometricCalculation.lineSegmentIntersectionPoint(bisector1, bisector2);
        centroid.setCenterX(centroidPos.x); centroid.setCenterY(centroidPos.y);

        Vector2d orthocenterPoint = GeometricCalculation.lineSegmentIntersectionPoint(orth1, orth3);
        orthocenter.setCenterX(orthocenterPoint.x); orthocenter.setCenterY(orthocenterPoint.y);

        orth1.setEndX(orthocenter.getCenterX()); orth1.setEndY(orthocenter.getCenterY());
        orth2.setEndX(orthocenter.getCenterX()); orth2.setEndY(orthocenter.getCenterY());
        orth3.setEndX(orthocenter.getCenterX()); orth3.setEndY(orthocenter.getCenterY());


        Controller.getInstance().updateDisplayInfo();

    }


}
