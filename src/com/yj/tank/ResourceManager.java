package com.yj.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.yj.tank.util.ImageUtils;

/**
 *  资源管理类
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-19:32
 **/
public class ResourceManager {

	private ResourceManager() {

	}

	private static class ResourceManagerHolder {
		private static final ResourceManager resourceManager=new ResourceManager();
	}

	public static ResourceManager getInstance() {
		return ResourceManagerHolder.resourceManager;
	}

	public static BufferedImage goodTankLeftImage,goodTankRightImage,goodTankUpImage,goodTankDownImage;
	public static BufferedImage badTankLeftImage,badTankRightImage,badTankUpImage,badTankDownImage;
	public static BufferedImage bulletLeftImage,bulletRightImage,bulletUpImage,bulletDownImage,upLeft45Image,
			downLeft45Image,upRight45Image,downRight45Image,left_leftFront45Image,left_rightFront45Image,
			right_rightFront45Image,right_leftFront45Image;

	public static BufferedImage[] explodes=new BufferedImage[16];
	static {
		try {
			badTankUpImage= ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankLeftImage= ImageUtils.rotateImage(badTankUpImage,-90);
			badTankRightImage=ImageUtils.rotateImage(badTankUpImage,90);
			badTankDownImage=ImageUtils.rotateImage(badTankUpImage,180);

			goodTankUpImage= ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankLeftImage=ImageUtils.rotateImage(goodTankUpImage,-90);
			goodTankRightImage=ImageUtils.rotateImage(goodTankUpImage,90);
			goodTankDownImage=ImageUtils.rotateImage(goodTankUpImage,180);
			/*tankRightImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
			tankUpImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankDownImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.gif"));*/
			bulletUpImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletLeftImage=ImageUtils.rotateImage(bulletUpImage,-90);
			bulletRightImage=ImageUtils.rotateImage(bulletUpImage,90);
			bulletDownImage=ImageUtils.rotateImage(bulletUpImage,180);

			/**
			 * 左上角45度的图片(坦克朝上)
			 */
			upLeft45Image=ImageUtils.rotateImage(bulletUpImage,-45);
			/**
			 * 右上45(坦克朝上)
			 */
			upRight45Image=ImageUtils.rotateImage(bulletUpImage,45);
			/**
			 * 左下45(坦克朝下)
			 */
			downLeft45Image=ImageUtils.rotateImage(bulletUpImage,-135);
			/**
			 * 右下45(坦克朝下)
			 */
			downRight45Image=ImageUtils.rotateImage(bulletUpImage,135);
			/**
			 * 左前45(坦克朝左)
			 */
			left_leftFront45Image=ImageUtils.rotateImage(bulletUpImage,-135);
			/**
			 * 右前45(坦克朝左)
			 */
			left_rightFront45Image=ImageUtils.rotateImage(bulletUpImage,-45);
			/**
			 * 右前45(坦克朝右)
			 */
			right_rightFront45Image=ImageUtils.rotateImage(bulletUpImage,135);
			/**
			 * 左前45(坦克朝右)
			 */
			right_leftFront45Image=ImageUtils.rotateImage(bulletUpImage,45);
			/*bulletLeftImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletRightImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletUpImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletDownImage=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));*/

			for (int i=0;i<explodes.length;i++) {
				explodes[i]=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
