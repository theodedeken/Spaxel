package code.engine;

import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.graphics.MasterBuffer;
import code.math.VectorF;
import code.ui.UILabel;
import code.graphics.SpriteData;
import code.ui.UIBar;

/**
 * Created by theo on 31-5-2016.
 */
public class LoadingScreen {
    private SpriteData overlay;

    private UIBar progress;

    private UILabel message;

    private UILabel title;

    public LoadingScreen(){
        overlay = new SpriteData(1280, 720, 0xff000000);
        progress = new UIBar();
        progress.setPosition(new PositionComponent(new VectorF(320, 80), 1));
        progress.setWidth(640);
        progress.setSprite(new SpriteComponent(new SpriteData(1,8, 0xffffffff), 1));
        message = new UILabel();
        message.setPosition(new PositionComponent(new VectorF(640,40), 0));
        message.setScale(2);
        message.setText("");
        title = new UILabel();
        title.setPosition(new PositionComponent(new VectorF(640, 400), 0));
        title.setText("SPAXEL");
        title.setScale(16);
    }

    public UIBar getProgress(){
        return progress;
    }

    public UILabel getMessage(){
        return message;
    }

    public void render(MasterBuffer buffer){
        overlay.renderSprite(0,0,1, 0,1,false, buffer);
        progress.render(buffer);
        message.render(buffer);
        title.render(buffer);
    }
}
