package core.game;

public class ShovelEnterAction extends Repaint{
    // 铲子入场
    @Override
    public void doAction(PaintingList paintingList) {
        if(paintingList.getShovels().size()==0) {
            paintingList.getShovels().add(new Shovel());
        }
    }
}
