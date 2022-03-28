<template>
  <div id="div-canvas">
    <canvas v-on:click="click" id="canvas" width="400" height="400"/>
  </div>
</template>

<script>
import {drawCoordinatePlane} from "@/drawCoordinatePlane"
import {eventBus} from "@/main";

export default {
  name: "Canvas",
  mounted() {
    drawCoordinatePlane(document.getElementById('canvas').clientWidth, document.getElementById('canvas').clientHeight)
    eventBus.$on('draw-circles', this.drawCircles)
  },
  methods: {
    drawCircles(data) {
      for (let i in data) {
        let xCoord = (150 * data[i].x) / data[i].r + 200;
        let yCoord = (-150 * data[i].y) / data[i].r + 200;
        let ctx = document.getElementById("canvas").getContext('2d');
        ctx.beginPath();
        ctx.arc(xCoord, yCoord, 3, 0, 2 * Math.PI);
        ctx.fillStyle = data[i].result === 'true' ? 'green' : 'red';
        ctx.fill();
        ctx.stroke();
      }
    },
    click(event) {
      let r = Number(this.$parent.$data.selectedR.toString())
      let xCoord = event.offsetX
      let yCoord = event.offsetY

      if (r !== null && r !== 0) {
        if (r < 0) this.flashMessage.error({title: 'Ошибка!', message: 'R должен быть больше нуля!'})
        let x = (xCoord - 200) / 150 * r;
        let y = -1 * (yCoord - 200) / 150 * r;
        let shot = {x: x.toFixed(2), y: y.toFixed(2), r: r, login: sessionStorage.getItem('login')}
        eventBus.$emit('click-shot', shot)
      } else {
        this.flashMessage.error({title: 'Ошибка!', message: 'Введите R!'})
      }
    }
  }
}
</script>

<style scoped>

</style>