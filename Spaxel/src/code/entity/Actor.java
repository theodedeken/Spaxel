package code.entity;

import code.graphics.Sprite;
import code.inventory.Item;
import code.inventory.StatusEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 27-5-2016.
 */
public class Actor extends Entity {
    protected int health;
    protected int maxHealth;
    protected Sprite sprite;
    protected double maxspeed;
    protected double acc;
    protected double xdir;
    protected double ydir;
    protected boolean canshoot;
    protected List<StatusEffect> effects;
    protected List<Item> mouse1Items;
    protected List<Item> mouse3Items;
    protected List<Item> shipItems;

    public Actor(double x, double y, double rot,int health, Sprite sprite, double maxspeed, double acc){
        super(x, y, rot);
        this.health = health;
        this.sprite = sprite;
        this.maxspeed = maxspeed;
        this.acc = acc;
        maxHealth = health;
        effects = new ArrayList<>();
        mouse1Items = new ArrayList<>();
        mouse3Items = new ArrayList<>();
        shipItems = new ArrayList<>();
        xdir =0;
        ydir = 0;
        canshoot = true;
        this.life = -1;
    }

    public void update(){
        super.update();
    }

    public boolean controlSpeed(double dx, double dy) {
        double speed = Math.sqrt(dx * dx + dy * dy);
        return speed < maxspeed;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    public void addStatusEffect(StatusEffect effect){
        synchronized (effects){
            effect.affect(this);
            effects.add(effect);
        }
    }



    public Sprite getSprite(){
        return sprite;
    }

    public double getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(double maxspeed) {
        this.maxspeed = maxspeed;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public boolean getCanShoot() {
        return canshoot;
    }

    public void setCanshoot(boolean canshoot) {
        this.canshoot = canshoot;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
}
