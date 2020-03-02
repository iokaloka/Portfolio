package ti4v;

public class Dreadnought extends Unit{
    
    private Handler h;
    private Player p;
    private int cost;
    private int combat;
    private int dices;
    private int movement;
    private int capacity;
    private boolean ignorePlanetaryShield;
    private boolean sustainDamage;
    private boolean bombardment;
    private int bombardmentCombat;
    private int bombardmentDices;

    public Dreadnought(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(0);
        this.cost = 4;
        this.combat = 5;
        this.dices = 1;
        this.movement = 1;
        if (this.p.getFaction().getName().equals("The L1z1x Mindnet")) {
            this.capacity = 2;
        } else {
            this.capacity = 1;
        }
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = true;
        this.bombardmentCombat = 5;
        this.bombardmentDices = 1;

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(0, 0));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(0, 0));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(0, 0));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(0, 0));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(0, 0));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(0, 0));
        }
    }
    
}
