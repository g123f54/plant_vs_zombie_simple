package core.game;

import core.plants.PlantStop;
import core.plants.PlantWait;

public class PlantBangAction implements Repaint{
    // 植物在滚轮机上的碰撞判定
    @Override
    public void doAction(PaintingList paintingList) {
        for(int i=1;i<paintingList.getPlants().size();i++) {
            // 如果第一个植物的y大于0，并且是stop状态，则状态改为wait
            if(paintingList.getPlants().get(0).getY()>0&&paintingList.getPlants().get(0).isStop()) {
                PlantWait plantWait = new PlantWait();
                paintingList.getPlants().get(0).setState(plantWait);
                //plantWait.doAction(plants.get(0));//refactory
//				plants.get(0).goWait();
            }
            // 如果第i个植物y小于i-1个植物的y+height，则说明碰到了，改变i的状态为stop
            if((paintingList.getPlants().get(i).isStop()||paintingList.getPlants().get(i).isWait())&&
                    (paintingList.getPlants().get(i-1).isStop()||paintingList.getPlants().get(i-1).isWait())&&
                    paintingList.getPlants().get(i).getY()<=paintingList.getPlants().get(i-1).getY()+paintingList.getPlants().get(i-1).getHeight()
            ) {
                PlantStop plantStop = new PlantStop();
                paintingList.getPlants().get(i).setState(plantStop);
                //plantStop.doAction(plants.get(i));
//				plants.get(i).goStop();
            }
            /*
             * 如果第i个植物y大于于i-1个植物的y+height，则说明还没碰到或者第i-1个
             * 植物被移走了，改变i的状态为wait，可以继续往上走
             */
            if(paintingList.getPlants().get(i).isStop()&&
                    paintingList.getPlants().get(i).getY()>paintingList.getPlants().get(i-1).getY()+paintingList.getPlants().get(i-1).getHeight()) {
                PlantWait plantWait = new PlantWait();
                paintingList.getPlants().get(i).setState(plantWait);
                //plantWait.doAction(plants.get(i));
//				plants.get(i).goWait();
            }
        }
    }
}
