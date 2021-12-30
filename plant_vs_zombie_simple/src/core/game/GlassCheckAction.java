package core.game;

import core.plants.Plant;

public class GlassCheckAction implements Repaint{
    // 检测草地状态
    @Override
    public void doAction(PaintingList paintingList) {
        // 先遍历所有草地，将状态改为空
        for(Glass g: paintingList.getGlasses()) {
            g.goEmpty();
            // 遍历所有植物，如果草地上有植物，将草地状态改为被占有
            for(Plant p: paintingList.getPlantsLife()) {
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
