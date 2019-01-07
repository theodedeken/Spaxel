package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.entity.EntityType;
import code.entity.Entity;

import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class TrailSegmentIndustry extends EntityIndustry {
    public Entity produce(PositionComponent pc, SpriteComponent rc) {
        Entity entity = new Entity(EntityType.HITPARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(rc.getType(), rc);
        entity.setComponents(components);
        return entity;
    }
}
