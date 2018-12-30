package code.components.marker;

import code.Constants;
import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.LineSegment;
import code.math.VectorD;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerComponent extends Component {
    private static final int MARKER_THRESHOLD = 50;
    private static final int MARKER_OFFSET = 20;

    private NEntity marker;
    private String markerIndustry;

    public MarkerComponent(String markerIndustry) {
        super(ComponentType.MARKER);
        this.marker = Engine.getEngine().getIndustryMap().get(markerIndustry).produce();
    }

    public void update(NEntity entity) {
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        PositionComponent playerPos = (PositionComponent) player.getComponent(ComponentType.POSITION);
        PositionComponent entityPos = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        RenderComponent mrc = (RenderComponent) marker.getComponent(ComponentType.RENDER);
        VectorD renderPos = entityPos.getCoord().sum(Engine.getEngine().getScreenOffset());
        if (renderPos.getValue(0) < -MARKER_THRESHOLD || renderPos.getValue(0) > Constants.GAME_WIDTH + MARKER_THRESHOLD
                || renderPos.getValue(1) < -MARKER_THRESHOLD
                || renderPos.getValue(1) > Constants.GAME_HEIGHT + MARKER_THRESHOLD) {
            VectorD intersect = getIntersection(new VectorD(MARKER_OFFSET, MARKER_OFFSET),
                    new VectorD(Constants.GAME_WIDTH - MARKER_OFFSET, Constants.GAME_HEIGHT - MARKER_OFFSET),
                    playerPos.getCoord().sum(Engine.getEngine().getScreenOffset()), renderPos);

            if (intersect != null) {
                mrc.setVisible(true);
                VectorD diff = entityPos.getCoord().diff(playerPos.getCoord());
                double rot = Math.atan2(diff.getValue(0), diff.getValue(1));
                PositionComponent mpc = (PositionComponent) marker.getComponent(ComponentType.POSITION);
                mpc.setCoord(intersect);
                mpc.setRot(rot);
            }
        } else {
            mrc.setVisible(false);
        }
    }

    public NEntity getMarker() {
        return marker;
    }

    public void setMarker(NEntity markerEntity) {
        this.marker = markerEntity;
    }

    public String getMarkerIndustry() {
        return markerIndustry;
    }

    public void setMarkerIndustry(String markerIndustry) {
        this.markerIndustry = markerIndustry;
    }

    @Override
    public void addCascade(NEntity entity) {
        Engine.getEngine().getNEntityStream().addEntity(marker);
    }

    @Override
    public void removeCascade() {
        Engine.getEngine().getNEntityStream().removeEntity(marker);
    }

    private static VectorD getIntersection(VectorD leftTop, VectorD rightBot, VectorD playerPos, VectorD enemyPos) {
        LineSegment toEnemy = new LineSegment(playerPos, enemyPos);
        LineSegment a = new LineSegment(leftTop, new VectorD(leftTop.getValue(0), rightBot.getValue(1)));
        LineSegment b = new LineSegment(new VectorD(leftTop.getValue(0), rightBot.getValue(1)), rightBot);
        LineSegment c = new LineSegment(rightBot, new VectorD(rightBot.getValue(0), leftTop.getValue(1)));
        LineSegment d = new LineSegment(new VectorD(rightBot.getValue(0), leftTop.getValue(1)), leftTop);
        LineSegment[] borders = { a, b, c, d };
        for (LineSegment border : borders) {
            VectorD intersect = toEnemy.intersection(border);
            if (intersect != null) {
                return intersect;
            }

        }
        return null;
    }

    public Component copy() {
        return new MarkerComponent(markerIndustry);
    }
}
