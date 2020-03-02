package com.joka.guiltspawn.entity

import com.joka.guiltspawn.Interfaces.IBlock
import java.awt.Graphics

class Wall(x: Float, y: Float) : IBlock(x, y) {


    override val objectId = ObjectId.Wall

    override fun tick() {}

    override fun render(g: Graphics) {}


}