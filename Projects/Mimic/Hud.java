
import java.awt.Color;
import java.awt.Graphics;

public class Hud {

    private int score = 0;
    private int timer;
    private int length = 60;
    private Game game;
    private int level = 1;
    private int elapsed = 0;
    
    //Annetaan Game-olio luokalle

    public Hud(Game game) {
        this.game = game;
        this.timer = length * game.getTicks();
    }
    
    //Resetoidaan kunkin pelin jälkeen pisteet ja kulunut aika
    public void reset(){
        this.score = 0;
        this.timer = length * game.getTicks();
        this.level = 1;
        this.elapsed = 0;
    }
    
    //Tarkistetaan ajan kuluminen. Mikäli aika on loppunut, siirrytään pelin lopetus -tilaan
    //Mikäli aikaa on jäljellä, nostetaan pelin tasoa jolloin kohteen nopeus kasvaa
    public void update() {
        timer -= 1;
        if (timer <= 0) {
            game.setCurrentState(Game.STATE.End);
        }
        elapsed++;
        if(elapsed %300 == 0){
            level++;
        }
    }
    
    //Piirretään näkyviin pisteet ja kuluva aika meneillään olevaan peliin
    
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawString("Points: " + score, game.getW() - 200, 200);
        g.drawString("Timer: " + timer / game.getTicks() + " s", game.getW() - 200, 250);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    
    public int getTimer(){
        return this.timer;
    }
    
    public int getLevel(){
        return this.level;
    }

    public Game getGame() {
        return game;
    }

}
