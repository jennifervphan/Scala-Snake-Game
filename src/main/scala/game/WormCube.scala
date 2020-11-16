package game

import org.scalajs.dom.CanvasRenderingContext2D

class WormCube (x: Int, y: Int, ctx: CanvasRenderingContext2D) {
  ctx.lineWidth = 2
  ctx.fillStyle = "#B0762A"
  ctx.strokeStyle = "#734508"

  ctx.fillRect(x, y, 15, 15)
  ctx.strokeRect(x, y, 15, 15)
}
