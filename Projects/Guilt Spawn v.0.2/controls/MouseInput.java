/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiltspawn.controls;

import guiltspawn.engine.GuiltSpawn;
import guiltspawn.entity.Player;
import guiltspawn.sound.SoundEffect;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

/**
 *
 * @author jonde
 */
public class MouseInput implements MouseListener {

    private GuiltSpawn game;

    public MouseInput(GuiltSpawn game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (game.getCurrentState() == GuiltSpawn.STATE.MENU) {
            menuControls(me);
        } else if (game.getCurrentState() == GuiltSpawn.STATE.GAME) {
            gameControls(me);
        } else if (game.getCurrentState() == GuiltSpawn.STATE.DEATH) {
            failureControls(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        gameReleaseControls(me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void menuControls(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (x > 990 && x < 1165 && y > 445 && y < 477) {
            SoundEffect.THEME.stop();
            SoundEffect.SHOT1.play();
            game.setCurrentState(GuiltSpawn.STATE.GAME);
            game.getHandler().setSpawnPoint(1);
            game.getHandler().loadLevel();
            game.getMenu().clearParticles();
            SoundEffect.MUSIC1.play(Clip.LOOP_CONTINUOUSLY);
            SoundEffect.volume = SoundEffect.volume.HIGH;
        } else if (x > 990 && x < 1140 && y > 480 && y < 512) {
        } else if (x > 990 && x < 1080 && y > 515 && y < 547) {
        } else if (x > 990 && x < 1115 && y > 550 && y < 580) {
        } else if (x > 990 && x < 1065 && y > 585 && y < 617) {
            System.exit(0);
        }
//        g2d.drawRect(990, 445, 175, 32);
//        g2d.drawRect(990, 480, 150, 32);
//        g2d.drawRect(990, 515, 90, 32);
//        g2d.drawRect(990, 550, 125, 32);
//        g2d.drawRect(990, 585, 75, 32);
    }

    public void gameControls(MouseEvent me) {
        Player player = game.getHandler().getPlayer();
        if (SwingUtilities.isLeftMouseButton(me)) {
            if (!player.isAttacking()) {
                if(player.getFatigue() + player.getAttackCost() <= player.getMaxFatigue())
                player.setAttacking(true);
            }
        } else if (SwingUtilities.isRightMouseButton(me)) {
            player.setStabilizing(true);
        }
    }

    public void failureControls(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (x > 825 && x < 1025 && y > 795 && y < 845) {
            game.setCurrentState(GuiltSpawn.STATE.GAME);
            game.getHandler().loadLevel();
            SoundEffect.MUSIC1.play(Clip.LOOP_CONTINUOUSLY);
            SoundEffect.volume = SoundEffect.volume.MEDIUM;
        } else if (x > 825 && x < 1025 && y > 860 && y < 910) {
            game.setCurrentState(GuiltSpawn.STATE.MENU);
            SoundEffect.volume = SoundEffect.volume.LOW;
            SoundEffect.THEME.play(-1);

        }
//        g.drawRect(825, 795, 200, 50);
//        g.drawRect(825, 860, 200, 50);
    }

    public void gameReleaseControls(MouseEvent me) {
        Player player = game.getHandler().getPlayer();
        if (SwingUtilities.isRightMouseButton(me)) {
            player.setStabilizing(false);
        }
    }

}
