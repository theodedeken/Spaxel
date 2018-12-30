package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theo on 5/01/18.
 */
public class AbsoluteRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);

        data.setPos(pc.getCoord());
        data.setScale(sc.getSprite().getDim().multiplicate(sc.getScale()));
        data.setRot(pc.getRot());
    }
}
