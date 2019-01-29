package code.components.behaviour.render.renderers;

import code.entity.Entity;
import code.graphics.buffer.RenderJob;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by theo on 5/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = SpriteSheetRenderer.class, name = "RENDERABLESHEET"),
        @JsonSubTypes.Type(value = VelocityRenderer.class, name = "VELOCITY"),
        @JsonSubTypes.Type(value = StationaryRenderer.class, name = "STATIONARY"),
        @JsonSubTypes.Type(value = FadeRenderer.class, name = "FADE"),
        @JsonSubTypes.Type(value = LinkLinkVelocityRenderer.class, name = "LINK_LINK_VELOCITY"),
        @JsonSubTypes.Type(value = ShieldRenderer.class, name = "SHIELD"),
        @JsonSubTypes.Type(value = ShipFragmentRenderer.class, name = "SHIP_FRAGMENT"),
        @JsonSubTypes.Type(value = AbsoluteRenderer.class, name = "ABSOLUTE"),
        @JsonSubTypes.Type(value = MarkerRenderer.class, name = "MARKER")})
public abstract class Renderer {
    private RendererType type;

    public abstract void apply(RenderJob data, Entity entity);

    public RendererType getType() {
        return type;
    }

    public void setType(RendererType type) {
        this.type = type;
    }
}