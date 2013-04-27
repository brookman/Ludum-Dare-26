package eu32k.ludumdare.ld26.util;

import com.badlogic.gdx.math.Rectangle;

public class CollisionUtil {

    public static boolean rectanglesOverlap(Rectangle r1, Rectangle r2) {
        if (r1.x < r2.x + r2.width &&
                r1.x + r1.width > r2.x &&
                r1.y < r2.y + r2.height &&
                r1.y + r1.height > r2.y)
            return true;
        else
            return false;
    }
}
