package core.game;

import core.plants.Blover;
import core.plants.Plant;
import core.plants.PlantClick;
import core.plants.PlantDead;

public class CheckBloverAction implements Repaint{
    @Override
    public void doAction(PaintingList paintingList) {
        paintingList.setBloverTime(paintingList.getBloverTime()+1);
        if(paintingList.getBloverTime()%200==0) {
            for(Plant p : paintingList.getPlantsLife()) {
                if(p instanceof Blover &&p.isLife()) {
                    PlantClick plantClick = new PlantClick();
                    p.setState(plantClick);
                    //((Blover) p).goClick();
                }
            }
        }
    }

}
