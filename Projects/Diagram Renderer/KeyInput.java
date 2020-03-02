package diagramrenderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private DiagramRenderer dr;

    public KeyInput(DiagramRenderer dr) {
        this.dr = dr;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (key == KeyEvent.VK_D) {
            if (dr.getCurrentRender() < 6) {
                dr.setCurrentRender(dr.getCurrentRender() + 1);
            }
        } else if (key == KeyEvent.VK_A){
            if (dr.getCurrentRender() > 1) {
                dr.setCurrentRender(dr.getCurrentRender() - 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
