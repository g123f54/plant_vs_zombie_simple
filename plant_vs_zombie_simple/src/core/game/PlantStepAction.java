package core.game;

import core.plants.Plant;

public class PlantStepAction implements Repaint{
    // 植物移动
    @Override
    public void doAction(PaintingList paintingList) {
        for(Plant p: paintingList.getPlants()) {
            // 只有滚轮机集合中的wait状态的植物会移动
            if(p.isWait()) {
                p.step();
            }
        }
    }
}
