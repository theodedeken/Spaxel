package code.projectiles;

import code.entity.Enemy;
import code.graphics.Sprite;
import code.inventory.SpeedEffect;

/**
 * Created by theo on 12-5-2016.
 */
public class SlowingLaser extends Projectile {
    public SlowingLaser(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }

    public void hit(Enemy e){
        super.hit(e);
        e.addStatusEffect(new SpeedEffect(100, .5));
    }
}