package code.util;

import code.graphics.SpriteData;

import java.util.Random;

/**
 * Created by theo on 15/07/17.
 */
public class SpriteDataUtil {

    private static Random random = new Random();

    public static SpriteData getRandomPart(SpriteData spriteData, int width, int height){
        int x = random.nextInt(spriteData.getWidth() - width);
        int y = random.nextInt(spriteData.getHeight() - height);
        return new SpriteData(width, height, spriteData.getxPos() + x, spriteData.getyPos() + y, spriteData.getSpritesheet());
    }
}
