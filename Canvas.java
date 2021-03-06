import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Canvas extends JPanel {

private Image image;
Graphics2D twoD;
private int old_x, old_y, current_x, current_y;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image image2 = toolkit.getImage("src/brush.png");
    private static Image newCursor = image2.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ; 
    private static Cursor c = toolkit.createCustomCursor(newCursor , new Point(0, 
               0), "newCursor");

public Canvas() {
    this.init();
    // this.frameInit();
}

private void init() {
    this.setPreferredSize(new Dimension(600,600));
    this.setDoubleBuffered(false);
 this.setCursor (c);
    this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            old_x = e.getX();
            old_y = e.getY();
            // moves.add(e.getPoint());
            // repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    });

    this.addMouseMotionListener(new MouseAdapter() {

        public void mousePressed(MouseEvent e) {
            current_x = e.getX();
            current_y = e.getY();
            if(twoD != null){
                twoD.drawOval(current_x,current_y,1,1);
                repaint();
                // old_x = current_x;
                // old_y = current_y;
            }
        }

        public void mouseDragged(MouseEvent e) {
            current_x = e.getX();
            current_y = e.getY();

            if(twoD != null){
                twoD.drawLine(old_x,old_y,current_x,current_y);
                repaint();
                old_x = current_x;
                old_y = current_y;
            }

            // moves.add(e.getPoint());

        }

        public void mouseReleased(MouseEvent e) {
        }

    });

}

private void frameInit() {

    JFrame window = new JFrame("GPaint");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setContentPane(this);
    window.pack();
    window.setVisible(true);

}

public void paintComponent(Graphics g) {
    if(image == null){
        image = createImage(getSize().width, getSize().height);
        twoD = (Graphics2D) image.getGraphics();
        twoD.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // clear();
    }

    g.drawImage(image,0,0,null);
    

}
public void clearCanvas(){
    twoD.setPaint(Color.white);
    twoD.fillRect(0,0,getSize().width,getSize().height);
    twoD.setPaint(Color.black);
    repaint();
}

public void changetoRed(){
    twoD.setPaint(Color.decode("#c0392b"));
}
public void changetoBlue(){
    twoD.setPaint(Color.decode("#2980b9"));
}
public void changetoYellow(){
    twoD.setPaint(Color.yellow);
}
public void changetoGreen(){
    twoD.setPaint(Color.decode("#27ae60"));
}
public void changetoBlack(){
    twoD.setPaint(Color.black);
}

}



