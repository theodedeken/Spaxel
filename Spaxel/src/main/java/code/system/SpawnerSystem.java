package code.system;

import code.components.ComponentType;
import code.components.spawner.SpawnerComponent;
import code.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by theo on 14-6-2016.
 */
public class SpawnerSystem extends GameSystem {
    public SpawnerSystem() {
        super(SystemType.SPAWNER);
    }

    public void update(){
        //update all spawners and acquire particles
        NEntityStream nentities = Engine.getEngine().getNEntityStream();
        Set<NEntity> spawners = nentities.getEntities(ComponentType.SPAWNER);
        List<NEntity> newParticles = new ArrayList<>();
        for (NEntity ne: spawners){
            newParticles.addAll(((SpawnerComponent)ne.getComponent(ComponentType.SPAWNER)).spawn(ne));
        }
        nentities.addEntities(EntityType.HITPARTICLE, newParticles);
    }


}
