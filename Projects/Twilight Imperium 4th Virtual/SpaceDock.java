package ti4v;

public class SpaceDock extends Unit {

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
    private int bombardmentCombat;
    private int bombardmentDices;
    private int antiFighterBarrageCombat;
    private int antiFighterBarrageDices;
    private int production;

    public SpaceDock(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(8);
        this.cost = 0;
        this.combat = 0;
        this.dices = 0;
        if (this.p.getFaction().getName().equals("The Clan of Saar")) {
            this.movement = 1;
            this.capacity = 4;
            this.ship = true;
        } else {
            this.movement = 0;
            this.capacity = 0;
            this.ship = false;
        }
        this.ignorePlanetaryShield = false;
        this.sustainDamage = false;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = true;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.production = 3;

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(0, 3));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(0, 3));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(0, 3));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(0, 3));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(0, 3));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(0, 3));
        }
    }

}
