package core.zombies;

import java.awt.image.BufferedImage;

public class Zombie0 extends Zombie{
	
	// 铁桶僵尸
	// 加载图片
	private static BufferedImage imgs[];
	static {
		imgs = new BufferedImage[10];
		for(int i=0;i<imgs.length;i++) {
			imgs[i] = loadImage("zombie0"+i+".png");
		}
	}
	
	// 设置图片,0-4为生存，5-9为攻击
	int index = 0;
	public BufferedImage getImage() {
		if(isLife()) {
			return imgs[index++%5];//0-4
		}else if(isAttack()) {
			return imgs[index++%5+5];//5-9
		}else{
			return null;
		}
	}
	
	// 构造器
	public Zombie0() {
		super(166,144);
		live = 20;
		xSpeed = 2;
	}
	
	// 僵尸移动
//	public void step() {
//		this.x-= xSpeed;
//	}
//
	// 僵尸的停止状态结束
	public void goRun() {
		xSpeed = 2;
	}
	
}
