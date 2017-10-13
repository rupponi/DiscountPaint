//         Name: Rohan Upponi
//  Description: This WholePanel acts as the entirety of the applications functionality. It is composed of a
//				 private ColorListener, FillListener, DirectionListener, and PointListener class that each will
//				 color the produced circle and layout, fill/not fill the circle, move the circle, and allow
//				 for click inputs to be registered in the program, respectively. A circle will be produced and
//				 can then be subsequently moved around with the arrow keys. The radio buttons on the top can
//				 change the color or interior of the circle and the b and s keys can modify the size of the circle.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // to use listener interfaces

public class WholePanel extends JPanel
{
    private Color foregroundColor, backgroundColor;
    private int currentDiameter, x1, y1;
    private boolean filledTrue = false; //The boolean that FillListener passes to paintComponent to communicate filling preferences.
    private CanvasPanel canvas;
    private JPanel buttonPanel,firstRowPanel,secondRowPanel,thirdRowPanel;
    private JLabel foreColorLabel,backColorLabel,circleFillLabel;
    private JRadioButton redForeRadio,greenForeRadio,blueForeRadio,cyanBackRadio,yellowBackRadio,pinkBackRadio,unfilledRadio,filledRadio;

    public WholePanel()
    {
        backgroundColor = Color.CYAN;
        foregroundColor = Color.RED;

        currentDiameter = 100; //Default diameter of circle.
        x1 = 200; y1 = 100; //Default x- and y- Cartesian coordinates of circle (NOTE: Y-axis is inverted).

        buttonPanel = new JPanel(); //This panel is the upper half of the program, holding all the buttons.
        firstRowPanel = new JPanel(); //This panel holds the first row of radio buttons and their descriptor.
        secondRowPanel = new JPanel(); //This panel holds the second row of selections.
        thirdRowPanel = new JPanel(); //This panel holds the third row of selections.

        foreColorLabel = new JLabel("Foreground Color"); //Labels for each row of selections.
        backColorLabel = new JLabel("Background Color");
        circleFillLabel = new JLabel("Circle Filling");

        ColorListener colorChoice = new ColorListener(); //Instantiating ColorListener class to listen for choices.
        FillListener fillChoice = new FillListener(); //Instantiating FillListener class to listen for choices.

        redForeRadio = new JRadioButton("red", true); //Instantiates all radio buttons to be seen in top of applet.
        greenForeRadio = new JRadioButton("green");
        blueForeRadio = new JRadioButton("blue");
        cyanBackRadio = new JRadioButton("cyan", true);
        yellowBackRadio = new JRadioButton("yellow");
        pinkBackRadio = new JRadioButton("pink");
        unfilledRadio = new JRadioButton("Unfilled", true);
        filledRadio = new JRadioButton("Filled");

        redForeRadio.addActionListener(colorChoice); //Adds instantiated ColorListener to each color button.
        greenForeRadio.addActionListener(colorChoice);
        blueForeRadio.addActionListener(colorChoice);
        cyanBackRadio.addActionListener(colorChoice);
        yellowBackRadio.addActionListener(colorChoice);
        pinkBackRadio.addActionListener(colorChoice);
        unfilledRadio.addActionListener(fillChoice); //Adds instantiated FillListener to fill buttons.
        filledRadio.addActionListener(fillChoice);

        ButtonGroup group1 = new ButtonGroup(); //First group toggles foreground colors.
        group1.add(redForeRadio);
        group1.add(greenForeRadio);
        group1.add(blueForeRadio);

        ButtonGroup group2 = new ButtonGroup(); //Second group toggles background colors.
        group2.add(cyanBackRadio);
        group2.add(yellowBackRadio);
        group2.add(pinkBackRadio);

        ButtonGroup group3 = new ButtonGroup(); //Third group toggles fill/unfill.
        group3.add(unfilledRadio);
        group3.add(filledRadio);

        buttonPanel.setLayout(new GridLayout(3,1)); //This is the layout for the entire menu panel.
        firstRowPanel.setLayout(new FlowLayout()); //Each row of buttons and their descriptor is in a flow layout.
        secondRowPanel.setLayout(new FlowLayout());
        thirdRowPanel.setLayout(new FlowLayout());

        firstRowPanel.add(foreColorLabel); //Adds buttons and label for first row of buttons.
        firstRowPanel.add(redForeRadio);
        firstRowPanel.add(greenForeRadio);
        firstRowPanel.add(blueForeRadio);

        secondRowPanel.add(backColorLabel); //Adds buttons and label for second row of buttons.
        secondRowPanel.add(cyanBackRadio);
        secondRowPanel.add(yellowBackRadio);
        secondRowPanel.add(pinkBackRadio);

        thirdRowPanel.add(circleFillLabel); //Adds buttons and label for third row of buttons.
        thirdRowPanel.add(unfilledRadio);
        thirdRowPanel.add(filledRadio);

        buttonPanel.add(firstRowPanel); //Adds previous three panels to a larger panel that becomes the entire menu.
        buttonPanel.add(secondRowPanel);
        buttonPanel.add(thirdRowPanel);



        canvas = new CanvasPanel(filledTrue); //Creates a CanvasPanel object and passes through the given filling boolean.


        JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, canvas);

