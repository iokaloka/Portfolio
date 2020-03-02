/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework;

import com.joka.guiltspawn.Interfaces.IBlock;
import com.joka.guiltspawn.Interfaces.IEnemy;
import com.joka.guiltspawn.Interfaces.ITrigger;
import com.joka.guiltspawn.entity.*;
import com.joka.guiltspawn.entity.npc.enemies.E1;
import com.joka.guiltspawn.entity.player.Player;
import com.joka.guiltspawn.enviroment.*;
import com.joka.guiltspawn.main.Game;
import com.joka.guiltspawn.io.BufferedImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author jonde
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public LinkedList<IBlock> blocks = new LinkedList<IBlock>();
    public LinkedList<ITrigger> triggers = new LinkedList<ITrigger>();
    public LinkedList<IEnemy> enemies = new LinkedList<IEnemy>();

    private Camera cam;
    private Game game;
    private GameObject tempObject;
    private MoveZone zone;
    private Level currentLevel;
    private int dn = 1;
    private float ls = 2048, le = 2048;
    private BufferedImage poe11 = null, poe12 = null, poe13 = null, poe14 = null, poe15 = null, poe16 = null,
            poe17 = null, poe18 = null, poe19 = null, poe110 = null;

    Block gate = null;
    Block pillar = null;
    Event event = null;

    public Handler(Camera cam, Game game) {
        this.cam = cam;
        this.game = game;
        BufferedImageLoader loader = new BufferedImageLoader();

        ////////////////////////////////LEVELS//////////////////////////////////
        poe11 = loader.loadImage("/poe1_1s/1_1_0.png");
        poe12 = loader.loadImage("/poe1_2s/1_2_0.png");
        poe13 = loader.loadImage("/poe1_3s/1_3_0.png");
        poe14 = loader.loadImage("/poe1_4s/1_4_0.png");
        poe15 = loader.loadImage("/poe1_5s/1_5_0.png");
        poe16 = loader.loadImage("/poe1_6s/1_6_0.png");
        poe17 = loader.loadImage("/poe1_7s/1_7_0.png");
        poe18 = loader.loadImage("/poe1_8s/1_8_0.png");
        poe19 = loader.loadImage("/poe1_9s/1_9_0.png");
        poe110 = loader.loadImage("/poe1_10s/1_10_0.png");
    }

    public void tick() {
        if (!game.getLoading()) {
            E1.Companion.getAll().forEach(E1::tick);
            if(Game.Companion.getState() == Game.STATE.MENU){
                for (int i = 0; i < object.size(); i++) {
                    tempObject = object.get(i);
                    if(tempObject.getId() == ObjectId.Block)
                    tempObject.tick();
                }
            } else {
                triggers.forEach(e -> e.tick());
                for (int i = 0; i < object.size(); i++) {
                    tempObject = object.get(i);
                    tempObject.tick();
                }
                if (this.zone != null) {
                    zone.tick();
                }
                if (this.event != null) {
                    this.event.tick();
                }
            }
        }
    }

    public void render(Graphics g) {
        if (!game.getLoading()) {
            E1.Companion.getAll().forEach(e -> e.render(g));
            object.forEach(e -> e.render(g));
            blocks.forEach(e -> e.render(g));
            triggers.forEach(e -> e.render(g));
            enemies.forEach(e -> e.render(g));
            game.getPlayer().render(g);
        }
    }

    public void loadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //////////////////Blocks and single objects/////////////////////
                if (red == 255 && green == 255 && blue == 255) {
                    addBlock(new Wall(xx * 32, yy * 32)); // ground/wall
                } else if (red == 0 && green == 0 && blue == 50) {
                    addBlock(new Water(xx * 32, yy * 32)); // water
                } else if (green == 254) {
                    Flag flag = new Flag(xx * 32, yy * 32, blue, red); // flag
                    addTrigger(flag);
                    if (flag.getX() < this.ls) {
                        this.ls = flag.getX();
                    } else if (flag.getX() > this.le) {
                        this.le = flag.getX();
                    }
                } else if (red == 0 && green == 0 && blue == 255) { // move player to
                    if (this.dn == 1) {
                        this.game.getPlayer().setX(xx * 32);
                        this.game.getPlayer().setY(yy * 32);
                        this.zone = new MoveZone(this.game.getPlayer().getX() - 126, this.game.getPlayer().getY() - 150, ObjectId.Zone, this.game.getPlayer());
                        this.game.getPlayer().setMoveZone(zone);
                        dn--;
                    } else {
                        dn--;
                    }
                } else if (red == 150 && green == 0 && blue == 0) {
                    addEnemy(new E1(xx * 32, yy * 32)); // enemy 1
                } else if (red == 255 && green == 255 && blue == 1) {
                    Block b = new Block(xx * 32, yy * 32, 9, ObjectId.Block, this); // gate 1
                    addObject(b);
                    gate = b;
                } else if (red == 255 && green == 255 && blue == 2) {
                    Block p = new Block(xx * 32, yy * 32, 10, ObjectId.Block, this); // supporting pillar 1
                    addObject(p);
                    pillar = p;
                } /*else if (red == 255 && green == 0 && blue == 255) { // torch
                    if (game.getPlayer().getWeaponR() == null) {
                        addObject(new Activateable(xx * 32, yy * 32, ObjectId.Activateable, game.getTexture().getBlock()[13], new Action(1, this.game), this.game));
                    }
                } else if (red == 255 && green == 50 && blue == 255) {
                    addObject(new Activateable(xx * 32, yy * 32, ObjectId.Activateable, game.getTexture().getObjects()[21], new Action(2, this.game), this.game));

                }*/ else if (red == 0 && green == 10 && blue == 0) {
                    addTrigger(new Message(xx * 32, yy * 32, this.game.getTexture2()));
                }
            }
        }
        this.dn = 1;
        if (Game.Companion.getLEVEL() == 11) {
            ls = 640;
        }
    }

    public MoveZone getMoveZone() {
        return this.zone;
    }

    public void restart() {

        clearLevel();
        loadImageLevel(currentLevel.getLevelSceleton());
        this.event = null;
    }



    public void loadStartLevel() {

        game.setLoading(true);
        this.game.setTexture2(new Texture(11));
        this.currentLevel = new Level(11, poe11, game.getTexture2().getLevel()[0], game.getTexture2().getLevel()[1], game.getTexture2().getLevel()[2], game.getTexture2().getLevel()[3],
                game.getTexture2().getLevel()[4], game.getTexture2().getLevel()[5], game.getTexture2().getLevel()[6], game.getTexture2().getLevel()[7], game.getTexture2().getLevel()[8],
                game.getTexture2().getLevel()[9], game.getTexture2().getLevel()[10], game.getTexture2().getLevel()[11], game.getTexture2().getLevel()[12], this, this.game.getPlayer());
        Game.Companion.setLEVEL(11);
        loadImageLevel(currentLevel.getLevelSceleton());
        game.setSoundName("snd/music1.WAV");

        game.setLoading(false);
    }

    public void switchLevel() {
        if (event != null){
            event.setValiable(false);
        }

        this.ls = 2048;
        this.le = 2048;
        game.setLoading(true);
        clearLevel();
        cam.setX(0);
        Texture texture = new Texture(game.Companion.getLEVEL());
        game.setTexture2(texture);
        game.Companion.setCURRENTTEXTURE(texture);

        BufferedImage scele = null;
        switch (game.Companion.getLEVEL()) {
            case 11:
                scele = poe11;
                break;
            case 12:
                scele = poe12;
                break;
            case 13:
                scele = poe13;
                break;
            case 14:
                scele = poe14;
                break;
            case 15:
                scele = poe15;
                break;
            case 16:
                scele = poe16;
                game.setDarkness(false);
                break;
            case 17:
                scele = poe17;
                break;
            case 18:
                scele = poe18;
                game.setDarkness(true);
                break;
            case 19:
                scele = poe19;
                break;
            case 20:
                scele = poe110;
                break;
        }

        this.currentLevel = new Level(game.Companion.getLEVEL(), scele, game.getTexture2().getLevel()[0], game.getTexture2().getLevel()[1], game.getTexture2().getLevel()[2], game.getTexture2().getLevel()[3], game.getTexture2().getLevel()[4], game.getTexture2().getLevel()[5], game.getTexture2().getLevel()[6], game.getTexture2().getLevel()[7], game.getTexture2().getLevel()[8], game.getTexture2().getLevel()[9], game.getTexture2().getLevel()[10], game.getTexture2().getLevel()[11], game.getTexture2().getLevel()[12], this, this.game.getPlayer());
        loadImageLevel(currentLevel.getLevelSceleton());
        game.setSoundName("snd/music1.WAV");
        game.setLoading(false);
    }

    private void clearLevel() {
        Player playerNext = this.game.getPlayer();
        Weapon weaponR = null;
        if (this.game.getPlayer().getWeaponR() != null) {
            weaponR = this.game.getPlayer().getWeaponR();
        }
        object.clear();
        blocks.clear();
        triggers.clear();
        enemies.clear();
        addObject(playerNext);
        if (weaponR != null) {
            addObject(weaponR);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }
    public void addBlock(IBlock block) { this.blocks.add(block); }
    public void addTrigger(ITrigger trigger) { this.triggers.add(trigger); }
    public void addEnemy(IEnemy enemy) { this.enemies.add(enemy); }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
    public void removeBlock(IBlock block) {
        this.blocks.remove(block);
    }
    public void removeTrigger(ITrigger trigger) {
        this.triggers.remove(trigger);
    }
    public void removeEnemy(IEnemy enemy) {
        this.enemies.remove(enemy);
    }

    public Game getGame() {
        return this.game;
    }

    public Block getPillar() {
        return this.pillar;
    }

    public Block getGate() {
        return this.gate;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LinkedList<GameObject> getObject() {
        return this.object;
    }

    public LinkedList<IBlock> getBlocks() {
        return this.blocks;
    }

    public LinkedList<ITrigger> getTriggers() {
        return this.triggers;
    }

    public LinkedList<IEnemy> getEnemies() {
        return this.enemies;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setLevel(int level){
        game.Companion.setLEVEL(level);
    }

    public void setDN(int dn) {
        this.dn = dn;
    }

    public float getLs() {
        return this.ls;
    }

    public void setLs(float ls) {
        this.ls = ls;
    }

    public float getLe() {
        return this.le;
    }

    public void setLe(float le) {
        this.le = le;
    }

}
