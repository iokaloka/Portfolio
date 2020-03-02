import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Summary {

    private Background bg;
    private Font font;
    private Game game;
    private int timer = 0;
    private boolean cursor = true;
    private boolean ready = false;
    private String name = "";

    public Summary(Game game) {
        this.game = game;
        bg = new Background(game, "/end1.png");
        this.font = new Font("End", Font.BOLD, 40);
    }

    public void update() {
        if (this.timer >= 30) {
            this.timer = 0;
            if (cursor) {
                cursor = false;
            } else {
                cursor = true;
            }
        } else {
            timer++;
        }
    }
    
    //Piirretään pelin lopetusnäyttö kun peliaika on loppunut. 
    //Luetaan pelaajan saama pistemäärä Hud-oliolta
    public void render(Graphics g) {
        g.drawImage(bg.getImage(), 0, 0, game.getW(), game.getH(), null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Game over", 900, 500);
        g.drawString("Your score: " + game.getHud().getScore(), 900, 600);
        g.drawString("Back to Menu", 850, 900);

        if (game.getRecords().checkIfRecord(game.getHud().getScore())) {    //Tarkistetaan, onko saatu pistemäärä uusi ennätys
            g.drawString("CONGRATULATIONS! You made it to top 10!", 600, 100);
            g.drawString("Type name: ", 850, 150);
            char currentChar = game.getKeyInput().getCurrentChar(); //Kysytään käyttäjältä nimi ennätyksen tallentamiseen
            if (cursor) {
                if (game.getKeyInput().getNameLength() > 0) {
                    for (int i = 0; i < game.getKeyInput().getNameLength(); i++) {
                        g.drawString(game.getKeyInput().getName().charAt(i) + "", 1080 + (20 * i - 1), 150);
                    }
                }
                g.fillRect(1080 + 20 * game.getKeyInput().getNameLength(), 150, 30, 5);
                g.drawString(currentChar + "", 1080 + 20 * game.getKeyInput().getNameLength(), 150);
            }
            name = game.getKeyInput().getName();
            if (currentChar == KeyEvent.VK_ENTER) { //Kun painetaan Enteriä, syötetty nimi tallennetaan
                if (!this.ready) {
                    game.getRecords().update();
                    this.ready = true;
                }
                game.setCurrentState(Game.STATE.Scoreboard);
            }
        }
    }
    
    public void reset(){    //Resetoidaan tiedot pelaajan syöttämästä nimestä
        game.getHud().reset();
        game.getKeyInput().reset();
        this.ready = false;
        this.name = "";
        this.timer = 0;
    }

    public boolean getReady() {
        return this.ready;
    }

    public String getName() {
        return this.name;
    }
}
