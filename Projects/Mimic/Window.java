import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

//Luodaan ikkuna jossa kaikki tapahtuu
public class Window {

    private Game g;
    private Dimension screen;

    public Window(String title, Game game) {
        this.g = game;
        System.out.println("Game: " + this.g);
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("screen: " + this.screen);
        setSizes();
        System.out.println("Sizes set");
        JFrame frame = new JFrame(title);
        System.out.println("Frame created");
        frame.setUndecorated(true);
        System.out.println("TRUE-fullscreen");
        frame.add(game);
        System.out.println("Game added to frame");
        frame.pack();
        System.out.println("Frame packed");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Frame finished");
        System.out.println(frame.getWidth() + ", " + frame.getHeight());
    }

    public void setSizes() {
        this.g.setPreferredSize(new Dimension((int)screen.getWidth(), (int)screen.getHeight()));
        this.g.setMaximumSize(new Dimension((int)screen.getWidth(), (int)screen.getHeight()));
        this.g.setMinimumSize(new Dimension((int)screen.getWidth(), (int)screen.getHeight()));
    }

    public Dimension getScreen() {
        return this.screen;
    }
   

}
