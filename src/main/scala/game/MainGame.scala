package game

import org.scalajs.dom
import dom.{CanvasRenderingContext2D, document, html}
import game.Direction.{DOWN, LEFT, RIGHT, UP}
import game.entities.{Food, WormCube}
import org.scalajs.dom.ext.KeyCode

object MainGame {
  val canvasLength = 600
  val backgroundColor = "#014133"
  val backgroundWall = "#022E03"
  var time = 0
  var gameOver = false
  var foods : Seq[Food] = Seq.empty
  val worm = new Worm(new WormCube(6, 1), Seq(new WormCube(5, 1),new WormCube(4, 1), new WormCube(3, 1), new WormCube(2, 1), new WormCube(1, 1)).reverse)
  var score = 0
  var speed = 1
  var intervalID = 0

  def main(args: Array[String]): Unit =  {
    intervalID = dom.window.setInterval(drawCanvas _, 200)

    document.onkeydown =
      (e: dom.KeyboardEvent) => {
        e.keyCode match {
          case KeyCode.S => {worm.setDirection(DOWN)}
          case KeyCode.W => {worm.setDirection(UP)}
          case KeyCode.D => {worm.setDirection(RIGHT)}
          case KeyCode.A => {worm.setDirection(LEFT)}
        }
      }
    spawnNewFood()
  }

  def drawCanvas(): Unit = {
    if(gameOver == true) {
      dom.window.clearInterval(intervalID)
      return
    }
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
    val collide = worm.checkForCollision(canvasLength)

    val foodToEat = foods.find(food => food.getX() == worm.getHead().getX() && food.getY() == worm.getHead().getY())
    if (foodToEat.nonEmpty) {
      foods = foods.drop(1)
      worm.eat()
      score += 1
      spawnNewFood()
    }

    context.fillStyle = "white"
    context.font = "20px sans-serif"
    context.fillText("Score:" + score.toString(), 15, 30)

    if (collide == true){
      gameOver = true
      displayEnd(context)
      return
    }

    worm.draw(context)
    foods.foreach(food => food.draw(context))
  }

  def displayEnd(context: CanvasRenderingContext2D) ={
      context.fillStyle = "#2b9093"
      context.font = "75px sans-serif"

      context.fillText("Game Over", 120, 300)
  }

  def spawnNewFood() = {
    foods = foods :+ new Food(scala.util.Random.nextInt(40), scala.util.Random.nextInt(40))
  }
}
