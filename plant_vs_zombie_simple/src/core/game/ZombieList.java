package core.game;

import core.plants.Plant;
import core.plants.Spikerock;
import core.zombies.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ZombieList {

    // 僵尸集合
    private List<Zombie> zombies = new ArrayList<Zombie>();

    public List<Zombie> getZombieList() {
        return zombies;
    }

    // 生成僵尸
    public Zombie nextOneZombie() {

        Random rand = new Random();
        // 控制不同种类僵尸出现的概率
        int type = rand.nextInt(20);
        if(type<5) {
            return new Zombie0();
        }else if(type<10) {
            return new Zombie1();
        }else if(type<15) {
            return new Zombie2();
        }else {
            return new Zombie3();
        }
    }

    // 僵尸入场
    // 设置进场间隔
    int zombieEnterTime = 0;
    public void zombieEnterAction() {
        zombieEnterTime++;
        if(zombieEnterTime%300==0) {
            zombies.add(nextOneZombie());
        }
    }

    //僵尸移动
    //设置移动间隔
    int zombieStepTime = 0;
    public void zombieStepAction() {
        if(zombieStepTime++%3==0) {
            for(Zombie z:zombies) {
                //只有活着的僵尸会移动
                if(z.isLife()) {
                    z.step();
                }
            }
        }
    }

    // 僵尸走到地刺上扣血
    // 设置地刺攻击间隔
    int spikerockHitTime = 0;
    public void zombieMoveToSpikerockAction(PlantList plantList) {
        if(spikerockHitTime++%20==0) {
            for(Plant p :plantList.getPlantsLifeList()) {
                // 如果植物是地刺类型就去遍历僵尸集合
                if(p instanceof Spikerock) {
                    for(Zombie z: zombies) {
                        int x1 = p.getX();
                        int x2 = p.getX()+p.getWidth();
                        int y1 = p.getY();
                        int y2 = p.getY()+p.getHeight();
                        int x = z.getX();
                        int y = z.getY();
                        // 如果僵尸在地刺上就扣血
                        if(x>x1&&x<x2&&y>y1&&y<y2&&p.isLife()&&(z.isLife()||z.isAttack())) {
                            z.loseLive();
                        }
                    }
                }
            }
        }
    }

    // 僵尸攻击
    // 设置攻击间隔
    int zombieHitTime = 0;
    public void zombieHitAction(PlantList plantList) {
        if(zombieHitTime++%100==0) {
            for(Zombie z:zombies) {
                // 如果战场上没有植物，则把所有僵尸的状态改为life
                if(!z.isDead()) {
                    ZombieLife zombieLife = new ZombieLife();
                    z.setState(zombieLife);
                    //zombieLife.doAction(z);
                    //z.goLife();
                }
                for(Plant p:plantList.getPlantsLifeList()) {
                    // 如果僵尸是活的，并且植物是活的，并且僵尸进入攻击植物的范围
                    if(z.isLife()&&!p.isDead()&&z.zombieHit(p)&&!(p instanceof Spikerock)) {
                        // 僵尸状态改为攻击状态
                        ZombieAttack zombieAttack = new ZombieAttack();
                        z.setState(zombieAttack);
                        //zombieAttack.doAction(z);
                        //z.goAttack();
                        // 植物掉血
                        p.loseLive();
                    }
                }
            }
        }
    }

    // 检测僵尸状态
    public void checkZombieAction() {
        // 迭代器
        Iterator<Zombie> it = zombies.iterator();
        while(it.hasNext()) {
            Zombie z = it.next();
            // 僵尸血量小于0则死亡,死亡的僵尸从集合中删除
            if(z.getLive()<=0) {
                // 判断僵尸是否有奖励的接口
                if(z instanceof Award) {
                    Award a = (Award)z;
                    int type = a.getAwardType();
                    switch(type) {
                        case Award.CLEAR:
                            for(Zombie zo:zombies) {
                                ZombieDead zombieDead = new ZombieDead();
                                zo.setState(zombieDead);
                                //zombieDead.doAction(z);
                                //zo.goDead();
                            }
                            break;
                        case Award.STOP:
                            for(Zombie zom:zombies) {
                                zom.goStop();
                                timeStop = 1;
                                //zombieGoLife();
                            }
                            break;
                    }
                }
                ZombieDead zombieDead = new ZombieDead();
                z.setState(zombieDead);
                //zombieDead.doAction(z);
                //z.goDead();
                it.remove();
            }
            // 僵尸跑进房子，而游戏生命减一，并删除僵尸
            if(z.OutOfBound()) {
                GamePlay.gameLife--;
                it.remove();
            }
        }
    }

    // 僵尸静止2秒后继续移动
    int timeStop = 1;
    public void zombieGoLife() {
        if(timeStop++%200==0) {
            for(Zombie z:zombies) {
                z.goRun();
            }
        }
    }



}
