//         Name: Rohan Upponi
//  Description: This is essentially just an outer shell of the applet. Extending JApplet, this creates the applet itself and, upon initializing creates an object of the entire
//				 WholePanel class, which is the code for the actual functionality, inflating the panel into the layout and setting a default window size of 500 x 400.

import javax.swing.*;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DiscountPaint extends Application {

    private SwingNode wholePanelNode = new SwingNode();
    private WholePanel wholePanelObj = new WholePanel();
    private BorderPane contentHolder = new BorderPane();

    private TextArea textBox = new TextArea("Hello");


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage paintStage) {
        wholePanelNode.setContent(wholePanelObj);
        paintStage.setTitle("Discount Paint");

        contentHolder.setTop(wholePanelNode);
        contentHolder.setBottom(textBox);

        paintStage.setScene(new Scene(contentHolder,500,400));
    }


}





