package core.plants;

public class PlantStop implements PlantState{
    @Override
    public void doAction(Plant p) {
        p.setState(1);
    }
}
