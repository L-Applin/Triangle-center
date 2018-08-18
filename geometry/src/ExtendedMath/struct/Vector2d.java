package ExtendedMath.struct;

import javafx.scene.shape.Circle;
import lombok.AllArgsConstructor;

/**
 *
 */
@AllArgsConstructor
public class Vector2d {

    /**
     *
     */
    public final double x, y;

    /**
     *
     * @param p
     */
    public Vector2d(Circle p){
        x = p.getCenterX(); y = p.getCenterY();
    }



}

