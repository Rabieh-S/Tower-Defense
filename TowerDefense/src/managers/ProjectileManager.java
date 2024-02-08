package managers;

import enemies.Enemy;
import helpers.Constants;
import helpers.LoadSave;
import projectiles.Projectile;
import scenes.Playing;
import towers.Tower;
import static helpers.Constants.Towers.*;
import static helpers.Constants.Projectile.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImgs;
    private int projectileId = 0;
    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importProjectileImgs();
    }

    public void update() {
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                p.trajectory();
                if(isProjectileHitEnemy(p)) {
                    p.setActive(false);
                } else {
                    //
                }
            }
        }
    }

    private boolean isProjectileHitEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.isAlive()) {
                if (e.getBounds().contains(p.getPos())) {
                    e.receiveDamageFromTowers(p.getDamage());
                    return true;
                }
            }
        }
        return false;
    }
    private void importProjectileImgs(){
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImgs = new BufferedImage[3];
        projectileImgs[0] = atlas.getSubimage(32 * 7, 32 * 4, 32, 32);
        projectileImgs[1] = atlas.getSubimage(32 * 8, 32 * 3, 32, 32);
        projectileImgs[2] = atlas.getSubimage(32 * 4, 32 * 4, 32, 32);
    }

    public void draw(Graphics g) {
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                g.drawImage(projectileImgs[p.getProjectileType()], (int)p.getPos().x, (int)p.getPos().y, null);
            }

        }
    }

    public void refreshProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);
        int xDist = (int) Math.abs(t.getX() - e.getX());
        int yDist = (int) Math.abs(t.getY() - e.getY());
        int totalDist = xDist + yDist;

        float xPercent = (float) xDist / totalDist;

        float xSpeed = xPercent * helpers.Constants.Projectile.GetSpeed(type);
        float ySpeed = helpers.Constants.Projectile.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        }

        if (t.getY() > e.getY()) {
            ySpeed *= -1;
        }
        projectiles.add(new Projectile(t.getX()+16, t.getY() +16, projectileId++, xSpeed, ySpeed, t.getDamage(), type));
    }

    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHERY_1:
                return ARROW;
            case ARCHERY_2:
                return ARROW;
            case ARCHERY_3:
                return ARROW;
            case CANNON_1:
                return BOMB;
            case CANNON_2:
                return BOMB;
            case CANNON_3:
                return BOMB;
            case MAGIC_1:
                return FIREBALL;
            case MAGIC_2:
                return FIREBALL;
            case MAGIC_3:
                return FIREBALL;
        }
        return 0;
    }
}
