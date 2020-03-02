/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework

import java.awt.image.BufferedImage

/**
 *
 * @author jonde
 */
class SpriteSheet(private val image: BufferedImage) {

    fun grabImage(col: Int, row: Int, width: Int, height: Int): BufferedImage {
        return image.getSubimage(col * width - width, row * height - height, width, height)
    }

    fun grabImage(col: Int, row: Int, width: Int, height: Int, b: Int): BufferedImage { // col and row are now pixel values
        return image.getSubimage(col, row, width, height)
    }

}
