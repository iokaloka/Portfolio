import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class Bg {
   private Game game;
   private ArrayList<Background> images;
   private Random r;
   
   public Bg(Game game, Random r){
       this.game = game;
       this.r = r;
       images = new ArrayList<>();
       images.add(new Background(game,"/smoke_black.png")); 
       images.add(new Background(game,"/smoke_r.png")); 
   }
   
   public void update(){
       for(Background bg: images){
           bg.update(this.r);
       }
   }
   
   public void render(Graphics g){
       for(Background bg: images){
           bg.render(g);
       }
   }
}
