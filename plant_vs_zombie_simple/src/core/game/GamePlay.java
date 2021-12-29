package core.game;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.bullets.Bullet;
import core.bullets.SnowBullet;
import core.plants.*;
import core.zombies.*;

/**
 * 植物大战僵尸
 * 游戏内容：
 * 		左边的滚轮机会自动生成植物的卡牌，鼠标单击可以选中植物，
 * 被选中的植物将跟随鼠标移动，再次点击后，可在草地中放置植物。
 * 豌豆射手可以发射豌豆，寒冰射手发射的冰冻豌豆可以使僵尸减速，
 * 三头豌豆射手可以发射三颗豌豆，坚果可以阻止僵尸前进，吹风草
 * 可以将所有的僵尸吹到屏幕外。地刺会持续对僵尸进行攻击。若对
 * 放置的植物不满意，可以使用铲子，将草地上的植物移除。
 * 		不同的僵尸血量不同，移动速度不同。击杀足球僵尸后将获得
 * 随机奖励，奖励类型有：全屏僵尸死亡，全屏僵尸静止两秒等。
 * 		当有僵尸闯入房间，游戏结束。点击图标可重新开始游戏。
 * 
 * @author Lychee
 *
 */

public class GamePlay extends JPanel{

	// 游戏窗口大小，游戏状态
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 600;
	public static final int START = 0;
	public static final int RUNNING =1;
	public static final int GAME_OVER =2;
	// 游戏的初始状态
	public static int state = RUNNING;

	// 背景
	private Background start = new Background(800,533,300,50);
	private Background running = new Background(WIDTH,HEIGHT,0,0);
	private Background gameOver = new Background(WIDTH,HEIGHT,0,0);

	// 游戏对象
	// 僵尸集合
	ZombieList zombieList = new ZombieList();

	// 植物集合
	PlantList plantList = new PlantList();

	// 子弹集合
	BulletList bulletList = new BulletList();

	// 草地集合
	GlassList glassList = new GlassList();

	// 铲子
	ShovelList shovelList = new ShovelList();

	// 检测游戏状态
	// 初始游戏生命值
	static int gameLife = 1;
	public void checkGameAction() {
		if(gameLife<=0) {
			state = GAME_OVER;
			// 游戏结束清空所有集合
			plantList.getPlantsList().clear();
			plantList.getPlantsLifeList().clear();
			zombieList.getZombieList().clear();
			bulletList.getBulletsList().clear();
			shovelList.getShovelsList().clear();
		}
	}

