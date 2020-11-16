package game

import org.scalajs.dom
import dom.{document, html, CanvasRenderingContext2D}
import html.Canvas
//import worm.Worm

object MainGame {
  val canvasLength = 600
  val backgroundColor = "#409D42"
  val backgroundWall = "#022E03"
  var time = 0
  val worm = new Worm(new WormCube(2, 1), Seq(new WormCube(1, 1)))

  def main(args: Array[String]): Unit =  {
    dom.window.setInterval(drawCanvas _, 200)
  }

  def drawCanvas(): Unit = {
    val simulationCanvas = document.createElement("canvas")
    simulationCanvas.id = "game-canvas"
    simulationCanvas.setAttribute("width", canvasLength + "px")
    simulationCanvas.setAttribute("height", canvasLength + "px")
    document.body.appendChild(simulationCanvas)

    val canvas = document.getElementById("game-canvas").asInstanceOf[html.Canvas]
    val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    context.clearRect(0, 0, canvas.width, canvas.height)
    context.fillStyle = backgroundColor
    context.strokeStyle = backgroundWall
    context.fillRect(0, 0, canvasLength, canvasLength)

    time += 1

    worm.move()
    worm.draw(context)
  }
}
