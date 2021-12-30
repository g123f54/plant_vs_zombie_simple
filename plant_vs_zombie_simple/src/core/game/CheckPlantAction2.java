package core.game;

import core.plants.Plant;
import core.plants.PlantDead;

import java.util.Iterator;

public class CheckPlantAction2 implements Repaint{
    // 检测战场上的植物状态
    @Override
    public void doAction(PaintingList paintingList) {
        // 迭代器
        Iterator<Plant> it = paintingList.getPlantsLife().iterator();
        while(it.hasNext()) {
            Plant p = it.next();
            // 植物生命小于0死亡，死亡状态的植物从集合中移出
            if(p.getLive()<=0) {
                PlantDead plantDead = new PlantDead();
                p.setState(plantDead);
                //plantDead.doAction(p);
//				p.goDead();
                it.remove();
            }
        }
    }
}
