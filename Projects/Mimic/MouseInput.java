
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    //mousePressed -metodilla seurataan hiiren klikkauksia. Tällä pystytään
    //seuraamaan ja muuttamaan valikon näkymiä kun hiirellä klikataan valikon kohtia
    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (game.getCurrentState() == Game.STATE.Menu) {
            if (x > 885 && x < 1005 && y > 260 && y < 310) {
                game.setCurrentState(Game.STATE.Game);
            } else if (x > 885 && x < 1130 && y > 460 && y < 510) {
                game.setCurrentState(Game.STATE.Instructions);
            } else if (x > 885 && x < 1135 && y > 660 && y < 710) {
                game.setCurrentState(Game.STATE.Scoreboard);
            } else if (x > 885 && x < 1175 && y > 60 && y < 115) {
                if(game.getFrequence() == 30){
                    game.setFrequence(60);
                    game.updateGoal();
                } else {
                    game.setFrequence(30);
                    game.updateGoal();
                }
            } else if (x > 885 && x < 985 && y > 860 && y < 910) {
                System.exit(0);
            }

        }

        if (game.getCurrentState() == Game.STATE.Instructions) {
            if (x > 830 && x < 1050 && y > 850 && y < 950) {
                game.setCurrentState(Game.STATE.Menu);
                game.getSummary().reset();
            }
        }

        if (game.getCurrentState() == Game.STATE.Scoreboard) {
            if (x > 830 && x < 1050 && y > 850 && y < 950) {
                game.setCurrentState(Game.STATE.Menu);
                game.getSummary().reset();
            }
        }

        if (game.getCurrentState() == Game.STATE.End) {
            if (x > 830 && x < 1050 && y > 850 && y < 950) {
                game.setCurrentState(Game.STATE.Menu);
                game.getSummary().reset();
            }
        }
    }

    //Nämä metodit on oltava koska luokka toteuttaa MouseListenerin vaikka ne ovat tyhjiä metodeja
    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
