package guiltspawn.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] images;
    private BufferedImage currentImage;
    private int index;
    private int speed;
    private int counter = 0;
    private boolean playedOnce;

    public Animation() {
        playedOnce = false;
        index = 0;
    }

    public Animation(int speed, BufferedImage... Args) {
        playedOnce = false;
        this.speed = speed;
        images = Args;
        if (images.length != 0) {
            currentImage = images[0];
            index = 0;
        } else {
            System.out.println("Empty animation");
        }
    }

    public void tick() {
        counter++;
        if (counter >= speed) {
            counter = 0;
            if (index + 1 >= images.length) {
                currentImage = images[0];
                index = 0;
                playedOnce = true;
            } else {
                index++;
                currentImage = images[index];
            }
        }
    }
    
    public void tickTillEnd(){
        counter++;
        if(counter >= speed){
            if(index < 1){
                index++;
            }
            counter = 0;
        }
    }

    public void render(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    public int getFrame() {
        return this.index;
    }

    public BufferedImage getImage() {
        return images[getFrame()];
    }

    public void setAnimation(BufferedImage[] animation) {
        this.images = animation;
        this.currentImage = animation[0];
        this.index = 0;
    }

    public boolean hasPlayedOnce() {
        return this.playedOnce;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void reset(){
        currentImage = images[0];
        index = 0;
        counter = 0;
        playedOnce = false;
    }

}
