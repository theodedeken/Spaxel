package code.factories;

import code.graphics.Sprite;
import code.projectiles.BasicMissile;
import code.projectiles.ClusterMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 9-5-2016.
 */
public class ClusterMissileFactory extends ProjectileFactory {
    public ClusterMissileFactory(Sprite sprite, Sprite trail, int damage, int life, float speed) {
        super(sprite,trail, damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new ClusterMissile(x, y, rot, sprite,trail, damage,life, speed);
    }
}
