package core.game;

import core.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class GlassList {
    // 草地集合
    private List<Glass> glasses = new ArrayList<Glass>();

    public List<Glass> getGlassesList() {
        return glasses;
    }

    int glassX = 260;
    int glassY = 80;
    public void glassEnterAction() {
        for(int i=0;i<9;i++) {
            int x = glassX + i*Glass.WIDTH;
            for(int j=0;j<5;j++) {
                int y = glassY + j*Glass.HEIGHT;
                glasses.add(new Glass(x,y));
            }
        }
    }

    // 检测草地状态
    public void glassCheckAction(PlantList plantList) {
        // 先遍历所有草地，将状态改为空
        for(Glass g:glasses) {
            g.goEmpty();
            // 遍历所有植物，如果草地上有植物，将草地状态改为被占有
            for(Plant p:plantList.getPlantsLifeList()) {
                if(p.isLife()) {
                    int x1 = g.getX();
                    int y1 = g.getY();
                    int x = p.getX();
                    int y = p.getY();
                    // 共点问题
                    if(x==x1&&y==y1) {
                        g.goHold();
                        break;
                    }
                }
            }
        }
    }

}
