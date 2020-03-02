/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti4v;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author jonde
 */
public class MouseMovement implements MouseMotionListener {

    private int x = 0;
    private int y = 0;

    @Override
    public void mouseDragged(MouseEvent me) {
        this.x = me.getX();
        this.y = me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.x = me.getX();
        this.y = me.getY();
    }

///////////////////////////////////GETERS AND SETTERS///////////////////////////

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    

}
