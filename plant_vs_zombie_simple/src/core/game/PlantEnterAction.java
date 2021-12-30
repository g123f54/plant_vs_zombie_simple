package core.game;

import core.plants.*;

import java.util.Random;

public class PlantEnterAction extends Repaint{
    // 植物入场
    @Override
    public void doAction(PaintingList paintingList) {
        // 设置进场间隔
        paintingList.setPlantTime(paintingList.getPlantTime()+1);
        if(paintingList.getPlantTime()%300==0) {
            // 添加到滚轮机集合中
            paintingList.getPlants().add(nextOnePlant());
        }

    }

    // 生成植物
    public Plant nextOnePlant() {
        Random rand = new Random();
        int type = rand.nextInt(30);
        // 控制植物的出场概率
        if(type<5) {
            return new Repeater();
        }else if(type<10) {
            return new SnowPea();
        }else if(type<15) {
            return new ThreePeater();
        }else if(type<20) {
            return new Spikerock();
        }else if(type<25) {
            return new Blover();
        }else {
            return new WallNut();
        }
    }
}
