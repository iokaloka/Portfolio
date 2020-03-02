import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//Luodaan pallolle häntä, jonka seuraamisesta saa myös pisteitä

public class Tail {
    private float x;
    private float y;
    private int size;
    private boolean alive = true;
    private boolean activated = false;
    private Color color = Color.BLUE;

    public Tail(float x, float y) {
        this.x = x;
        this.y = y;
        size = 60;
    }
    
    public void update() {  //Päivitetään hännän tila (onko hännästä saatu pisteitä?)
        size -= 1;          //pienennetään häntää alkaen pääpallosta
        if(size < 2){
            alive = false;  //Tällä poistetaan hännän osat jotka ovat liian pieniä enää seurata
        }
        if(activated){
            this.color = Color.GREEN;   //Jos hännästä on saatu pisteitä, tämä kohta muuttuu vihreäksi
                                        //Samasta kohdasta ei enää saa uudestaan pisteitä
        }
    }

    public void render(Graphics g) {        //Piirretään sininen häntä
        g.setColor(color);
        g.fillOval((int) x, (int) y, size, size);
    }
    
    public Rectangle getBounds(){    
        return new Rectangle ((int) x, (int) y, size, size);
    }
    
    public boolean getAlive(){  //Tarkistetaan seurattavatn kokoiset palat ja tallennetaan niistä uusi häntä Target-luokassa
        return this.alive;
    }
    
    public boolean getActivated(){  //Palautetaan hännän palan aktivointitila
        return this.activated;
    }
    
    public void setActivated(boolean Activated){    //Asetetaan hännän pala taas aktiiviseksi jolloin siitä saa pisteet
        this.activated = true;
    }

    public int getSize() {
        return size;
    }

}
