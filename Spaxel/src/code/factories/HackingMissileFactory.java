package code.factories;

import code.graphics.Sprite;
import code.projectiles.HackingMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 13-5-2016.
 */
public class HackingMissileFactory extends ProjectileFactory {
    public HackingMissileFactory(Sprite sprite, int damage, int life, double speed) {
        super(sprite, damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new HackingMissile(x, y, rot, sprite, damage, life, speed);
    }
}