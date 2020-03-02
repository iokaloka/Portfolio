package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FactionPromissory {
    
    private BufferedImage icon;
    private String factionName;
    
    public FactionPromissory(String fn){
        this.factionName = fn;
        fetchByName(fn);
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
    
     public void fetchByName(String name) {
        switch (name) {
            case "The Arborec":
                this.icon = fetch("/promissoryArborec.png");
                break;
            case "The Ghost of Creuss":
                this.icon = fetch("/promissoryCreuss.png");
                break;
            case "The Emirates of Hacan":
                this.icon = fetch("/promissoryHacan.png");
                break;
            case "The Universities of Jol-Nar":
                this.icon = fetch("/promissoryJolNar.png");
                break;
            case "The L1z1x Mindnet":
                this.icon = fetch("/promissoryL1z1x.png");
                break;
            case "The Barony of Letnev":
                this.icon = fetch("/promissoryLetnev.png");
                break;
            case "The Mentak Coalition":
                this.icon = fetch("/promissoryMentak.png");
                break;
            case "The Embers of Muaat":
                this.icon = fetch("/promissoryMuaat.png");
                break;
            case "The Naalu Collective":
                this.icon = fetch("/promissoryNaalu.png");
                break;
            case "The Nekro Virus":
                this.icon = fetch("/promissoryNekro.png");
                break;
            case "The Clan of Saar":
                this.icon = fetch("/promissorySaar.png");
                break;
            case "Sardakk N'orr":
                this.icon = fetch("/promissorySardakk.png");
                break;
            case "The Federation of Sol":
                this.icon = fetch("/promissorySol.png");
                break;
            case "The Winnu":
                this.icon = fetch("/promissoryWinnu.png");
                break;
            case "The Xxcha Kingdom":
                this.icon = fetch("/promissoryXxcha.png");
                break;
            case "The Yin Brotherhood":
                this.icon = fetch("/promissoryYin.png");
                break;
            case "The Yssaril Tribes":
                this.icon = fetch("/promissoryYssaril.png");
                break;
            default:
                break;
        }
    }
    
///////////////////////////////GETTERS AND SETTERS//////////////////////////////

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
    
}
