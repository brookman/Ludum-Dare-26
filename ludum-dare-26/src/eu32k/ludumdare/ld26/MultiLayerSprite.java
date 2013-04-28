package eu32k.ludumdare.ld26;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MultiLayerSprite extends Sprite {

   private TextureRegion[] layers;

   public MultiLayerSprite(TextureRegion... layers) {
      super(layers[0]);
      this.layers = layers;
   }

   public void activateLayer(int i) {
      setRegion(layers[i]);
   }

   public int getNumberOfLayers() {
      return layers.length;
   }
}
