package core.game;

public class GlassEnterAction extends Repaint{
    private int glassX = 260;
    private int glassY = 80;
    @Override
    public void doAction(PaintingList paintingList) {

        for(int i=0;i<9;i++) {
            int x = glassX + i*Glass.WIDTH;
            for(int j=0;j<5;j++) {
                int y = glassY + j*Glass.HEIGHT;
                paintingList.getGlasses().add(new Glass(x,y));
            }
        }
    }
}
