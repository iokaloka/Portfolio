package com.joka.guiltspawn.Interfaces

import com.joka.guiltspawn.entity.ObjectId

abstract class ITrigger(x: Float, y: Float): IEntity(x, y) {

    abstract val destination: Int
    abstract val dn: Int
    abstract var active: Boolean

}