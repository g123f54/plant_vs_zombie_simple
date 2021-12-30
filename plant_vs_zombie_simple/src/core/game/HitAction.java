package core.game;

import core.bullets.Bullet;
import core.bullets.SnowBullet;
import core.zombies.Zombie;

public class HitAction extends Repaint{
    // 子弹与僵尸的碰撞
    @Override
    public void doAction(PaintingList paintingList) {
        for(Zombie z:paintingList.getZombies()) {
            for(Bullet b:paintingList.getBullets()) {
                // 满足条件则僵尸扣血，子弹去死
                if((z.isAttack()||z.isLife())&&b.isLife()&&b.hit(z)&&z.getX()<GamePlay.WIDTH) {
                    if(b instanceof SnowBullet) {
                        z.goSlowDown();
                    }
                    z.loseLive();
                    b.goDead();
                }
            }
        }
    }
}
