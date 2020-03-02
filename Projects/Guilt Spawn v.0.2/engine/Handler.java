package guiltspawn.engine;

import guiltspawn.entity.Enemy;
import guiltspawn.entity.MapObject;
import guiltspawn.entity.Player;
import guiltspawn.graphics.Level;
import guiltspawn.sound.SoundEffect;
import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

    private GuiltSpawn game;
    private int currentLevel;
    private int currentRoom;
    private int spawnPoint;
    private int spawnsFound;
    private int minX = 2048;
    private int maxX = 2048;
    private boolean loading = false;
    private boolean readyToLoadLevel = false;
    private TileMap tm;
    private Level level;
    private Player player;
    private MapObject[][] blocks;
    private final ArrayList<MapObject> toUpdate = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Handler(GuiltSpawn game) {
        this.game = game;
        tm = new TileMap(game, 32);
        this.player = new Player(tm);
        player.setPosition(500, 500);
        currentLevel = 1;
        currentRoom = 1;
        spawnPoint = 0;
    }

    public void loadLevel() {
        loading = true;
        minX = 2048;
        maxX = 2048;
        spawnsFound = 0;
        resetLevel();
        level = new Level(currentLevel, currentRoom, this);
        tm.loadMap(level, currentLevel, currentRoom);
        blocks = tm.getMap();
        tm.setPosition(0, 0);
        tm.setTween(1);
        level.countDistance(minX, maxX);
        loading = false;
        readyToLoadLevel = false;
    }

    public void resetLevel() {
        if (blocks != null) {
            for (int i = 0; i < tm.getNumRows(); i++) {
                for (int j = 0; j < tm.getNumCols(); j++) {
                    blocks[j][i] = null;
                }
            }
            clearUpdateList();
            clearEnemyList();
        }
    }

    public void addToUpdate(MapObject o) {
        this.toUpdate.add(o);
    }

    public void clearUpdateList() {
        if (loading) {
            toUpdate.clear();
        }
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void clearEnemyList() {
        if (loading) {
            enemies.clear();
        }
    }

    public void tick() {
        if (!loading) {
            player.tick();
            tm.setPosition(game.getWidth() / 2 - player.getX(), game.getHeight() / 2 - player.getY());
            tickToUpdate();
            updateEnemies();
            level.tick();
        }
        if (readyToLoadLevel) {
            loadLevel();
        }
    }

    public void tickToUpdate() {
        if (loading) {
            return;
        }
        ArrayList<MapObject> temp = toUpdate;
        for (MapObject o : temp) {
            if (loading && temp.size() != 0) {
                break;
            }
            o.tick();
        }
    }

    public void updateEnemies() {
        ArrayList<Enemy> living = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
            if (!enemies.get(i).getDead()) {
                living.add(enemies.get(i));
            }
        }
        enemies = living;
    }

    public void gameOver() {
        SoundEffect.MUSIC1.stop();
        game.setCurrentState(GuiltSpawn.STATE.DEATH);
        player.reset();
    }

    public void render(Graphics g) {
        if (loading) {
            return;
        }
        level.renderBack(g);
        renderBlocks(g);
        renderEnemies(g);
        player.render(g);
        level.renderFront(g);
        //level.renderEffects(g);
    }

    public void renderBlocks(Graphics g) {
        if (blocks != null) {
            for (int row = tm.getRowOffset(); row < tm.getRowOffset() + tm.getNumRowsToDraw(); row++) {
                if (row >= tm.getNumRows()) {
                    break;
                }
                for (int col = tm.getColOffset(); col < tm.getColOffset() + tm.getNumColsToDraw(); col++) {
                    if (col >= tm.getNumCols()) {
                        break;
                    }
//                if (tm.getMap()[col][row] == 0) {
//                    continue;
//                }
                    if (blocks[col][row] != null) {
                        blocks[col][row].render(g);
                    }
                }
            }
        }
    }

    public void renderEnemies(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
    }

    ///////////////////////GETTERS AND SETTERS//////////////////////////////////
    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public GuiltSpawn getGame() {
        return game;
    }

    public void setGame(GuiltSpawn game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TileMap getTm() {
        return tm;
    }

    public void setTm(TileMap tm) {
        this.tm = tm;
    }

    public int getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(int spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public int getSpawnsFound() {
        return spawnsFound;
    }

    public void setSpawnsFound(int spawnsFound) {
        this.spawnsFound = spawnsFound;
    }

    public boolean getLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isReadyToLoadLevel() {
        return readyToLoadLevel;
    }

    public void setReadyToLoadLevel(boolean readyToLoadLevel) {
        this.readyToLoadLevel = readyToLoadLevel;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }
    
}
