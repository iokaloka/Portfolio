/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.Interfaces.ITrigger
import com.joka.guiltspawn.framework.Animation
import com.joka.guiltspawn.framework.Texture
import java.awt.Color

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle

/**
 *
 * @author jonde
 */
class Message(x: Float, y: Float, val tex: Texture) : ITrigger(x, y) {

    override val destination: Int = 0
    override val dn: Int = 0
    override var active: Boolean = false
    override val objectId = ObjectId.Message
    private var ani: Animation = Animation(5, tex.message1[7], tex.message1[6], tex.message1[5],
            tex.message1[4], tex.message1[3], tex.message1[2], tex.message1[1], tex.message1[0])
    private var ano: Animation = Animation(5, tex.message1[0], tex.message1[1], tex.message1[2],
            tex.message1[3], tex.message1[4], tex.message1[5], tex.message1[6], tex.message1[7])
    private var timer = 0

    override fun tick() {
        if (active) {
            ani.runAnimationOnce()
            timer = 60
        }
        if (timer > 0) {
            if (!active) {
                timer--
                ano.runAnimationOnce()
            }
        }
    }

    override fun render(g: Graphics) {
        if (this.active) {
            ani.drawAnimation(g, x.toInt(), y.toInt())
        } else {
            if (this.timer != 0) {
                ano.drawAnimation(g, x.toInt(), y.toInt())
            }
        }

                val g2d: Graphics2D = g as Graphics2D
                g.setColor(Color.red)
                g2d.draw(getBounds())
    }

    override fun getBounds(): Rectangle {
        return Rectangle(this.x.toInt(), this.y.toInt(), 200, 200)
    }

    fun resetAnimationIn() {
        ani = Animation(5, tex.message1[7], tex.message1[6], tex.message1[5], tex.message1[4],
                tex.message1[3], tex.message1[2], tex.message1[1], tex.message1[0])
    }

    fun resetAnimationOut() {
        ano = Animation(5, tex.message1[0], tex.message1[1], tex.message1[2], tex.message1[3],
                tex.message1[4], tex.message1[5], tex.message1[6], tex.message1[7])
    }

}
