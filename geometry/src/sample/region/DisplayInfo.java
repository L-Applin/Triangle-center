package sample.region;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import sample.shapes.CircleCenter;
import sample.shapes.InfoTriangle;

@Getter @Setter
public class DisplayInfo {

    private Text circumTitle, circumRadius, circumPosition;
    private Text orthoTitle, orthoPosition;
    private Text centroTitle, centroPosition;
    private Text innerTitle, innerPos, innerRadius;

    private InfoTriangle triangle;

    private final HBox circumInfo = new HBox();
    private final HBox centroInfo = new HBox();
    private final HBox orthoInfo =  new HBox();
    private final HBox innerInfo =  new HBox();

    @Getter
    private VBox allInfos = new VBox();


    public DisplayInfo(InfoTriangle triangle){
        this.triangle = triangle;
    }

    public void initInfos(){

        circumTitle =   new Text("Circumcenter : ");
        centroTitle =   new Text("Centroid     : ");
        orthoTitle =    new Text("Orthocenter  : ");
        innerTitle =    new Text("Innercernter : ");

        CircleCenter circum = triangle.getCircumcenter();
        circumPosition = new Text(String.format("(%.2f, %.2f)", circum.getCenterX(), circum.getCenterY()) + " | ");
        circumRadius = new Text(String.format("radius = %.2f", triangle.getOuterCircle().getRadius()));

        CircleCenter inner = triangle.getIncenter();
        innerPos = new Text(String.format("(%.2f, %.2f)", inner.getCenterX(), inner.getCenterY()) + " | ");
        innerRadius = new Text(String.format("radius = %.2f", triangle.getIncircle().getRadius()));

        CircleCenter ortho = triangle.getOrthocenter();
        orthoPosition = new Text(String.format("(%.2f, %.2f)", ortho.getCenterX(), ortho.getCenterY()));

        CircleCenter centro = triangle.getCentroid();
        centroPosition = new Text(String.format("(%.2f, %.2f)", centro.getCenterX(), centro.getCenterY()));

        circumInfo.getChildren().addAll(circumTitle, circumPosition, circumRadius);
        innerInfo.getChildren().addAll(innerTitle, innerPos, innerRadius);
        centroInfo.getChildren().addAll(centroTitle, centroPosition);
        orthoInfo .getChildren().addAll(orthoTitle, orthoPosition);


        circumTitle.setFont(Font.font(java.awt.Font.MONOSPACED));
        circumRadius.setFont(Font.font(java.awt.Font.MONOSPACED));
        circumPosition.setFont(Font.font(java.awt.Font.MONOSPACED));
        orthoTitle.setFont(Font.font(java.awt.Font.MONOSPACED));
        orthoPosition.setFont(Font.font(java.awt.Font.MONOSPACED));
        centroTitle.setFont(Font.font(java.awt.Font.MONOSPACED));
        centroPosition.setFont(Font.font(java.awt.Font.MONOSPACED));
        innerPos.setFont(Font.font(java.awt.Font.MONOSPACED));
        innerTitle.setFont(Font.font(java.awt.Font.MONOSPACED));
        innerRadius.setFont(Font.font(java.awt.Font.MONOSPACED));

        addAllInfo();

    }


    public void updateInfos(){

        CircleCenter circum = triangle.getCircumcenter();
        CircleCenter ortho = triangle.getOrthocenter();
        CircleCenter centro = triangle.getCentroid();
        CircleCenter inner = triangle.getIncenter();

        circumRadius.setText(String.format("radius = %.2f", triangle.getOuterCircle().getRadius()));
        circumPosition.setText(String.format("(%.2f, %.2f)", circum.getCenterX(), circum.getCenterY()) + " | ");

        innerRadius.setText(String.format("radius = %.2f", triangle.getIncircle().getRadius()));
        innerPos.setText(String.format("(%.2f, %.2f)", inner.getCenterX(), inner.getCenterY()) + " | ");


        orthoPosition.setText(String.format("(%.2f, %.2f)", ortho.getCenterX(), ortho.getCenterY()));

        centroPosition.setText(String.format("(%.2f, %.2f)", centro.getCenterX(), centro.getCenterY()));

    }

    public void removeText(){
        allInfos.getChildren().clear();
    }

    public void addAllInfo(){
        allInfos.getChildren().addAll(circumInfo, innerInfo, centroInfo, orthoInfo);

    }

}
