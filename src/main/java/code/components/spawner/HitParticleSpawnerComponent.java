package code.components.spawner;

import code.components.ComponentType;
import code.components.age.AgeComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.HitParticleIndustry;
import code.math.VectorF;
import code.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 6/06/17.
 */
public class HitParticleSpawnerComponent extends SpawnerComponent {
    private static final double HALF = 0.5;
    private float maxDeltaRot;
    private float maxSpeed;
    private int maxLife;

    public HitParticleSpawnerComponent(int rate, float maxDeltaRot, float maxSpeed, int maxLife) {
        super(SpawnerType.HITPARTICLE, rate);
        this.maxDeltaRot = maxDeltaRot;
        this.maxSpeed = maxSpeed;
        this.maxLife = maxLife;
    }

    public List<NEntity> spawn(NEntity entity){
        List<NEntity> temp = new ArrayList<>();
        HitParticleIndustry hpi = (HitParticleIndustry) Engine.getEngine().getIndustryMap().get("hit_particle_industry");
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent)entity.getComponent(ComponentType.PARTICLE);
        for (int i = 0; i < rate; i++) {
            int life = rand.nextInt(maxLife);
            float dir = rand.nextFloat() * (float) Constants.FULL_CIRCLE;
            float speed = rand.nextFloat() * maxSpeed;
            float deltaRot = (float) (rand.nextFloat() - HALF) * maxDeltaRot;
            float dx = (float)Math.sin(dir) * speed;
            float dy = (float)Math.cos(dir) * speed;
            temp.add(hpi.produce(
                    (PositionComponent)pc.clone(),
                    new AgeComponent(life, life),
                    new VelocityComponent(new VectorF(dx, dy), deltaRot),
                    new SpriteComponent(pac.getParticle(), pac.getScale())));
        }
        return temp;
    }

    public float getMaxDeltaRot() {
        return maxDeltaRot;
    }

    public void setMaxDeltaRot(float maxDeltaRot) {
        this.maxDeltaRot = maxDeltaRot;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
}