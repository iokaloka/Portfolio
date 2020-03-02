package guiltspawn.controls;

import guiltspawn.engine.GuiltSpawn;
import guiltspawn.entity.Player;
import guiltspawn.sound.SoundEffect;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.Clip;

public class KeyInput implements KeyListener {

    private final GuiltSpawn game;
    private boolean[] pressed;
    private Clip clip;

    public KeyInput(GuiltSpawn game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (game.getCurrentState() == GuiltSpawn.STATE.MENU) {
            menuControls(ke);
        } else if (game.getCurrentState() == GuiltSpawn.STATE.GAME) {
            gameControls(ke);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (game.getCurrentState() == GuiltSpawn.STATE.MENU) {
            menuControls(ke);
        } else if (game.getCurrentState() == GuiltSpawn.STATE.GAME) {
            gameControls(ke);
        } else if (game.getCurrentState() == GuiltSpawn.STATE.DEATH) {
            deathControls(ke);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (game.getCurrentState() == GuiltSpawn.STATE.GAME) {
            gameControlsReleased(ke);
        }
    }

    public void menuControls(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void gameControls(KeyEvent ke) {
        int key = ke.getKeyCode();
        Player player = game.getHandler().getPlayer();
        if (key == KeyEvent.VK_D) {
            player.setRight(true);
        } else if (key == KeyEvent.VK_A) {
            player.setLeft(true);
        } else if (key == KeyEvent.VK_W) {
            player.setUp(true);
        } else if (key == KeyEvent.VK_S) {
            player.setDown(true);
        } else if (key == KeyEvent.VK_SPACE) {
            player.setJumping(true);
        } else if (key == KeyEvent.VK_SHIFT) {
            player.setDodging(true);
        } else if (key == KeyEvent.VK_F) {
            player.setDrive(50);
        } else if (key == KeyEvent.VK_G) {
            game.getHandler().setLoading(true);
            game.getHandler().setSpawnPoint(0);
            game.getHandler().setCurrentLevel(0);
            game.getHandler().setCurrentRoom(1);
            game.getHandler().loadLevel();
            SoundEffect.MUSIC1.stop();
            SoundEffect.volume = SoundEffect.volume.LOW;
            SoundEffect.DOCTOR.play(Clip.LOOP_CONTINUOUSLY);
            game.setCurrentState(GuiltSpawn.STATE.GAME);
            game.getHandler().setLoading(false);
        } else if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void deathControls(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (key == KeyEvent.VK_R) {
            game.setCurrentState(GuiltSpawn.STATE.MENU);
        } else if (key == KeyEvent.VK_T) {
            game.setCurrentState(GuiltSpawn.STATE.GAME);
            game.getHandler().loadLevel();
        }
    }

    public void gameControlsReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        Player player = game.getHandler().getPlayer();
        if (key == KeyEvent.VK_D) {
            player.setRight(false);
        } else if (key == KeyEvent.VK_A) {
            player.setLeft(false);
        } else if (key == KeyEvent.VK_W) {
            player.setUp(false);
        } else if (key == KeyEvent.VK_S) {
            player.setDown(false);
        } else if (key == KeyEvent.VK_SPACE) {
            player.setJumping(false);
        } else if (key == KeyEvent.VK_SHIFT) {
            player.setDodging(false);
        }
    }

}
