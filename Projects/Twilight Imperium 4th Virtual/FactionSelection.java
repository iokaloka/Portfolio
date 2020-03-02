package ti4v;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class FactionSelection {

    private BufferedImage current;
    private BufferedImage chosenColor;
    private Handler h;
    private String[] factions = {"The Arborec", "The Ghost of Creuss", "The Emirates of Hacan", "The Universities of Jol-Nar",
        "The L1z1x Mindnet", "The Barony of Letnev", "The Mentak Coalition", "The Embers of Muaat", "The Naalu Collective",
        "The Nekro Virus", "The Federation of Sol", "The Clan of Saar", "Sardakk N'orr", "The Winnu", "The Xxcha Kingdom",
        "The Yin Brotherhood", "The Yssaril Tribes"};
    private String[] colors = {"Red", "Yellow", "Blue", "Green", "Purple", "Black"};
    private ArrayList<String> takenFactions;
    private ArrayList<String> takenColors;
    private Player activePlayer;
    private String activeFaction = "The Arborec";
    private String activeColor = "Red";
    private Font font1 = new Font("Arial Black", Font.PLAIN, 20);
    private Font font2 = new Font("Arial Black", Font.PLAIN, 40);
    private Sprite icons;
    private Sprite icons2;
    private BufferedImage[] ic;

    public FactionSelection(Handler h) {
        this.h = h;
        this.takenFactions = new ArrayList<>();
        this.takenColors = new ArrayList<>();
        this.activePlayer = h.getPlayerList().get((h.getRnd().nextInt(h.getPlayers())));
        fetchByName(activeFaction);
        chosenColor = fetch("/chosenColor.png");
        this.icons = new Sprite("/factionIcons.png");
        this.icons2 = new Sprite("/factionIcons2.png");
    }

    public void fetchByName(String name) {
        switch (name) {
            case "The Arborec":
                this.current = fetch("/factionSheetArborec.png");
                break;
            case "The Ghost of Creuss":
                this.current = fetch("/factionSheetCreuss.png");
                break;
            case "The Emirates of Hacan":
                this.current = fetch("/factionSheetHacan.png");
                break;
            case "The Universities of Jol-Nar":
                this.current = fetch("/factionSheetJolnar.png");
                break;
            case "The L1z1x Mindnet":
                this.current = fetch("/factionSheetL1z1x.png");
                break;
            case "The Barony of Letnev":
                this.current = fetch("/factionSheetLetnev.png");
                break;
            case "The Mentak Coalition":
                this.current = fetch("/factionSheetMentak.png");
                break;
            case "The Embers of Muaat":
                this.current = fetch("/factionSheetMuaat.png");
                break;
            case "The Naalu Collective":
                this.current = fetch("/factionSheetNaalu.png");
                break;
            case "The Nekro Virus":
                this.current = fetch("/factionSheetNekro.png");
                break;
            case "The Clan of Saar":
                this.current = fetch("/factionSheetSaar.png");
                break;
            case "Sardakk N'orr":
                this.current = fetch("/factionSheetSardakk.png");
                break;
            case "The Federation of Sol":
                this.current = fetch("/factionSheetSol.png");
                break;
            case "The Winnu":
                this.current = fetch("/factionSheetWinnu.png");
                break;
            case "The Xxcha Kingdom":
                this.current = fetch("/factionSheetXxcha.png");
                break;
            case "The Yin Brotherhood":
                this.current = fetch("/factionSheetYin.png");
                break;
            case "The Yssaril Tribes":
                this.current = fetch("/factionSheetYssaril.png");
                break;
            default:
                break;
        }
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

    public void nextActivePlayer() {
        switch (this.h.getPlayers()) {
            case 3:
                nextActivePlayer3();
                break;
            case 4:
                nextActivePlayer4();
                break;
            case 5:
                nextActivePlayer5();
                break;
            case 6:
                nextActivePlayer6();
                break;
        }
    }

    public void nextActivePlayer3() {
        if (activePlayer.getNumber() == 3) {
            this.activePlayer = searchPlayer(1);
        } else {
            this.activePlayer = searchPlayer(this.activePlayer.getNumber() + 1);
        }
    }

    public void nextActivePlayer4() {
        switch (this.activePlayer.getNumber()) {
            case 1:
                this.activePlayer = searchPlayer(2);
                break;
            case 2:
                this.activePlayer = searchPlayer(4);
                break;
            case 3:
                this.activePlayer = searchPlayer(1);
                break;
            case 4:
                this.activePlayer = searchPlayer(3);
                break;
        }
    }

    public void nextActivePlayer5() {
        switch (this.activePlayer.getNumber()) {
            case 1:
                this.activePlayer = searchPlayer(2);
                break;
            case 2:
                this.activePlayer = searchPlayer(4);
                break;
            case 3:
                this.activePlayer = searchPlayer(5);
                break;
            case 4:
                this.activePlayer = searchPlayer(3);
                break;
            case 5:
                this.activePlayer = searchPlayer(1);
                break;
        }
    }

    public void nextActivePlayer6() {
        switch (this.activePlayer.getNumber()) {
            case 1:
                this.activePlayer = searchPlayer(2);
                break;
            case 2:
                this.activePlayer = searchPlayer(6);
                break;
            case 3:
                this.activePlayer = searchPlayer(5);
                break;
            case 4:
                this.activePlayer = searchPlayer(3);
                break;
            case 5:
                this.activePlayer = searchPlayer(1);
                break;
            case 6:
                this.activePlayer = searchPlayer(4);
                break;
        }
    }

    public Player searchPlayer(int i) {
        Player player = null;
        for (Player p : this.h.getPlayerList()) {
            if (p.getNumber() == i) {
                player = p;
            }
        }
        return player;
    }

    public synchronized void confirm(Faction f, String c) {
        if (!takenFactions.contains(f.getName())) {
            if (!takenColors.contains(c)) {
                confirmPlayer(f, c);
                if (takenFactions.size() == this.h.getPlayers()) {
                    this.h.getGame().setState(TI4V.State.SETUP);
                    this.h.setFactionsSelected(true);
                    createHomes();
                    for (Player p : this.getH().getPlayerList()) {
                        p.crateStartingUnits();
                        p.createHud();
                        if (p.isSpeaker()) {
                            this.h.setActivePlayer(p);
                        }
                    }
                    System.out.println("Handler has " + this.h.getNeutralPlanets().size() + " planets.");
                    for (Player p : this.getH().getPlayerList()) {
                        p.addStartingPlanets();
                    }
                    System.out.println("Handler has " + this.h.getNeutralPlanets().size() + " planets.");
                } else {
                    nextActivePlayer();
                    ArrayList<String> remaining = new ArrayList<>();
                    for (String fact : factions) {
                        if (!takenFactions.contains(fact)) {
                            remaining.add(fact);
                        }
                    }
                    activeFaction = remaining.get(0);
                    fetchByName(activeFaction);
                    ArrayList<String> remainingColors = new ArrayList<>();
                    for (String col : colors) {
                        if (!takenColors.contains(col)) {
                            remainingColors.add(col);
                        }
                    }
                    if (takenColors.size() != 6) {
                        activeColor = remainingColors.get(0);
                    }
                }
            }
        }
    }

    public void confirmPlayer(Faction f, String c) {
        activePlayer.setFaction(f);
        activePlayer.setColor(c);
        this.takenFactions.add(f.getName());
        this.takenColors.add(c);
        activePlayer.getFaction().setIcon(this.icons.icon(activePlayer.getFaction().getFactionNumber()));
        activePlayer.getFaction().setIcon2(this.icons2.icon2(activePlayer.getFaction().getFactionNumber()));
        activePlayer.setCommandSheet(fetch("/commandSheet" + activePlayer.getColor() + ".png"));
        activePlayer.setReinforcements(fetch("/reinforcements" + activePlayer.getColor() + ".png")); 
        activePlayer.getFaction().setFp(new FactionPromissory(f.getName()));
   }

    public synchronized void createHomes() {
        for (Player p : this.h.getPlayerList()) {
            addHome(p.getNumber(), p.getFaction().getName());
        }
    }

    public void addHome(int playerNumber, String faction) {
        int location = 0;
        switch (playerNumber) {
            case 1:
                location = 24;
                break;
            case 2:
                location = 28;
                break;
            case 3:
                location = 33;
                break;
            case 4:
                location = 37;
                break;
            default:
                break;
        }
        int type = 8;
        switch (faction) {
            case "The Arborec":
                type = 29;
                break;
            case "The Ghost of Creuss":
                type = 30;
                break;
            case "The Emirates of Hacan":
                type = 31;
                break;
            case "The Universities of Jol-Nar":
                type = 32;
                break;
            case "The L1z1x Mindnet":
                this.current = fetch("/factionSheetL1z1x.png");
                type = 33;
                break;
            case "The Barony of Letnev":
                this.current = fetch("/factionSheetLetnev.png");
                type = 34;
                break;
            case "The Mentak Coalition":
                this.current = fetch("/factionSheetMentak.png");
                type = 35;
                break;
            case "The Embers of Muaat":
                this.current = fetch("/factionSheetMuaat.png");
                type = 36;
                break;
            case "The Naalu Collective":
                this.current = fetch("/factionSheetNaalu.png");
                type = 37;
                break;
            case "The Nekro Virus":
                this.current = fetch("/factionSheetNekro.png");
                type = 38;
                break;
            case "The Clan of Saar":
                this.current = fetch("/factionSheetSaar.png");
                type = 39;
                break;
            case "Sardakk N'orr":
                this.current = fetch("/factionSheetSardakk.png");
                type = 40;
                break;
            case "The Federation of Sol":
                this.current = fetch("/factionSheetSol.png");
                type = 41;
                break;
            case "The Winnu":
                this.current = fetch("/factionSheetWinnu.png");
                type = 42;
                break;
            case "The Xxcha Kingdom":
                this.current = fetch("/factionSheetXxcha.png");
                type = 43;
                break;
            case "The Yin Brotherhood":
                this.current = fetch("/factionSheetYin.png");
                type = 44;
                break;
            case "The Yssaril Tribes":
                type = 45;
                this.current = fetch("/factionSheetYssaril.png");
                break;
        }
        this.h.addTile(location, type);
    }

    public boolean mouseOverConfirm() {
        int startX = this.h.getGame().getMm().getX();
        int startY = this.h.getGame().getMm().getY();
        if (startX > 198 && startX < 408 && startY > 947 && startY < 983) {
            return true;
        } else {
            return false;
        }
    }

    public int mouseOverFaction() {
        int currentF = -1;
        int fX = this.h.getGame().getMm().getX();
        int fY = this.h.getGame().getMm().getY();
        for (int i = 0; i < 17; i++) {
            if (fX > 47 && fX < 347 && fY > (170 + (i * 40)) && fY < (210 + (i * 40))) {
                if (!takenFactions.contains(factions[i])) {
                    currentF = i;
                }
            }
        }
        return currentF;
    }

    public int mouseOverColor() {
        int currentColor = -1;
        int cX = this.h.getGame().getMm().getX();
        int cY = this.h.getGame().getMm().getY();
        for (int i = 0; i < 6; i++) {
            if (cX > 395 && cX < 495 && cY > (370 + (i * 40)) && cY < (370 + ((i + 1) * 40))) {
                currentColor = i;
            }
        }
        return currentColor;
    }

    public void clickedFaction(int f) {
        if (f >= 0 && f <= 16) {
            this.activeFaction = this.factions[f];
            fetchByName(activeFaction);
        }
    }

    public void clickedColor(int c) {
        if (c >= 0 && c <= 5) {
            if (!takenColors.contains(colors[c])) {
                this.activeColor = this.colors[c];
            }
        }
    }

    public void render(Graphics g) {
        g.setFont(font1);
        g.drawString("Player " + activePlayer.getNumber(), 50, 100);
        renderFactions(g);
        g.drawImage(this.current, 600, 100, null);
        renderColors(g);
        if (mouseOverConfirm()) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.setFont(font2);
        g.drawString("CONFIRM", 200, 980);
    }

    public void renderFactions(Graphics g) {
        int fX = this.h.getGame().getMm().getX();
        int fY = this.h.getGame().getMm().getY();
        for (int i = 0; i < 17; i++) {
            if (fX > 47 && fX < 347 && fY > (170 + (i * 40)) && fY < (210 + (i * 40))) {
                g.setColor(Color.gray);
            } else {
                g.setColor(Color.white);
            }
            if (takenFactions.contains(factions[i])) {
                g.setColor(Color.black);
            }
            g.drawString(factions[i], 50, 200 + (i * 40));
        }
    }

    public void renderColors(Graphics g) {
        g.setFont(font1);
        if (!takenColors.contains("Red")) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }
        g.drawString("RED", 400, 400);
        if (!takenColors.contains("Yellow")) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.black);
        }
        g.drawString("YELLOW", 400, 440);
        if (!takenColors.contains("Blue")) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.black);
        }
        g.drawString("BLUE", 400, 480);
        if (!takenColors.contains("Green")) {
            g.setColor(Color.green);
        } else {
            g.setColor(Color.black);
        }
        g.drawString("GREEN", 400, 520);
        if (!takenColors.contains("Purple")) {
            g.setColor(new Color(87, 0, 127));
        } else {
            g.setColor(Color.black);
        }
        g.drawString("PURPLE", 400, 560);
        if (!takenColors.contains("Black")) {
            g.setColor(new Color(20, 20, 20));
        } else {
            g.setColor(Color.black);
        }
        g.drawString("BLACK", 400, 600);
        for (int i = 0; i < 6; i++) {
            if (colors[i].equals(activeColor)) {
                g.drawImage(chosenColor, 370, 375 + (i * 40), null);
            }
        }
    }

////////////////////////GETTERS AMD SETTERS/////////////////////////////////////
    public Handler getH() {
        return h;
    }

    public void setH(Handler h) {
        this.h = h;
    }

    public String[] getFactions() {
        return factions;
    }

    public void setFactions(String[] factions) {
        this.factions = factions;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public String getActiveFaction() {
        return activeFaction;
    }

    public void setActiveFaction(String activeFaction) {
        this.activeFaction = activeFaction;
    }

    public ArrayList<String> getTakenFactions() {
        return takenFactions;
    }

    public void setTakenFactions(ArrayList<String> takenFactions) {
        this.takenFactions = takenFactions;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }

}
