package core.plants;

public class PlantWait implements PlantState{
    @Override
    public void doAction(Plant p) {
        p.setState(0);
    }
}
