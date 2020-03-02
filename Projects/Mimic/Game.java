
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

// Luodaan kaikki tarvittavat muuttujat.
    private Thread thread;
    private boolean running = false;
    private int frequence = 30;
    private long goal = 1000 / frequence;

    private Window window;
    private Menu menu;
    private Bg bg;
    private Target ball;
    private Hud points;
    private Random r;
    private STATE currentState = STATE.Menu;
    private Summary score;
    private Records records;
    private Instructions inst;
    private KeyInput ki;

    enum STATE {
        Menu,
        Game,
        End,
        Scoreboard,
        Instructions
    };

    //Luo käskypinon Game oliolle ja käynnistää sen.
    public synchronized void start() {
        if (running) {
            return;
        } else {
            running = true;
            thread = new Thread(this);
            System.out.println("Starting new thread");
        }
    }

    //Luodaan valmiiksi kaikki, mitä tarvitaan ohjelman pyörittämiseen.
    public void init() {
        this.window = new Window("MIMIC", this);
        this.menu = new Menu(this);
        r = new Random();
        bg = new Bg(this, r);
        points = new Hud(this);
        ball = new Target(points);
        this.addMouseListener(new MouseInput(this));
        this.addMouseMotionListener(new MouseMovement(ball, points));
        ki = new KeyInput(this);
        this.addKeyListener(ki);
        score = new Summary(this);
        records = new Records(this);
        this.inst = new Instructions(this);
        if (records.checkSaveFile()) {
            records.formate();
            records.save();
        } else {
            records.read();
        }
        this.requestFocus();
    }

    //Ohjelma pyörii ja kutsutaan aloitusta ja kutsutaan init() -metodia jolla luodaan tarvittavat oliot
    @Override
    public void run() {
        start();
        init();

        while (running) {

            //Loopin muuttujat
            long start;
            long last;
            long left;

            //Monitorointi
            long counter = 0;
            long round = 0;
            int second = 0;

            //Tässä on varsinainen Game-loop
            while (running) {

                start = System.nanoTime();

                update();
                render();
                round++;

                last = System.nanoTime() - start;

                left = goal - last / 1000000;
                if (left < 0) {
                    left = 5;
                }

                try {
                    Thread.sleep(left);
                } catch (Exception e) {
                    System.out.println("wait failed");
                }

                //Monitorointi
                counter += goal;
                if (counter >= 1000) {
                    second++;
                    System.out.println(second + ": " + round + " kierrosta");
                    round = 0;
                    counter = 0;
                }

            }
        }

        //Suljetaan ohjelman suoritus
        System.exit(0);
    }

    public void update() {
        if (currentState == STATE.Game) {
            updateGame();
        } else if (currentState == STATE.End) {
            updateEnd();
        }
    }

    public void updateGame() {
        bg.update();
        ball.update();
        points.update();
    }

    public void updateEnd() {
        score.update();
    }

    public void render() {

        //Graiikka-olion alustus
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //Tässä piirretään aloitussivu
        if (currentState == STATE.Menu) {
            renderMenu(g);
        } else if (currentState == STATE.Game) {
            renderGame(g);
        } else if (currentState == STATE.End) {
            renderEnd(g);
        } else if (currentState == STATE.Scoreboard) {
            renderScoreboard(g);
        } else if (currentState == STATE.Instructions){
            renderInstructions(g);
        }
        //Piirto loppuu tässä vaiheessa ja piirto tuodaan näkyviin

        g.dispose();
        bs.show();
    }

    public void renderMenu(Graphics g) {
        menu.render(g);
    }

    public void renderGame(Graphics g) {
        bg.render(g);
        ball.render(g);
        points.render(g);
    }

    public void renderScoreboard(Graphics g) {
        records.render(g);
    }

    public void renderEnd(Graphics g) {
        score.render(g);
    }
    
    public void renderInstructions(Graphics g){
        inst.render(g);
    }
    
    public void updateGoal(){
        this.goal = 1000 / frequence;
    }

    public static void main(String[] args) {
        new Game().run();
    }

    public int getTicks() {
        return this.frequence;
    }

    public STATE getCurrentState() {
        return currentState;
    }

    public void setCurrentState(STATE currentState) {
        this.currentState = currentState;
    }

    public Hud getHud() {
        return this.points;
    }

    public Records getRecords() {
        return this.records;
    }

    public KeyInput getKeyInput() {
        return this.ki;
    }

    public Summary getSummary() {
        return this.score;
    }
    
    public Window getWindow(){
        return this.window;
    }
    
    public int getW(){
        return this.getWidth();
    }

    public int getH(){
        return this.getHeight();
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

}
