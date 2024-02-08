package managers;

import scenes.Playing;
import waves.Wave;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemyIndex, waveIndex;
    private int enemySpawnTickLimit = 60*1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int waveTickLimit = 60 * 15;
    private int waveTick = 0;
    private boolean waveStartTimer, waveTickTimerOver;
    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    private void createWaves(){
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 2, 2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 0, 0, 0, 0, 0, 0, 1, 3))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 2, 3))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 0, 0, 0, 0, 1, 2, 2, 3))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 2, 2, 2, 3))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 0, 1, 1, 2, 2, 3, 3, 3))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }
        if (waveStartTimer) {
            waveTick++;
            if (waveTick >= waveTickLimit) {
                waveTickTimerOver = true;
            }
        }
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    public boolean isWaveStarts() {

        return enemySpawnTick >= enemySpawnTickLimit;
    }
    public boolean isEnemiesLeftInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }
    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }
    public void increaseWaveIndex() {
        waveIndex++;
        waveTickTimerOver = false;
        waveStartTimer = false;
    }
    public boolean isWaveTimerOver() {

        return waveTickTimerOver;
    }
    public void resetEnemyIndex() {
        enemyIndex = 0;
    }
    public void startWaveTimer() {
        waveStartTimer = true;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }
}

