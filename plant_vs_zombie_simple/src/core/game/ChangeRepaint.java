package core.game;

public class ChangeRepaint {
    private Repaint repaint;

    public ChangeRepaint(Repaint repaint) {
        this.repaint = repaint;
    }
    public void executeRepaint(PaintingList paintingList){
        repaint.doAction(paintingList);
    }
}
