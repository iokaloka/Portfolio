import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Instructions {
    private Background instructions;
    private Game game;
    
    
    public Instructions(Game game){
        instructions = new Background(this.game, "/scores1.png");
        this.game = game;
    }
    
    public void render(Graphics g){
        g.drawImage(instructions.getImage(), 0, 0, game.getW(), game.getH(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 22));
        g.drawString("- Use your mouse to move the cursor and try to mimic the movement of blue target-ball.", 100, 200);
        g.drawString("- You will be rewarded with points each time you manage to touch the target or it's tail.", 100, 300);
        g.drawString("- The closer you are to the target, the more points you'll get.", 100, 400);
        g.drawString("- The target will move in random directions with increasing speed.", 100, 500);
        g.drawString("- Game ends when timer runs out.", 100, 600);
        g.drawString("Back to Menu", 850, 900);
    }
}
