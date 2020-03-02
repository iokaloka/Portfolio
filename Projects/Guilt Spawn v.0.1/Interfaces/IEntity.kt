/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.Interfaces

import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.enviroment.MoveZone
import com.joka.guiltspawn.main.Game
import java.awt.Graphics
import java.awt.Rectangle

/**
 *
 * @author jonde
 */
abstract class IEntity(open var x: Float, open var y: Float) {

    abstract val objectId: ObjectId

    abstract fun tick()
    abstract fun render(g: Graphics)
    abstract fun getBounds(): Rectangle

    init {
        IEntity.all += this
    }

    companion object {
        val all = mutableListOf<IEntity>()
    }

}
