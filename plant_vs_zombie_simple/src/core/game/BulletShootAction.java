package core.game;

import core.plants.Plant;
import core.plants.Shoot;

import java.util.Arrays;

public class BulletShootAction implements Repaint{
    // 子弹入场
    @Override
    public void doAction(PaintingList paintingList) {
        // 控制子弹进场的间隔时间
        paintingList.setBulletTime(paintingList.getBulletTime()+1);
        if(paintingList.getBulletTime()%50==0) {
            for(Plant p : paintingList.getPlantsLife()) {
                if(p.isLife()) {
                    if(p instanceof Shoot) {
                        // 如果植物有射击的接口
                        Shoot s = (Shoot)p;
                        // 射击
                        //s.shoot();
                        // 把射击的子弹装进子弹数组
                        paintingList.getBullets().addAll(Arrays.asList(s.shoot()));
                    }
                }
            }
        }
    }
}
