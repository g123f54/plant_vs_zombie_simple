package core.game;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        int WIDTH = 1400;
        int HEIGHT = 600;
        JFrame frame = new JFrame();
        GamePlay game= new GamePlay();
        frame.add(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.action();

        // 启动线程加载音乐
        Runnable r = new zombieAubio("bgm.wav");
        Thread t = new Thread(r);
        t.start();
    }
}
