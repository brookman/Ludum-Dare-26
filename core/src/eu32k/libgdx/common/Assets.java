package eu32k.libgdx.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Assets {
   public static final AssetManager MANAGER = new AssetManager();

   public static void loadTexture(String path) {
      TextureParameter param = new TextureParameter();
      param.minFilter = TextureFilter.MipMapLinearLinear;
      param.magFilter = TextureFilter.MipMapLinearLinear;
      param.genMipMaps = true;
      MANAGER.load(path, Texture.class, param);
   }
}
