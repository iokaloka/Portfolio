package ti4v;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Planet {

    private Handler h;
    private String name;
    private int owner = -1;
    private int resources = 0;
    private int influence = 0;
    private boolean techRed = false;
    private boolean techBlue = false;
    private boolean techGreen = false;
    private boolean techYellow = false;
    private boolean hazardous = false;
    private boolean industrial = false;
    private boolean cultural = false;
    private BufferedImage card;

    public Planet(Handler h, String name) {
        this.h = h;
        this.name = name;
        switch (name) {
            case "Abyz":
                this.resources = 3;
                this.influence = 0;
                this.hazardous = true;
                this.card = fetch("/planetABYZ.png");
                break;
            case "Archon Ren":
                this.resources = 2;
                this.influence = 3;
                this.card = fetch("/planetARCHONREN.png");
                break;
            case "Archon Tau":
                this.resources = 1;
                this.influence = 1;
                this.card = fetch("/planetARCHONTAU.png");
                break;
            case "Arc Prime":
                this.resources = 4;
                this.influence = 0;
                this.card = fetch("/planetARCPRIME.png");
                break;
            case "Arinam":
                this.resources = 1;
                this.influence = 2;
                this.industrial = true;
                this.card = fetch("/planetARINAM.png");
                break;
            case "Arnor":
                this.resources = 2;
                this.influence = 1;
                this.industrial = true;
                this.card = fetch("/planetARNOR.png");
                break;
            case "Arretze":
                this.resources = 2;
                this.influence = 0;
                this.card = fetch("/planetARRETZE.png");
                break;
            case "Bereg":
                this.resources = 3;
                this.influence = 1;
                this.hazardous = true;
                this.card = fetch("/planetBEREG.png");
                break;
            case "Centauri":
                this.resources = 1;
                this.influence = 3;
                this.cultural = true;
                this.card = fetch("/planetCENTAURI.png");
                break;
            case "Corneeq":
                this.resources = 1;
                this.influence = 2;
                this.cultural = true;
                this.card = fetch("/planetCORNEEQ.png");
                break;
            case "Creuss":
                this.resources = 4;
                this.influence = 2;
                this.card = fetch("/planetCREUSS.png");
                break;
            case "Dal Bootha":
                this.resources = 0;
                this.influence = 2;
                this.cultural = true;
                this.card = fetch("/planetDALBOOTHA.png");
                break;
            case "Darien":
                this.resources = 4;
                this.influence = 4;
                this.card = fetch("/planetDARIEN.png");
                break;
            case "Druaa":
                this.resources = 3;
                this.influence = 1;
                this.card = fetch("/planetDRUAA.png");
                break;
            case "Fria":
                this.resources = 2;
                this.influence = 0;
                this.hazardous = true;
                this.card = fetch("/planetFRIA.png");
                break;
            case "Gral":
                this.resources = 1;
                this.influence = 1;
                this.industrial = true;
                this.techBlue = true;
                this.card = fetch("/planetGRAL.png");
                break;
            case "Hercant":
                this.resources = 1;
                this.influence = 1;
                this.card = fetch("/planetHERCANT.png");
                break;
            case "Jol":
                this.resources = 1;
                this.influence = 2;
                this.card = fetch("/planetJOL.png");
                break;
            case "Jord":
                this.resources = 4;
                this.influence = 2;
                this.card = fetch("/planetJORD.png");
                break;
            case "Kamdorn":
                this.resources = 0;
                this.influence = 1;
                this.card = fetch("/planetKAMDORN.png");
                break;
            case "Lazar":
                this.resources = 1;
                this.influence = 0;
                this.industrial = true;
                this.techYellow = true;
                this.card = fetch("/planetLAZAR.png");
                break;
            case "Lirta IV":
                this.resources = 2;
                this.influence = 3;
                this.hazardous = true;
                this.card = fetch("/planetLIRTAIV.png");
                break;
            case "Lisis II":
                this.resources = 1;
                this.influence = 0;
                this.card = fetch("/planetLISISII.png");
                break;
            case "Lodor":
                this.resources = 3;
                this.influence = 1;
                this.cultural = true;
                this.card = fetch("/planetLODOR.png");
                break;
            case "Lor":
                this.resources = 1;
                this.influence = 2;
                this.industrial = true;
                this.card = fetch("/planetLOR.png");
                break;
            case "Maaluuk":
                this.resources = 0;
                this.influence = 2;
                this.card = fetch("/planetMAALUUK.png");
                break;
            case "Mecatol Rex":
                this.resources = 1;
                this.influence = 6;
                this.card = fetch("/planetMECATOLREX.png");
                break;
            case "Meer":
                this.resources = 0;
                this.influence = 4;
                this.hazardous = true;
                this.techRed = true;
                this.card = fetch("/planetMEER.png");
                break;
            case "Mehar Xull":
                this.resources = 1;
                this.influence = 3;
                this.hazardous = true;
                this.techRed = true;
                this.card = fetch("/planetMEHARXULL.png");
                break;
            case "Mellon":
                this.resources = 0;
                this.influence = 2;
                this.cultural = true;
                this.card = fetch("/planetMELLON.png");
                break;
            case "Moll Primus":
                this.resources = 4;
                this.influence = 1;
                this.card = fetch("/planetMOLLPRIMUS.png");
                break;
            case "Mordai II":
                this.resources = 4;
                this.influence = 0;
                this.card = fetch("/planetMORDAIII.png");
                break;
            case "Muaat":
                this.resources = 4;
                this.influence = 1;
                this.card = fetch("/planetMUAAT.png");
                break;
            case "Nar":
                this.resources = 2;
                this.influence = 3;
                this.card = fetch("/planetNAR.png");
                break;
            case "Nestphar":
                this.resources = 3;
                this.influence = 2;
                this.card = fetch("/planetNESTPHAR.png");
                break;
            case "New Albion":
                this.resources = 1;
                this.influence = 1;
                this.industrial = true;
                this.techGreen = true;
                this.card = fetch("/planetNEWALBION.png");
                break;
            case "Origo":
                this.resources = 5;
                this.influence = 0;
                this.card = fetch("/planetORIGO.png");
                break;
            case "Quann":
                this.resources = 2;
                this.influence = 1;
                this.cultural = true;
                this.card = fetch("/planetQUANN.png");
                break;
            case "Qucen'n":
                this.resources = 1;
                this.influence = 2;
                this.industrial = true;
                this.card = fetch("/planetQUCENN.png");
                break;
            case "Quinarra":
                this.resources = 3;
                this.influence = 1;
                this.card = fetch("/planetQUINARRA.png");
                break;
            case "Ragh":
                this.resources = 2;
                this.influence = 1;
                this.card = fetch("/planetRAGH.png");
                break;
            case "Rarron":
                this.resources = 0;
                this.influence = 3;
                this.cultural = true;
                this.card = fetch("/planetRARRON.png");
                break;
            case "Resculon":
                this.resources = 2;
                this.influence = 0;
                this.cultural = true;
                this.card = fetch("/planetRESCULON.png");
                break;
            case "Retillion":
                this.resources = 2;
                this.influence = 3;
                this.card = fetch("/planetRETILLION.png");
                break;
            case "Sakulag":
                this.resources = 2;
                this.influence = 1;
                this.hazardous = true;
                this.card = fetch("/planetSAKULAG.png");
                break;
            case "Saudor":
                this.resources = 2;
                this.influence = 2;
                this.industrial = true;
                this.card = fetch("/planetSAUDOR.png");
                break;
            case "Shalloq":
                this.resources = 1;
                this.influence = 2;
                this.card = fetch("/planetSHALLOQ.png");
                break;
            case "Starpoint":
                this.resources = 3;
                this.influence = 1;
                this.hazardous = true;
                this.card = fetch("/planetSTARPOINT.png");
                break;
            case "Tar'Mann":
                this.resources = 1;
                this.influence = 1;
                this.industrial = true;
                this.techGreen = true;
                this.card = fetch("/planetTARMANN.png");
                break;
            case "Tequ'Ran":
                this.resources = 2;
                this.influence = 0;
                this.hazardous = true;
                this.card = fetch("/planetTEQURAN.png");
                break;
            case "Thibah":
                this.resources = 1;
                this.influence = 1;
                this.industrial = true;
                this.techBlue = true;
                this.card = fetch("/planetTHIBAH.png");
                break;
            case "Torkan":
                this.resources = 0;
                this.influence = 3;
                this.cultural = true;
                this.card = fetch("/planetTORKAN.png");
                break;
            case "Tren'Lak":
                this.resources = 1;
                this.influence = 0;
                this.card = fetch("/planetTRENLAK.png");
                break;
            case "Vefut II":
                this.resources = 2;
                this.influence = 2;
                this.hazardous = true;
                this.card = fetch("/planetVEFUTII.png");
                break;
            case "Wellon":
                this.resources = 1;
                this.influence = 2;
                this.industrial = true;
                this.techYellow = true;
                this.card = fetch("/planetWELLON.png");
                break;
            case "Winnu":
                this.resources = 3;
                this.influence = 4;
                this.card = fetch("/planetWINNU.png");
                break;
            case "Wren Terra":
                this.resources = 2;
                this.influence = 1;
                this.card = fetch("/planetWRENTERRA.png");
                break;
            case "Xxehan":
                this.resources = 1;
                this.influence = 1;
                this.cultural = true;
                this.card = fetch("/planetXXEHAN.png");
                break;
            case "Zohbat":
                this.resources = 3;
                this.influence = 1;
                this.hazardous = true;
                this.card = fetch("/planetZOHBAT.png");
                break;
        }
        this.h.addNeutralPlanet(this);
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

    public void claim(Player p) {
        this.owner = p.getNumber();
        p.addPlanet(this);
        this.h.removePlanet(this);
    }

//////////////////////////////////GETTERS AND SETTERS///////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public boolean isTechRed() {
        return techRed;
    }

    public void setTechRed(boolean techRed) {
        this.techRed = techRed;
    }

    public boolean isTechBlue() {
        return techBlue;
    }

    public void setTechBlue(boolean techBlue) {
        this.techBlue = techBlue;
    }

    public boolean isTechGreen() {
        return techGreen;
    }

    public void setTechGreen(boolean techGreen) {
        this.techGreen = techGreen;
    }

    public boolean isTechYellow() {
        return techYellow;
    }

    public void setTechYellow(boolean techYellow) {
        this.techYellow = techYellow;
    }

    public boolean isHazardous() {
        return hazardous;
    }

    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    public boolean isIndustrial() {
        return industrial;
    }

    public void setIndustrial(boolean industrial) {
        this.industrial = industrial;
    }

    public boolean isCultural() {
        return cultural;
    }

    public void setCultural(boolean cultural) {
        this.cultural = cultural;
    }

    public BufferedImage getCard() {
        return card;
    }

    public void setCard(BufferedImage card) {
        this.card = card;
    }

}
