package com.joka.guiltspawn.entity.player

import com.joka.guiltspawn.Interfaces.IBlock
import com.joka.guiltspawn.Interfaces.IEnemy
import com.joka.guiltspawn.Interfaces.IEntity
import com.joka.guiltspawn.Interfaces.ITrigger
import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.framework.Handler
import com.joka.guiltspawn.sound.Sound
import java.awt.Graphics
import java.awt.Rectangle

class NewPlayer(x: Float, y: Float, val handler: Handler) : IEntity(x, y) {

    override val objectId = ObjectId.Player
    var maxHealth = 100
    var health = maxHealth
    var lostHealth = 0
    var invulnerable = 0
    val width = 48
    val height = 96
    private var velY: Float = 0f
    private var jumpSpeed: Float = 0f
    private var jumpCharge: Boolean = false
    private var falling: Boolean = false
    private var jumping: Boolean = false

    override fun tick() {
        collision()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(g: Graphics) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun collision(){
        IEntity.all.forEach{
            when(it){
                is IBlock -> when(it.objectId) {
                    ObjectId.Water -> collisionWater(it)
                    ObjectId.Wall -> collisionWall(it)
                }
                is IEnemy -> collisionEnemy(it)
                is ITrigger -> when(it.objectId){
                    ObjectId.Flag -> collisionFlag(it)
                }
            }
        }
    }

    fun collisionWall(collidable: IBlock) {
        when {
            getBoundsTop().intersects(collidable.getBounds()) -> {
                y = collidable.y +33
                velY = 0f
                jumpSpeed = 0f
                jumpCharge = false
            }
            getBoundsRight().intersects(collidable.getBounds()) -> {
                x = collidable.x - width + 2
            }
            getBoundsBottom().intersects(collidable.getBounds()) -> {
                y = collidable.y - height
                velY = 0f
                if(falling && jumping) Sound().play("snd/land.WAV")
                jumping = false
                falling = false
            }
            getBoundsLeft().intersects(collidable.getBounds()) -> {
                x = collidable.x + 32
            }
        }
    }

    fun collisionWater(collidable: IBlock){
        when {
            getBoundsTop().intersects(collidable.getBounds()) -> {
                health = 0
            }
        }
    }

    fun collisionFlag(collidable: ITrigger){
        if(collidable.objectId == ObjectId.Flag){
            when {
                getBoundsTop().intersects(collidable.getBounds()) -> {
                    handler.setLevel(collidable.destination)
                    handler.setDN(collidable.dn)
                    handler.switchLevel()
                }
            }
        }
    }

    fun collisionEnemy(collidable: IEnemy){
        when {
            getBounds().intersects(collidable.getBounds()) -> {
                health -= collidable.baseDamage
                lostHealth += collidable.baseDamage
                invulnerable = 30
                Sound().play("snd/ouch.WAV")
            }
        }
    }
    fun collideMessage(collidable: ITrigger){
        if(collidable.objectId == ObjectId.Message){
            when{
                getBounds().intersects((collidable.getBounds())) -> {

                }
            }
        }
    }


    override fun getBounds() = Rectangle(x.toInt(), y.toInt(), 48, 96)
    fun getBoundsTop() = Rectangle((x + width / 2 - width / 2 / 2).toInt(), y.toInt(), width / 2, height / 2)
    fun getBoundsRight() = Rectangle((x + width - 5).toInt(), y.toInt() + 5, 5, height - 10)
    fun getBoundsBottom() = Rectangle((x + width / 2 - width / 2 / 2).toInt(), (y + height / 2).toInt(), width / 2, height / 2)
    fun getBoundsLeft() = Rectangle(x.toInt(), y.toInt() + 5, 5, height - 10)
}