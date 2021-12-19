package core.plants;

public class PlantDead implements PlantState{
    @Override
    public void doAction(Plant p) {
        p.setState(4);
    }
}
