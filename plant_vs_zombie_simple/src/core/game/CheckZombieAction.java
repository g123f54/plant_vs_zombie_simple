package core.game;

import core.zombies.Award;
import core.zombies.Zombie;
import core.zombies.ZombieDead;

import java.util.Iterator;

public class CheckZombieAction extends Repaint{
    // 检测僵尸状态
    @Override
    public void doAction(PaintingList paintingList) {
        // 迭代器
        Iterator<Zombie> it = paintingList.getZombies().iterator();
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
                            for(Zombie zo: paintingList.getZombies()) {
                                ZombieDead zombieDead = new ZombieDead();
                                zo.setState(zombieDead);
                                //zombieDead.doAction(z);
                                //zo.goDead();
                            }
                            break;
                        case Award.STOP:
                            for(Zombie zom: paintingList.getZombies()) {
                                zom.goStop();
                                paintingList.setTimeStop(1);
                                //timeStop = 1;
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
                paintingList.setGameLife(paintingList.getGameLife()-1);
                //GamePlay.gameLife--;
                it.remove();
            }
        }
    }
}
