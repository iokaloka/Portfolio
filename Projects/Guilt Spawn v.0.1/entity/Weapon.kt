/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.entity.player.Player
import com.joka.guiltspawn.entity.GameObject
import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.framework.Texture
import com.joka.guiltspawn.sound.Sound

import java.awt.Graphics
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.util.Random

/**
 *
 * @author jonde
 */
class Weapon(x: Float, y: Float, id: ObjectId, private val player: Player, var weaponId: Int, val duration: Int) : GameObject(x, y, id) {
    var gone = 0
        private set
    private val dmg = 10
    private val fireChange = 3
    private var fireGone = 0
    private val tex: Texture
    private var fire: BufferedImage? = null

    init {
        this.setFacing(player.getFacing())
        this.tex = player.tex
        fire = tex.objects[23]
    }

    override fun tick() {
        this.setFacing(player.getFacing())

        if (!player.getAttackingR() && !player.getAttackingL()) {
            if (player.getFacing() == 1) {
                this.x = player.getX() - 12
                this.y = player.getY() - 22
            } else {
                this.x = player.getX() + 17
                this.y = player.getY() - 15
            }
        }

        if (player.getAttackingR()) {
            if (gone < duration) {
                collision()
                gone++
                if (facing == 1) {
                    this.x = player.getX() - 22
                    this.y = player.getY() + 2
                } else {
                    this.x = player.getX() + 22
                    this.y = player.getY() + 9
                }

            } else {
                player.setAttackingR(false)
                gone = 0
            }

        }

        if (player.isJumping) {
            if (facing == 1) {
                if (player.currentAnimation != null) {
                    if (player.currentAnimation.count == 1) {
                        this.x = player.getX() - 65
                        this.y = player.getY() + 7
                    } else if (player.currentAnimation.count >= 2) {
                        this.x = player.getX() - 63
                        this.y = player.getY() - 23
                    }
                }
            } else {
                if (player.currentAnimation != null) {
                    if (player.currentAnimation.count == 1) {
                        this.x = player.getX() + 14
                        this.y = player.getY() + 8
                    }
                }
            }
        }
    }

    override fun render(g: Graphics) {
        //        Graphics2D g2d = (Graphics2D) g;
        //        g.setColor(Color.red);
        //        if (player.getAttackingR()) {
        //            g2d.draw(getBounds());
        //        }
        val random = player.handler.game.random
        if (fireGone >= fireChange) {
            val i = random.nextInt(8)
            when (i) {
                0 -> fire = tex.objects[23]
                1 -> fire = tex.objects[24]
                2 -> fire = tex.objects[25]
                3 -> fire = tex.objects[26]
                4 -> fire = tex.objects[27]
                5 -> fire = tex.objects[28]
                6 -> fire = tex.objects[29]
                7 -> fire = tex.objects[30]
            }
            fireGone = 0
        } else {
            fireGone++
        }

        if (this.player.getFacing() == 1) {
            g.drawImage(tex.objects[21], x.toInt() + 46, y.toInt() + 16, null)
            if (weaponId == 2) {
                g.drawImage(fire, x.toInt() + 50, y.toInt() - 16, null)
            }

        } else {
            g.drawImage(tex.objects[22], x.toInt() - 54, y.toInt() + 6, null)
            if (weaponId == 2) {
                g.drawImage(fire, x.toInt() - 58, y.toInt() - 16, null)
            }

        }
        //Collision boxes
        //        g2d.draw(getBounds());
    }

    fun collision() {
        for (i in 0 until player.handler.getObject().size) {
            val tempObject = player.handler.getObject()[i]

            if (tempObject.getId() === ObjectId.Enemy) {
                if (this.bounds.intersects(tempObject.bounds) && !tempObject.checkInvulnerably()) {
                    knockback(tempObject)
                    knockbackV(tempObject)
                    tempObject.setHealth(tempObject.getHealth() - dmg * player.str + 2)
                    tempObject.setInvulnerable(duration)
                    Sound().play("snd/hit.WAV")
                }
            } else if (tempObject.getId() === ObjectId.Block) {
                if (tempObject.type == 10) {
                    if (this.bounds.intersects(tempObject.bounds) && !tempObject.checkInvulnerably()) {
                        tempObject.setHealth(tempObject.getHealth() - this.dmg)
                        tempObject.setInvulnerable(duration)
                    }
                }
            }
        }
    }

    override fun knockback(`object`: GameObject) {
        if (`object`.getSpeedModifier() == 0f) {
            if (this.getFacing() == -1) {
                `object`.setSpeedModifier(-10f)
            } else {
                `object`.setSpeedModifier(10f)
            }
        }
    }

    fun knockbackV(`object`: GameObject) {
        `object`.setSpeedModifierV(-10f)
    }

    override fun getBounds(): Rectangle {

        var bounds = Rectangle(0, 0, 1, 1)
        if (player.getAttackingR()) {
            if (player.getFacing() == 1) {
                bounds = Rectangle((player.getX() + 24).toInt(), player.getY().toInt() + 10, 68, 96)
            } else {
                bounds = Rectangle((player.getX() + 24 - 68).toInt(), player.getY().toInt() + 10, 68, 96)
            }
        }

        return bounds
    }

}
