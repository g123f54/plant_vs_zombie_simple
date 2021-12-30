package core.game;

import core.zombies.Zombie;

public class ZombieStepAction implements Repaint{
    //僵尸移动
    @Override
    public void doAction(PaintingList paintingList) {
        //设置移动间隔
        paintingList.setZombieStepTime(paintingList.getZombieStepTime()+1);
        if(paintingList.getZombieStepTime()%3==0) {
            for(Zombie z:paintingList.getZombies()) {
                //只有活着的僵尸会移动
                if(z.isLife()) {
                    z.step();
                }
            }
        }

    }
}
