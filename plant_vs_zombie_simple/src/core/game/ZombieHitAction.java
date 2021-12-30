package core.game;

import core.plants.Plant;
import core.plants.Spikerock;
import core.zombies.Zombie;
import core.zombies.ZombieAttack;
import core.zombies.ZombieLife;

public class ZombieHitAction implements Repaint{
    // 僵尸攻击
    @Override
    public void doAction(PaintingList paintingList) {
        // 设置攻击间隔
        paintingList.setZombieHitTime(paintingList.getZombieHitTime()+1);
        if(paintingList.getZombieHitTime()%100==0) {
            for(Zombie z:paintingList.getZombies()) {
                // 如果战场上没有植物，则把所有僵尸的状态改为life
                if(!z.isDead()) {
                    ZombieLife zombieLife = new ZombieLife();
                    z.setState(zombieLife);
                    //zombieLife.doAction(z);
                    //z.goLife();
                }
                for(Plant p:paintingList.getPlantsLife()) {
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
}
