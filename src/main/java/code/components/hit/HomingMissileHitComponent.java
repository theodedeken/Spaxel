package code.components.hit;

import code.components.ComponentType;
import code.components.Component;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.util.SpriteDataUtil;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 4;

    public HomingMissileHitComponent(int damage) {
        super(HitType.HOMING_MISSILE, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, 
                new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "missile_hit_particle_spawner_industry");

        Engine.get().getNEntityStream().removeEntity(entity);
    }

    public Component copy() {
        return new HomingMissileHitComponent(damage);
    }
}
