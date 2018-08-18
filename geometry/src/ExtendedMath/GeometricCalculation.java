package ExtendedMath;

import ExtendedMath.struct.LineSegment;
import javafx.scene.shape.Circle;
import ExtendedMath.struct.Vector2d;
import javafx.scene.shape.Line;
import org.jetbrains.annotations.NotNull;
import sample.shapes.TriangleVertex;

import java.awt.geom.Line2D;

public class GeometricCalculation {

    private GeometricCalculation() throws IllegalAccessException {
        throw new IllegalAccessException("Helper class cannot be instanciated");
    }

    public static Vector2d carculateCircleCenter(TriangleVertex p1, TriangleVertex p2, TriangleVertex p3) {

        Circle tmp =  initThreePointCircle(p1, p2, p3);
        return new Vector2d(tmp.getCenterX(), tmp.getCenterY());
    }

    public static double carculateCircleRadius(TriangleVertex p1, TriangleVertex p2, TriangleVertex p3) {

        double distA = distanceFromTwoPoints(p1, p2);
        double distB = distanceFromTwoPoints(p2, p3);
        double distC = distanceFromTwoPoints(p3, p1);

        double cosC =  (Math.pow(distC, 2) - Math.pow(distA, 2) - Math.pow(distB, 2)) / (-2 * distA * distB);
        double angleC = Math.cos(cosC);

        return distC / (2 * Math.sin(angleC));

    }

    public static double distanceFromTwoPoints(Circle p1, Circle p2){

        double dx = Math.pow(p1.getCenterX() - p2.getCenterX(), 2);
        double dy = Math.pow(p1.getCenterY() - p2.getCenterY(), 2);

        return Math.sqrt(dx + dy);

    }

    public static double distanceFromTwoPoints(Vector2d p1, Vector2d p2){

        double dx = Math.pow(p1.x - p2.x, 2);
        double dy = Math.pow(p1.y - p2.y, 2);

        return Math.sqrt(dx + dy);

    }


    // https://github.com/geotools/geotools/blob/master/modules/extension/xsd/xsd-gml3/src/main/java/org/geotools/gml3/Circle.java
    public static Circle initThreePointCircle(Circle _p1, Circle _p2, Circle _p3) {

        Vector2d p1 = new Vector2d(_p1);
        Vector2d p2 = new Vector2d(_p2);
        Vector2d p3 = new Vector2d(_p3);

        double a13, b13, c13;
        double a23, b23, c23;
        double x = 0, y = 0, rad = 0;

        // begin pre-calculations for linear system reduction
        a13 = 2 * (p1.x - p3.x);
        b13 = 2 * (p1.y - p3.y);
        c13 = (p1.y * p1.y - p3.y * p3.y) + (p1.x * p1.x - p3.x * p3.x);
        a23 = 2 * (p2.x - p3.x);
        b23 = 2 * (p2.y - p3.y);
        c23 = (p2.y * p2.y - p3.y * p3.y) + (p2.x * p2.x - p3.x * p3.x);

        // testsuite-suite to be certain we have three distinct points passed
        double smallNumber = 0.01;

        if (!((Math.abs(a13) < smallNumber && Math.abs(b13) < smallNumber)
                || (Math.abs(a13) < smallNumber && Math.abs(b13) < smallNumber))) {

            // everything is acceptable do the y calculation
            y = (a13 * c23 - a23 * c13) / (a13 * b23 - a23 * b13);
            // x calculation
            // choose best formula for calculation
            if (Math.abs(a13) > Math.abs(a23)) {
                x = (c13 - b13 * y) / a13;
            } else {
                x = (c23 - b23 * y) / a23;
            }
            // radius calculation
            rad = Math.sqrt((x - p1.x) * (x - p1.x) + (y - p1.y) * (y - p1.y));
        }

        return new Circle(x, y, rad);

    }

    public static Vector2d findMiddlePoint(Circle _p1, Circle _p2){
        Vector2d p1 = new Vector2d(_p1);
        Vector2d p2 = new Vector2d(_p2);

        double x = (p1.x+ p2.x) / 2;
        double y = (p1.y+ p2.y) / 2;

        return new Vector2d(x, y);

    }

    @NotNull
    public static Vector2d lineSegmentIntersectionPoint(double l1BeginX, double l1BeginY, double l1EndX, double l1EndY,
                                                        double l2BeginX, double l2BeginY, double l2EndX, double l2EndY) {

        double x0, y0;
        // check if vertical
        if (l1BeginX == l1EndX){

            double a2 = (l2EndY - l2BeginY) / (l2EndX - l2BeginX);
            double b2 = l2BeginY - a2 * l2BeginX;

            x0 = l1BeginX;
            y0 = a2 * x0 + b2;


        } else if (l2BeginX == l2EndX){

            double a1 = (l1EndY - l1BeginY) / (l1EndX - l1BeginX);
            double b1 = l1BeginY - a1 * l1BeginX;

            x0 = l2BeginX;
            y0 = a1 * x0 + b1;

        } else {

            //no vertical
            double a1 = (l1EndY - l1BeginY) / (l1EndX - l1BeginX);
            double b1 = l1BeginY - a1 * l1BeginX;
            double a2 = (l2EndY - l2BeginY) / (l2EndX - l2BeginX);
            double b2 = l2BeginY - a2 * l2BeginX;

            x0 = - (b1-b2)/(a1-a2);
            y0 = a1 * x0 + b1;

        }

        return new Vector2d(x0, y0);



    }


