package code.projectiles;

import code.entity.Enemy;
import code.graphics.Sprite;
import code.inventory.ShootEffect;
import code.inventory.SpeedEffect;

/**
 * Created by theo on 13-5-2016.
 */
public class HackingMissile extends Projectile {
    public HackingMissile(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }

    public void hit(Enemy e){
        e.addStatusEffect(new ShootEffect(150));
        e.addStatusEffect(new SpeedEffect(150, 0));
        super.hit(e);
    }
}