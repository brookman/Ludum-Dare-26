package eu32k.libgdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

/**
 * Profile operations.
 */
public class ProfileService {

   private IOWriter io;
   private String filename;
   // the location of the profile data file

   // the loaded profile (may be null)
   private Profile profile;

   /**
    * Creates the profile service.
    */
   public ProfileService(IOWriter writer, String filename) {
      this.io = writer; 
      this.filename = filename;
   }

   /**
    * Retrieves the player's profile, creating one if needed.
    */
   public Profile retrieveProfile() {
      if (profile != null)
         return profile;
      Json json = new Json();
      try {
         String profileAsCode = io.readFile(filename);
         if (profileAsCode == null || profileAsCode == "") {
            profile = new Profile();
         } else {
            deserialize(json, profileAsCode);
         }
      } catch (Exception e) {
         profile = new Profile();
         persist(profile);
      }
      if(profile == null){
         profile = new Profile();
      }
      return profile;
   }


   private void deserialize(Json json, String profileAsCode) {
      // decode the contents
      String profileAsText = Base64Coder.decodeString(profileAsCode);

      // restore the state
      profile = json.fromJson(Profile.class, profileAsText);
   }


   /**
    * Persists the given profile.
    */
   protected void persist(Profile profile) {
      // Gdx.app.log( Tyrian.LOG, "Persisting profile" );

      // create the JSON utility object
      Json json = new Json();

      // create the handle for the profile data file


      // convert the given profile to text
      String profileAsText = json.toJson(profile);

      // encode the text
      String profileAsCode = Base64Coder.encodeString(profileAsText);

      // write the profile data file
      try
      {
         io.writeFile( filename, profileAsCode);
      }
      catch(Exception e){
         Gdx.app.exit();
         e.printStackTrace();
      }
   }

   /**
    * Persists the player's profile.
    * <p>
    * If no profile is available, this method does nothing.
    */
   public void persist() {
      if (profile != null) {
         persist(profile);
      }
   }
}