package diagramrenderer;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    
    private DiagramRenderer dr;
    private int WIDTH = 1920;
    private int HEIGHT = 1080;
    
    public Window(DiagramRenderer dr){
        this.dr = dr;
        setSizes();
        JFrame frame = new JFrame("Diagram");
        frame.setUndecorated(true);
        frame.add(dr);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void setSizes() {
        this.dr.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.dr.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.dr.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }
}
