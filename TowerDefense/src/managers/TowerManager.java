package managers;

import enemies.Enemy;
import helpers.LoadSave;
import scenes.Playing;
import towers.Tower;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int nbrTower = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            towerImgs[i] = atlas.getSubimage(i * 32, 32 * 6, 32, 32);
        }
    }

    public void draw(Graphics g) {
        for(Tower t: towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    public Tower getTowerAt(int x, int y) {
     for (Tower t: towers) {
         if(t.getX()== x) {
             if(t.getY() == y) {
                 return t;
             }
         }
     }
        return null;
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        Tower newTower = selectedTower.createTowerInstance(xPos, yPos, nbrTower++);
        towers.add(newTower);
    }

    public void update() {
        for (Tower t: towers) {
            t.update();
            attackEnemyInRange(t);
        }
    }

    private void attackEnemyInRange(Tower t) {

            for (Enemy e: playing.getEnemyManager().getEnemies()) {
                if (e.isAlive()) {
                    if (isEnemyInAttackRange(t, e)) {
                        if(t.isAttackReset()) {
                            playing.aimAtEnemy(t, e);
                            t.refreshAttack();
                        }
                    } else {

                    }
                }
            }

    }

    private boolean isEnemyInAttackRange(Tower t, Enemy e) {
        int towerAttackRange = helpers.Utils.getDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return towerAttackRange < t.getRange();
    }
    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }
}