//         Name: Rohan Upponi
//  Description: This is essentially just an outer shell of the applet. Extending JApplet, this creates the applet itself and, upon initializing creates an object of the entire
//				 WholePanel class, which is the code for the actual functionality, inflating the panel into the layout and setting a default window size of 500 x 400.

import javax.swing.*;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DiscountPaint extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage paintStage)
    {
        SwingNode adapter = new SwingNode();
        swingNodeManager(adapter);

        Pane holder = new Pane();
        holder.getChildren().add(adapter);


        paintStage.setScene(new Scene(holder,500,400));
        paintStage.setTitle("Discount Paint");
        paintStage.show();
    }

    public void swingNodeManager(SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WholePanel wholePanel = new WholePanel();
                swingNode.setContent(wholePanel);
            }
        });
    }

}





