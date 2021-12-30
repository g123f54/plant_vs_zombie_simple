package core.game;

import core.zombies.Zombie;

public class ZombieGoLife implements Repaint{
    @Override
    public void doAction(PaintingList paintingList) {
        paintingList.setTimeStop(paintingList.getTimeStop()+1);
        if(paintingList.getTimeStop()%200==0) {
            for(Zombie z: paintingList.getZombies()) {
                z.goRun();
            }
        }

    }
}
