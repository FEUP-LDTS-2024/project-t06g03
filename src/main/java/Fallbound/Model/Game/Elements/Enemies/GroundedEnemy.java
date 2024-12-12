package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class GroundedEnemy extends Enemy implements Stompable {
    private int direction = 1;
    private final Scene scene;
    private final long moveCooldown = 300;
    private long lastMoveTime = 0;

    public GroundedEnemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    @Override
    public void followPlayer() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < moveCooldown) return;
        lastMoveTime = currentTime;

        Vector currentPos = this.getPosition();
        Vector nextPosition = new Vector(currentPos.getX() + direction, currentPos.getY());

        boolean canMove = false;
        Vector belowNextPosition = new Vector(nextPosition.getX(), nextPosition.getY() + 1);
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(belowNextPosition, wall.getPosition())) {
                canMove = true;
                break;
            }
        }

        if (scene.isColliding(nextPosition, scene.getPlayer().getPosition())) {
            // TODO damage player
            canMove = false;
        }


        if (!canMove) {
            direction = -direction;
        } else {
            this.setPosition(nextPosition);
        }
    }
}
