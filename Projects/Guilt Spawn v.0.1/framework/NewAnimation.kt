package com.joka.guiltspawn.framework

import java.awt.Graphics
import java.awt.image.BufferedImage

class NewAnimation(val speed: Int, val images: List<BufferedImage?>) {

    var currentImage = 0
    fun render(g: Graphics, x: Int, y: Int) {
        g.drawImage(images[(currentImage++ / speed)], x, y, null)
        println(currentImage)
        if (currentImage / speed >= images.size) currentImage = 0
    }

    fun tick() {

    }
}