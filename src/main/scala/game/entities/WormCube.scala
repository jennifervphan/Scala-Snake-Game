package game.entities

import org.scalajs.dom.CanvasRenderingContext2D

class WormCube (x: Int, y: Int) {
  def draw(ctx: CanvasRenderingContext2D): Unit = {
    ctx.lineWidth = 2
    ctx.fillStyle = "#B0762A"
    ctx.strokeStyle = "#734508"

    ctx.beginPath()
    ctx.arc(15 * x + 7, 15 * y + 7, 7, 0, 2*Math.PI)
    ctx.fill()
  }

  def getX() : Int = x


  def getY() : Int = y
}
