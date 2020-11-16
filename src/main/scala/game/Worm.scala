package game

import org.scalajs.dom.CanvasRenderingContext2D

class Worm(initHead: WormCube, initBody: Seq[WormCube]) {
  var head: WormCube = initHead
  var body: Seq[WormCube] = initBody
  var direction = "right"

  def draw(ctx: CanvasRenderingContext2D): Unit = {
    head.draw(ctx)
    body.foreach(cube => cube.draw(ctx))

  }

  def move() = {
    direction match {
      case "right" => {
        head = new WormCube(head.getX() + 1, head.getY())
        if (body.length > 0) {
          body = body.map(piece => new WormCube(piece.getX() + 1, piece.getY()))
        }
      }
    }
  }

  def eat(): Unit = {

  }
}
