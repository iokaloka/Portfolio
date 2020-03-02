/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework

import com.joka.guiltspawn.io.BufferedImageLoader
import java.awt.image.BufferedImage

/**
 *
 * @author jonde
 */
class Texture(private val type: Int) {
    val loader = BufferedImageLoader()

    var block_sheet = loader.loadImage("/block_sheet.png")
    var object_sheet = loader.loadImage("/object_sheet.png")
    var mc_sheet = loader.loadImage("/mc_1_sheet.png")

    var e1_sheet = loader.loadImage("/e1_sheet.png")
    var e1 = SpriteSheet(e1_sheet!!)

    var bs = SpriteSheet(block_sheet!!)
    var os = SpriteSheet(object_sheet!!)
    var mcs = SpriteSheet(mc_sheet!!)

    var block = arrayOfNulls<BufferedImage>(18)
    var objects = arrayOfNulls<BufferedImage>(32)
    var message1: Array<BufferedImage?> = arrayOfNulls(8)
    var e_1: Array<BufferedImage?> = arrayOfNulls(22)
    var menu: Array<BufferedImage?> = arrayOfNulls(5)

    var inventory: BufferedImage? = null
    var mc = arrayOfNulls<BufferedImage>(40)

    var level = arrayOfNulls<BufferedImage>(13)

    var fail: BufferedImage? = null
    var pause: BufferedImage? = null
    var fading: BufferedImage? = null
    var fading2: BufferedImage? = null
    var vingette1: BufferedImage? = null
    var vingette2: BufferedImage? = null

