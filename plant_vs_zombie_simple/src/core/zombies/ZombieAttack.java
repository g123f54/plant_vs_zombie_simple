package core.zombies;

public class ZombieAttack implements ZombieState{
    @Override
    public void doAction(Zombie z) {
        z.setState(1);
    }
}
