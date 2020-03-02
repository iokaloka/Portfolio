import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//Tässä luokassa tarkkaillaan kursorin/hiiren liikettä. 
//Tällä luokalla luodaan mahdollisuus kerätä pisteitä seuraamalla liikkuvaa kohdetta.

public class MouseMovement implements MouseMotionListener {

    private Target t;
    private Hud score;

    public MouseMovement(Target t, Hud score) {
        this.t = t;
        this.score = score;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        for (Tail piece : t.getTail()) {
            if (piece.getAlive() && !piece.getActivated() && score.getTimer() > 0) {
                if (checkCollision(x, y, piece.getBounds())) {
                    piece.setActivated(true);
                    score.setScore(score.getScore() + 1);
                }
            }
        }
    }

    public Rectangle getBounds(int x, int y) {
        return new Rectangle(x, y, 1, 1);
    }

    public boolean checkCollision(int x, int y, Rectangle other) {
        boolean flag = false;

        if (getBounds(x, y).intersects(other) || other.getBounds().contains(getBounds(x, y))) {
            flag = true;
        }
        return flag;
    }

}
