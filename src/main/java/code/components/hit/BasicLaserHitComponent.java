package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.components.storage.renderable.RenderableStorage;
import code.entity.Entity;
import code.graphics.texture.Texture;
import code.util.SpriteDataUtil;

/**
 * Created by theo on 18/06/17.
 */
public class BasicLaserHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 3;

    public BasicLaserHitComponent(int damage) {
        super(HitType.BASIC_LASER, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        RenderableStorage sc = (RenderableStorage) victim.getComponent(ComponentType.RENDERABLE);
        addParticleSpawner(
                entity, new RenderableStorage(SpriteDataUtil
                        .getRandomPart((Texture) sc.getRenderable(), PARTICLE_SIZE, PARTICLE_SIZE)),
                "laser_hit_particle_spawner_industry");

        entity.destroy();
    }

    public Component copy() {
        return new BasicLaserHitComponent(damage);
    }
}
