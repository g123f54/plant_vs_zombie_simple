package core.zombies;

public class ZombieDead implements ZombieState{
    @Override
    public void doAction(Zombie z) {
        z.setState(2);
    }
}
