package guiltspawn.entity;

import guiltspawn.engine.TileMap;

public abstract class Enemy extends MapObject{

    protected int health, maxHealth, damage;
    private boolean dead;
    
    public Enemy(TileMap tm){
        super(tm);
    }
    
    ///////////////////////////////GETTERS AND SETTERS//////////////////////////
    
    public boolean getDead(){
        return this.dead;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    public void hit(int damage){
        if(dead) return;
        health -= damage;
        if(health < 0) health = 0;
        if(health == 0) dead = true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
      
}
