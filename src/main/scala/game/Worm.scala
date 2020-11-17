package game

import game.Direction.{DOWN, Direction, LEFT, RIGHT, UP}
import game.entities.{Food, Head, WormCube}
import org.scalajs.dom.CanvasRenderingContext2D

class Worm(initHead: Head, initBody: Seq[WormCube]) {
  var head: Head = initHead
  var body: Seq[WormCube] = initBody
  var digestingFood: Seq[Food] = Seq.empty
  var direction: Direction = RIGHT

  def draw(ctx: CanvasRenderingContext2D): Unit = {
    head.draw(ctx)
    body.foreach(cube => cube.draw(ctx))
    digestingFood.foreach(food => food.draw(ctx))
  }

  def move() = {
    if (body.nonEmpty) {
      val last = body(0)
      val digestedFood = digestingFood.find(food => food.getY() == last.getY() && food.getX() == last.getX())
      if (digestedFood.isEmpty) {
        body = body.drop(1)
      } else {
        digestingFood = digestingFood.filter(food => food.getY() != last.getY() || food.getX() != last.getX())
      }
      body = body :+ new WormCube(head.getX(), head.getY())
    }

    direction match {
      case RIGHT => {
        head = new Head(head.getX() + 1, head.getY())
      }
      case LEFT => {
        head = new Head(head.getX() - 1, head.getY())
      }
      case DOWN => {
        head = new Head(head.getX(), head.getY() + 1)
      }
      case UP => {
        head = new Head(head.getX(), head.getY() - 1)
      }
    }
  }

  def checkForCollision(canvasLength: Int): Boolean = {
    if ( head.getX() * 15 >= canvasLength || head.getX() < 0) return true
    if ( head.getY() * 15 >= canvasLength || head.getY() < 0) return true
    if (body.exists(part => part.getX() == head.getX() && part.getY() == head.getY())) return true

    false
  }

  def isAllowedDirection(newDirection: Direction, oldDirection: Direction): Boolean = {
    oldDirection match {
      case UP | DOWN => newDirection.equals(LEFT) || newDirection.equals(RIGHT)
      case RIGHT | LEFT => newDirection.equals(UP) || newDirection.equals(DOWN)
    }
  }

  def setDirection(direction: Direction) = {
    if(isAllowedDirection(direction, this.direction)) {
      this.direction = direction
    }
  }

  def getHead(): Head = head

  def eat(): Unit = {
    digestingFood = digestingFood :+ new Food(head.getX(), head.getY())
  }
}
