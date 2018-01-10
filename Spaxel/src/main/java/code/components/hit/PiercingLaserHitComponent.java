package code.components.hit;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.NEntity;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 28-6-2017.
 */
public class PiercingLaserHitComponent extends HitComponent {
    public PiercingLaserHitComponent(int damage) {
        super(HitType.PIERCING_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim,new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(),3,3), sc.getScale()), "laser_hit_particle_spawner_industry");
    }
}
