package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

    private Handler h;
    private String warChief;
    private int number;
    private boolean speaker = false;
    private Faction faction;
    private String color = "White";
    private ArrayList<Unit> units;
    private ArrayList<Unit> unplacedUnits;
    private ArrayList<Planet> planets;
    private int unplacedDNCount = 0;
    private int reservedDN = 5;
    private int unplacedWarSunCount = 0;
    private int reservedWarSun = 2;
    private int unplacedFlagShip = 0;
    private int reservedFlagShip = 1;
    private int unplacedFighterCount = 0;
    private int reservedFighter = 10;
    private int unplacedDestroyerCount = 0;
    private int reservedDestroyer = 8;
    private int unplacedCarrierCount = 0;
    private int reservedCarrier = 4;
    private int unplacedCruiserCount = 0;
    private int reservedCruiser = 8;
    private int unplacedInfantryCount = 0;
    private int reservedInfantry = 12;
    private int unplacedSpaceDockCount = 0;
    private int reservedSpaceDock = 3;
    private int unplacedPDSCount = 0;
    private int reservedPDS = 6;
    private int tacticPool = 0;
    private int fleetPool = 0;
    private int strategyPool = 0;
    private int commandTokens = 16;
    private int controlTokens = 17;
    private BufferedImage unplacedBg;
    private BufferedImage commandSheet;
    private BufferedImage reinforcements;
    private Hud hud;

    public Player(Handler h, int number) {
        this.h = h;
        this.number = number;
        this.units = new ArrayList<>();
        this.unplacedUnits = new ArrayList<>();
        this.planets = new ArrayList<>();
        unplacedBg = h.getUnplacedBg();
    }

    public void addStartingPlanets() {
        for (String g : this.getFaction().startingPlanets()) {
            for (int i = 0; i < this.h.getNeutralPlanets().size(); i++) {
                if (this.h.getNeutralPlanets().get(i).getName().equals(g)) {
                    System.out.println(this.getFaction().getName() + " claims " + this.h.getNeutralPlanets().get(i).getName());
                    this.h.getNeutralPlanets().get(i).claim(this);
                    i--;
                }
            }
        }
    }

    public void addPlanet(Planet p) {
        this.planets.add(p);
    }

    public void removePlanet(Planet p) {
        for (int i = 0; i < this.planets.size(); i++) {
            if (this.planets.get(i).getName().equals(p.getName())) {
                this.planets.remove(i);
                i--;
            }
        }
    }

    public void addDreadnought() {
        System.out.println("Creating new Dreadnought");
        this.unplacedUnits.add(new Dreadnought(this));
        unplacedDNCount++;
    }

    public void addWarSun() {
        System.out.println("Creating new War Sun");
        this.unplacedUnits.add(new WarSun(this));
        unplacedWarSunCount++;
    }

    public void addFighter() {
        System.out.println("Creating new fighter");
        this.unplacedUnits.add(new Fighter(this));
        unplacedFighterCount++;
    }

    public void addDestroyer() {
        System.out.println("Creating new destroyer");
        this.unplacedUnits.add(new Destroyer(this));
        unplacedDestroyerCount++;
    }

    public void addCarrier() {
        System.out.println("Creating new carrier");
        this.unplacedUnits.add(new Carrier(this));
        unplacedCarrierCount++;
    }

    public void addCruiser() {
        System.out.println("Creating new cruiser");
        this.unplacedUnits.add(new Cruiser(this));
        unplacedCruiserCount++;
    }

    public void addInfantry() {
        System.out.println("Creating new infantry");
        this.unplacedUnits.add(new Infantry(this));
        unplacedInfantryCount++;
    }

    public void addSpaceDock() {
        System.out.println("Creating new SpaceDock");
        this.unplacedUnits.add(new SpaceDock(this));
        unplacedSpaceDockCount++;
    }

    public void addPDS() {
        System.out.println("Creating new PDS");
        this.unplacedUnits.add(new PDS(this));
        unplacedPDSCount++;
    }

    public void crateStartingUnits() {
        System.out.println(this.faction.getName());
        int[] createUnits = this.faction.getStartingUnits();
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addDreadnought();
                    }
                    break;
                case 1:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addWarSun();
                    }
                    break;
                case 2:
                    for (int j = 0; j < createUnits[i]; j++) {
                        //addFlagship();
                    }
                    break;
                case 3:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addFighter();
                    }
                    break;
                case 4:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addDestroyer();
                    }
                    break;
                case 5:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addCarrier();
                    }
                    break;
                case 6:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addCruiser();
                    }
                    break;
                case 7:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addInfantry();
                    }
                    break;
                case 8:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addSpaceDock();
                    }
                    break;
                case 9:
                    for (int j = 0; j < createUnits[i]; j++) {
                        addPDS();
                    }
                    break;

            }
        }
    }

    public void createHud() {
        this.hud = new Hud(this);
    }

    public void renderUnplaced(Graphics g) {
        g.drawImage(unplacedBg, 0, 0, null);
        int dreadnoughtToRender = 0;
        int warSunToRender = 0;

        int fighterToRender = 0;
        int destroyerToRender = 0;
        int carrierToRender = 0;
        int cruiserToRender = 0;
        int infantryToRender = 0;
        int spaceDockToRender = 0;
        int PDSToRender = 0;
        for (Unit u : this.unplacedUnits) {
            if (u.getTypeNumber() == 0) {
                u.render(g, 10 + dreadnoughtToRender * 94, u.getTypeNumber() * 106);
                dreadnoughtToRender++;
            } else if (u.getTypeNumber() == 1) {
                u.render(g, 10 + warSunToRender * 94, u.getTypeNumber() * 106);
                warSunToRender++;
            } else if (u.getTypeNumber() == 3) {
                u.render(g, 10 + fighterToRender * 94, u.getTypeNumber() * 106);
                fighterToRender++;
            } else if (u.getTypeNumber() == 4) {
                u.render(g, 10 + destroyerToRender * 94, u.getTypeNumber() * 106);
                destroyerToRender++;
            } else if (u.getTypeNumber() == 5) {
                u.render(g, 10 + carrierToRender * 94, u.getTypeNumber() * 106);
                carrierToRender++;
            } else if (u.getTypeNumber() == 6) {
                u.render(g, 10 + cruiserToRender * 94, u.getTypeNumber() * 106);
                cruiserToRender++;
            } else if (u.getTypeNumber() == 7) {
                u.render(g, 10 + infantryToRender * 94, u.getTypeNumber() * 106);
                infantryToRender++;
            } else if (u.getTypeNumber() == 8) {
                u.render(g, 10 + spaceDockToRender * 94, u.getTypeNumber() * 106);
                spaceDockToRender++;
            } else if (u.getTypeNumber() == 9) {
                u.render(g, 10 + PDSToRender * 94, u.getTypeNumber() * 106);
                PDSToRender++;
            }
        }
    }

    public void renderHud(Graphics g) {
        this.hud.render(g);
    }

    public String identify() {
        return "Player " + this.number;
    }

    @Override
    public String toString() {
        return identify() + ", Race: " + this.faction;
    }
