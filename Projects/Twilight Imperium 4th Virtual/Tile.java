package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Tile {

    private Handler h;
    private int x = 0;
    private int y = 0;
    private int type;
    private int location = -1;
    private boolean anomality = false;
    private boolean homeSystem = false;
    private boolean wha = false;
    private boolean whb = false;
    private boolean whd = false;
    private BufferedImage img;
    private ArrayList<Planet> planets;
    private ArrayList<Integer> neighbors;

    public Tile(Handler h, int x, int y, int type) {
        this.h = h;
        this.x = x;
        this.y = y;
        this.type = type;
        this.planets = new ArrayList<>();
        this.neighbors = calculateNeighbors();
        switch (type) {
            case 0: //Empty
                img = fetch("/systemTileEmpty.png");
                this.anomality = true;
                break;
            case 1: //Asteroid
                img = fetch("/systemTileAsteroidbelt.png");
                this.anomality = true;
                break;
            case 2: //Nebula
                img = fetch("/systemTileNebula.png");
                this.anomality = true;
                break;
            case 3: //Supernova
                img = fetch("/systemTileSupernova.png");
                this.anomality = true;
                break;
            case 4: //GravityRift
                img = fetch("/systemTileGravityRift.png");
                this.anomality = true;
                break;
            case 5: //Mecatol rex
                img = fetch("/systemTileMecatol.png");
                this.planets.add(new Planet(this.h, "Mecatol Rex"));
                break;
            case 6: //WHA
                img = fetch("/systemTileWormholeA.png");
                break;
            case 7: //WHB
                img = fetch("/systemTileWormholeB.png");
                break;
            case 8: //home
                img = fetch("/systemTileStart.png");
                break;
            case 9: //TARMANN
                img = fetch("/TARMANN.png");
                this.planets.add(new Planet(this.h, "Tramann"));
                break;
            case 10: //SAUDOR
                img = fetch("/SAUDOR.png");
                this.planets.add(new Planet(this.h, "Saudor"));
                break;
            case 11: //THIBAH
                img = fetch("/THIBAH.png");
                this.planets.add(new Planet(this.h, "Thibah"));
                break;
            case 12: //WELLON
                img = fetch("/WELLON.png");
                this.planets.add(new Planet(this.h, "Wellon"));
                break;
            case 13: //MEHAR XULL
                img = fetch("/MEHARXULL.png");
                this.planets.add(new Planet(this.h, "Mehar Xull"));
                break;
            case 14: //VEFUT II
                img = fetch("/VEFUTII.png");
                this.planets.add(new Planet(this.h, "Vefut II"));
                break;
            case 15: //LODOR + WHA
                img = fetch("/systemTile1Planet1WHA.png");
                this.planets.add(new Planet(this.h, "Lodor"));
                break;
            case 16: //QUANN + WHB
                img = fetch("/systemTile1Planet1WHB.png");
                this.planets.add(new Planet(this.h, "Quann"));
                break;
            case 17: //ARINAM + MEER
                img = fetch("/ARINAMMEER.png");
                this.planets.add(new Planet(this.h, "Arinam"));
                this.planets.add(new Planet(this.h, "Meer"));
                break;
            case 18: //ABYT + FRIA
                img = fetch("/ABYZFRIA.png");
                this.planets.add(new Planet(this.h, "Abyz"));
                this.planets.add(new Planet(this.h, "Fria"));
                break;
            case 19: //TEQU'RAN + TORKAN
                img = fetch("/TEQURANTORKAN.png");
                this.planets.add(new Planet(this.h, "Tequ'Ran"));
                this.planets.add(new Planet(this.h, "Torkan"));
                break;
            case 20: //CORNEEQ + RESCULON
                img = fetch("/CORNEEQRESCULON.png");
                this.planets.add(new Planet(this.h, "Corneeq"));
                this.planets.add(new Planet(this.h, "Resculon"));
                break;
            case 21: //ARNOR + LOR
                img = fetch("/ARNORLOR.png");
                this.planets.add(new Planet(this.h, "Arnor"));
                this.planets.add(new Planet(this.h, "Lor"));
                break;
            case 22: //DAL BOOTHA + XXEHAN
                img = fetch("/DALBOOTHAXXEHAN.png");
                this.planets.add(new Planet(this.h, "Dal Bootha"));
                this.planets.add(new Planet(this.h, "Xxehan"));
                break;
            case 23: //MELLON + ZOHBAT
                img = fetch("/MELLONZOHBAT.png");
                this.planets.add(new Planet(this.h, "Mellon"));
                this.planets.add(new Planet(this.h, "Zohbat"));
                break;
            case 24: //CENTAURI + GRAL
                img = fetch("/CENTAURIGRAL.png");
                this.planets.add(new Planet(this.h, "Centauri"));
                this.planets.add(new Planet(this.h, "Gral"));
                break;
            case 25: //LAZAR + SAKULAG
                img = fetch("/LAZARSAKULAG.png");
                this.planets.add(new Planet(this.h, "Lazar"));
                this.planets.add(new Planet(this.h, "Sakulag"));
                break;
            case 26: //BEREG + LIRTA IV
                img = fetch("/BEREGLIRTAIV.png");
                this.planets.add(new Planet(this.h, "Bereg"));
                this.planets.add(new Planet(this.h, "Lirta IV"));
                break;
            case 27: //QUCEN'N + RARDON
                img = fetch("/QUCENNRARDON.png");
                this.planets.add(new Planet(this.h, "Qucen'n"));
                this.planets.add(new Planet(this.h, "Rardon"));
                break;
            case 28: //NEW ALBION + STARPOINT
                img = fetch("/NEWALBIONSTARPOINT.png");
                this.planets.add(new Planet(this.h, "New Albion"));
                this.planets.add(new Planet(this.h, "Starpoint"));
                break;
            case 29: //Arborec
                this.homeSystem = true;
                img = fetch("/NESTPHAR.png");
                this.planets.add(new Planet(this.h, "Nestphar"));
                break;
            case 30: //CREUSS
                this.homeSystem = true;
                img = fetch("/systemTileWormholeD.png");
                this.planets.add(new Planet(this.h, "Creuss"));
                break;
            case 31: //HACAN
                this.homeSystem = true;
                img = fetch("/ARRETZE.png");
                this.planets.add(new Planet(this.h, "Arretze"));
                this.planets.add(new Planet(this.h, "Hercant"));
                this.planets.add(new Planet(this.h, "Kamdorn"));
                break;
            case 32: //JOL-NAR
                this.homeSystem = true;
                img = fetch("/JOLNAR.png");
                this.planets.add(new Planet(this.h, "Jol"));
                this.planets.add(new Planet(this.h, "Nar"));
                break;
            case 33: //L1Z1X
                this.homeSystem = true;
                img = fetch("/ORIGO.png");
                this.planets.add(new Planet(this.h, "Origo"));
                break;
            case 34: //LETNEV
                this.homeSystem = true;
                img = fetch("/ARCPRIMEWRENTERRA.png");
                this.planets.add(new Planet(this.h, "Arc Prime"));
                this.planets.add(new Planet(this.h, "Wren Terra"));
                break;
            case 35: //MENTAK
                this.homeSystem = true;
                img = fetch("/MOLLPRIMUS.png");
                this.planets.add(new Planet(this.h, "Moll Primus"));
                break;
            case 36: //MUAAT
                this.homeSystem = true;
                img = fetch("/MUAAT.png");
                this.planets.add(new Planet(this.h, "Muaat"));
                break;
            case 37: //NAALU
                this.homeSystem = true;
                img = fetch("/MAALUUKDRUUA.png");
                this.planets.add(new Planet(this.h, "Maaluuk"));
                this.planets.add(new Planet(this.h, "Druua"));
                break;
            case 38: //NEKRO
                this.homeSystem = true;
                img = fetch("/MORDAIII.png");
                this.planets.add(new Planet(this.h, "Mordai II"));
                break;
            case 39: //SAAR
                this.homeSystem = true;
                img = fetch("/LISISIIRAGH.png");
                this.planets.add(new Planet(this.h, "Lisis II"));
                this.planets.add(new Planet(this.h, "Ragh"));
                break;
            case 40: //SARDAKK
                this.homeSystem = true;
                img = fetch("/TRENLAKQUINARRA.png");
                this.planets.add(new Planet(this.h, "Tren'Lak"));
                this.planets.add(new Planet(this.h, "Quinarra"));
                break;
            case 41: //SOL
                this.homeSystem = true;
                img = fetch("/JORD.png");
                this.planets.add(new Planet(this.h, "Jord"));
                break;
            case 42: //WINNU
                this.homeSystem = true;
                img = fetch("/WINNU.png");
                this.planets.add(new Planet(this.h, "Winnu"));
                break;
            case 43: //XXCHA
                this.homeSystem = true;
                img = fetch("/ARCHONRENARCHONTAU.png");
                this.planets.add(new Planet(this.h, "Archon Ren"));
                this.planets.add(new Planet(this.h, "Archon Tau"));
                break;
            case 44: //YIN
                this.homeSystem = true;
                img = fetch("/DARIEN.png");
                this.planets.add(new Planet(this.h, "Darien"));
                break;
            case 45: //YSSARIL
                this.homeSystem = true;
                img = fetch("/RETILLIONSHALLOQ.png");
                this.planets.add(new Planet(this.h, "Retillion"));
                this.planets.add(new Planet(this.h, "Shalloq"));
                break;
            default:
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
    
    public ArrayList<Integer> calculateNeighbors(){
        return new ArrayList<>();
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    /////////////////////////////////GETTERS AND SETTERS////////////////////////
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

}
