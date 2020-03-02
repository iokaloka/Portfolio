package ti4v;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Faction {

    private String name;
    private BufferedImage sheet;
    private BufferedImage icon;
    private BufferedImage icon2;
    private int factionNumber;
    private int[] startingUnits = new int[10];
    private FactionPromissory fp;
    private Technology ft1;
    private Technology ft2;
    private FactionSpecificToken naalu0;
    private FactionSpecificToken creussA;
    private FactionSpecificToken creussB;
    private FactionSpecificToken nekroX;
    private FactionSpecificToken nekroY;

    public Faction(String name) {
        this.name = name;
        fetchByName(name);
        setStartingUnits();
        this.fp = new FactionPromissory(this.name);
        setFactionSpecifics();
    }

    public ArrayList<String> startingPlanets() {
        ArrayList<String> startingPlanets = new ArrayList<>();
        switch (this.getName()) {
            case "The Arborec":
                startingPlanets.add("Nestphar");
                break;
            case "The Ghost of Creuss":
                startingPlanets.add("Creuss");
                break;
            case "The Emirates of Hacan":
                startingPlanets.add("Arretze");
                startingPlanets.add("Hercant");
                startingPlanets.add("Kamdorn");
                break;
            case "The Universities of Jol-Nar":
                startingPlanets.add("Jol");
                startingPlanets.add("Nar");
                break;
            case "The L1z1x Mindnet":
                startingPlanets.add("Origo");
                break;
            case "The Barony of Letnev":
                startingPlanets.add("Arc Prime");
                startingPlanets.add("Wren Terra");
                break;
            case "The Mentak Coalition":
                startingPlanets.add("Moll Primus");
                break;
            case "The Embers of Muaat":
                startingPlanets.add("Muaat");
                break;
            case "The Naalu Collective":
                startingPlanets.add("Maaluuk");
                startingPlanets.add("Druua");
                break;
            case "The Nekro Virus":
                startingPlanets.add("Mordai II");
                break;
            case "The Clan of Saar":
                startingPlanets.add("Lisis II");
                startingPlanets.add("Ragh");
                break;
            case "Sardakk N'orr":
                startingPlanets.add("Tren'Lak");
                startingPlanets.add("Quinarra");
                break;
            case "The Federation of Sol":
                startingPlanets.add("Jord");
                break;
            case "The Winnu":
                startingPlanets.add("Winnu");
                break;
            case "The Xxcha Kingdom":
                startingPlanets.add("Archon Ren");
                startingPlanets.add("Archon Tau");
                break;
            case "The Yin Brotherhood":
                startingPlanets.add("Darien");
                break;
            case "The Yssaril Tribes":
                startingPlanets.add("Retillion");
                startingPlanets.add("Shalloq");
                break;
        }
        return startingPlanets;
    }

    public void setFactionSpecifics() {
        if (this.name.equals("The Naalu Collective")) {
            this.naalu0 = new FactionSpecificToken(0);
        } else if (this.name.equals("The Ghost of Creuss")) {
            this.creussA = new FactionSpecificToken(1);
            this.creussB = new FactionSpecificToken(2);
        } else if (this.name.equals("The Nekro Virus")) {
            this.nekroX = new FactionSpecificToken(3);
            this.nekroY = new FactionSpecificToken(4);
        }
    }

    public void fetchByName(String name) {
        switch (name) {
            case "The Arborec":
                this.sheet = fetch("/factionSheetArborec.png");
                this.factionNumber = 0;
                this.ft1 = new Technology("bioplasmosis", "The Arborec", true);
                this.ft2 = new Technology("letaniWarriorII", "The Arborec", true);
                break;
            case "The Ghost of Creuss":
                this.sheet = fetch("/factionSheetCreuss.png");
                this.factionNumber = 1;
                this.ft1 = new Technology("wormholeGenerator", "The Ghost of Creuss", true);
                this.ft2 = new Technology("dimensionalSplicer", "The Ghost of Creuss", true);
                break;
            case "The Emirates of Hacan":
                this.sheet = fetch("/factionSheetHacan.png");
                this.factionNumber = 2;
                this.ft1 = new Technology("productionBiomes", "The Emirates of Hacan", true);
                this.ft2 = new Technology("quantumDatahubNode", "The Emirates of Hacan", true);
                break;
            case "The Universities of Jol-Nar":
                this.sheet = fetch("/factionSheetJolnar.png");
                this.factionNumber = 3;
                this.ft1 = new Technology("specialConduitCylinder", "The Universities of Jol-Nar", true);
                this.ft2 = new Technology("e-resSiphons", "The Universities of Jol-Nar", true);
                break;
            case "The L1z1x Mindnet":
                this.sheet = fetch("/factionSheetL1z1x.png");
                this.factionNumber = 4;
                this.ft1 = new Technology("inheritanceSystems", "The L1z1x Mindnet", true);
                this.ft2 = new Technology("super-dreadnoughtII", "The L1z1x Mindnet", true);
                break;
            case "The Barony of Letnev":
                this.sheet = fetch("/factionSheetLetnev.png");
                this.factionNumber = 5;
                this.ft1 = new Technology("l4Disruptors", "The Barony of Letnev", true);
                this.ft2 = new Technology("non-euclideanShielding", "The Barony of Letnev", true);
                break;
            case "The Mentak Coalition":
                this.sheet = fetch("/factionSheetMentak.png");
                this.factionNumber = 6;
                this.ft1 = new Technology("mirrorComputing", "The Mentak Coalition", true);
                this.ft2 = new Technology("salvageOperations", "The Mentak Coalition", true);
                break;
            case "The Embers of Muaat":
                this.sheet = fetch("/factionSheetMuaat.png");
                this.factionNumber = 7;
                this.ft1 = new Technology("magmusReactor", "The Embers of Muaat", true);
                this.ft2 = new Technology("prototypeWarSunII", "The Embers of Muaat", true);
                break;
            case "The Naalu Collective":
                this.sheet = fetch("/factionSheetNaalu.png");
                this.factionNumber = 17;
                this.ft1 = new Technology("neuroglaive", "The Naalu Collective", true);
                this.ft2 = new Technology("hybridCrystalFighterII", "The Naalu Collective", true);
                break;
            case "The Nekro Virus":
                this.sheet = fetch("/factionSheetNekro.png");
                this.factionNumber = 9;
                this.ft1 = new Technology("valefarAssimilatorX", "The Nekro Virus", true);
                this.ft2 = new Technology("valefarAssimilatorY", "The Nekro Virus", true);
                break;
            case "The Clan of Saar":
                this.sheet = fetch("/factionSheetSaar.png");
                this.factionNumber = 10;
                this.ft1 = new Technology("chaosMapping", "The Clan of Saar", true);
                this.ft2 = new Technology("floatingFactoryII", "The Clan of Saar", true);
                break;
            case "Sardakk N'orr":
                this.sheet = fetch("/factionSheetSardakk.png");
                this.factionNumber = 11;
                this.ft1 = new Technology("valkyrieParticleWeave", "Sardakk N'orr", true);
                this.ft2 = new Technology("exotriremeII", "Sardakk N'orr", true);
                break;
            case "The Federation of Sol":
                this.sheet = fetch("/factionSheetSol.png");
                this.factionNumber = 12;
                this.ft1 = new Technology("specOpsII", "The Federation of Sol", true);
                this.ft2 = new Technology("advancedCarrierII", "The Federation of Sol", true);
                break;
            case "The Winnu":
                this.sheet = fetch("/factionSheetWinnu.png");
                this.factionNumber = 13;
                this.ft1 = new Technology("lazaxGateFolding", "The Winnu", true);
                this.ft2 = new Technology("hegemonicTradePolicy", "The Winnu", true);
                break;
            case "The Xxcha Kingdom":
                this.sheet = fetch("/factionSheetXxcha.png");
                this.factionNumber = 14;
                this.ft1 = new Technology("nullificationField", "The Xxcha Kingdom", true);
                this.ft2 = new Technology("instinctTraining", "The Xxcha Kingdom", true);
                break;
            case "The Yin Brotherhood":
                this.sheet = fetch("/factionSheetYin.png");
                this.factionNumber = 15;
                this.ft1 = new Technology("impulseCore", "The Yin Brotherhood", true);
                this.ft2 = new Technology("yinSpinner", "The Yin Brotherhood", true);
                break;
            case "The Yssaril Tribes":
                this.sheet = fetch("/factionSheetYssaril.png");
                this.factionNumber = 16;
                this.ft1 = new Technology("mageonImplants", "The Yssaril Tribes", true);
                this.ft2 = new Technology("transparasteelPlating", "The Yssaril Tribes", true);
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

    public void setStartingUnits() {
        //0 = Dreadnought
        //1 = War Sun
        //2 = Flagship
        //3 = Fighter
        //4 = Destroyer
        //5 = Carrier
        //6 = Cruiser
        //7 = Infantry
        //8 = Space Dock
        //9 = PDS
        switch (name) {
            case "The Arborec":
                this.startingUnits = new int[]{0, 0, 0, 2, 0, 1, 1, 4, 1, 1};
                break;
            case "The Ghost of Creuss":
                this.startingUnits = new int[]{0, 0, 0, 2, 2, 1, 0, 4, 1, 0};
                break;
            case "The Emirates of Hacan":
                this.startingUnits = new int[]{0, 0, 0, 2, 0, 2, 1, 4, 1, 0};
                break;
            case "The Universities of Jol-Nar":
                this.startingUnits = new int[]{1, 0, 0, 1, 0, 2, 0, 2, 1, 2};
                break;
            case "The L1z1x Mindnet":
                this.startingUnits = new int[]{1, 0, 0, 3, 0, 1, 0, 5, 1, 1};
                break;
            case "The Barony of Letnev":
                this.startingUnits = new int[]{1, 0, 0, 1, 1, 1, 0, 3, 1, 0};
                break;
            case "The Mentak Coalition":
                this.startingUnits = new int[]{0, 0, 0, 3, 0, 1, 2, 4, 1, 1};
                break;
            case "The Embers of Muaat":
                this.startingUnits = new int[]{0, 1, 0, 2, 0, 0, 0, 4, 1, 0};
                break;
            case "The Naalu Collective":
                this.startingUnits = new int[]{0, 0, 0, 3, 1, 1, 1, 4, 1, 1};
                break;
            case "The Nekro Virus":
                this.startingUnits = new int[]{1, 0, 0, 2, 0, 1, 1, 2, 1, 0};
                break;
            case "The Clan of Saar":
                this.startingUnits = new int[]{0, 0, 0, 2, 0, 2, 1, 4, 1, 0};
                break;
            case "Sardakk N'orr":
                this.startingUnits = new int[]{0, 0, 0, 0, 0, 2, 1, 5, 1, 1};
                break;
            case "The Federation of Sol":
                this.startingUnits = new int[]{0, 0, 0, 3, 1, 2, 0, 5, 1, 0};
                break;
            case "The Winnu":
                this.startingUnits = new int[]{0, 0, 0, 2, 0, 1, 1, 2, 1, 1};
                break;
            case "The Xxcha Kingdom":
                this.startingUnits = new int[]{0, 0, 0, 3, 0, 1, 2, 4, 1, 1};
                break;
            case "The Yin Brotherhood":
                this.startingUnits = new int[]{0, 0, 0, 4, 1, 2, 0, 4, 1, 0};
                break;
            case "The Yssaril Tribes":
                this.startingUnits = new int[]{0, 0, 0, 2, 0, 2, 1, 5, 1, 1};
                break;
            default:
                break;
        }
        for (int y : this.startingUnits) {
            System.out.print(y + ", ");
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return this.name;
    }

/////////////////////////////////////////GETTERS AND SETTERS////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getStartingUnits() {
        return startingUnits;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getFactionNumber() {
        return factionNumber;
    }

    public BufferedImage getSheet() {
        return sheet;
    }

    public BufferedImage getIcon2() {
        return icon2;
    }

    public void setIcon2(BufferedImage icon2) {
        this.icon2 = icon2;
    }

    public FactionPromissory getFp() {
        return fp;
    }

    public void setFp(FactionPromissory fp) {
        this.fp = fp;
    }

    public Technology getFt1() {
        return ft1;
    }

    public void setFt1(Technology ft1) {
        this.ft1 = ft1;
    }

    public Technology getFt2() {
        return ft2;
    }

    public void setFt2(Technology ft2) {
        this.ft2 = ft2;
    }

    public FactionSpecificToken getNaalu0() {
        return naalu0;
    }

    public void setNaalu0(FactionSpecificToken naalu0) {
        this.naalu0 = naalu0;
    }

    public FactionSpecificToken getCreussA() {
        return creussA;
    }

    public void setCreussA(FactionSpecificToken creussA) {
        this.creussA = creussA;
    }

    public FactionSpecificToken getCreussB() {
        return creussB;
    }

    public void setCreussB(FactionSpecificToken creussB) {
        this.creussB = creussB;
    }

    public FactionSpecificToken getNekroX() {
        return nekroX;
    }

    public void setNekroX(FactionSpecificToken nekroX) {
        this.nekroX = nekroX;
    }

    public FactionSpecificToken getNekroY() {
        return nekroY;
    }

    public void setNekroY(FactionSpecificToken nekroY) {
        this.nekroY = nekroY;
    }

}
