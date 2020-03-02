package guiltspawn.engine;

import guiltspawn.entity.Block;
import guiltspawn.entity.E1;
import guiltspawn.entity.E2;
import guiltspawn.entity.E3;
import guiltspawn.entity.Enemy;
import guiltspawn.entity.Flag;
import guiltspawn.entity.MapObject;
import guiltspawn.graphics.Level;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TileMap {

    private final GuiltSpawn game;

    //position
    private double x, y;

    //bounds
    private int xmin, ymin, xmax, ymax;
    private double tween;

    //map
    private MapObject[][] map;
    private int tileSize, numRows, numCols, width, height;

    //draw
    private int rowOffset, colOffset, numRowsToDraw, numColsToDraw;

    //Random
    private Random r;

    public TileMap(GuiltSpawn game, int tileSize) {
        r = new Random();
        this.game = game;
        this.tileSize = tileSize;
        numRowsToDraw = game.getHeight() / tileSize + 2;
        numColsToDraw = game.getWidth() / tileSize + 2;
        tween = 0.7;
    }

    public void loadMap(Level level, int currentLevel, int currentRoom) {
        try {
            BufferedImage img = level.getMap();
            numCols = img.getWidth();
            numRows = img.getHeight();
            map = new MapObject[numCols][numRows];
            width = numCols * tileSize;
            height = numRows * tileSize;
            xmin = game.getWidth() - (width);
            xmax = 0;
            ymin = game.getHeight() - (height);
            ymax = 0;
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    int pixel = img.getRGB(col, row);
                    pixelRGB(pixel, col, row);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while loading map; Level:" + level + "-" + currentRoom);
        }
    }

    public void printMap() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(map[col][row] + " ");
            }
            System.out.println("");
        }
    }

    public void pixelRGB(int pixel, int x, int y) {
        //int alpha = (pixel >> 24) & 0xff;
        int r = (pixel >> 16) & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = (pixel) & 0xff;
        build(r, g, b, x, y);
    }

    public void build(int r, int g, int b, int x, int y) {
        if (r == 255 && g == 255 && b == 255) {                                                 //floor
            map[x][y] = new Block(this, x * getTileSize(), y * getTileSize(), 1);
        } else if (g == 250 || g == 251 || g == 252 || g == 253 || g == 254) {                  //flag
            Flag f = new Flag(this, r, b);
            map[x][y] = f;
            f.setX(x);
            f.setY(y);
            f.setSpawnPoint(g - 250);
            game.getHandler().addToUpdate(f);
            
        } else if (r == 0 && g == 0 && b == 253) {                                              //spawn
            if (game.getHandler().getSpawnsFound() != game.getHandler().getSpawnPoint()) {
                game.getHandler().setSpawnsFound(game.getHandler().getSpawnsFound() + 1);
            } else {
                game.getHandler().getPlayer().setX(x * 32);
                game.getHandler().getPlayer().setY(y * 32);
                game.getHandler().setSpawnsFound(game.getHandler().getSpawnsFound() + 1);
            }
        } else if (r == 255 && g == 0 && b == 0) {                                               //enemy 1
            Enemy e = new E1(this);
            e.setX(x * tileSize);
            e.setY(y * tileSize);
            game.getHandler().addEnemy(e);
        } else if (r == 255 && g == 0 && b == 1) {                                               //enemy 2
            Enemy e2 = new E2(this);
            e2.setX(x * tileSize);
            e2.setY(y * tileSize);
            game.getHandler().addEnemy(e2);
        } else if (r == 255 && g == 0 && b == 2){                                                //enemy 3
            Enemy e3 = new E3(this);
            e3.setX(x * tileSize);
            e3.setY(y * tileSize);
            game.getHandler().addEnemy(e3);
        } else if (r == 240 && g == 240 && b == 240){
            game.getHandler().setMinX(x * 32 + 40);
        } else if (r == 240 && g == 240 && b == 241){
            game.getHandler().setMaxX(x * 32);
        }
        
//        else {                                                                                //empty
//            map[x][y] = new Block(this, x * getTileSize(), y * getTileSize(), 0);
//        }
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;
        fixBounds();
        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }

    public void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    //////////////////////////GETTERS AND SETTERS///////////////////////////////
    public MapObject[][] getMap() {
        return map;
    }

    public void setMap(MapObject[][] map) {
        this.map = map;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType(int row, int col) {
        if (map != null) {
            if (map[col][row] instanceof Block) {
                return ((Block) (map[col][row])).getType();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(int rowOffset) {
        this.rowOffset = rowOffset;
    }

    public int getNumRowsToDraw() {
        return numRowsToDraw;
    }

    public void setNumRowsToDraw(int numRowsToDraw) {
        this.numRowsToDraw = numRowsToDraw;
    }

    public int getNumColsToDraw() {
        return numColsToDraw;
    }

    public void setNumColsToDraw(int numColsToDraw) {
        this.numColsToDraw = numColsToDraw;
    }

    public int getColOffset() {
        return colOffset;
    }

    public void setColOffset(int colOffset) {
        this.colOffset = colOffset;
    }

    public GuiltSpawn getGame() {
        return game;
    }

    public double getTween() {
        return tween;
    }

    public void setTween(double tween) {
        this.tween = tween;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

}
