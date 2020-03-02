/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.enviroment

import com.joka.guiltspawn.Interfaces.ITrigger
import com.joka.guiltspawn.entity.GameObject
import com.joka.guiltspawn.entity.ObjectId

import java.awt.Graphics
import java.awt.Rectangle

/**
 *
 * @author jonde
 */
class Flag(x: Float, y: Float, override val destination: Int, override val dn: Int) : ITrigger(x, y) {
    override var active: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override val objectId = ObjectId.Flag

    override fun tick() {}

    override fun render(g: Graphics) {}

    override fun getBounds(): Rectangle =  Rectangle(x.toInt(), y.toInt(), 32, 32)

    fun getDoorNumber(): Int = this.dn

}
