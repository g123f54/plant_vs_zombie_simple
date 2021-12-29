package core.game;

import java.util.ArrayList;
import java.util.List;

public class ShovelList {

    private List<Shovel> shovels = new ArrayList<Shovel>();

    public List<Shovel> getShovelsList() {
        return shovels;
    }

    // 铲子入场
    public void shovelEnterAction() {
        // 铲子只有一把
        if(shovels.size()==0) {
            shovels.add(new Shovel());
        }
    }

}
