package eu32k.ludumdare.ld26.intensityDataGenerator;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Generator {

	public static void main(String[] args) throws Exception {
		float[] result = loadImage("data/color shape 01.png");
//		float[] result = loadImage("data/test.png");
		System.out.print("new float[]{");
		for(int x = 0; x < result.length; x++)
		{
			if(x > 0)
			{
				System.out.print(", ");
			}
			System.out.print(String.format("%ff", result[x]));
		}
		System.out.println("}");
	}

	public static float[] loadImage(String path) throws Exception {
		BufferedImage i = ImageIO.read(new File(path));

		int width = i.getWidth(null);
		int height = i.getHeight(null);
		float[] result = new float[width];
		
		for (int x = 0; x < width; x++) {
			int distance = 0;
			boolean doContinue = true;
			int y = 0;
			while (doContinue && y < height) {
				int color = i.getRGB(x, y);
				int alpha = (color>>24) & 0xff;
				if(alpha > 0)
				{
					doContinue = false;
				}
				else
				{
					y++;
				}
			}
			result[x] = 1f - (1f * y / height);
		}
		return result;
	}
}
