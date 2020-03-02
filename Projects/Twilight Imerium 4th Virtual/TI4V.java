package ti4v;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TI4V extends Canvas implements Runnable {

    private boolean running = false;
    private int targetTime = 17;
    private int downTime = 0;
    private Font font1 = new Font("Arial", Font.PLAIN, 12);
    private Graphics g;
    private State state = State.MAINMENU;

    private KeyInput ki;
    private MouseInput mi;
    private MouseMovement mm;
    private Window w;
    private Mainmenu m;
    private int players = 4;
    private Room r;
    private Handler h;
    private FactionSelection factionSelection;

    enum State {
        MAINMENU,
        SETUP,
        FACTIONSELECTION,
        STRATEGY,
        ACTION,
        STATUS,
        AGENDA,
        PLAYERHUD,
        UNPLACED
    }

    public static void main(String[] args) {
        TI4V game = new TI4V();
        game.start();
    }

    public void start() {
        if (!running) {
            running = true;
        }
        init();
        run();
    }

    public void init() {
        this.ki = new KeyInput();
        this.mi = new MouseInput(this);
        this.mm = new MouseMovement();
        this.addKeyListener(ki);
        this.addMouseListener(mi);
        this.addMouseMotionListener(mm);
        this.w = new Window("Twilight Imperium Virtual", this);
        this.m = new Mainmenu(this);
        this.r = new Room();
        this.requestFocus();
    }

    @Override
    public void run() {

        long start;
        long elapsed;
        long wait;

        while (running) {

            start = System.nanoTime();

            update();
            render();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                System.out.println("Victor Mike");
            }
        }
    }

    public void update() {
        downTimeSpend();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();
        Graphics g = bs.getDrawGraphics();

        //white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w.getScreen().width, w.getScreen().height);

        //////////////////////////////DRAW HERE/////////////////////////////////
        if (this.state == State.MAINMENU) {
            m.render(g);
        } else if (this.state == State.SETUP || this.state == State.FACTIONSELECTION || this.state == State.UNPLACED || this.state == State.PLAYERHUD) {
            r.render(g);
            h.render(g);
            if (this.state == State.FACTIONSELECTION) {
                factionSelection.render(g);
            }
            if (this.state == State.UNPLACED) {
                this.getH().getActivePlayer().renderUnplaced(g);
            }
            if (this.state == State.PLAYERHUD){
                this.getH().getActivePlayer().renderHud(g);
            }
        }

        //Cursor location on screen
        g.setColor(Color.green);
        g.setFont(font1);
        g.drawString("" + this.getMm().getX() + ", " + this.getMm().getY(), 20, 20);
        ////////////////////////////END DRAW HERE///////////////////////////////
        g.dispose();
        bs.show();
    }

    public void downTimeSpend() {
        if (downTime > 0) {
            downTime--;
        }
    }

    public void wait(int down) {
        this.setDownTime(down);
        while (this.getDownTime() > 0) {
            System.out.print("");
        }
    }

//////////////////////////////////GETTERS AND SETTERS///////////////////////////
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(int targetTime) {
        this.targetTime = targetTime;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        System.out.println("Game changed to state: " + state);
        this.state = state;
    }

    public KeyInput getKi() {
        return ki;
    }

    public void setKi(KeyInput ki) {
        this.ki = ki;
    }

    public MouseInput getMi() {
        return mi;
    }

    public void setMi(MouseInput mi) {
        this.mi = mi;
    }

    public MouseMovement getMm() {
        return mm;
    }

    public void setMm(MouseMovement mm) {
        this.mm = mm;
    }

    public Window getW() {
        return w;
    }

    public void setW(Window w) {
        this.w = w;
    }

    public Mainmenu getM() {
        return m;
    }

    public void setM(Mainmenu m) {
        this.m = m;
    }

    public Room getR() {
        return r;
    }

    public void setR(Room r) {
        this.r = r;
    }

    public Handler getH() {
        return h;
    }

    public void setH(Handler h) {
        this.h = h;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getDownTime() {
        return downTime;
    }

    public void setDownTime(int downTime) {
        this.downTime = downTime;
    }

    public FactionSelection getFactionSelection() {
        return factionSelection;
    }

    public void setFactionSelection(FactionSelection factionSelection) {
        this.factionSelection = factionSelection;
    }

}
