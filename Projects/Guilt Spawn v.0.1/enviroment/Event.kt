package com.joka.guiltspawn.enviroment

import com.joka.guiltspawn.entity.npc.enemies.E1
import com.joka.guiltspawn.framework.Handler
import java.util.*

data class Event(val handler: Handler) {

    val random = Random()
    var timer = 0
    var spawned = 0
    var valiable = true

    fun tick() {
        if (valiable) {
            if (spawned < 20) {
                timer++
                if (timer >= 30) {
                    timer = 0
                    val i = random.nextInt(3)
                    val j = random.nextInt(4) + 1
                    run {
                        /*when (i) {
                            0 -> handler.addObject(E1(1950f, 1350f, ObjectId.Enemy, this.handler, j))
                            1 -> handler.addObject(E1(2150f, 1400f, ObjectId.Enemy, this.handler, j))
                            2 -> handler.addObject(E1(2930f, 1550f, ObjectId.Enemy, this.handler, j))
                        }*/
                        when (i) {
                            0 -> E1(1950f, 1350f)
                            1 -> E1(2150f, 1400f)
                            2 -> E1(2930f, 1550f)
                            else -> println("Rarg")
                        }
                    }
                    spawned++
                }
                if (this.spawned >= 20) {
                    handler.event = null
                }
            }
        }
    }

}