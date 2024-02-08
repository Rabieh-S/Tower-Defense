package projectiles;

import java.awt.geom.Point2D;

public class Projectile{

    protected Point2D.Float pos;
    protected int id, projectileType, damage;
    protected boolean isActive = true;
    protected float xSpeed, ySpeed;

    public int getDamage() {
        return damage;
    }

    public Projectile(float x, float y, int id, float xSpeed, float ySpeed, int damage, int projectileType) {
        pos = new Point2D.Float(x, y);
        this.id = id;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.damage = damage;
        this.projectileType = projectileType;
    }
    public void trajectory() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }
}
