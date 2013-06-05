package eu32k.libgdx.common;

public interface IOWriter {
   public String readFile(String filename) throws Exception;
   public void writeFile(String filename, String content) throws Exception;
}
