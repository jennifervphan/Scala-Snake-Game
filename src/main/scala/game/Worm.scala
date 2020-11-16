package game

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, document}
import org.scalajs.dom.ext.KeyCode

class Worm(initHead: WormCube, initBody: Seq[WormCube]) {
  var head: WormCube = initHead
  var body: Seq[WormCube] = initBody
  var direction = "right"

  def draw(ctx: CanvasRenderingContext2D): Unit = {
    head.draw(ctx)
    body.foreach(cube => cube.draw(ctx))
  }

  def move() = {
    if (body.nonEmpty) {
      body = body.drop(1)
      body = body :+ new WormCube(head.getX(), head.getY())
    }
    direction match {
      case "right" => {
        head = new WormCube(head.getX() + 1, head.getY())
      }
      case "left" => {
        head = new WormCube(head.getX() - 1, head.getY())
      }
      case "down" => {
        head = new WormCube(head.getX(), head.getY() + 1)
      }
      case "up" => {
        head = new WormCube(head.getX(), head.getY() - 1)
      }
    }
    }

  def setDirection(dir: String) = {
    direction = dir
  }

  def eat(): Unit = {

  }
}
