package game.entities

import org.scalajs.dom.CanvasRenderingContext2D

class Food (x: Int, y: Int) {
  def draw(ctx: CanvasRenderingContext2D): Unit = {
    ctx.lineWidth = 2
    ctx.fillStyle = "#ff0000"
    ctx.strokeStyle = "#660000"

    ctx.fillRect(15 * x + 2, 15 * y + 2, 11, 11)
    ctx.strokeRect(15 * x + 2, 15 * y + 2, 11, 11)
  }

  def getX() : Int = x

  def getY() : Int = y
}