        setLayout(new BorderLayout());
        add(sPane, BorderLayout.CENTER);
        setMinimumSize(new Dimension(800,600));
    }


    private class ColorListener implements ActionListener //This class communicates the user-picked colors within the application
    {													 // to the paintComponent method so it can draw out the layout.
        public void actionPerformed(ActionEvent event) //This decides the action of the CanvasPanel based on the states of the radio buttons.
        {
            Object choice = event.getSource();

            if(redForeRadio.isSelected()) {
                foregroundColor = Color.RED;
                canvas.repaint();
            }
            if(greenForeRadio.isSelected()) {
                foregroundColor = Color.GREEN;
                canvas.repaint();
            }
            if(blueForeRadio.isSelected()) {
                foregroundColor = Color.BLUE;
                canvas.repaint();
            }
            if(cyanBackRadio.isSelected()) {
                backgroundColor = Color.CYAN;
                canvas.setBackground(backgroundColor);
            }
            if(yellowBackRadio.isSelected()) {
                backgroundColor = Color.YELLOW;
                canvas.setBackground(backgroundColor);
            }
            if(pinkBackRadio.isSelected()) {
                backgroundColor = Color.PINK;
                canvas.setBackground(backgroundColor);
            }

        }
    }



    private class FillListener implements ActionListener //This class communicates whether or not to fill the circle with a boolean
    {													//that will be passed to paintComponent, providing the user input.
        public void actionPerformed(ActionEvent event) //This decides the filling of the circle in CanvasPanel based on the state of each filling button.
        {
            Object choice = event.getSource();

            if(choice == unfilledRadio) {
                filledTrue = false;
                repaint();
            }
            if(choice == filledRadio) {
                filledTrue = true;
                repaint();
            }
        }
    }


    private class CanvasPanel extends JPanel //This panel is the bottom half of the applet and holds the circle and background.
    {
        //Constructor to initialize the canvas panel
        public CanvasPanel(boolean filledTrueIn) //This constructor contains an argument of a boolean specifying filling of the circle.
        {
            // make this canvas panel listen to keys
            addKeyListener (new DirectionListener()); //Adds KeyListener to manipulate CanvasPanel.
            // make this canvas panel listen to mouse
            addMouseListener(new PointListener());

            setBackground(backgroundColor); //Sets default background to CanvasPanel.

            boolean filledTrue = filledTrueIn; //Accepts argument and stores as boolean to use in paintComponent.


            //This method needs to be called for this panel to listen to keys
            //When panel listens to other things, and go back to listen
            //to keys, this method needs to be called again.
            requestFocus();
        }


        //this method draws all characters pressed by a user so far
        public void paintComponent(Graphics page) //paintComponent overriden to draw out circle and attributes.
        {
            super.paintComponent(page);
            setBackground(backgroundColor); //Sets color of background.
            page.setColor(foregroundColor); //Sets color of circle.
            page.drawOval(x1,y1,(currentDiameter/2),(currentDiameter/2));
            if(filledTrue) { //Uses boolean passed from FillListener class to decide whether or not to fill.
                page.fillOval(x1,y1,(currentDiameter/2),(currentDiameter/2));
            }

        }

        /** This method is overriden to enable keyboard focus */
        public boolean isFocusable()
        {
            return true;
        }

        // listener class to listen to keyboard keys
        private class DirectionListener implements KeyListener //This class controls movement of circle.
        {
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}

            // in case that a key is pressed, the following will be executed.
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) { //Registers up-arrow functionality.
                    y1 = y1 - 5;
                    repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) { //Registers down-arrow functionality.
                    y1 = y1 + 5;
                    repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //Registers right-arrow functionality.
                    x1 = x1 + 5;
                    repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) { //Registers left-arrow functionality.
                    x1 = x1 - 5;
                    repaint();
                }
                if(e.getKeyChar() == 'b') { //Registers size-increase functionality with B key.
                    currentDiameter = currentDiameter + 6;
                    repaint();
                }
                if(e.getKeyChar() == 's') { //Size decreases with the S key.
                    currentDiameter = currentDiameter - 6;
                    repaint();
                }

            }
        } // end of DirectionListener


        // listener class that listens to the mouse
        // This class is already completed. No adjustment is needed.
        public class PointListener implements MouseListener
        {
            //in case that a user presses using a mouse,
            //it gains the focus of the keyboard keys
            public void mousePressed (MouseEvent event) //Used to focus on CanvasPanel when the object is moved or changed in size by the keyboard input.
            {
                canvas.requestFocus();
            }

            public void mouseClicked (MouseEvent event) {} //These functions are not needed
            public void mouseReleased (MouseEvent event) {}
            public void mouseEntered (MouseEvent event) {}
            public void mouseExited (MouseEvent event) {}

        } // end of PointListener

    } // end of Canvas Panel Class

} // end of Whole Panel Class
