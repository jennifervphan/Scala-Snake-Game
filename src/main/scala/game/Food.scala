package game
import org.scalajs.dom.CanvasRenderingContext2D

class Food (x: Int, y: Int, ctx: CanvasRenderingContext2D) {
  ctx.lineWidth = 2
  ctx.fillStyle = "#000000"
  ctx.strokeStyle = "#734508"

  ctx.fillRect(15 * x, 15 * y, 15, 15)
  ctx.strokeRect(15 * x, 15 * y, 15, 15)
}
