/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.Interfaces.ITrigger
import com.joka.guiltspawn.main.Game

import java.awt.Graphics
import java.awt.Rectangle
import java.awt.image.BufferedImage

/**
 *
 * @author jonde
 */
class Activateable(x: Float, y: Float, id: ObjectId, private val image: BufferedImage, action: Action, private val game: Game, override var active: Boolean) : ITrigger(x, y) {


    override val objectId = ObjectId.Activateable
    override val destination = 0
    override val dn = 0
    private val fireChange = 3
    private var fireGone = 0
    private var fire: BufferedImage? = null

    init {
        /*this.action = action*/
        fire = game.texture.objects[23]
    }

    override fun tick() {
       /* if (this.activated) {
            game.handler.removeObject(this)
        }*/
    }

    override fun render(g: Graphics) {
        g.drawImage(image, x.toInt(), y.toInt(), null)
        //        Graphics2D g2d = (Graphics2D) (g);
        //        g2d.draw(getBounds());

       /* if (this.action.type == 2) {
            if (fireGone >= fireChange) {
                when (game.random.nextInt(8)) {
                    0 -> fire = game.texture.objects[23]
                    1 -> fire = game.texture.objects[24]
                    2 -> fire = game.texture.objects[25]
                    3 -> fire = game.texture.objects[26]
                    4 -> fire = game.texture.objects[27]
                    5 -> fire = game.texture.objects[28]
                    6 -> fire = game.texture.objects[29]
                    7 -> fire = game.texture.objects[30]
                }
                fireGone = 0
            } else {
                fireGone++
            }
            g.drawImage(fire, x.toInt() + 5, y.toInt() - 25, null)
        }*/
    }

    override fun getBounds(): Rectangle {
        return Rectangle(x.toInt() - 8, y.toInt() - 8, 48, 48)
    }

}
