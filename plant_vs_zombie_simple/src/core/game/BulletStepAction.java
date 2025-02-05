package core.game;

import core.bullets.Bullet;

public class BulletStepAction implements Repaint{
    // 子弹移动
    @Override
    public void doAction(PaintingList paintingList) {
        for(Bullet b: paintingList.getBullets()) {
            b.step();
        }
    }
}
