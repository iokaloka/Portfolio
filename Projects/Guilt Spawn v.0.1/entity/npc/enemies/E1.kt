package com.joka.guiltspawn.entity.npc.enemies

import com.joka.guiltspawn.Interfaces.IEnemy
import com.joka.guiltspawn.framework.NewAnimation
import com.joka.guiltspawn.main.Game
import com.joka.guiltspawn.main.Reference
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.image.BufferedImage

data class E1(override var x: Float, override var y: Float) : IEnemy(x, y) {
    override var invulnerable: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override val width: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val height: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var facing: FACING = randomizeFacing()
    override var maxHealth: Int = 100
    override var currentHealth: Int = 100
    override var baseDamage: Int = 1
    override var velX: Float = 0f
    override var velY: Float = 0f

    override fun spawn() {
    }

    override fun kill() {
    }

    override fun aggro() {
    }

    override fun attack() {
    }

    init {
        randomizeFacing()
        E1.all += this
    }

    val currentTexture: Array<BufferedImage?> = Game.CURRENTTEXTURE.e_1
    val right = NewAnimation(5,currentTexture.toList().subList(0,3))
    val left= NewAnimation(5,currentTexture.toList().subList(4,7).reversed())

    override fun tick() {
        x -= 0f
        y += 0f
    }

    /*fun checkAggro(): Boolean = false
     val a = Math.abs(this.x.toInt())
     val b = Math.abs(Game.Companion.))
     val c = Math.abs(this.getY().toInt())
     val d = Math.abs(this.handler.game.player.y.toInt())
     return (Math.abs(a - b) < 350 && Math.abs(c - d) < 150)*/

    override fun render(g: Graphics) {
        /*val g2d = g as Graphics2D
        g2d.color = Color.WHITE
        g2d.fillRect(x.toInt(), y.toInt(), 48, 48)*/
        when(facing){
            FACING.RIGHT -> right.render(g,x.toInt(),y.toInt())
            FACING.LEFT -> left.render(g,x.toInt(),y.toInt())
        }
    }

    override fun getBounds() = Rectangle(48, 48)

    fun randomizeFacing(): FACING{
        when( Reference.RANDOM.nextInt(2)){
            0 -> return FACING.RIGHT
            else -> return FACING.LEFT
        }
    }

    companion object {
        val all = mutableListOf<E1>()
    }
}
