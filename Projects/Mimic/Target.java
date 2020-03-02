import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Target {

    private float x;
    private float y;
    private float velX;
    private float velY;
    private int size;
    private int counter = 0;
    private Random r = new Random();
    private ArrayList<Tail> tail;
    private Hud score;

    public Target(Hud score) {
        this.score = score;
        x = 1000;
        y = 500;
        velX = 5 * (60 / score.getGame().getFrequence());
        velY = 5 * (60 / score.getGame().getFrequence());
        size = 60;
        tail = new ArrayList<>();   //Luodaan taulukko pallon "hännälle"
    }
    
    public void checkBorders(){     //Tarkistetaan että häntä pysyy koko ajan ruudun sisällä
        if(x > score.getGame().getW() - size){
            x = score.getGame().getW() - size;
            velX *= -1;
        }else if(x < 0){
            x = 0;
            velX *= -1;
        }
        if(y > score.getGame().getH() - size){
            y = score.getGame().getH() - size;
            velY *= -1;
        }else if (y < 0){
            y = 0;
            velY *= -1;
        }
    }

    public void update() {  //Päivitetään hännän taso ja nopeutetaan sen liikettä tason mukaan

        if (counter >= score.getGame().getFrequence()) {
            counter = 0;
            int i = r.nextInt(4);
            if (i == 0) {
                velX = (5 * (60 / score.getGame().getFrequence())) + score.getLevel();
            } else if (i == 1) {
                velY = (5 * (60 / score.getGame().getFrequence())) + score.getLevel();
            } else if (i == 2) {
                velX = (-5 * (60 / score.getGame().getFrequence())) - score.getLevel();
            } else {
                velY = (-5 * (60 / score.getGame().getFrequence())) - score.getLevel();
            }
        }

        x += velX;
        y += velY;
        checkBorders();
        spawn();
        counter++;
        
        ////////////////////////////KÄSITTELE HÄNTÄ/////////////////////////////
        ArrayList<Tail> fresh = new ArrayList<>();
        for(Tail piece: tail){
            piece.update();
            if(piece.getAlive()){
                fresh.add(piece);
            }
        }
        tail = fresh;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        for(Tail piece: tail){
            piece.render(g);
        }
        g.fillOval((int) x, (int) y, size, size);
    }
    
    public Rectangle getBounds(){    
        return new Rectangle ((int) x, (int) y, size, size);
    }
    
    public void spawn(){
        tail.add(new Tail(this.x, this.y));
    }
    
    public ArrayList<Tail> getTail(){
        return this.tail;
    }

}
