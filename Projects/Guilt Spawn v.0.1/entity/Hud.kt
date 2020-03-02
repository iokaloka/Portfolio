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
class Hud(private val game: Game, private val player: Player, private val tex: Texture) {

    fun render(g: Graphics) {
        drawHealth(g)
        drawDrive(g)
        //drawKarma(g)
        //drawAmmo(g)

    }

    fun drawHealth(g: Graphics) {
        //val r = 100 * (2 * (1.0 * player!!.health / (1.0 * player!!.maxHealth)))
        g.drawImage(tex.objects[10], 0, 0, null)
        g.color = Color.RED
        g.fillRect(100, 75, (5.3 * player.health).toInt(), 4)
        //g.drawImage(tex.objects[11], 0, 0, null)
    }

    fun drawDrive(g: Graphics){
        g.color = Color.MAGENTA
        g.fillRect(75, 100,3, (1.68 * player.drive).toInt())
    }

    fun drawKarma(g: Graphics) {
        g.color = Color.white
        g.drawImage(tex.objects[12], (30 * 2.4).toInt(), (40 * 1.8).toInt(), (48 * 2.4).toInt(), (48 * 1.8).toInt(), null)
        g.font = Font("Hud2", 1, 20)
        g.drawString("" + player!!.karma, (80 * 2.4).toInt(), (68 * 1.8).toInt())
    }
}
