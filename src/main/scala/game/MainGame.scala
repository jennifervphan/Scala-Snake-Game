package game

import org.scalajs.dom
import dom.{CanvasRenderingContext2D, document, html}
import html.Canvas
import org.scalajs.dom.ext.KeyCode
//import worm.Worm

object MainGame {
  val canvasLength = 600
  val backgroundColor = "#409D42"
  val backgroundWall = "#022E03"
  var time = 0
  val worm = new Worm(new WormCube(6, 1), Seq(new WormCube(5, 1),new WormCube(4, 1), new WormCube(3, 1), new WormCube(2, 1), new WormCube(1, 1)).reverse)

  def main(args: Array[String]): Unit =  {
    dom.window.setInterval(drawCanvas _, 200)
    document.onkeydown =
      (e: dom.KeyboardEvent) => {
        e.keyCode match {
          case KeyCode.S => {worm.setDirection("down")}
          case KeyCode.W => {worm.setDirection("up")}
          case KeyCode.D => {worm.setDirection("right")}
          case KeyCode.A => {worm.setDirection("left")}
        }
      }
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
