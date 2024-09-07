import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class PPMViewer{
    companion object{

        fun display(filename:String) {
            val ppmFile = File(filename)

            if (!ppmFile.exists()) {
                println("PPM file not found: ${ppmFile.absolutePath}")
                return
            }

            val (width, height, pixels) = readPPM(ppmFile)

            // Create the BufferedImage to display the image
            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

            // Populate the BufferedImage with the pixel data
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val rgb = pixels[y * width + x]
                    val color = Color(rgb[0], rgb[1], rgb[2]) // RGB values
                    image.setRGB(x, y, color.rgb)
                }
            }

            // Create a JFrame to display the image
            val frame = JFrame("PPM Image Viewer")
            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.preferredSize = Dimension(width, height)

            // Create a custom JPanel to draw the image
            val panel = object : JPanel() {
                override fun paintComponent(g: Graphics?) {
                    super.paintComponent(g)
                    g?.drawImage(image, 0, 0, null)
                }
            }
            panel.preferredSize = Dimension(width, height)

            // Add panel to the frame
            frame.add(panel)
            frame.pack()
            frame.isVisible = true
        }

        /**
         * Reads a PPM file in P3 format and returns the width, height, and the pixel data.
         */
        fun readPPM(file: File): Triple<Int, Int, List<IntArray>> {
            val lines = file.readLines().filter { it.isNotEmpty() && !it.startsWith("#") } // Ignore comments
            val header = lines[0]

            if (header != "P3") {
                throw IllegalArgumentException("Unsupported PPM format: $header")
            }

            // Get image width and height from the second line
            val (width, height) = lines[1].split(" ").map { it.toInt() }

            // Get the maximum color value (usually 255)
            val maxColorValue = lines[2].toInt()
            if (maxColorValue != 255) {
                throw IllegalArgumentException("Unsupported max color value: $maxColorValue")
            }

            // Collect all pixel data (each pixel has three values: R, G, B)
            val pixelData = lines.drop(3).flatMap { it.split(" ").map { it.toInt() } }

            // Convert flat pixel data into a list of RGB triplets
            val pixels = pixelData.chunked(3).map { intArrayOf(it[0], it[1], it[2]) }

            return Triple(width, height, pixels)
        }

    }


}