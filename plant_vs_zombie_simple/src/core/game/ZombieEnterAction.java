package core.game;

import core.zombies.*;

import java.util.Random;

public class ZombieEnterAction extends Repaint{
    // 僵尸入场
    @Override
    public void doAction(PaintingList paintingList) {
        // 设置进场间隔
        paintingList.setZombieEnterTime(paintingList.getZombieEnterTime()+1);
        if(paintingList.getZombieEnterTime()%300==0) {
            paintingList.getZombies().add(nextOneZombie());
        }
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

}


