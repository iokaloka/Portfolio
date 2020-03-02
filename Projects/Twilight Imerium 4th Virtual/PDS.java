package ti4v;

public class PDS extends Unit {

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
    private boolean factory;
    private boolean ship;
    private boolean planetaryShield;
    private boolean spaceCannon;
    private int bombardmentCombat;
    private int bombardmentDices;
    private int antiFighterBarrageCombat;
    private int antiFighterBarrageDices;
    private int production;
    private int spaceCannonCombat;

    public PDS(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(9);
        this.cost = 0;
        this.combat = 0;
        this.dices = 0;
        this.movement = 0;
        this.capacity = 0;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = false;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = false;
        this.planetaryShield = true;
        this.spaceCannon = true;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 6;
        this.production = 0;

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(0, 4));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(0, 4));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(0, 4));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(0, 4));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(0, 4));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(0, 4));
        }
    }

}
