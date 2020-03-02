package ti4v;

import java.awt.image.BufferedImage;

public class Infantry extends Unit {

    private Handler h;
    private Player p;
    private int cost;
    private int combat;
    private int movement;
    private int capacity;
    private boolean sustainDamage;
    private boolean gf;

    public Infantry(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(7);
        this.cost = 1;
        this.combat = 7;
        this.combat = 8;
        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(1, 4));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(1, 4));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(1, 4));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(1, 4));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(1, 4));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(1, 4));
        }
    }

    public void addCost() {
        this.cost++;
    }

    public void reduceCost() {
        this.cost--;
    }

    public void addCombat() {
        this.combat++;
    }

    public void reduceCombat() {
        this.combat--;
    }

    public void addMovement() {
        this.movement++;
    }

    public void reduceMovement() {
        this.movement--;
    }

    public void addCapacity() {
        this.capacity++;
    }

    public void reduceCapacity() {
        this.capacity--;
    }

//////////////////////////GETTERS AND SETTERS///////////////////////////////////

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isSustainDamage() {
        return sustainDamage;
    }

    public void setSustainDamage(boolean sustainDamage) {
        this.sustainDamage = sustainDamage;
    }

    public boolean isGf() {
        return gf;
    }

    public void setGf(boolean gf) {
        this.gf = gf;
    }

}
