package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Technology {

    private BufferedImage icon;
    private String techName;
    private String faction;
    private boolean active;
    private boolean factionTechnology;

    public Technology(String name, String faction, boolean factionTechnology) {
        this.techName = name;
        this.faction = faction;
        this.active = true;
        if (factionTechnology) {
            this.active = false;
        }
        this.icon = fetch("/" + name + ".png");

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

    public void takeEffect(String name) {
        switch (name) {
            case "bioplasmosis":
                break;
            case "letaniWarriorII":
                break;
            case "wormholeGenerator":
                break;
            case "dimensionalSplicer":
                break;
            case "productionBiomes":
                break;
            case "quantumDatahubNode":
                break;
            case "specialConduitCylinder":
                break;
            case "e-resSiphons":
                break;
            case "inheritanceSystems":
                break;
            case "super-dreadnoughtII":
                break;
            case "l4Disruptors":
                break;
            case "non-euclideanShielding":
                break;
            case "mirrorComputing":
                break;
            case "salvageOperations":
                break;
            case "magmusReactor":
                break;
            case "prototypeWarSunII":
                break;
            case "neuroglaive":
                break;
            case "hybridCrystalFighterII":
                break;
            case "valefarAssimilatorX":
                break;
            case "valefarAssimilatorY":
                break;
            case "chaosMapping":
                break;
            case "floatingFactoryII":
                break;
            case "valkyrieParticleWeave":
                break;
            case "exotriremeII":
                break;
            case "specOpsII":
                break;
            case "advancedCarrierII":
                break;
            case "lazaxGateFolding":
                break;
            case "hegemonicTradePolicy":
                break;
            case "nullificationField":
                break;
            case "instinctTraining":
                break;
            case "impulseCore":
                break;
            case "yinSpinner":
                break;
            case "mageonImplants":
                break;
            case "transparasteelPlating":
                break;
        }
    }

////////////////////////GETTERS AND SETTERS/////////////////////////////////////
    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFactionTechnology() {
        return factionTechnology;
    }

    public void setFactionTechnology(boolean factionTechnology) {
        this.factionTechnology = factionTechnology;
    }

}
