/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.io

import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

/**
 *
 * @author jonde
 */
class BufferedImageLoader {

    private var image: BufferedImage? = null

    fun loadImage(path: String): BufferedImage? {
        try {
            image = ImageIO.read(javaClass.getResource(path))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return image
    }

}
