package sample.shapes;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter @Setter
public class InfoTriangle extends Shape {

    /**
     *
     */
    private TriangleVertex v1, v2, v3;

    /**
     *
     */
    private Line edge1, edge2, edge3;

    /**
     *
     */
    private Line circumLine1, circumLine2, circumLine3;

    /**
     *
     */
    private Line centroLine1, centroLine2, centroLine3;

    /**
     *
     */
    private Line orthoLine1, orthoLine2, orthoLine3;

    /**
     *
     */
    private Circle outerCircle, incircle;

    /**
     *
     */
    private CircleCenter centroid, circumcenter, orthocenter, incenter;

    /**
     *
     */
    private Group triangle, circumGroup, centroGgroup, orthoGroup, eulerGroup, incenterGroup;


    /**
     *
     * @param shapes
     */
    public InfoTriangle(ShapeProvider shapes){

        // set shapes from ShapeProvider (dependencyInjection)

        // triangle itself
        v1 = shapes.p1; v2 = shapes.p2; v3 = shapes.p3;
        edge1 = shapes.l1; edge2 = shapes.l2; edge3 = shapes.l3;
        triangle = new Group(edge1, edge2, edge3, v1, v2, v3);

        // circumcenter
        outerCircle = shapes.circumcircle;
        circumLine1 = shapes.centerLine1; circumLine2 = shapes.centerLine2; circumLine3 = shapes.centerLine3;
        circumcenter = shapes.circumcenter;
        circumGroup = new Group(outerCircle, circumLine1, circumLine2, circumLine3);

        // centroid
        centroLine1 = shapes.bisector1; centroLine2 = shapes.bisector2; centroLine3 =shapes.bisector3;
        centroid = shapes.centroid;
        centroGgroup = new Group(centroLine1, centroLine2, centroLine3);

        // orhtocenter
        orthocenter = shapes.orthocenter;
        orthoLine1  = shapes.orth1; orthoLine2  = shapes.orth2; orthoLine3  = shapes.orth3;
        orthoGroup = new Group(orthoLine1, orthoLine2, orthoLine3);

        // euler line
        eulerGroup = new Group(shapes.eulerLine);

        // incenter
        incircle = shapes.inCircle;
        incenter = shapes.incenter;
        incenterGroup = new Group(incircle);


    }

}
