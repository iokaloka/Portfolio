/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.enviroment

import com.joka.guiltspawn.entity.player.Player
import com.joka.guiltspawn.framework.Handler
import com.joka.guiltspawn.main.Reference
import java.awt.Graphics
import java.awt.image.BufferedImage

/**
 *
 * @author jonde
 */
class Level(var current: Int, var levelSceleton: BufferedImage?,
            // TODO var levelLayers: Array<BufferedImage>
            val d1: BufferedImage, val d2: BufferedImage, val d3: BufferedImage, val d4: BufferedImage, val d5: BufferedImage,
            val d6: BufferedImage, val d7: BufferedImage, val d8: BufferedImage, val d9: BufferedImage, val d10: BufferedImage,
            val d11: BufferedImage, val d12: BufferedImage, val d13: BufferedImage, val handler: Handler, private val p: Player) {

    var levelLayers = arrayOf(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13)

    fun parallax(e:Int): Int =
        when(e) {
            in 0..3 -> (120.toFloat() * (e-3) * ((Reference.LEVEL_WIDTH/2 - p.x) / Reference.LEVEL_WIDTH/2)).toInt()
            in 6..11 -> (120.toFloat() * (e-5) * ((Reference.LEVEL_WIDTH/2 - p.x) / Reference.LEVEL_WIDTH/2)).toInt()
            else -> 0
        }

    fun render(g: Graphics){
        11.downTo(5).forEach{ g.drawImage(levelLayers[it], parallax(it), 0, Reference.LEVEL_WIDTH, Reference.LEVEL_HEIGHT, null)}
        handler.render(g)
        4.downTo(0).forEach{ g.drawImage(levelLayers[it], parallax(it), 0, Reference.LEVEL_WIDTH, Reference.LEVEL_HEIGHT, null)}
    }
}
