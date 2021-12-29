package core.game;

import core.plants.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PlantList {
    // 植物集合
    // 滚轮机上的植物，状态为stop和wait
    private List<Plant> plants = new ArrayList<Plant>();

    // 战场上的植物，状态为life和move
    private List<Plant> plantsLife = new ArrayList<Plant>();

    public List<Plant> getPlantsList() {
        return plants;
    }

    public List<Plant> getPlantsLifeList() {
        return plantsLife;
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

    // 植物入场
    // 设置进场间隔
    int plantTime = 0;
    public void plantEnterAction() {
        plantTime++;
        if(plantTime%300==0) {
            // 添加到滚轮机集合中
            plants.add(nextOnePlant());
        }
    }

    // 植物移动
    public void plantStepAction() {
        for(Plant p:plants) {
            // 只有滚轮机集合中的wait状态的植物会移动
            if(p.isWait()) {
                p.step();
            }
        }
    }

    // 植物在滚轮机上的碰撞判定
    public void plantBangAction() {
        // 遍历滚轮机上植物集合，从第二个开始
        for(int i=1;i<plants.size();i++) {
            // 如果第一个植物的y大于0，并且是stop状态，则状态改为wait
            if(plants.get(0).getY()>0&&plants.get(0).isStop()) {
                PlantWait plantWait = new PlantWait();
                plants.get(0).setState(plantWait);
                //plantWait.doAction(plants.get(0));//refactory
//				plants.get(0).goWait();
            }
            // 如果第i个植物y小于i-1个植物的y+height，则说明碰到了，改变i的状态为stop
            if((plants.get(i).isStop()||plants.get(i).isWait())&&
                    (plants.get(i-1).isStop()||plants.get(i-1).isWait())&&
                    plants.get(i).getY()<=plants.get(i-1).getY()+plants.get(i-1).getHeight()
            ) {
                PlantStop plantStop = new PlantStop();
                plants.get(i).setState(plantStop);
                //plantStop.doAction(plants.get(i));
//				plants.get(i).goStop();
            }
            /*
             * 如果第i个植物y大于于i-1个植物的y+height，则说明还没碰到或者第i-1个
             * 植物被移走了，改变i的状态为wait，可以继续往上走
             */
            if(plants.get(i).isStop()&&
                    plants.get(i).getY()>plants.get(i-1).getY()+plants.get(i-1).getHeight()) {
                PlantWait plantWait = new PlantWait();
                plants.get(i).setState(plantWait);
                //plantWait.doAction(plants.get(i));
//				plants.get(i).goWait();
            }
        }
    }

    // 检测滚轮机上的植物状态
    public void checkPlantAction1() {
        // 迭代器
        Iterator<Plant> it = plants.iterator();
        while(it.hasNext()) {
            Plant p = it.next();
            /*
             * 如果滚轮机集合里有move或者life状态的植物
             * 则添加到战场植物的集合中，并从原数组中删除
             */
            if(p.isMove()||p.isLife()) {
                plantsLife.add(p);
                it.remove();
            }
        }
    }

    // 检测战场上的植物状态
    public void checkPlantAction2() {
        // 迭代器
        Iterator<Plant> it = plantsLife.iterator();
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

    // 检测吹风草的状态
    int bloverTime = 1;
    public void checkBloverAction() {
        if(bloverTime++%200==0) {
            for(Plant p :plantsLife) {
                if(p instanceof Blover &&p.isLife()) {
                    ((Blover) p).goClick();
                }
            }
        }
    }

}
