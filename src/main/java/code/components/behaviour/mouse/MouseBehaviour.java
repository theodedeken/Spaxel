package code.components.behaviour.mouse;

import code.components.Behaviour;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;

public class MouseBehaviour extends Behaviour {
    private MouseHandler handler;

    public MouseBehaviour() {
        super(ComponentType.MOUSE);
    }

    public void execute(Entity entity) {
        handler.handle(entity, Engine.get().getMouseWrapper());
    }

    /**
     * @return the handler
     */
    public MouseHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(MouseHandler handler) {
        this.handler = handler;
    }

    public MouseBehaviour copy() {
        return new MouseBehaviour();
    }
}
