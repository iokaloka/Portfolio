/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti4v;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jonde
 */
public class KeyInput implements KeyListener{

    @Override
    public void keyTyped(KeyEvent ke) {
        int key = ke.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyTyped(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
