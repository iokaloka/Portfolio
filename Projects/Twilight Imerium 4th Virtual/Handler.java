package ti4v;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Handler {

    private TI4V game;
    private int players;
    private int speaker = 0;
    private Random rnd;
    private Tile[] tiles = new Tile[37];
    private ArrayList<Player> playerList;
    private ArrayList<Planet> neutralPlanets;
    private Font font1;
    private Font font2;
    private BufferedImage st;
    private BufferedImage ac;
    private BufferedImage pbg;
    private BufferedImage unplacedBg;
    private Sprite redUnitIcons;
    private Sprite blueUnitIcons;
    private Sprite greenUnitIcons;
    private Sprite yellowUnitIcons;
    private Sprite purpleUnitIcons;
    private Sprite blackUnitIcons;
    private Player activePlayer;
    private boolean factionsSelected = false;

    public Handler(TI4V game) {
        this.game = game;
        this.rnd = new Random();
        this.playerList = new ArrayList<>();
        this.neutralPlanets = new ArrayList<>();
        this.font1 = new Font("Arial Black", Font.PLAIN, 20);
        this.font2 = new Font("Arial Black", Font.PLAIN, 16);
        this.st = fetch("/speakerToken.png");
        this.ac = fetch("/activePlayer.png");
        this.pbg = fetch("/playerBg.png");
        this.redUnitIcons = new Sprite("/unitsRed.png");
        this.blueUnitIcons = new Sprite("/unitsBlue.png");
        this.greenUnitIcons = new Sprite("/unitsGreen.png");
        this.yellowUnitIcons = new Sprite("/unitsYellow.png");
        this.purpleUnitIcons = new Sprite("/unitsPurple.png");
        this.blackUnitIcons = new Sprite("/unitsBlack.png");
        this.unplacedBg = fetch("/unplacedBg.png");
    }

    public void addTile(int location, int type) {
        int l = location - 1;
        switch (location) {
            case 1:
                this.tiles[l] = new Tile(this, 900, 528, 5);
                break;
            case 2:
                this.tiles[l] = new Tile(this, 900, 422, type);
                break;
            case 3:
                this.tiles[l] = new Tile(this, 992, 475, type);
                break;
            case 4:
                this.tiles[l] = new Tile(this, 992, 581, type);
                break;
            case 5:
                this.tiles[l] = new Tile(this, 900, 634, type);
                break;
            case 6:
                this.tiles[l] = new Tile(this, 809, 581, type);
                break;
            case 7:
                this.tiles[l] = new Tile(this, 809, 475, type);
                break;
            case 8:
                this.tiles[l] = new Tile(this, 900, 316, type);
                break;
            case 9:
                this.tiles[l] = new Tile(this, 992, 369, type);
                break;
            case 10:
                this.tiles[l] = new Tile(this, 1084, 422, type);
                break;
            case 11:
                this.tiles[l] = new Tile(this, 1084, 528, type);
                break;
            case 12:
                this.tiles[l] = new Tile(this, 1084, 634, type);
                break;
            case 13:
                this.tiles[l] = new Tile(this, 992, 687, type);
                break;
            case 14:
                this.tiles[l] = new Tile(this, 900, 740, type);
                break;
            case 15:
                this.tiles[l] = new Tile(this, 809, 687, type);
                break;
            case 16:
                this.tiles[l] = new Tile(this, 717, 634, type);
                break;
            case 17:
                this.tiles[l] = new Tile(this, 717, 528, type);
                break;
            case 18:
                this.tiles[l] = new Tile(this, 717, 422, type);
                break;
            case 19:
                this.tiles[l] = new Tile(this, 809, 369, type);
                break;
            case 20:
                this.tiles[l] = new Tile(this, 900, 210, type);
                break;
            case 21:
                this.tiles[l] = new Tile(this, 992, 263, type);
                break;
            case 22:
                this.tiles[l] = new Tile(this, 1084, 316, type);
                break;
            case 23:
                this.tiles[l] = new Tile(this, 1176, 369, type);
                break;
            case 24:
                this.tiles[l] = new Tile(this, 1176, 475, type);
                break;
            case 25:
                this.tiles[l] = new Tile(this, 1176, 581, type);
                break;
            case 26:
                this.tiles[l] = new Tile(this, 1176, 687, type);
                break;
            case 27:
                this.tiles[l] = new Tile(this, 1084, 740, type);
                break;
            case 28:
                this.tiles[l] = new Tile(this, 992, 793, type);
                break;
            case 29:
                this.tiles[l] = new Tile(this, 900, 846, type);
                break;
            case 30:
                this.tiles[l] = new Tile(this, 809, 793, type);
                break;
            case 31:
                this.tiles[l] = new Tile(this, 717, 740, type);
                break;
            case 32:
                this.tiles[l] = new Tile(this, 625, 687, type);
                break;
            case 33:
                this.tiles[l] = new Tile(this, 625, 581, type);
                break;
            case 34:
                this.tiles[l] = new Tile(this, 625, 475, type);
                break;
            case 35:
                this.tiles[l] = new Tile(this, 625, 369, type);
                break;
            case 36:
                this.tiles[l] = new Tile(this, 717, 316, type);
                break;
            case 37:
                this.tiles[l] = new Tile(this, 809, 263, type);
                break;
            default:
        }
    }

    public void addPlayer(int number) {
        playerList.add(new Player(this, number));
    }
    
    public void addNeutralPlanet(Planet p){
        this.neutralPlanets.add(p);
    }
    
    public void removePlanet(Planet p){
        for(int i = 0; i < this.neutralPlanets.size(); i++){
            if(this.neutralPlanets.get(i).getName().equals(p.getName())){
                this.neutralPlanets.remove(i);
                i--;
            }
        }
    }

    public void removeTile(int location) {
        this.tiles[location - 1] = null;
    }

    public void randomizeGalaxy(int players) {
        int[] systems = {0, 0, 0, 0, 0, 1, 1, 2, 3, 4, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
        int index = systems.length;
        while (index > 0) {
            int tile = rnd.nextInt(systems.length);
            if (systems[tile] != -1) {
                int location = rnd.nextInt(37) + 1;
                if (tiles[location - 1] == null && location != 24 && location != 28 && location != 33 && location != 37 && location != 1) {
                    addTile(location, systems[tile]);
                    systems[tile] = -1;
                    index--;
                }
            }
        }
        addTile(1, 5);
    }

    public void initialize(int players) {
        this.setPlayers(players);
        for (int i = 1; i <= this.players; i++) {
            addPlayer(i);
        }
        this.randomizeGalaxy(this.players);
        randomizeSpeaker();
        selectFactions();
    }

    public void randomizeSpeaker() {
        this.speaker = 1 + rnd.nextInt(players);
        for (Player p : playerList) {
            if (p.getNumber() == this.speaker) {
                p.setSpeaker(true);
            } else {
                p.setSpeaker(false);
            }
        }
    }

    public void selectFactions() {
        this.game.wait(90);
        this.game.setFactionSelection(new FactionSelection(this));
        this.game.setState(TI4V.State.FACTIONSELECTION);
    }

    public BufferedImage fetch(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println("Image couldn't be fetched from: " + path);
        }
        return img;
    }

    public boolean mouseOverUnplaced() {
        boolean flag = false;
        Point p = null;
        switch (this.activePlayer.getNumber()) {
            case 1:
                p = new Point(1, 237);
                break;
            case 2:
                p = new Point(1322, 237);
                break;
            case 3:
                p = new Point(1, 958);
                break;
            case 4:
                p = new Point(1322, 958);
                break;
            case 5:
                p = new Point(1, 599);
                break;
            case 6:
                p = new Point(1322, 599);
                break;
        }
        if (this.getGame().getMm().getX() > p.getX() && this.getGame().getMm().getX() < p.getX() + 180
                && this.getGame().getMm().getY() > p.getY() && this.getGame().getMm().getY() < p.getY() + 120) {
            flag = true;
        }
        return flag;
    }
    
    public boolean mouseOverPlayer(){
        boolean flag = false;
        Point p = null;
        switch (this.activePlayer.getNumber()) {
            case 1:
                p = new Point(1, 1);
                break;
            case 2:
                p = new Point(1320, 0);
                break;
            case 3:
                p = new Point(0, 720);
                break;
            case 4:
                p = new Point(1320, 720);
                break;
            case 5:
                p = new Point(0, 361);
                break;
            case 6:
                p = new Point(1320, 361);
                break;
        }
        if (this.getGame().getMm().getX() > p.getX() && this.getGame().getMm().getX() < p.getX() + 600
                && this.getGame().getMm().getY() > p.getY() && this.getGame().getMm().getY() < p.getY() + 360) {
            flag = true;
        }
        return flag;
    }

    public void render(Graphics g) {
        for (Tile t : tiles) {
            if (t != null) {
                t.render(g);
            }
        }
        if (this.game.getState() != TI4V.State.FACTIONSELECTION) {
            renderPlayers(g);
            renderActivePlayer(g);
        }

        if (this.game.getState() == TI4V.State.SETUP) {
            renderSpeakerToken(g);
        }
    }

    public void renderSpeakerToken(Graphics g) {
        switch (speaker) {
            case 1:
                g.drawImage(st, 220, 155, null);
                break;
            case 2:
                g.drawImage(st, 1550, 155, null);
                break;
            case 3:
                g.drawImage(st, 220, 854, null);
                break;
            case 4:
                g.drawImage(st, 1550, 854, null);
                break;
            case 5:
                g.drawImage(st, 220, 495, null);
                break;
            case 6:
                g.drawImage(st, 1550, 495, null);
                break;
            default:
                break;
        }
    }

    public void renderPlayers(Graphics g) {
        g.setFont(font1);
        boolean flag = true;
        for (Player p : playerList) {
            if (p == null) {
                flag = false;
            }
        }
        if (flag == true) {
            playerColor(g, 0);
            if (playerList.get(0).getFaction() != null) {
                g.drawImage(pbg, 0, 0, null);
                g.drawImage(playerList.get(0).getFaction().getIcon2(), 0 + 192, 0 + 2, null);
                if (!allUnitsPlaced(0)) {

                }
                g.setFont(font2);
                g.drawString("! Unplaced units !", 12, 302);
            }
            g.drawString(playerList.get(0).identify() + ":", 50, 50);
            playerColor(g, 1);
            if (playerList.get(1).getFaction() != null) {
                g.drawImage(pbg, 1920 - 600, 0, null);
                g.drawImage(playerList.get(1).getFaction().getIcon2(), 1920 - 600 + 192, 0 + 2, null);
                if (!allUnitsPlaced(1)) {
                    g.setFont(font2);
                    g.drawString("! Unplaced units !", 1334, 302);
                }
            }
            g.drawString(playerList.get(1).identify() + ":", 1920 - 601 + 50, 50);
            playerColor(g, 2);
            if (playerList.get(2).getFaction() != null) {
                g.drawImage(pbg, 0, (1080 / 3 * 2), null);
                g.drawImage(playerList.get(2).getFaction().getIcon2(), 0 + 192, (1080 / 3 * 2) + 2, null);
                if (!allUnitsPlaced(2)) {
                    g.setFont(font2);
                    g.drawString("! Unplaced units !", 12, 1025);
                }
            }
            g.drawString(playerList.get(2).identify() + ":", 50, (1080 / 3 * 2) + 50);
            playerColor(g, 3);
            if (playerList.get(3).getFaction() != null) {
                g.drawImage(pbg, 1920 - 600, (1080 / 3 * 2), null);
                g.drawImage(playerList.get(3).getFaction().getIcon2(), 1920 - 600 + 192, (1080 / 3 * 2) + 2, null);
                if (!allUnitsPlaced(3)) {
                    g.setFont(font2);
                    g.drawString("! Unplaced units !", 1334, 1025);
                }
            }
            g.drawString(playerList.get(3).identify() + ":", 1920 - 601 + 50, (1080 / 3 * 2) + 50);
            if (players == 5 || players == 6) {
                playerColor(g, 4);
                if (playerList.get(4).getFaction() != null) {
                    g.drawImage(pbg, 0, (1080 / 3 * 1), null);
                    g.drawImage(playerList.get(4).getFaction().getIcon2(), 0 + 192, (1080 / 3 * 1) + 2, null);
                    if (!allUnitsPlaced(4)) {
//                        g.setFont(font2);
//                        g.drawString("! Unplaced units !", players, players);
                    }
                }
                g.drawString(playerList.get(4).identify() + ":", 50, (1080 / 3 * 1) + 50);
            }
            if (players == 6) {
                playerColor(g, 5);
                if (playerList.get(5).getFaction() != null) {
                    g.drawImage(pbg, 1920 - 600, (1080 / 3 * 1), null);
                    g.drawImage(playerList.get(5).getFaction().getIcon2(), 1920 - 600 + 192, (1080 / 3 * 1) + 2, null);
                    if (!allUnitsPlaced(5)) {
//                        g.setFont(font2);
//                        g.drawString("! Unplaced units !", players, players);
                    }

                }
                g.drawString(playerList.get(5).identify() + ":", 1920 - 601 + 50, (1080 / 3 * 1) + 50);
            }
        }
    }

    public boolean allUnitsPlaced(int player) {
        if (this.playerList.get(player).getUnplacedUnits().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void playerColor(Graphics g, int i) {
        if (this.playerList.get(i).getColor() != null) {
            String c = this.getPlayerList().get(i).getColor();
            switch (c) {
                case "Red":
                    g.setColor(Color.red);
                    break;
                case "Yellow":
                    g.setColor(Color.yellow);
                    break;
                case "Blue":
                    g.setColor(Color.cyan);
                    break;
                case "Green":
                    g.setColor(Color.green);
                    break;
                case "Purple":
                    g.setColor(new Color(87, 0, 127));
                    break;
                case "Black":
                    g.setColor(new Color(20, 20, 20));
                    break;
            }
        } else {
            g.setColor(Color.white);
        }
    }

    public void renderActivePlayer(Graphics g) {
        if (this.activePlayer != null) {
            g.drawImage(this.activePlayer.getFaction().getIcon(), 1920 / 2 - ac.getWidth() / 2 + 16, 8, null);
        }
        g.drawImage(ac, 1920 / 2 - ac.getWidth() / 2, 0, null);
    }
    //////////////////////////////////////GETTERS AND SETTERS///////////////////////

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public int getSpeaker() {
        return speaker;
    }

    public void setSpeaker(int speaker) {
        this.speaker = speaker;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public TI4V getGame() {
        return game;
    }

    public void setGame(TI4V game) {
        this.game = game;
    }

    public boolean isFactionsSelected() {
        return factionsSelected;
    }

    public void setFactionsSelected(boolean factionsSelected) {
        this.factionsSelected = factionsSelected;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Sprite getRedUnitIcons() {
        return redUnitIcons;
    }

    public void setRedUnitIcons(Sprite redUnitIcons) {
        this.redUnitIcons = redUnitIcons;
    }

    public Sprite getBlueUnitIcons() {
        return blueUnitIcons;
    }

    public void setBlueUnitIcons(Sprite blueUnitIcons) {
        this.blueUnitIcons = blueUnitIcons;
    }

    public Sprite getGreenUnitIcons() {
        return greenUnitIcons;
    }

    public void setGreenUnitIcons(Sprite greenUnitIcons) {
        this.greenUnitIcons = greenUnitIcons;
    }

    public Sprite getYellowUnitIcons() {
        return yellowUnitIcons;
    }

    public void setYellowUnitIcons(Sprite yellowUnitIcons) {
        this.yellowUnitIcons = yellowUnitIcons;
    }

    public Sprite getPurpleUnitIcons() {
        return purpleUnitIcons;
    }

    public void setPurpleUnitIcons(Sprite purpleUnitIcons) {
        this.purpleUnitIcons = purpleUnitIcons;
    }

    public Sprite getBlackUnitIcons() {
        return blackUnitIcons;
    }

    public void setBlackUnitIcons(Sprite blackUnitIcons) {
        this.blackUnitIcons = blackUnitIcons;
    }

    public BufferedImage getUnplacedBg() {
        return unplacedBg;
    }

    public ArrayList<Planet> getNeutralPlanets() {
        return neutralPlanets;
    }

    public void setNeutralPlanets(ArrayList<Planet> neutralPlanets) {
        this.neutralPlanets = neutralPlanets;
    }
}