///////////////////////////GETTERS AND SETTERS//////////////////////////////////

    public boolean isSpeaker() {
        return speaker;
    }

    public void setSpeaker(boolean speaker) {
        this.speaker = speaker;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWarChief() {
        return warChief;
    }

    public void setWarChief(String warChief) {
        this.warChief = warChief;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public Handler getH() {
        return h;
    }

    public int getUnplacedInfantryCount() {
        return unplacedInfantryCount;
    }

    public void setUnplacedInfantryCount(int unplacedInfantryCount) {
        this.unplacedInfantryCount = unplacedInfantryCount;
    }

    public ArrayList<Unit> getUnplacedUnits() {
        return unplacedUnits;
    }

    public void setUnplacedUnits(ArrayList<Unit> unplacedUnits) {
        this.unplacedUnits = unplacedUnits;
    }

    public int getTacticPool() {
        return tacticPool;
    }

    public void setTacticPool(int tacticPool) {
        this.tacticPool = tacticPool;
    }

    public int getFleetPool() {
        return fleetPool;
    }

    public void setFleetPool(int fleetPool) {
        this.fleetPool = fleetPool;
    }

    public int getStrategyPool() {
        return strategyPool;
    }

    public void setStrategyPool(int strategyPool) {
        this.strategyPool = strategyPool;
    }

    public int getCommandTokens() {
        return commandTokens;
    }

    public void setCommandTokens(int commandTokens) {
        this.commandTokens = commandTokens;
    }

    public int getControlTokens() {
        return controlTokens;
    }

    public void setControlTokens(int controlTokens) {
        this.controlTokens = controlTokens;
    }

    public BufferedImage getUnplacedBg() {
        return unplacedBg;
    }

    public void setUnplacedBg(BufferedImage unplacedBg) {
        this.unplacedBg = unplacedBg;
    }

    public BufferedImage getCommandSheet() {
        return commandSheet;
    }

    public void setCommandSheet(BufferedImage commandSheet) {
        this.commandSheet = commandSheet;
    }

    public Hud getHud() {
        return hud;
    }

    public void setHud(Hud hud) {
        this.hud = hud;
    }

    public BufferedImage getReinforcements() {
        return reinforcements;
    }

    public void setReinforcements(BufferedImage reinforcements) {
        this.reinforcements = reinforcements;
    }

    public int getReservedInfantry() {
        return reservedInfantry;
    }

    public int getUnplacedDNCount() {
        return unplacedDNCount;
    }

    public void setUnplacedDNCount(int unplacedDNcount) {
        this.unplacedDNCount = unplacedDNcount;
    }

    public int getReservedDN() {
        return reservedDN;
    }

    public void setReservedDN(int reservedDN) {
        this.reservedDN = reservedDN;
    }

    public int getUnplacedWarSunCount() {
        return unplacedWarSunCount;
    }

    public void setUnplacedWarSunCount(int unplacedWarSun) {
        this.unplacedWarSunCount = unplacedWarSun;
    }

    public int getReservedWarSun() {
        return reservedWarSun;
    }

    public void setReservedWarSun(int reservedWarSun) {
        this.reservedWarSun = reservedWarSun;
    }

    public int getUnplacedFlagShip() {
        return unplacedFlagShip;
    }

    public void setUnplacedFlagShip(int unplacedFlagShip) {
        this.unplacedFlagShip = unplacedFlagShip;
    }

    public int getReservedFlagShip() {
        return reservedFlagShip;
    }

    public void setReservedFlagShip(int reservedFlagship) {
        this.reservedFlagShip = reservedFlagship;
    }

    public int getUnplacedFighterCount() {
        return unplacedFighterCount;
    }

    public void setUnplacedFighterCount(int unplacedFighterCount) {
        this.unplacedFighterCount = unplacedFighterCount;
    }

    public int getReservedFighter() {
        return reservedFighter;
    }

    public void setReservedFighter(int reservedFighter) {
        this.reservedFighter = reservedFighter;
    }

    public int getUnplacedDestroyerCount() {
        return unplacedDestroyerCount;
    }

    public void setUnplacedDestroyerCount(int unplacedDestroyer) {
        this.unplacedDestroyerCount = unplacedDestroyer;
    }

    public int getReservedDestroyer() {
        return reservedDestroyer;
    }

    public void setReservedDestroyer(int reservedDestroyer) {
        this.reservedDestroyer = reservedDestroyer;
    }

    public int getUnplacedCarrierCount() {
        return unplacedCarrierCount;
    }

    public void setUnplacedCarrierCount(int unplacedCarrierCount) {
        this.unplacedCarrierCount = unplacedCarrierCount;
    }

    public int getReservedCarrier() {
        return reservedCarrier;
    }

    public void setReservedCarrier(int reservedCarrier) {
        this.reservedCarrier = reservedCarrier;
    }

    public int getUnplacedCruiserCount() {
        return unplacedCruiserCount;
    }

    public void setUnplacedCruiserCount(int unplcedCruiserCount) {
        this.unplacedCruiserCount = unplcedCruiserCount;
    }

    public int getReservedCruiser() {
        return reservedCruiser;
    }

    public void setReservedCruiser(int reservedCruiser) {
        this.reservedCruiser = reservedCruiser;
    }

    public int getUnplacedSpaceDockCount() {
        return unplacedSpaceDockCount;
    }

    public void setUnplacedSpaceDockCount(int unplacedSpaceDock) {
        this.unplacedSpaceDockCount = unplacedSpaceDock;
    }

    public int getReservedSpaceDock() {
        return reservedSpaceDock;
    }

    public void setReservedSpaceDock(int reservedSpaceDock) {
        this.reservedSpaceDock = reservedSpaceDock;
    }

    public int getUnplacedPDSCount() {
        return unplacedPDSCount;
    }

    public void setUnplacedPDSCount(int unplacedPDSCount) {
        this.unplacedPDSCount = unplacedPDSCount;
    }

    public int getReservedPDS() {
        return reservedPDS;
    }

    public void setReservedPDS(int reservedPDS) {
        this.reservedPDS = reservedPDS;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }
    
    

}
