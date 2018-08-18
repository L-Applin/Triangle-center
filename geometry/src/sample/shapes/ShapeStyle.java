package sample.shapes;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Helper class containing all Style information for the different
 * {@link javafx.scene.shape.Shape} used in the application. Provides a set of colors and sizes.
 */
public class ShapeStyle {

    /**
     * A private constructor so that it can never be called outside this class.
     * @throws IllegalAccessException to further prevent instanciating the class.
     */
    private ShapeStyle() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot instanciate Helper class");
    }


    /* * * * * * * * *
     * custom colors *
     * * * * * * * * */

    /**
     * RGB value for the custom {@link ShapeStyle#dark_grey} {@link Color}.
     */
    private static final double dark = 0.15;

    /**
     * RGB value for the custom {@link ShapeStyle#light_grey} {@link Color}
     */
    private static final double light = 0.70;

    /**
     * Custom dark gray {@link Color}.
     */
    private static final Paint dark_grey = new Color(dark, dark, dark,1);

    /**
     * Custom light gray {@link Color}.
     */
    private static final Paint light_grey = new Color(light, light, light,1);





    /* * * * * * * * * * * * * * * * * * * *
     * Same value for light and dark style *
     * * * * * * * * * * * * * * * * * * * */
    /**
     * Color of the {@link TriangleVertex}.
     */
    public static final Paint OUTER_POINT_COLOR = Color.BLUE;

    /**
     * Color of the {@link ShapeProvider#circumcenter}
     */
    public static final Paint CICUMCENTER_COLOR = Color.BLUE;

    /**
     * Color of the {@link ShapeProvider#circumcenter}
     */
    public static final Paint CIRCUMCIRCLE_COLOR = Color.BLUE;

    /**
     * Color of everything related to {@link ShapeProvider#centroid}
     */
    public static final Paint BISECTOR_COLOR = Color.DARKRED;

    /**
     * Color of everything related to {@link ShapeProvider#orthocenter}
     */
    public static final Paint ORTHO_COLOR = Color.GREEN;

    /**
     * Color of everything related to {@link ShapeProvider#incenter}
     */
    public static final Paint INCENTER_COLOR = Color.BLUEVIOLET;

    /**
     * Color of the {@link ShapeProvider#eulerLine}
     */
    public static final Paint EULER_LINE = Color.DARKGOLDENROD;





    /* * * * * * * * * * * * * * * * *
     * Custom values for dark style  *
     * * * * * * * * * * * * * * * * */

    /**
     * Background color, dark style.
     */
    public static final Paint BCK_COLOR_DARK = dark_grey;

    /**
     * Triangle edges color, dark style.
     */
    public static final Paint LINE_COLOR_DARK = light_grey;


    /* * * * * * * * * * * * * * * * *
     * Custom values for light style *
     * * * * * * * * * * * * * * * * */

    /**
     * Background color, light style.
     */
    public static final Paint BCK_COLOR_LIGHT = light_grey;

    /**
     * Triangle edges color, light style.
     */
    public static final Paint LINE_COLOR_LIGHT = dark_grey;





    /* * * * *
     * Lines *
     * * * * */

    /**
     * Basic thickness for light lines.
     */
    public static final double SMALL_LINES_WIDTH = 0.45;

    /**
     * Basic thicknes for hard lines.
     */
    public static final double THICK_LINES_WIDTH = 1.75;


}
