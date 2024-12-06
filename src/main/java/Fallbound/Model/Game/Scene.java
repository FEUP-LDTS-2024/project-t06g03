package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Tiles.Tile;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Scene {

    private final int width;
    private final int height;
    private final List<Coin> coins = new ArrayList<>();
    private final long startTime;
    private Player player = new Player(new Vector(19, 19), this);
    private List<Tile> walls = new ArrayList<>();

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();

        buildRandomPlatform(20);
        buildRandomPlatform(30);
        buildRandomPlatform(40);
        buildRandomPlatform(50);
        buildRandomPlatform(60);
        buildRandomPlatform(70);
        buildRandomPlatform(80);
}

    public void buildRandomPlatform(int y) {
        int platformOffsetMax = 20;
        int platformWidth = 30;
        int platformHeight = 4;

        int platformOffset = (int) ((Math.random() * platformOffsetMax) - ((double) platformOffsetMax / 2));
        System.out.println(platformOffset);

        buildWallBlock(0, y, platformWidth + platformOffset, platformHeight);
        buildWallBlock(60 + platformOffset, y, platformWidth - platformOffset, platformHeight);
    }

    public long getStartTime() {
        return startTime;
    }

    public String timeToString(long time) {
        long minutes = time / 60000;
        long seconds = (time % 60000) / 1000;
        long milliseconds = (time % 1000) / 10;
        return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void removeCoin(Coin coin) {
        this.coins.remove(coin);
    }

    public List<Tile> getWalls() {
        return walls;
    }

    public void setWalls(List<Tile> walls) {
        this.walls = walls;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void buildWallBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.walls.add(new Wall(new Vector(x + i, y + j)));
            }
        }
    }

    private void buildCoinBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.coins.add(new Coin(new Vector(x + i, y + j)));
            }
        }
    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round((float) position1.getX()) == round((float) position2.getX()) && round((float) position1.getY()) == round((float) position2.getY());
    }
}
