/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.framework.Texture
import com.joka.guiltspawn.entity.player.Player
import com.joka.guiltspawn.main.Game
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

/**
 *
 * @author jonde
 */

class Inventory(private val game: Game) {
    private val tex: Texture
    private val player: Player?



    init {
        this.tex = game.texture
        this.player = game.player
    }

    fun drawStats(g: Graphics) {
        g.color = Color.white
        g.font = Font("Inventory", 1, 20)
        g.drawString("" + player!!.health + " / " + player.maxHealth, (580 * 2.4).toInt(), (96 * 1.8 - 5).toInt())
        g.drawString("" + player.def, (580 * 2.4).toInt(), (134 * 1.8 - 5).toInt())
        g.drawString("" + player.str, (580 * 2.4).toInt(), (173 * 1.8 - 5).toInt())

    }

    fun render(g: Graphics) {
        g.drawImage(game.texture.inventory, 0, 0, 1920, 1080, null)
        drawStats(g)
    }
}
