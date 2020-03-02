package com.joka.guiltspawn.entity

import com.joka.guiltspawn.Interfaces.IItem
import java.awt.Graphics
import java.awt.Rectangle

class KeyItem(x: Float, y: Float, override var active: Boolean): IItem(x, y) {
    override val objectId = ObjectId.KeyItem
    override val destination = 0
    override val dn = 0



    override fun tick() {
    }

    override fun render(g: Graphics) {
    }

    override fun getBounds(): Rectangle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}