package game.entities
import org.scalajs.dom.CanvasRenderingContext2D

class Head  (x: Int, y: Int){
  def draw(ctx: CanvasRenderingContext2D): Unit = {
    ctx.lineWidth = 2
    ctx.fillStyle = "#FFFFFF"
    ctx.strokeStyle = "#734508"

    ctx.strokeRect(15 * x, 15 * y, 15, 15)
  }

  def getX() : Int = x


  def getY() : Int = y
}
