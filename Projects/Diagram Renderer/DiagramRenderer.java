package diagramrenderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class DiagramRenderer extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    private Window window;
    private int frequency = 60;
    private long targetTime = 1000 / frequency;
    private KeyInput ki;
    private int currentRender = 1;

    public static void main(String[] args) {
        new DiagramRenderer().run();
    }

    public void start() {
        if (running) {
            return;
        }
        this.running = true;
        this.thread = new Thread(this);
    }

    public void init() {
        this.window = new Window(this);
        this.ki = new KeyInput(this);
        this.addKeyListener(ki);
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

            render();

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

        System.exit(0);
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        //DRAW HERE

        if (currentRender == 1) {
            r1(g);
        } else if (currentRender == 2) {
            r2(g);
        } else if (currentRender == 3) {
            r3(g);
        } else if (currentRender == 4) {
            r4(g);
        } else if (currentRender == 5) {
            r5(g);
        } else if (currentRender == 6) {
            r6(g);
        }

        //END DRAW
        g.dispose();
        g.dispose();
        bs.show();
    }

    public void r0(Graphics g) {
        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 24);

        String[] s1 = new String[6];
        s1[0] = "EK";
        s1[1] = "STTK";
        s1[2] = "Akava";
        s1[3] = "Pardia";
        s1[4] = "Tehy";
        s1[5] = "PAM";

        int[] v1 = new int[6];
        v1[0] = 9;
        v1[1] = 11;
        v1[2] = 7;
        v1[3] = 12;
        v1[4] = 28;
        v1[5] = 16;

        int[] v2 = new int[6];
        v2[0] = 38;
        v2[1] = 25;
        v2[2] = 18;
        v2[3] = 13;
        v2[4] = 43;
        v2[5] = 45;

        g.setColor(Color.GRAY);
        g.setFont(f1);
        g.drawString("Naisten ja miesten kokema seksuaalinen häirintä", 420, 90);

        g.setFont(f2);
        for (int i = 0; i < 11; i++) {
            g.drawString("" + (50 - (i * 5)), 40, 156 + (i * 70));
            g.drawLine(100, 150 + (i * 70), 1870, 150 + (i * 70));
        }
        g.setFont(f2);
        for (int k = 0; k < 6; k++) {
            //1770 / 6 * k + 50
            g.setColor(Color.GRAY);
            g.drawString(s1[k], 150 + (1770 / 6 * k) + 50, 900);
            g.setColor(new Color(189, 73, 69));
            g.fillRect(155 + (1770 / 6 * k) + 50, 850, 15, (int) -(70.0 / 5 * v1[k]));
            g.setColor(new Color(150, 184, 81));
            g.fillRect(172 + (1770 / 6 * k) + 50, 850, 15, (int) -(70.0 / 5 * v2[k]));
        }

        g.setColor(new Color(189, 73, 69));
        g.fillRect(800, 972, 20, 20);
        g.setColor(Color.GRAY);
        g.drawString("miehet", 830, 990);

        g.setColor(new Color(150, 184, 81));
        g.fillRect(920, 972, 20, 20);
        g.setColor(Color.GRAY);
        g.drawString("naiset", 950, 990);

    }

    public void r1(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 24);

        String[] s1 = new String[6];
        s1[0] = "EK";
        s1[1] = "STTK";
        s1[2] = "Akava";
        s1[3] = "Pardia";
        s1[4] = "Tehy";
        s1[5] = "PAM";

        int[] v1 = new int[6];
        v1[0] = 9;
        v1[1] = 11;
        v1[2] = 7;
        v1[3] = 12;
        v1[4] = 28;
        v1[5] = 16;

        int[] v2 = new int[6];
        v2[0] = 38;
        v2[1] = 25;
        v2[2] = 18;
        v2[3] = 13;
        v2[4] = 43;
        v2[5] = 45;

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Naisten ja miesten kokema seksuaalinen häirintä", 420, 90);

        g.setFont(f2);

        for (int i = 0; i < 11; i++) {
            g.drawString("" + (50 - (i * 5)), 40, 156 + (i * 70));
            g.drawLine(100, 150 + (i * 70), 1870, 150 + (i * 70));
        }

        for (int k = 0; k < 6; k++) {

            g.setColor(Color.BLACK);
            g.drawString(s1[k], 155 + (1770 / 6 * k) + 50, 900);
            g.setColor(new Color(0, 0, 0));
            g.fillRect(155 + (1770 / 6 * k) + 50, 850, 15, (int) -(70.0 / 5 * v1[k]));
            g.setColor(new Color(100, 100, 100));
            g.fillRect(172 + (1770 / 6 * k) + 50, 850, 15, (int) -(70.0 / 5 * v2[k]));
        }

        g.setColor(new Color(0, 0, 0));
        g.fillRect(820, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("miehet", 850, 990);

        g.setColor(new Color(100, 100, 100));
        g.fillRect(940, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("naiset", 970, 990);

    }

    public void r2(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 24);

        String[] s1 = new String[6];
        s1[0] = "EK";
        s1[1] = "STTK";
        s1[2] = "Akava";
        s1[3] = "Pardia";
        s1[4] = "Tehy";
        s1[5] = "PAM";

        int[] v1 = new int[6];
        v1[0] = 50;
        v1[1] = 43;
        v1[2] = 27;
        v1[3] = 29;
        v1[4] = 82;
        v1[5] = 38;

        int[] v2 = new int[6];
        v2[0] = 26;
        v2[1] = 39;
        v2[2] = 68;
        v2[3] = 55;
        v2[4] = 22;
        v2[5] = 0;

        int[] v3 = new int[6];
        v3[0] = 26;
        v3[1] = 9;
        v3[2] = 21;
        v3[3] = 30;
        v3[4] = 2;
        v3[5] = 0;

        int[] v4 = new int[6];
        v4[0] = 9;
        v4[1] = 9;
        v4[2] = 20;
        v4[3] = 11;
        v4[4] = 34;
        v4[5] = 0;

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Kenen taholta seksuaalista häirintää tapahtuu?", 442, 90);

        g.setFont(f2);
        for (int i = 0; i < 10; i++) {
            g.drawString("" + (90 - (i * 10)), 40, 156 + (i * 78));
            g.drawLine(100, 150 + (i * 78), 1870, 150 + (i * 78));
        }
        g.setFont(f2);
        for (int k = 0; k < 6; k++) {

            g.setColor(Color.BLACK);
            g.drawString(s1[k], 155 + (1770 / 6 * k) + 50, 900);
            g.setColor(new Color(0, 0, 0));
            g.fillRect(155 + (1770 / 6 * k) + 50, 852, 15, (int) -(78.0 / 10 * v1[k]));
            g.setColor(new Color(50, 50, 50));
            g.fillRect(172 + (1770 / 6 * k) + 50, 852, 15, (int) -(78.0 / 10 * v2[k]));
            g.setColor(new Color(100, 100, 100));
            g.fillRect(189 + (1770 / 6 * k) + 50, 852, 15, (int) -(78.0 / 10 * v3[k]));
            g.setColor(new Color(150, 150, 150));
            g.fillRect(206 + (1770 / 6 * k) + 50, 852, 15, (int) -(78.0 / 10 * v4[k]));
        }

        g.setColor(new Color(0, 0, 0));
        g.fillRect(680, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("asiakas", 710, 990);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(810, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("työtoveri", 840, 990);

        g.setColor(new Color(100, 100, 100));
        g.fillRect(940, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("esimies", 970, 990);

        g.setColor(new Color(150, 150, 150));
        g.fillRect(1070, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("muu", 1100, 990);

    }

    public void r3(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 24);

        String[] s1 = new String[5];
        s1[0] = "STTK";
        s1[1] = "Akava";
        s1[2] = "Pardia";
        s1[3] = "Tehy";
        s1[4] = "PAM";

        int[] v1 = new int[5];
        v1[0] = 21;
        v1[1] = 50;
        v1[2] = 43;
        v1[3] = 54;
        v1[4] = 65;

        int[] v2 = new int[5];
        v2[0] = 79;
        v2[1] = 50;
        v2[2] = 57;
        v2[3] = 46;
        v2[4] = 35;

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Tietoisuus seksuaaliseen häirintään puuttumisen ohjeista työpaikalla", 200, 90);

        g.setFont(f2);
        for (int i = 0; i < 10; i++) {
            g.drawString("" + (90 - (i * 10)), 40, 156 + (i * 78));
            g.drawLine(100, 150 + (i * 78), 1870, 150 + (i * 78));
        }
        g.setFont(f2);
        for (int k = 0; k < 5; k++) {

            g.setColor(Color.BLACK);
            g.drawString(s1[k], 155 + (1770 / 5 * k) + 50, 900);
            g.setColor(new Color(0, 0, 0));
            g.fillRect(155 + (1770 / 5 * k) + 50, 852, 15, (int) -(78.0 / 10 * v1[k]));
            g.setColor(new Color(100, 100, 100));
            g.fillRect(172 + (1770 / 5 * k) + 50, 852, 15, (int) -(78.0 / 10 * v2[k]));
        }

        g.setColor(new Color(0, 0, 0));
        g.fillRect(820, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("ei tiedä", 850, 990);

        g.setColor(new Color(100, 100, 100));
        g.fillRect(940, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("tietää", 970, 990);

    }

    public void r4(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 30);
        Font f3 = new Font("Arial", Font.PLAIN, 24);
        Font f4 = new Font("Arial", Font.PLAIN, 20);

        String[] s1 = new String[7];
        s1[0] = "Normaalityöpäivänä tavanomaisena työaikana";
        s1[1] = "Työhön liittyvissä epävirallisissa tilaisuuksissa (esim. pikkujoulut)";
        s1[2] = "Ollessani kahden häiritsijän kanssa (esim. palaverissa)";
        s1[3] = "Poikkeuksillisina työaikoina (esim. iltaisin tai viikonloppuisin)";
        s1[4] = "Työmatkoilla";
        s1[5] = "Työhön liittyvissä virallisissa tilaisuuksissa";
        s1[6] = "Muussa tilanteessa";

        int[] v1 = new int[7];
        v1[0] = 56;
        v1[1] = 23;
        v1[2] = 11;
        v1[3] = 11;
        v1[4] = 9;
        v1[5] = 8;
        v1[6] = 3;

        int[] v2 = new int[7];
        v2[0] = 77;
        v2[1] = 25;
        v2[2] = 18;
        v2[3] = 12;
        v2[4] = 7;
        v2[5] = 7;
        v2[6] = 5;

        int[] v3 = new int[7];
        v3[0] = 72;
        v3[1] = 24;
        v3[2] = 16;
        v3[3] = 12;
        v3[4] = 8;
        v3[5] = 8;
        v3[6] = 5;

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Eri häirintätilanteiden osuudet viimeisen kahden vuoden aikana", 240, 90);

        g.setFont(f2);
        g.drawRect(960, 170, 910, 745);

        for (int j = 0; j < 5; j++) {
            g.drawString("" + (0 + (j * 20)) + "%", 940 + ((910 / 4) * j), 150);
            if (j < 4) {
                g.drawLine(960 + ((910 / 4) * j), 170, 960 + ((910 / 4) * j), (170 + 745));
            }
        }

        for (int k = 0; k < 7; k++) {

            g.setColor(Color.BLACK);
            g.setFont(f2);
            g.drawString(s1[k], 940 - g.getFontMetrics().stringWidth(s1[k]), 230 + (int) (745.0 / 7 * k));

            g.setColor(new Color(0, 0, 0));
            g.setFont(f2);
            g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)), (int) (908 * (v1[k] / 80.0)), (int) (745.0 / 35));
            g.setFont(f4);
            g.drawString("" + v1[k] + "%", (int) (908 * (v1[k] / 80.0) + 971), (int) (187 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));

            g.setColor(new Color(70, 70, 70));
            g.setFont(f2);
            if (k <= 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2) - 1), (int) (908 * (v2[k] / 80.0)), (int) (745.0 / 35));
            } else if (k > 3 && k < 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2)), (int) (908 * (v2[k] / 80.0)), (int) (745.0 / 35));
            } else if (k == 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2) - 1), (int) (908 * (v2[k] / 80.0)), (int) (745.0 / 35));
            }

            g.setFont(f4);
            g.drawString("" + v2[k] + "%", (int) (908 * (v2[k] / 80.0) + 971), (int) (208 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));

            g.setColor(new Color(120, 120, 120));
            g.setFont(f2);
            if (k < 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 1), (int) (908 * (v3[k] / 80.0)), (int) (745.0 / 35));
            } else if (k == 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 2), (int) (908 * (v3[k] / 80.0)), (int) (745.0 / 35));
            } else if (k > 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 3), (int) (908 * (v3[k] / 80.0)), (int) (745.0 / 35));
            }

            g.setFont(f4);
            g.drawString("" + v3[k] + "%", (int) (908 * (v3[k] / 80.0) + 971), (int) (229 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));
        }

        g.setFont(f3);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(685, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("mies", 715, 990);
        g.drawRect(665, 960, 482, 43);

        g.setColor(new Color(70, 70, 70));
        g.fillRect(845, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("nainen", 875, 990);

        g.setColor(new Color(120, 120, 120));
        g.fillRect(1005, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("yhteensä", 1035, 990);
    }

    public void r5(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.PLAIN, 50);
        Font f2 = new Font("Arial", Font.PLAIN, 30);
        Font f3 = new Font("Arial", Font.PLAIN, 24);
        Font f4 = new Font("Arial", Font.PLAIN, 20);

        String[] s1 = new String[7];
        s1[0] = "Ajattelin, ettei asiaa kuitenkaan oteta vakavasti";
        s1[1] = "Ajattelin, että ilmoittamisesta voisi koitua minulle hankaluuksia työssäni";
        s1[2] = "Olin häpeissäni tapahtuneesta";
        s1[3] = "En pitänyt asiaa vakavana";
        s1[4] = "Ajattelin, että todisteet eivät riitä";
        s1[5] = "Ajattelin, että ilmoittaminen voisi haitata etenemistä urallani";
        s1[6] = "Muu syy";

        int[] v1 = new int[7];
        v1[0] = 46;
        v1[1] = 31;
        v1[2] = 16;
        v1[3] = 14;
        v1[4] = 13;
        v1[5] = 7;
        v1[6] = 26;

        int[] v2 = new int[7];
        v2[0] = 45;
        v2[1] = 33;
        v2[2] = 15;
        v2[3] = 14;
        v2[4] = 12;
        v2[5] = 8;
        v2[6] = 28;

        int[] v3 = new int[7];
        v3[0] = 54;
        v3[1] = 22;
        v3[2] = 19;
        v3[3] = 12;
        v3[4] = 18;
        v3[5] = 0;
        v3[6] = 13;

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Miksi et ilmoittanut kokemastasi häirinnästä?", 450, 90);

        g.setFont(f2);
        g.drawRect(960, 170, 910, 745);

        for (int j = 0; j < 7; j++) {
            g.drawString("" + (0 + (j * 10)) + "%", 940 + ((910 / 6) * j), 150);
            if (j < 6) {
                g.drawLine(960 + ((910 / 6) * j), 170, 960 + ((910 / 6) * j), (170 + 745));
            }
        }

        for (int k = 0; k < 7; k++) {

            g.setColor(Color.BLACK);
            g.setFont(f2);
            g.drawString(s1[k], 940 - g.getFontMetrics().stringWidth(s1[k]), 230 + (int) (745.0 / 7 * k));

            g.setColor(new Color(0, 0, 0));
            g.setFont(f2);
            g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)), (int) (908 * (v1[k] / 60.0)), (int) (745.0 / 35));
            g.setFont(f4);
            g.drawString("" + v1[k] + "%", (int) (908 * (v1[k] / 60.0) + 971), (int) (187 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));

            g.setColor(new Color(70, 70, 70));
            g.setFont(f2);
            if (k <= 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2) - 1), (int) (908 * (v2[k] / 60.0)), (int) (745.0 / 35));
            } else if (k > 3 && k < 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2)), (int) (908 * (v2[k] / 60.0)), (int) (745.0 / 35));
            } else if (k == 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 2) - 1), (int) (908 * (v2[k] / 60.0)), (int) (745.0 / 35));
            }

            g.setFont(f4);
            g.drawString("" + v2[k] + "%", (int) (908 * (v2[k] / 60.0) + 971), (int) (208 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));

            g.setColor(new Color(120, 120, 120));
            g.setFont(f2);
            if (k < 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 1), (int) (908 * (v3[k] / 60.0)), (int) (745.0 / 35));
            } else if (k == 3) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 2), (int) (908 * (v3[k] / 60.0)), (int) (745.0 / 35));
            } else if (k > 3 && k < 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 3), (int) (908 * (v3[k] / 60.0)), (int) (745.0 / 35));
            } else if (k == 6) {
                g.fillRect(961, (int) (170 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 3) - 1), (int) (908 * (v3[k] / 60.0)), (int) (745.0 / 35));
            }

            g.setFont(f4);
            g.drawString("" + v3[k] + "%", (int) (908 * (v3[k] / 60.0) + 971), (int) (230 + ((745.0 / 35 * 5) * k) + (745.0 / 35 * 1)));
        }

        g.setFont(f3);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(405, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Kaikki häirintää 2 v. aikana kokeneet, jotka eivät ilmoittaneet siitä", 435, 990);

        g.setColor(new Color(70, 70, 70));
        g.fillRect(1165, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Naiset", 1195, 990);

        g.setColor(new Color(120, 120, 120));
        g.fillRect(1325, 972, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Miehet", 1355, 990);
    }

    public void r6(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font f1 = new Font("Arial", Font.BOLD, 50);
        Font f2 = new Font("Arial", Font.BOLD, 40);
        Font f3 = new Font("Arial", Font.PLAIN, 40);

        String[] s1 = new String[11];
        s1[0] = "Yhteiskunnalle";
        s1[1] = "Sukupuolten välinen eriarvoisuus";
        s1[2] = "";
        s1[3] = "Palkkaerot";
        s1[4] = "";
        s1[5] = "Sosiaaliturvakulut";
        s1[6] = "Terveydenhoito ja lääkekulut";
        s1[7] = "Alhaisempi BKT";
        s1[8] = "Ennenaikaiset työeläkkeet";
        s1[9] = "";
        s1[10] = "";

        String[] s2 = new String[11];
        s2[0] = "Työpaikoille";
        s2[1] = "Poissaolot ja sairastelu";
        s2[2] = "";
        s2[3] = "Suurempi henkilöstön";
        s2[4] = "vaihtuvuus";
        s2[5] = "Alhainen tuottavuus";
        s2[6] = "Heikko moraali ja motivaatio";
        s2[7] = "Korvaukset ja oikeuskulut";
        s2[8] = "Liikearvon ja maineen";
        s2[9] = "menetys";
        s2[10] = "";

        String[] s3 = new String[11];
        s3[0] = "Yksilölle";
        s3[1] = "Heikko fyysinen ja psyykkinen";
        s3[2] = "terveys";
        s3[3] = "Stressi ja masennus";
        s3[4] = "";
        s3[5] = "Nolous ja häpeä";
        s3[6] = "Mielipaha ja viha";
        s3[7] = "Alhainen työtyytyväisyys";
        s3[8] = "Vaikutukset perhesuhteisiin";
        s3[9] = "";
        s3[10] = "Tulojen menetys";

        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Seksuaalisen häirinnän kustannuksia ja seurauksia", 370, 90);
        g.drawRect(50, 150, 1820, 880);
        g.drawLine((1820 / 3) + 50, 150, (1820 / 3) + 50, 1030);
        g.drawLine((1820 / 3 * 2) + 50, 150, (1820 / 3 * 2) + 50, 1030);
        for (int i = 1; i < 11; i++) {
            if (i != 2 && i != 4 && i != 9) {
                g.drawLine(50, 150 + 80 * i, 1870, 150 + 80 * i);
            }
        }
        for (int j = 0; j < 3; j++) {
            String[] kasiteltava = new String[11];
            if (j == 0) {
                kasiteltava = s1;
            } else if (j == 1) {
                kasiteltava = s2;
            } else if (j == 2) {
                kasiteltava = s3;
            }
            for (int k = 0; k < 11; k++) {
                if (k == 0) {
                    g.setFont(f2);
                } else {
                    g.setFont(f3);
                }
                if (k == 2 || k == 4 || k == 9) {
                    g.drawString(kasiteltava[k], 75 + (j * (1820 / 3)), 170 + (80 * k));
                } else {
                    g.drawString(kasiteltava[k], 75 + (j * (1820 / 3)), 205 + (80 * k));
                }
            }
        }
    }

    ///////////////////GETTERS AND SETTERS/////////////////////////////
    public int getCurrentRender() {
        return currentRender;
    }

    public void setCurrentRender(int currentRender) {
        this.currentRender = currentRender;
    }

}
