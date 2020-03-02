/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity

import com.joka.guiltspawn.main.Game

/**
 *
 * @author jonde
 */
class Action(val type: Int, private val game: Game) {

    fun act() {
        when (this.type) {
        // Turn of darkness
            1 -> this.game.darkness = false
        // Pick up torch
            2 -> if (game.player.weaponR == null) {
                val torch = Weapon(game.player.getX().toInt().toFloat(), game.player.getY().toInt().toFloat(), ObjectId.Weapon, game.player, 2, 10)
                game.player.weaponR = torch
                game.handler.addObject(torch)
            } else if (game.player.weaponL == null) {

            }
        }
    }

}
