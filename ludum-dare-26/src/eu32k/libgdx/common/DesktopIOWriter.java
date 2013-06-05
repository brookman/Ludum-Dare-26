package eu32k.libgdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class DesktopIOWriter implements IOWriter {
   @Override
   public String readFile(String filename) {
      String profileAsCode = null;
      FileHandle profileDataFile = Gdx.files.external(filename);
      if (profileDataFile.exists()) {
         profileAsCode = profileDataFile.readString();
      }
      return profileAsCode;
   }
   @Override
   public void writeFile(String filename, String content) {
      FileHandle profileDataFile = Gdx.files.external(filename);
      profileDataFile.writeString(content, false);
   }

}
