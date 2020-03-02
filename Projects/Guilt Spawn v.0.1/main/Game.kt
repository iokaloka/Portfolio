
package com.joka.guiltspawn.main

import com.joka.guiltspawn.framework.*
import com.joka.guiltspawn.entity.Hud
import com.joka.guiltspawn.entity.GameObject
import com.joka.guiltspawn.entity.Inventory
import com.joka.guiltspawn.entity.Menu
import com.joka.guiltspawn.entity.ObjectId
import com.joka.guiltspawn.entity.player.Player
import com.joka.guiltspawn.sound.Sound
import java.awt.*
import java.util.Random
import javax.swing.JFrame
/**
 *
 * @author jonde
 */
class Game : Canvas(), Runnable {

    enum class STATE {
        MENU,
        GAME,
        FAIL,
        PAUSE,
        WAIT,
        INVENTORY
    }

    private var running = false
    var failTrue = false
    var darkness = true
    var loading = false

    private val UPDATE_CAP = Reference.UPDATE_CAP
    private var thread: Thread? = null
    var texture: Texture = Texture(1)
    var texture2: Texture? = null

    private var cam = Camera(0f, 0f, this)
    val handler = Handler(cam, this)
    var player = Player(-100f, -100f, handler, ObjectId.Player)
    private val mi = MouseInput(handler)
    var ki = KeyInput(handler)
    var random = Random()
    private var menu = Menu(this, random, texture, handler)
    var hud = Hud(this, player, texture)
    var inventory = Inventory(this)

    private var musicOn = false
    private val bgMusic = Sound()
    var soundName: String? = null
    ////////////////////////////////////////////////////////////////FUNCTIONS//////////////////////////////////////////////////////////////////

    private fun init() {
        handler.addObject(player as GameObject)
        this.addKeyListener(ki)
        this.addMouseListener(mi)
}

    @Synchronized
    fun start() {
        if (running) {
            return
        }
        running = true
        thread = Thread(this)
        thread!!.start()
    }

    override fun run() {
        start()
        init()
        this.requestFocus()

        var render = false
        var firstTime = 0.0
        var lastTime = System.nanoTime() / 1000000000.0
        var passedTime = 0.0
        var unprocessedTime = 0.0

        var frameTime = 0.0
        var frames = 0
        var fps = 0

        while (running) {
            render = false

            firstTime = System.nanoTime() / 1000000000.0
            passedTime = firstTime - lastTime
            lastTime = firstTime

            unprocessedTime += passedTime
            frameTime += passedTime

            while (unprocessedTime > UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP

                render = true
                tick()
                if (frameTime >= 1.0) {
                    frameTime = 0.0
                    fps = frames
                    frames = 0
                    //println("FPS: $fps")
                }
            }
            if (render) {
                render()
                frames++
            } else {
                try {
                    Thread.sleep(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }


    private fun tick() {
        when {
            !loading -> {
                when(State) {
                    STATE.FAIL -> ki.resetMovement()
                    STATE.INVENTORY -> "Jee"
                    STATE.MENU -> {
                        menu.tick()
                        handler.tick()
                    }
                    STATE.GAME -> {
                        if(!this.hasFocus()){
                            State = STATE.PAUSE
                        }
                        handler.tick()
                        handler.`object`.forEach {
                            if(it.id == ObjectId.Player) cam.tick(it)
                        }
                    }
                    STATE.PAUSE -> "Jee"
                    STATE.WAIT -> "Jee"
                }
            } loading -> {
                ki.resetMovement()
        }
        }
    }

    private fun render() {
        val bs = this.bufferStrategy
        if (bs == null) {
            this.createBufferStrategy(3)
            return
        }

        val g = bs.drawGraphics
        val g2d = g as Graphics2D

        if (!loading) {
            ////////////////////////////DRAW///////////////////////////////////////
            if (State == STATE.GAME || State == STATE.PAUSE || State == STATE.INVENTORY) {

                g.setColor(Color(0, 0, 0))
                g.fillRect(0, 0, width, height)

                g.drawImage(handler.currentLevel.d13, 0, 0, 1920, 1080, null) // draw bg

                g2d.translate(cam.x.toDouble(), cam.y.toDouble()) //////////////////////////////////////////////////////////// Begin of cam

                handler.currentLevel.render(g)

                if (darkness == true) {
                    if (player!!.weaponR != null) {
                        if (player!!.facing == 1) {
                            g.drawImage(texture.vingette1, player!!.weaponR.x.toInt() - 1860, player!!.weaponR.y.toInt() - 1040, 3840, 2160, null)
                        } else {
                            g.drawImage(texture.vingette1, player!!.weaponR.x.toInt() - 1780 - 136, player!!.weaponR.y.toInt() - 1040, 3840, 2160, null)
                        }
                    } else {
                        //Starting level vingette
                    }
                }

                g2d.translate((-cam.x).toDouble(), (-cam.y).toDouble()) /////////////////////////////////////////////////////////// End of cam

                g.drawImage(texture.vingette2, 0, 0, null)
                hud.render(g)

                if (State == STATE.PAUSE) {
                    g.drawImage(texture.pause, 0, 0, 1920, 1080, null)
                }

                if (State == STATE.INVENTORY) {
                    inventory.render(g)
                }

            } else if (State == STATE.MENU) { // Menu
                menu.render(g)
                handler.render(g)
            } else if (State == STATE.FAIL) { // Failure
                g.drawImage(texture.fail, 0, 0, 1920, 1080, null)
            } else if (State == STATE.WAIT) {
                g.setColor(Color(0, 0, 0))
                g.fillRect(0, 0, width, height)
            }
        } else {
            g.setColor(Color(0, 0, 0))
            g.fillRect(0, 0, width, height)
        }

        //////////////////////END DRAW/////////////////////////////////////////
        g.dispose()
        bs.show()
    }

    companion object {

        var WIDTH = Reference.WIDTH
        var HEIGHT = Reference.HEIGHT

        var State = STATE.MENU
        var LEVEL = 1
        var CURRENTTEXTURE = Texture(11)

        @JvmStatic
        fun main(Args: Array<String>) {

            val game = Game()
            game.preferredSize = Dimension(1920, 1080)
            game.maximumSize = Dimension(1920, 1080)
            game.minimumSize = Dimension(1920, 1080)

            val frame = JFrame("Guilt Spawn")

            frame.add(game)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.isResizable = false
            frame.setLocationRelativeTo(null)
            frame.setSize(1920, 1080)
            frame.extendedState = JFrame.MAXIMIZED_BOTH
            frame.isUndecorated = true
            frame.pack()
            frame.isVisible = true
            frame.setLocation(0, 0)

            game.start()

        }
    }
}
