package com.joka.guiltspawn.Interfaces

import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.entity.npc.enemies.FACING

abstract class IEnemy(x: Float, y: Float) : INpc(x, y) {

    override val objectId = ObjectId.Enemy

    abstract var maxHealth: Int
    abstract var currentHealth: Int
    abstract var baseDamage: Int
    abstract var velX: Float
    abstract var velY: Float
    abstract var facing: FACING
    abstract var invulnerable: Boolean
    abstract val width: Int
    abstract val height: Int

    abstract fun spawn()
    abstract fun kill()
    abstract fun aggro()
    abstract fun attack()

}
