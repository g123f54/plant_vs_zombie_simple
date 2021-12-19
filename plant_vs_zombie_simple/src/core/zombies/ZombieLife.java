package core.zombies;

public class ZombieLife implements ZombieState{
    @Override
    public void doAction(Zombie z) {
        z.setState(0);
    }
}
