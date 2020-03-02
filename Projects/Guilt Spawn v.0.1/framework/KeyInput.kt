/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework

import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.entity.player.Player
import com.joka.guiltspawn.main.Game
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

/**
 *
 * @author jonde
 */
class KeyInput(internal var handler: Handler) : KeyAdapter() {
    private val player: Player? = handler.game.player
    private var keyDown = BooleanArray(3)

    override fun keyPressed(e: KeyEvent?) {
        val key = e!!.keyCode

        if (Game.State !== Game.STATE.PAUSE && Game.State !== Game.STATE.WAIT && Game.State !== Game.STATE.MENU && !handler.game.loading) {
            if (key == KeyEvent.VK_D) {
                player!!.setVelX(5f)
                player.setFacing(1)
                keyDown[1] = true
            }

            if (key == KeyEvent.VK_A) {
                player!!.setVelX(-5f)
                player.setFacing(-1)
                keyDown[0] = true
            }

            if (key == KeyEvent.VK_SPACE || keyDown[2]) {
                keyDown[2] = true
                if (!player!!.isJumping) {
                    player.jump()
                }
            }

            if (key == KeyEvent.VK_E) {
                for (i in 0 until handler.getObject().size) {
                    val tempObject = handler.getObject()[i]
                    if (tempObject.getId() == ObjectId.Activateable) {
                        if (tempObject.bounds.intersects(player!!.bounds) || tempObject.bounds.intersects(player.boundsTop)) {
                            tempObject.act()
                        }
                    }
                }
            }

            if (key == KeyEvent.VK_I) {
                if (Game.State === Game.STATE.GAME) {
                    Game.State = Game.STATE.INVENTORY
                } else if (Game.State === Game.STATE.INVENTORY) {
                    Game.State = Game.STATE.GAME

                }

            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (Game.State === Game.STATE.GAME || Game.State === Game.STATE.INVENTORY) {
                Game.State = Game.STATE.PAUSE
            } else if (Game.State === Game.STATE.PAUSE) {
                Game.State = Game.STATE.GAME
            } else if (Game.State === Game.STATE.MENU) {
                System.exit(0)
            }
        }

        if (key == KeyEvent.VK_Y) {
            Game.LEVEL++
            handler.switchLevel()
            handler.game.darkness = false
        }

        if (key == KeyEvent.VK_R) {
            player!!.reset()
            handler.restart()
        }

        if (key == KeyEvent.VK_Q) {
            player?.spendDrive()
        }

    }

    override fun keyReleased(e: KeyEvent?) {
        val key = e!!.keyCode

        if (Game.State == Game.STATE.GAME) {

            if (key == KeyEvent.VK_SPACE) {
                player!!.setJumpCharge(false)
                keyDown[2] = false
            }

            if (key == KeyEvent.VK_D) keyDown[1] = false

            if (key == KeyEvent.VK_A) keyDown[0] = false

            if (keyDown[0] && !keyDown[1]) player!!.setVelX(-5f)

            if (!keyDown[0] && keyDown[1]) player!!.setVelX(5f)

            if (!keyDown[0] && !keyDown[1]) player!!.setVelX(0f)


        }
    }

     fun resetMovement(){
        keyDown[0] = false
        keyDown[1] = false
        keyDown[2] = false

        player?.velX = 0f
    }
}
