package code.components.behaviour.mouse;

import code.entity.Entity;
import code.input.MouseWrapper;

public abstract class MouseHandler {
    private MouseHandlerType type;

    public MouseHandler(MouseHandlerType type) {
        this.type = type;
    }

    public abstract void handle(Entity entity, MouseWrapper mouse);

    /**
     * @return the type
     */
    public MouseHandlerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(MouseHandlerType type) {
        this.type = type;
    }


}
