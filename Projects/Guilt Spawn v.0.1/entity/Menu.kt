/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.framework.Texture
import com.joka.guiltspawn.entity.Block
import com.joka.guiltspawn.framework.Animation
import com.joka.guiltspawn.framework.Handler
import com.joka.guiltspawn.main.Game
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.util.*

/**
 *
 * @author jonde
 */
class Menu(private val game: Game, private val random: Random, private val tex: Texture, private val handler: Handler) {
    private val animation: Animation
    private val img: BufferedImage?
    private val flash: BufferedImage?
    private val shade: BufferedImage?
    private var posX: Int = 0
    private var posY: Int = 0
    private var counter: Int = 0

    init {
        animation = Animation(10, tex.menu[0])
        img = tex.menu[4]
        flash = game.texture.loader.loadImage("/RandomObjects/menu_flash.png")
        shade = game.texture.loader.loadImage("/RandomObjects/menu_flash2.png")
        counter = random.nextInt(50)
    }

    fun tick() {
        if (counter % 10 == 0){
            handler.addObject(Block(random.nextInt(2100).toFloat(), 1080f, 12, ObjectId.Block, handler))
        }
        if (counter <= 0) {
         handler.addObject(Block(random.nextInt(2100).toFloat(), 0f, 8, ObjectId.Block, handler))
         handler.addObject(Block(960f + random.nextInt(1140), 1080f, 11, ObjectId.Block, handler))
            counter = random.nextInt(50)
        }
        animation.runAnimation()
        counter--
    }

    fun render(g: Graphics) {
        animation.drawAnimation(g, 0, 0, 1920, 1080)
        if (counter % 5 == 0) {
            posX = random.nextInt(1920)
            posY = random.nextInt(540) + 540
        }
        g.drawImage(flash, posX, posY, 1920, 1080, null)
        g.drawImage(shade, posX - 1920, posY - 1020, 1920, 1080, null)
        g.drawImage(img, (450 * 2.4).toInt(), (240 * 1.8).toInt(), 480, 270, null)
    }

}
