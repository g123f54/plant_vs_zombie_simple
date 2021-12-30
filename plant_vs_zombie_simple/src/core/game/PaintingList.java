package core.game;

import core.bullets.Bullet;
import core.plants.Plant;
import core.zombies.Zombie;

import java.util.ArrayList;
import java.util.List;

public class PaintingList {
    private List<Zombie> zombies = new ArrayList<Zombie>();
    private List<Plant> plants = new ArrayList<Plant>();
    private List<Plant> plantsLife = new ArrayList<Plant>();
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Shovel> shovels = new ArrayList<Shovel>();
    private List<Glass> glasses = new ArrayList<Glass>();
    // 设置进场间隔
    private int zombieEnterTime ;
    //设置僵尸移动间隔
    private int zombieStepTime ;
    // 设置地刺攻击间隔(僵尸走到地刺上扣血)
    private int spikerockHitTime ;
    // 设置僵尸攻击间隔
    private int zombieHitTime ;
    // 设置植物入场进场间隔
    private int plantTime ;
    private int BulletTime ;

    private int timeStop ;
    private int bloverTime ;
    private int gameLife ;

    public PaintingList() {
        this.zombieEnterTime = 0;
        this.zombieStepTime = 0;
        this.spikerockHitTime = 0;
        this.zombieHitTime = 0;
        this.plantTime = 0;
        this.BulletTime = 0;
        this.timeStop = 1;
        this.bloverTime = 1;
        this.gameLife = 1;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(List<Zombie> zombies) {
        this.zombies = zombies;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public List<Plant> getPlantsLife() {
        return plantsLife;
    }

    public void setPlantsLife(List<Plant> plantsLife) {
        this.plantsLife = plantsLife;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Shovel> getShovels() {
        return shovels;
    }

    public void setShovels(List<Shovel> shovels) {
        this.shovels = shovels;
    }

    public List<Glass> getGlasses() {
        return glasses;
    }

    public void setGlasses(List<Glass> glasses) {
        this.glasses = glasses;
    }

    public int getZombieEnterTime() {
        return zombieEnterTime;
    }

    public void setZombieEnterTime(int zombieEnterTime) {
        this.zombieEnterTime = zombieEnterTime;
    }

    public int getZombieStepTime() {
        return zombieStepTime;
    }

    public void setZombieStepTime(int zombieStepTime) {
        this.zombieStepTime = zombieStepTime;
    }

    public int getSpikerockHitTime() {
        return spikerockHitTime;
    }

    public void setSpikerockHitTime(int spikerockHitTime) {
        this.spikerockHitTime = spikerockHitTime;
    }

    public int getZombieHitTime() {
        return zombieHitTime;
    }

    public void setZombieHitTime(int zombieHitTime) {
        this.zombieHitTime = zombieHitTime;
    }

    public int getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(int plantTime) {
        this.plantTime = plantTime;
    }

    public int getBulletTime() {
        return BulletTime;
    }

    public void setBulletTime(int bulletTime) {
        BulletTime = bulletTime;
    }

    public int getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(int timeStop) {
        this.timeStop = timeStop;
    }

    public int getBloverTime() {
        return bloverTime;
    }

    public void setBloverTime(int bloverTime) {
        this.bloverTime = bloverTime;
    }

    public int getGameLife() {
        return gameLife;
    }

    public void setGameLife(int gameLife) {
        this.gameLife = gameLife;
    }
}
