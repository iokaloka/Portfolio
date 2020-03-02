package guiltspawn.engine;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Window {

    private final GuiltSpawn game;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public Window(String title, GuiltSpawn game) {
        this.game = game;
        setSizes();
        JFrame frame = new JFrame(title);
        frame.setUndecorated(true);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /////////////////////////////GETTERS AND SETTERS////////////////////////////
    public void setSizes() {
        this.game.setPreferredSize(new Dimension(screen.width, screen.height));
        this.game.setMaximumSize(new Dimension(screen.width, screen.height));
        this.game.setMinimumSize(new Dimension(screen.width, screen.height));
    }

    public Dimension getScreen() {
        return screen;
    }

    public void setScreen(Dimension screen) {
        this.screen = screen;
    }

}
