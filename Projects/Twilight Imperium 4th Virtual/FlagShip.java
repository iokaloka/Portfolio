package ti4v;

public class FlagShip extends Unit {

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
    private int spaceCannonDices;
    
    private boolean arborec = false;
    private boolean creuss = false;
    private boolean hacan = false;
    private boolean jolnar = false;
    private boolean l1z1x = false;
    private boolean letnev = false;
    private boolean mentak = false;
    private boolean muaat = false;
    private boolean naalu = false;
    private boolean nekro = false;
    private boolean saar = false;
    private boolean sardakk = false;
    private boolean sol = false;
    private boolean winnu = false;
    private boolean xxcha = false;
    private boolean yin = false;
    private boolean yssaril = false;

    public FlagShip(Player p) {
        this.h = p.getH();
        this.p = p;
        super.setTypeNumber(2);
        createFlagShip(this.p.getFaction().getName());

        if (p.getColor().equals("Red")) {
            super.setIcon(this.h.getRedUnitIcons().unit(0, 2));
        } else if (p.getColor().equals("Blue")) {
            super.setIcon(this.h.getBlueUnitIcons().unit(0, 2));
        } else if (p.getColor().equals("Green")) {
            super.setIcon(this.h.getGreenUnitIcons().unit(0, 2));
        } else if (p.getColor().equals("Yellow")) {
            super.setIcon(this.h.getYellowUnitIcons().unit(0, 2));
        } else if (p.getColor().equals("Purple")) {
            super.setIcon(this.h.getPurpleUnitIcons().unit(0, 2));
        } else if (p.getColor().equals("Black")) {
            super.setIcon(this.h.getBlackUnitIcons().unit(0, 2));
        }
    }

    public void createFlagShip(String faction) {
        switch (faction) {
            case "The Arborec":
                createArborecFlagShip();
                break;
            case "The Ghost of Creuss":
                createCreussFlagShip();
                break;
            case "The Emirates of Hacan":
                createHacanFlagShip();
                break;
            case "The Universities of Jol-Nar":
                createJolNarFlagShip();
                break;
            case "The L1z1x Mindnet":
                createL1z1xFlagShip();
                break;
            case "The Barony of Letnev":
                createLetnevFlagShip();
                break;
            case "The Mentak Coalition":
                createMentakFlagShip();
                break;
            case "The Embers of Muaat":
                createMuaatFlagShip();
                break;
            case "The Naalu Collective":
                createNaaluFlagShip();
                break;
            case "The Nekro Virus":
                createNekroFlagShip();
                break;
            case "The Clan of Saar":
                createSaarFlagShip();
                break;
            case "Sardakk N'orr":
                createSardakkFlagShip();
                break;
            case "The Federation of Sol":
                createSolFlagShip();
                break;
            case "The Winnu":
                createWinnuFlagShip();
                break;
            case "The Xxcha Kingdom":
                createXxchaFlagShip();
                break;
            case "The Yin Brotherhood":
                createYinFlagShip();
                break;
            case "The Yssaril Tribes":
                createYssarilFlagShip();
                break;
        }
    }
    
    public void createArborecFlagShip(){
        this.cost = 8;
        this.combat = 7;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 5;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.arborec = true;
    }
    
    public void createCreussFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 1;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.creuss = true;
    }
    
    public void createHacanFlagShip(){
        this.cost = 8;
        this.combat = 7;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.hacan = true;
    }
    
    public void createJolNarFlagShip(){
        this.cost = 8;
        this.combat = 6;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.jolnar = true;
    }
    
    public void createL1z1xFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 5;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.l1z1x = true;
    }
    
    public void createLetnevFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = true;
        this.sustainDamage = true;
        this.bombardment = true;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 5;
        this.bombardmentDices = 3;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.letnev = true;
    }
    
    public void createMentakFlagShip(){
        this.cost = 8;
        this.combat = 7;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.mentak = true;
    }
    
    public void createMuaatFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.muaat = true;
    }
    
    public void createNaaluFlagShip(){
        this.cost = 8;
        this.combat = 9;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 6;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.naalu = true;
    }
    
    public void createNekroFlagShip(){
        this.cost = 8;
        this.combat = 9;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.nekro = true;
    }
    
    public void createSaarFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = true;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 6;
        this.antiFighterBarrageDices = 4;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.saar = true;
    }
    
    public void createSardakkFlagShip(){
        this.cost = 8;
        this.combat = 6;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.sardakk = true;
    }
    
    public void createSolFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 12;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.sol = true;
    }
    
    public void createWinnuFlagShip(){
        this.cost = 8;
        this.combat = 7;
        this.dices = 1;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.winnu = true;
    }
    
    public void createXxchaFlagShip(){
        this.cost = 8;
        this.combat = 7;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = true;
        this.spaceCannon = true;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 5;
        this.spaceCannonDices = 3;
        this.production = 0;
        this.xxcha = true;
    }
    
    public void createYinFlagShip(){
        this.cost = 8;
        this.combat = 9;
        this.dices = 2;
        this.movement = 1;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = true;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.yin = true;
    }
    
    public void createYssarilFlagShip(){
        this.cost = 8;
        this.combat = 5;
        this.dices = 2;
        this.movement = 2;
        this.capacity = 3;
        this.ignorePlanetaryShield = false;
        this.sustainDamage = true;
        this.bombardment = false;
        this.antiFighterBarrage = false;
        this.factory = false;
        this.ship = true;
        this.planetaryShield = false;
        this.spaceCannon = false;
        this.bombardmentCombat = 0;
        this.bombardmentDices = 0;
        this.antiFighterBarrageCombat = 0;
        this.antiFighterBarrageDices = 0;
        this.spaceCannonCombat = 0;
        this.production = 0;
        this.yssaril = true;
    }

}
