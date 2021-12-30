package core.game;

import core.plants.Plant;

import java.util.Iterator;

public class CheckPlantAction1 extends Repaint{
    // 检测滚轮机上的植物状态
    @Override
    public void doAction(PaintingList paintingList) {
        // 迭代器
        Iterator<Plant> it = paintingList.getPlants().iterator();
        while(it.hasNext()) {
            Plant p = it.next();
            /*
             * 如果滚轮机集合里有move或者life状态的植物
             * 则添加到战场植物的集合中，并从原数组中删除
             */
            if(p.isMove()||p.isLife()) {
                paintingList.getPlantsLife().add(p);
                it.remove();
            }
        }
    }
}
