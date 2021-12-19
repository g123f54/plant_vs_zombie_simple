package core.plants;

public class PlantLife implements PlantState{
    @Override
    public void doAction(Plant p) {
        p.setState(3);
    }
}
