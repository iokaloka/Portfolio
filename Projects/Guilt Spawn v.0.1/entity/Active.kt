package com.joka.guiltspawn.entity

import com.joka.guiltspawn.Interfaces.IItem
import java.awt.Graphics
import java.awt.Rectangle

class Active(x: Float, y: Float) : IItem(x, y) {
    override val destination: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val dn: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var active: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override val objectId: ObjectId
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun tick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(g: Graphics) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBounds(): Rectangle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}