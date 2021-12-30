package core.game;

import core.bullets.Bullet;

import java.util.Iterator;

public class BulletCheckAction extends Repaint{
    // 检测子弹状态
    @Override
    public void doAction(PaintingList paintingList) {
        Iterator<Bullet> it = paintingList.getBullets().iterator();
        while(it.hasNext()) {
            Bullet b = it.next();
            // 如果子弹死亡或者越界则删除
            if(b.isDead()||b.isOutOfBound()) {
                it.remove();
            }
        }
    }
}
