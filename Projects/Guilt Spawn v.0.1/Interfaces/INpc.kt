package com.joka.guiltspawn.Interfaces

import com.joka.guiltspawn.entity.ObjectId
import java.awt.Graphics
import java.awt.Rectangle

abstract class INpc(x: Float, y: Float) : IEntity(x, y) {
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