package com.joka.guiltspawn.Interfaces

import java.awt.Rectangle

abstract class IBlock(x: Float, y: Float): IEntity(x, y) {

    override fun getBounds(): Rectangle = Rectangle(x.toInt(), y.toInt(), 32, 32)

}