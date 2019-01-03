package code.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import code.components.ComponentType;
import code.components.spawner.SpawnerComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.NEntityStream;
import code.engine.SystemType;

/**
 * The SpawnerSystem is responsible for updating the entities with SpawnerComponents
 * 
 * Created by theo on 14-6-2016.
 */
public class SpawnerSystem extends GameSystem {
    /**
     * Create a new SpawnerSystem
     */
    public SpawnerSystem() {
        super(SystemType.SPAWNER);
    }

    public void update() {
        // update all spawners and acquire particles
        NEntityStream nentities = Engine.get().getNEntityStream();
        Set<NEntity> spawners = nentities.getEntities(ComponentType.SPAWNER);
        List<NEntity> newParticles = new ArrayList<>();
        for (NEntity ne : spawners) {
            newParticles
                    .addAll(((SpawnerComponent) ne.getComponent(ComponentType.SPAWNER)).spawn(ne));
        }
        nentities.addEntities(EntityType.HITPARTICLE, newParticles);
    }

}