	// 运行代码
	// 鼠标上是否有植物和铲子的判定
	boolean plantCheck = false;
	boolean shovelCheck = false;
	public void action() {
		// 生成草地
		glassList.glassEnterAction();
		// 鼠标的相关操作
		MouseAdapter l = new MouseAdapter() {
			// 鼠标点击事件
			public void mouseClicked(MouseEvent e) {
				// 获得鼠标的坐标
				int Mx = e.getX();
				int My = e.getY();
				//System.out.println(Mx+"-"+My);

				if(state==RUNNING) {
					// 放置植物
					f:for(Plant p:plantList.getPlantsLifeList()) {
						if(p.isMove()&&plantCheck) {
							for(Glass g:glassList.getGlassesList()) {
								int x1 = g.getX();
								int x2 = g.getX()+g.getWidth();
								int y1 = g.getY();
								int y2 = g.getY()+g.getHeight();	
								if(Mx>x1&&Mx<x2&&My>y1&&My<y2&&g.isEmpty()) {
									p.setX(x1);
									p.setY(y1);
									g.goHold();
									PlantLife plantLife = new PlantLife();
									p.setState(plantLife);
									//plantLife.doAction(p);
//									p.goLife();
									plantCheck = false;
									if(p instanceof Blover) {
										plantList.bloverTime = 0;
									}
									break f;
								}
							}			
						}
					}
				// 使用铲子
				Iterator<Shovel> it = shovelList.getShovelsList().iterator();
				Iterator<Plant> it2 = plantList.getPlantsLifeList().iterator();
				while(it.hasNext()) {
					Shovel s = it.next();
					// 如果铲子是移动状态，就遍历植物集合
					if(s.isMove()) {
						while(it2.hasNext()) {
							Plant p  = it2.next();
							int x1 = p.getX();
							int x2 = p.getX()+p.getWidth();
							int y1 = p.getY();
							int y2 = p.getY()+p.getHeight();
							if((p.isLife()||((Blover) p).isClick())&&Mx>x1&&Mx<x2&&My>y1&&My<y2&&shovelCheck) {
								// 移除植物
								it2.remove();
								// 移除铲子
								it.remove();
								shovelCheck = false;
							}
						}
					}
				}
				// 鼠标单击后，植物将改变状态，随鼠标移动
				for(Plant p:plantList.getPlantsList()) {
					if((p.isStop()||p.isWait())&&!plantCheck&&!shovelCheck) {
						int x1 = p.getX();
						int x2 = p.getX()+p.getWidth();
						int y1 = p.getY();
						int y2 = p.getY()+p.getHeight();						
						if(Mx>=x1&&Mx<=x2&&My>=y1&&My<=y2) {
							PlantMove plantMove = new PlantMove();
							p.setState(plantMove);
							//plantMove.doAction(p);
//							p.goMove();
							plantCheck = true;
							break;
						}
					}
				}
				// 铲子被选中后随鼠标移动
				Iterator<Shovel> it3 = shovelList.getShovelsList().iterator();
				if(plantList.getPlantsLifeList().size()>0) {
					while(it3.hasNext()) {
						Shovel s = it3.next();
						int x1 = s.getX();
						int x2 = s.getX()+s.getWidth();
						int y1 = s.getY();
						int y2 = s.getY()+s.getHeight();
						if(s.isWait()&&Mx>x1&&Mx<x2&&My>y1&&My<y2&&!plantCheck) {
							s.goMove();
							shovelCheck = true;
						}
					}
				}
				// 点击吹风草吹走僵尸
				for(Plant p:plantList.getPlantsLifeList()) {
					if(p instanceof Blover) {
						int x1 = p.getX();
						int x2 = p.getX()+p.getWidth();
						int y1 = p.getY();
						int y2 = p.getY()+p.getHeight();
						if(((Blover) p).isClick()&&Mx>x1&&Mx<x2&&My>y1&&My<y2
					&&!plantCheck&&!shovelCheck) {
							PlantDead plantDead = new PlantDead();
							p.setState(plantDead);
							//plantDead.doAction(p);
//							p.goDead();
							for(Zombie z:zombieList.getZombieList()) {
								if(z.isAttack()) {
									ZombieLife zombieLife = new ZombieLife();
									z.setState(zombieLife);
									//zombieLife.doAction(z);
									//z.goLife();
								}
								z:for(int i=0;i<10;i++) {
									z.goOut();
									if(z.getX()>=GamePlay.WIDTH-z.getWidth()) {
										z.goRun();
										break z;
									}
								}
							}
						}
					}
				}

				}	
				// 点击按钮开始游戏
				if(state==START) {
					int x1 = 720;
					int x2 = 990;
					int y1 = 210;
					int y2 = 320;
					if(Mx>=x1&&Mx<=x2&&My>=y1&&My<=y2) {
						state = RUNNING;
					}
				}
				// 点击按钮重新开始游戏
				if(state==GAME_OVER) {
					int x1 = 480;
					int x2 = 950;
					int y1 = 100;
					int y2 = 540;
					if(Mx>=x1&&Mx<=x2&&My>=y1&&My<=y2) {
						// 重新开始游戏
						state = START;
						gameLife = 1;
					}
				}
			}

			// 鼠标移动事件
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING) {
					// 被选中的植物随鼠标移动
					for(Plant p:plantList.getPlantsLifeList()) {
						if(p.isMove()) {
							int x = e.getX();
							int y = e.getY();
							p.moveTo(x, y);
							break;
						}
					}
					// 被选中的铲子随鼠标移动
					for(Shovel s:shovelList.getShovelsList()) {
						if(s.isMove()) {
							int x = e.getX();
							int y = e.getY();
							s.moveTo(x, y);
							break;
						}
					}
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		// 定时器
		Timer timer = new Timer();
		int interval = 10;
		timer.schedule(new TimerTask() {
			public void run() {
				if(state==RUNNING) {
					shovelList.shovelEnterAction();// 铲子入场
					zombieList.zombieEnterAction();// 僵尸入场
					zombieList.zombieStepAction();//僵尸移动
					zombieList.zombieMoveToSpikerockAction(plantList);// 僵尸走到地刺上扣血
					zombieList.zombieHitAction(plantList);// 僵尸攻击
					plantList.plantEnterAction();
					plantList.plantStepAction();
					plantList.plantBangAction();
					zombieList.zombieGoLife();
					bulletList.BulletShootAction(plantList);
					bulletList.BulletStepAction();
					bulletList.hitAction(zombieList);
					plantList.checkBloverAction();
					plantList.checkPlantAction1();
					plantList.checkPlantAction2();
					zombieList.checkZombieAction();
					bulletList.bulletCheckAction();
					glassList.glassCheckAction(plantList);
					checkGameAction();
				}
				repaint();
			}
		},interval,interval);
	}

	// 画
	public void paint(Graphics g) {
		// 画背景
		if(state==START) {
			start.paintObject(g);
		}else if(state==RUNNING) {
			running.paintObject(g);
		}else if(state==GAME_OVER) {
			gameOver.paintObject(g);
		}
		// 画植物
		for(Plant p:plantList.getPlantsList()) {
			p.paintObject(g);
		}
		for(Plant p:plantList.getPlantsLifeList()) {
			p.paintObject(g);
		}
		// 画僵尸
		for(Zombie z:zombieList.getZombieList()) {
			z.paintObject(g);
		}
		// 画子弹
		for(Bullet b:bulletList.getBulletsList()) {
			b.paintObject(g);
		}		
		// 画铲子
		for(Shovel s:shovelList.getShovelsList()) {
			s.paintObject(g);
		}
	}

}
