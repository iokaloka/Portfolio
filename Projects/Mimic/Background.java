import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Background {
    
    private Game game;
    private float x;
    private float y;
    private float velX;
    private float velY;
    private BufferedImage img;
    private int counter = 0;
    
    public Background(Game game, String s){
        this.game = game;
        x = 0;
        y = 0;
        velX = 3;
        velY = 0;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException e){
            System.out.println("Failed!");
        }
    }
    
    public void update(Random r){
        counter++;
        if (counter >= (120 / (60 / game.getFrequence()))){
            counter = 0;
            if(r.nextInt(2) == 0){
                velX = r.nextInt(3) + 1;
            } else {
                velX = (r.nextInt(3) + 1) * -1;
            }
            if(r.nextInt(2) == 0){
                velY = r.nextInt(3) + 1;
            } else {
                velY = (r.nextInt(3) + 1) * -1;
            }
        }
        x += velX;
        y += velY;
        
        //////////////////////Luodaan rajat taustakuvan liikkeelle//////////////////////////
        if(x > game.getW() / 2){
            x = game.getW() / 2;
            velX *= -1;
        } else if (x < -game.getW() / 2){
            x = -game.getW() / 2;
            velX *= -1;
        }
        if(y > game.getH() / 2){
            y = game.getH() / 2;
            velY *= -1;
        } else if (y < -game.getH() / 2){
            y = -game.getH() / 2;
            velY *= -1;
        }
    }
    
    public void render(Graphics g){
        g.drawImage(img, (int)x - (game.getW() / 2), (int)y - (game.getH() / 2), game.getW() * 2, game.getH() * 2, null);
    }
    
    public BufferedImage getImage(){
        return this.img;
    }
    
}
