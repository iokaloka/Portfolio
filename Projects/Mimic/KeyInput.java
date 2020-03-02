import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//Tällä luokalla kysytään käyttäjältä nimimerkki ennätystuloksen tallentamista varten
public class KeyInput implements KeyListener {

    private char currentChar = KeyEvent.VK_A;
    private Game game;
    private String name = "";

    public KeyInput(Game game) {
        this.game = game;
    }
    
    public void reset(){
        currentChar = KeyEvent.VK_A;
        name = "";
    }


    @Override
    public void keyPressed(KeyEvent ke) {
        if(game.getCurrentState() == Game.STATE.End) {
            this.currentChar = ke.getKeyChar();
            if (Character.isLetter(currentChar)) {
                name += currentChar;
            } else if (ke.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                name = name.substring(0, name.length());
            }
        } else {
            if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
    }

    public char getCurrentChar() {
        return this.currentChar;
    }

    public String getName() {
        return this.name;
    }

    public int getNameLength() {
        return this.name.length();
    }
    
    //Tässä on kaikki metodit jotka pitää toteutaa sillä KeyInput toteuttaa KeyListenerin
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
    }

}

