/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author jonde
 */
public class Animation {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args){
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++){
            images[i] = args[i];
        }
        frames = args.length;
        currentImg = images[0];
    }

    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    public void runAnimationOnce(){
        index++;
        if(index > speed){
            index = 0;
            nextFrameTillEnd();
        }
    }

    public void nextFrame(){
        for(int i = 0; i < frames; i++){
            if(count == i){
                currentImg = images[i];
            }
        }
        count++;

        if(count >= frames)
            count = 0;
    }

    public void nextFrameTillEnd(){
        for(int i = 0; i < frames; i++){
            if(count == i){
                currentImg = images[i];
            }
        }
        count++;

        if(count >= frames)
            this.currentImg = this.images[this.images.length - 1];
    }

    public void reset(){
        this.index = 0;
        this.currentImg = this.images[0];
    }

    public void drawAnimation(Graphics g, int x, int y){
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
