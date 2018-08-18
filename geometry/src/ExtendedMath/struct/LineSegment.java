package ExtendedMath.struct;

import lombok.Getter;
import lombok.Setter;
import ExtendedMath.GeometricCalculation;

import java.awt.geom.Line2D;

/**
 *
 */
@Getter @Setter
public class LineSegment {

    /**
     *
     */
    private Vector2d v1, v2;

    /**
     *
     * @param v1
     * @param v2
     */
    public LineSegment(Vector2d v1, Vector2d v2){

        this.v1 = v1; this.v2 = v2;

    }

    /**
     * Computes the length of the line segment, the distance between the two {@link Vector2d}
     *
     * @return the value of the length
     */
    public double length(){

        return GeometricCalculation.distanceFromTwoPoints(v1, v2);

    }


    /**
     *
     * @return
     */
    public double[] getEquation(){
        return new double[]{slope(), yIntersect()};
    }

    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public boolean intersect(LineSegment l1, LineSegment l2){

        return Line2D.linesIntersect(l1.v1.x, l1.v1.y, l1.v2.x, l1.v2.y,
                                     l2.v1.x, l2.v1.y, l2.v2.x, l2.v2.y);

    }

    /**
     *
     * @return
     */
    public double slope(){
        return (v2.y - v1.y) / (v2.x - v1.x);
    }

    /**
     *
     * @return
     */
    public double yIntersect(){
        return v1.y - slope() * v1.x;
    }

    /**
     *
     * @return
     */
    public double xIntersect(){
        //todo
        return 0d;
    }



}
