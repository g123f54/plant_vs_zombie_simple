package core.game;

import core.plants.Blover;
import core.plants.Plant;

public class CheckBloverAction extends Repaint{
    @Override
    public void doAction(PaintingList paintingList) {
        paintingList.setBloverTime(paintingList.getBloverTime()+1);
        if(paintingList.getBloverTime()%200==0) {
            for(Plant p : paintingList.getPlantsLife()) {
                if(p instanceof Blover &&p.isLife()) {
                    ((Blover) p).goClick();
                }
            }
        }
    }

}
