package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static Image getImage(String filename, int width, int height) {
		Image image=null;
		
		try {

			BufferedImage buffImg = ImageIO.read(ClassLoader.getSystemResource("res/"+filename));
			image = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static List<Image> getImageList(String[] filename, int width, int height) {
		ArrayList<Image> imageList=new ArrayList<Image>();
		
		for(int i=0;i<filename.length;i++) {
			try {
				BufferedImage buffImg = ImageIO.read(ClassLoader.getSystemResource("res/"+filename[i]));
				imageList.add(buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return imageList;
	}
	
}





