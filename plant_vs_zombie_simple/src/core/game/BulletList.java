package core.game;

import core.bullets.Bullet;
import core.bullets.SnowBullet;
import core.plants.Plant;
import core.plants.Shoot;
import core.zombies.Zombie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BulletList {

    // 子弹集合
    private List<Bullet> bullets = new ArrayList<Bullet>();

    public List<Bullet> getBulletsList() {
        return bullets;
    }

    // 子弹入场
    // 控制子弹进场的间隔时间
    int BulletTime = 0;
    public void BulletShootAction(PlantList plantList) {
        if(BulletTime++%50==0) {
            for(Plant p : plantList.getPlantsLifeList()) {
                if(p.isLife()) {
                    if(p instanceof Shoot) {
                        // 如果植物有射击的接口
                        Shoot s = (Shoot)p;
                        // 射击
                        //s.shoot();
                        // 把射击的子弹装进子弹数组
                        bullets.addAll(Arrays.asList(s.shoot()));
                    }
                }
            }
        }
    }

    // 子弹移动
    public void BulletStepAction() {
        for(Bullet b:bullets) {
            b.step();
        }
    }

    // 子弹与僵尸的碰撞
    public void hitAction(ZombieList zombieList) {
        // 遍历僵尸和子弹数组
        for(Zombie z:zombieList.getZombieList()) {
            for(Bullet b:bullets) {
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

    // 检测子弹状态
    public void bulletCheckAction() {
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()) {
            Bullet b = it.next();
            // 如果子弹死亡或者越界则删除
            if(b.isDead()||b.isOutOfBound()) {
                it.remove();
            }
        }
    }

}
