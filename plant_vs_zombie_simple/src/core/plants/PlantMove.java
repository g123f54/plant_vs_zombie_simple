package core.plants;

public class PlantMove implements PlantState{
    @Override
    public void doAction(Plant p) {
        p.setState(2);
    }
}