    public static Vector2d lineSegmentIntersectionPoint(Circle c1, Circle c2, Circle c3, Circle c4) {

        Vector2d p1 = new Vector2d(c1); Vector2d p2 = new Vector2d(c2); Vector2d p3 = new Vector2d(c4); Vector2d p4 = new Vector2d(c4);
        return lineSegmentIntersectionPoint(p1, p2, p3, p4);
    }

    public static Vector2d lineSegmentIntersectionPoint(Vector2d c1, Vector2d c2, Vector2d c3, Vector2d c4) {

        return lineSegmentIntersectionPoint(c1.x, c1.y, c2.x, c2.y, c3.x, c3.y, c4.x, c4.y);
    }

    public static Vector2d lineSegmentIntersectionPoint(Line l1, Line l2) {

        return lineSegmentIntersectionPoint(l1.getStartX(), l1.getStartY(), l1.getEndX(), l1.getEndY(),
                                     l2.getStartX(), l2.getStartY(), l2.getEndX(), l2.getEndY());

    }

    public static Vector2d lineSegmentIntersectionPoint(LineSegment l1, LineSegment l2) {

        return lineSegmentIntersectionPoint(l1.getV1(), l1.getV2(), l2.getV1(), l2.getV2());

    }

    //double x1, double y1
    //double x2, double y2
    //double x3, double y3
    //double x4, double y4
    public static boolean linesintersect(Line l1, Line l2){

        return Line2D.linesIntersect(l1.getStartX(),   l1.getStartY(),
                              l1.getEndX(),     l1.getEndY(),
                              l2.getStartX(),   l2.getStartY(),
                              l2.getEndX(),     l2.getEndY());
    }



    public static Vector2d perpendicularPointProjectionOnLine(LineSegment lineSegment, Vector2d point){

        double a = lineSegment.slope();
        double b = -1d;
        double c = lineSegment.yIntersect();

        double x0 = point.x; double y0 = point.y;

        double denum = Math.pow(a, 2) + Math.pow(b, 2);

        double x = ((b * (b * x0 - a * y0)) - a * c) / denum;
        double y = ((a * (-b * x0 + a * y0)) - b * c) / denum;

        return new Vector2d(x, y);

    }


    public static Vector2d perpendicularPointProjectionOnLine(Line lineSegmentTmp, Vector2d point){

        LineSegment lineSegment = new LineSegment(
                new Vector2d(lineSegmentTmp.getStartX(), lineSegmentTmp.getStartY()),
                new Vector2d(lineSegmentTmp.getEndX(), lineSegmentTmp.getEndY()));

        return perpendicularPointProjectionOnLine(lineSegment, point);
    }


    /**
     *
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static Vector2d inCenterPoint(Vector2d v1, Vector2d v2, Vector2d v3){

        double xa = v1.x; double ya = v1.y;
        double xb = v2.x; double yb = v2.y;
        double xc = v3.x; double yc = v3.y;

        double a = distanceFromTwoPoints(v2, v3);
        double b = distanceFromTwoPoints(v1, v3);
        double c = distanceFromTwoPoints(v1, v2);

        double denum = a + b + c;

        double x = (a*xa + b*xb + c*xc) / denum;
        double y = (a*ya + b*yb + c*yc) / denum;

        return new Vector2d(x, y);

    }

    /**
     *
     * area = 1/2 * r * perimeter :: r = 2 * area / permieter
     *
     * @param v1 an vertex of the triangle
     * @param v2 an vertex of the triangle
     * @param v3 an vertex of the triangle
     * @return the value of the radius of the inner circle
     */
    public static double inCenterRadius(Vector2d v1, Vector2d v2, Vector2d v3){

        double area = triangleArea(v1, v2, v3);
        double perimeter = trianglePerimeter(v1, v2, v3);

        return 2 * (triangleArea(v1, v2, v3) / trianglePerimeter(v1, v2, v3));

    }

    /**
     *
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static double triangleArea(Vector2d v1, Vector2d v2, Vector2d v3){

        Vector2d oppositePoint = perpendicularPointProjectionOnLine(new LineSegment(v1, v2), v3);
        double height = distanceFromTwoPoints(oppositePoint, v3);
        double base = distanceFromTwoPoints(v1, v2);
        return base * height / 2;

    }

    /**
     *
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static double trianglePerimeter(Vector2d v1, Vector2d v2, Vector2d v3){

        return distanceFromTwoPoints(v1, v2) + distanceFromTwoPoints(v2, v3) + distanceFromTwoPoints(v3, v1);

    }

    public static Circle initInCircle(TriangleVertex v1, TriangleVertex v2, TriangleVertex v3){

        return initInCircle(new Vector2d(v1), new Vector2d(v2), new Vector2d(v3));

    }


    public static Circle initInCircle(Vector2d v1, Vector2d v2, Vector2d v3){

        Vector2d center = inCenterPoint(v1, v2, v3);
        return new Circle(center.x, center.y, inCenterRadius(v1, v2, v3));
    }





}
