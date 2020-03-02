package guiltspawn.engine;

import guiltspawn.controls.KeyInput;
import guiltspawn.controls.MouseInput;
import guiltspawn.graphics.Death;
import guiltspawn.graphics.HUD;
import guiltspawn.graphics.Menu;
import guiltspawn.sound.SoundEffect;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.Instant;

public class GuiltSpawn extends Canvas implements Runnable {

    private Window window;
    private boolean running = false;
    private Thread thread;
    private int frequency = 60;
    private long targetTime = 1000 / frequency;
    private Graphics g;
    private VolatileImage volatileImg;

    private STATE currentState;
    private KeyInput ki;
    private MouseInput mi;
    private Handler h;
    private Camera cam;
    private Menu menu;
    private Death death;
    private HUD hud;

    private String[] output = new String[10];

    public static void main(String[] args) {
        new GuiltSpawn().run();
    }

    public enum STATE {
        MENU,
        GAME,
        DEATH
    };

    public void start() {
        if (running) {
            return;
        }
        this.running = true;
        this.thread = new Thread(this);
    }

    public void init() {
        //system stats
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available processing cores: " + cores);
        Instant now = Instant.now();
        System.out.println("Current instant is: " + now.toString());
        ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
        System.out.println("Number of threads: " + tmxb.getThreadCount());
        System.out.println("Number of daemon threads: " + tmxb.getDaemonThreadCount());
        long fm = Runtime.getRuntime().freeMemory();
        System.out.println("Amount of free memory on JVM: " + fm);
        long maxm = Runtime.getRuntime().maxMemory();
        System.out.println("Maximum amount of memory on JVM: " + maxm);
        
        
        //game stuff
        this.window = new Window("GuiltSpawn", this);

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        this.g = bs.getDrawGraphics();

        this.ki = new KeyInput(this);
        this.addKeyListener(ki);
        this.mi = new MouseInput(this);
        this.addMouseListener(mi);
        
        currentState = STATE.MENU;
        this.h = new Handler(this);
        this.cam = new Camera(0, 0, this);
        this.menu = new Menu(this);
        this.death = new Death();
        hud = new HUD(h.getPlayer());
        
        SoundEffect.init();
        SoundEffect.THEME.play(-1);
        this.requestFocus();
    }

    @Override
    public void run() {
        
        start();
        init();

        long start;
        long elapsed;
        long wait;

        while (running) {

            start = System.nanoTime();

            tick();
            renderOnScreen(g);

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
            }
        }
    }

    public void tick() {
        if (currentState == STATE.MENU) {
            menu.tick();
        } else if (currentState == STATE.GAME) {
            h.tick();
            cam.tick();
        } else if (currentState == STATE.DEATH) {

        }
    }

    public void renderOnScreen(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        
        g = bs.getDrawGraphics();
        //Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, window.getScreen().width, window.getScreen().height);

        //////////////////////////////DRAW HERE/////////////////////////////////
        
        if(currentState == STATE.MENU){
            menu.render(g);
        } else if(currentState == STATE.GAME){
            
            ///////////////////////////BEGIN CAMERA/////////////////////////////
            g2d.translate(cam.getX(), cam.getY());
            
            h.render(g);
            
            g2d.translate(-(cam.getX()), -(cam.getY()));
            ////////////////////////////END CAMERA//////////////////////////////
            hud.render(g);
            if(h.getLoading()){
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1920, 1080);
            }
            
        } else if(currentState == STATE.DEATH){
            death.render(g);
        }

        ////////////////////////////END DRAW HERE///////////////////////////////
        g.dispose();
        g2d.dispose();
        bs.show();

    }

    public void render(Graphics g) {
        createBackBuffer();
        do {
            GraphicsConfiguration gc = this.getGraphicsConfiguration();
            int valCode = volatileImg.validate(gc);

            if (valCode == VolatileImage.IMAGE_INCOMPATIBLE) {
                createBackBuffer();
            }

            Graphics offscreenG = volatileImg.getGraphics();
            offscreenG.setColor(Color.WHITE);
            offscreenG.fillRect(0, 0, getWidth(), getHeight());
            offscreenG.setColor(Color.BLACK);
            offscreenG.drawLine(0, 0, 100, 100);
            g.drawImage(volatileImg, 0, 0, this);

        } while (volatileImg.contentsLost());
    }

    public void createBackBuffer() {
        GraphicsConfiguration gc = getGraphicsConfiguration();
        volatileImg = gc.createCompatibleVolatileImage(getWidth(), getHeight());
    }

    public void addOutput(String s) {
        moveOutput();
        output[0] = s;
        System.out.println(s);
    }

    public void moveOutput() {
        for (int i = 8; i >= 0; i--) {
            output[i + 1] = output[i];
        }
    }
    
    

//    public void drawOutput(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRect(40, 240, 150, 150);
//        g.setFont(new Font("Calibri", Font.PLAIN, 12));
//        g.setColor(Color.GREEN);
//        for (int i = 0; i < 10; i++) {
//            if (output[i] != null) {
//                g.drawString("- " + output[i], 50, 250 + (15 * (9 - i)));
//            }
//        }
//    }

    ///////////////////////////////GETTERS AND SETTERS//////////////////////////
    public STATE getCurrentState() {
        return currentState;
    }

    public void setCurrentState(STATE currentState) {
        this.currentState = currentState;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Handler getHandler() {
        return h;
    }

    public void setHandler(Handler h) {
        this.h = h;
    }

    public Menu getMenu() {
        return menu;
    }

    public Camera getCam() {
        return cam;
    }

}
