package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theod on 10-7-2017.
 */
public class ShieldRenderer extends Renderer {
    private static final int ALPHA_REDUCER = 2;
    private static final double ALPHA_OFFSET = 0.15;

    public void apply(RenderData data, NEntity entity) {
        NEntity link = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
        ShieldItemComponent shc = (ShieldItemComponent) link.getComponent(ComponentType.ITEM);

        data.setAlpha(((double) shc.getCapacity() / shc.getMaxCapacity()) / ALPHA_REDUCER + ALPHA_OFFSET);
    }
}
