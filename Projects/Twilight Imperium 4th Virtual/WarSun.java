package ti4v;

public class WarSun extends Unit {

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

    public WarSun(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(1);
        this.cost = 12;
        this.combat = 3;
        this.dices = 3;
        if (this.p.getFaction().getName().equals("The Embers of Muaat")) {
            this.movement = 1;
        } else {
            this.movement = 2;
        }
        this.capacity = 6;
        this.ignorePlanetaryShield = true;
        this.sustainDamage = true;
        this.bombardment = true;
        this.bombardmentCombat = 3;
        this.bombardmentDices = 3;

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(0, 1));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(0, 1));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(0, 1));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(0, 1));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(0, 1));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(0, 1));
        }
    }

}
