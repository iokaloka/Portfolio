package ti4v;

public class Cruiser extends Unit {

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
    private boolean antiFighterBarrage;
    private int bombardmentCombat;
    private int bombardmentDices;
    private int antiFighterBarrageCombat;
    private int antiFighterBarrageDices;

    public Cruiser(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(6);
        this.cost = 2;
        this.combat = 7;
        this.dices = 1;
        this.movement = 2;
        this.capacity = 0;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = false;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(1, 3));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(1, 3));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(1, 3));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(1, 3));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(1, 3));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(1, 3));
        }
    }

}
