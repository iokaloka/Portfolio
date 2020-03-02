/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework

import com.joka.guiltspawn.entity.GameObject
import com.joka.guiltspawn.main.Game

/**
 *
 * @author jonde
 */
class   Camera(var x: Float, var y: Float, private val game: Game) {

    fun tick(player: GameObject) {

        if (game.handler.moveZone != null) {
            x = -player.moveZone.x - 150 + (Game.WIDTH / 2 - 24) //Add modifiers later
            y = -player.moveZone.y - 75 + (Game.HEIGHT / 2 + 50) //Add modifiers later
        }


    }

}
