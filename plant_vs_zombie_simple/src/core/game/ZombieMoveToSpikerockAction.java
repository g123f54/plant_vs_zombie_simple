package core.game;

import core.plants.Plant;
import core.plants.Spikerock;
import core.zombies.Zombie;

public class ZombieMoveToSpikerockAction extends Repaint{
    // 僵尸走到地刺上扣血
    @Override
    public void doAction(PaintingList paintingList) {
        // 设置地刺攻击间隔
        paintingList.setSpikerockHitTime(paintingList.getSpikerockHitTime()+1);
        if(paintingList.getSpikerockHitTime()%20==0) {
            for(Plant p :paintingList.getPlantsLife()) {
                // 如果植物是地刺类型就去遍历僵尸集合
                if(p instanceof Spikerock) {
                    for(Zombie z: paintingList.getZombies()) {
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
}