    init {
        try {
            when(type) {
                 1 -> {
                    (0..3).forEach{ menu[it] = loader.loadImage("/menu" + (it+1) + ".png")}
                    menu[4] = loader.loadImage("/menut.png")
                    pause = loader.loadImage("/pause.png")
                    fail = loader.loadImage("/failure.png")
                    inventory = loader.loadImage("/inventory.png")
                    fading = loader.loadImage("/fading1.png")
                    fading2 = loader.loadImage("/fading2.png")
                    vingette1 = loader.loadImage("/vingette_1.png")
                    vingette2 = loader.loadImage("/vingette_2.png")
                    objects[31] = loader.loadImage("/RandomObjects/smoke1.png")
                }
                 11, 12, 13, 14, 15, 16, 17, 18 -> {
                    (0..12).forEach{ level[it] = loader.loadImage("/poe1_${type - 10}s/1_${type - 10}_${it + 1}.png")}
                     (0..7).forEach{ message1[it] = loader.loadImage("/RandomObjects/messages/pressE${it + 1}.png")}
                    block[14] = loader.loadImage("/RandomObjects/gate1.png") // karma
                    block[15] = loader.loadImage("/RandomObjects/pillar1_1.png")
                    block[16] = loader.loadImage("/RandomObjects/pillar1_2.png")
                    block[17] = loader.loadImage("/RandomObjects/pillar1_3.png")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        getTextures()
    }

    private fun getTextures() {

        if (this.type == 1) {

            block[6] = bs.grabImage(1, 2, 32, 32) // karma
            block[7] = bs.grabImage(2, 2, 32, 32) // karma
            block[8] = bs.grabImage(3, 2, 32, 32) // karma
            block[9] = bs.grabImage(4, 2, 32, 32) // karma
            block[10] = bs.grabImage(5, 2, 32, 32) // karma
            block[11] = bs.grabImage(6, 2, 32, 32) // karma
            block[12] = bs.grabImage(7, 2, 32, 32) // karma
            block[13] = bs.grabImage(7, 1, 32, 32) // karma

            objects[10] = loader.loadImage("/RandomObjects/hud1.png")
           // objects[11] = loader.loadImage("/RandomObjects/hud1.png")
            objects[12] = os.grabImage(9, 10, 48, 48) //  hud karma
            objects[16] = os.grabImage(10, 1, 48, 96) //  flash
            objects[17] = os.grabImage(1, 3, 48, 48) //  flake
            objects[18] = os.grabImage(2, 3, 48, 48) //  flake
            objects[19] = os.grabImage(3, 3, 48, 48) //  flake
            objects[20] = os.grabImage(4, 3, 48, 48) //  flake
            objects[21] = os.grabImage(1, 4, 48, 48) // torch right
            objects[22] = os.grabImage(2, 4, 48, 48) // torch left
            objects[23] = os.grabImage(3, 4, 48, 48) // fire1
            objects[24] = os.grabImage(4, 4, 48, 48) // fire2
            objects[25] = os.grabImage(5, 4, 48, 48) // fire3
            objects[26] = os.grabImage(6, 4, 48, 48) // fire4
            objects[27] = os.grabImage(7, 4, 48, 48) // fire5
            objects[28] = os.grabImage(8, 4, 48, 48) // fire6
            objects[29] = os.grabImage(9, 4, 48, 48) // fire7
            objects[30] = os.grabImage(10, 4, 48, 48) // fire8


            mc[0] = mcs.grabImage(1, 1, 48, 96) //walkR 1
            mc[1] = mcs.grabImage(48, 0, 53, 96, 0) //walkR 2
            mc[2] = mcs.grabImage(4, 1, 48, 96) //walkR 3
            mc[3] = mcs.grabImage(5, 1, 48, 96) //walkR 4

            mc[4] = mcs.grabImage(5, 2, 48, 96) //walkL 1
            mc[5] = mcs.grabImage(96, 96, 54, 96, 0) //walkL 2
            mc[6] = mcs.grabImage(2, 2, 48, 96) //walkL 3
            mc[7] = mcs.grabImage(1, 2, 48, 96) //walkL 4

            mc[8] = mcs.grabImage(0, 192, 61, 96, 0) //jumpR 1
            mc[9] = mcs.grabImage(96, 192, 77, 96, 0) //jumpR 2

            mc[10] = mcs.grabImage(192, 192, 61, 96, 0) //jumpL 1
            mc[11] = mcs.grabImage(288, 192, 76, 96, 0) //jumpL 2

            mc[12] = mcs.grabImage(1, 4, 48, 96) //standR
            mc[13] = mcs.grabImage(2, 4, 48, 96) //standR + attackR
            mc[14] = mcs.grabImage(3, 4, 48, 96) //standR + holdLanternR
            mc[15] = mcs.grabImage(4, 4, 53, 96, 0) //standR + attackL
            mc[16] = mcs.grabImage(6, 4, 56, 96, 0) //standR + holdLanternL
            mc[17] = mcs.grabImage(8, 4, 55, 96, 0) //standR + attackR + holdLanternL

            mc[18] = mcs.grabImage(1, 5, 48, 96) //standL
            mc[19] = mcs.grabImage(2, 5, 48, 96) //standL + attackL
            mc[20] = mcs.grabImage(3, 5, 48, 96) //standL + holdLanternL
            mc[21] = mcs.grabImage(141, 384, 51, 96, 0) //standL + attackR
            mc[22] = mcs.grabImage(192, 384, 52, 96, 0) //standL + holdLanternR
            mc[23] = mcs.grabImage(7, 5, 52, 96, 0) //standL + attackL + holdLanternL

            mc[24] = mcs.grabImage(1, 6, 48, 96) //walkL + holdLanternR 1
            mc[25] = mcs.grabImage(2, 6, 48, 96) //walkL + holdLanternR 2
            mc[26] = mcs.grabImage(3, 6, 48, 96) //walkL + holdLanternR 3
            mc[27] = mcs.grabImage(4, 6, 48, 96) //walkL + holdLanternR 4

            mc[28] = mcs.grabImage(288, 768, 57, 96, 0) //walkR + holdLanternR 1
            mc[29] = mcs.grabImage(192, 768, 61, 96, 0) //walkR + holdLanternR 2
            mc[30] = mcs.grabImage(96, 768, 54, 96, 0) //walkR + holdLanternR 3
            mc[31] = mcs.grabImage(0, 768, 54, 96, 0) //walkR + holdLanternR 4

            mc[32] = mcs.grabImage(1, 7, 48, 96) //walkL + holdLanternL 1
            mc[33] = mcs.grabImage(2, 7, 48, 96) //walkL + holdLanternL 2
            mc[34] = mcs.grabImage(3, 7, 48, 96) //walkL + holdLanternL 3
            mc[35] = mcs.grabImage(4, 7, 48, 96) //walkL + holdLanternL 4

            mc[36] = mcs.grabImage(0, 672, 57, 96, 0) //walkL + holdLanternR 1
            mc[37] = mcs.grabImage(96, 672, 61, 96, 0) //walkL + holdLanternR 2
            mc[38] = mcs.grabImage(192, 672, 58, 96, 0) //walkL + holdLanternR 3
            mc[39] = mcs.grabImage(288, 672, 57, 96, 0) //walkL + holdLanternR 4

        } else if (type == 13 || type == 17 || type == 18) {

            e_1[0] = e1.grabImage(0, 0, 96, 96, 0) //crawl right 1
            e_1[1] = e1.grabImage(96, 0, 96, 96, 0) //crawl right 2
            e_1[2] = e1.grabImage(192, 0, 96, 96, 0) //crawl right 3
            e_1[3] = e1.grabImage(288, 0, 96, 96, 0) //crawl right 4

            e_1[4] = e1.grabImage(0, 96, 96, 96, 0) //crawl left 1
            e_1[5] = e1.grabImage(96, 96, 96, 96, 0) //crawl left 2
            e_1[6] = e1.grabImage(192, 96, 96, 96, 0) //crawl left 3
            e_1[7] = e1.grabImage(288, 96, 96, 96, 0) //crawl left 4

            e_1[8] = e1.grabImage(0, 288, 96, 96, 0) //4x right 1
            e_1[9] = e1.grabImage(96, 288, 96, 96, 0) //4x right 2
            e_1[10] = e1.grabImage(192, 288, 96, 96, 0) //4x right 3

            e_1[11] = e1.grabImage(0, 192, 96, 96, 0) //4x left 1
            e_1[12] = e1.grabImage(96, 192, 96, 96, 0) //4x left 2
            e_1[13] = e1.grabImage(192, 192, 96, 96, 0) //4x left 3

            e_1[14] = e1.grabImage(0, 576, 96, 110, 0) //2x right 1
            e_1[15] = e1.grabImage(96, 576, 96, 110, 0) //2x right 2
            e_1[16] = e1.grabImage(192, 576, 96, 110, 0) //2x right 3
            e_1[17] = e1.grabImage(288, 576, 96, 110, 0) //2x right 4

            e_1[18] = e1.grabImage(0, 384, 96, 110, 0) //2x left 1
            e_1[19] = e1.grabImage(96, 384, 96, 110, 0) //2x left 2
            e_1[20] = e1.grabImage(192, 384, 96, 110, 0) //2x left 3
            e_1[21] = e1.grabImage(288, 384, 96, 110, 0) //2x left 4


        }

    }

}
