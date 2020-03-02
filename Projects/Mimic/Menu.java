import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
    private Game game;
    private Background bg;
    private Font font;
    
    public Menu(Game game){
        this.game = game;
        bg = new Background(game, "/menu1.png");
        System.out.println("-BACKGROUND IN MENU");
        this.font = new Font("Menu", Font.BOLD, 40);
        System.out.println("-FONT IN MENU");
    }
    
    //Piirretään aloitusnäyttö (Menu)
    
    public void render(Graphics g){
        g.drawImage(bg.getImage(), 0, 0, game.getW(), game.getH(), null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("toggle FPS:" + game.getFrequence(), 900, 100);
        g.drawString("Start", 900, 300);
        g.drawString("Instructions", 900, 500);
        g.drawString("High scores", 900, 700);
        g.drawString("Exit", 900, 900);
        g.setColor(Color.WHITE);
        g.drawRect(885, 60, 290, 55);
        g.drawRect(885, 260, 120, 50);
        g.drawRect(885, 460, 245, 50);
        g.drawRect(885, 660, 250, 50);
        g.drawRect(885, 860, 100, 50);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 100));
        g.drawString("-MIMIC-", 250, 550);
    }
} 