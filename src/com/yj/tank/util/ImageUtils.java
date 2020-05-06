package com.yj.tank.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-19:07
 **/
public class ImageUtils {

	public static BufferedImage getImage(Path path) {
		BufferedImage bufferedImage=null;
		try {
			bufferedImage=ImageIO.read(path.toFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	public static BufferedImage getImage(InputStream inputStream) {
		BufferedImage bufferedImage=null;
		try {
			bufferedImage=ImageIO.read(inputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	/**
	 * 旋转图片
	 * @param bufferedimage
	 * @param degree 旋转的角度
	 * @return
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,
			final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type))
				.createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}
}
