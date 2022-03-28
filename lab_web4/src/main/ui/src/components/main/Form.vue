<template>
  <div>
    <div>
      <Canvas ref="canvas"/>
    </div>
    <div>
      <span v-if="selectedX.length === 0">Выберите значение X</span>
      <span v-else-if="selectedX.length !== 1" style="color: red">Выберите только одно значение X!</span>
      <span v-else>Текущее значение X: {{ selectedX.toString() }}</span>
      <br>
      <input id="X-2" type="checkbox" class="checkboxClass" value="-2" v-model="selectedX"><label for="X-2">-2</label>

      <input id="X-1h" type="checkbox" class="checkboxClass" value="-1.5" v-model="selectedX"><label
        for="X-1h">-1.5</label>

      <input id="X-1" type="checkbox" class="checkboxClass" value="-1" v-model="selectedX"><label for="X-1">-1</label>

      <input id="X-0h" type="checkbox" class="checkboxClass" value="-0.5" v-model="selectedX"><label
        for="X-0h">-0.5</label>

      <input id="X0" type="checkbox" class="checkboxClass" value="0" v-model="selectedX"><label for="X0">0</label>

      <input id="X0h" type="checkbox" class="checkboxClass" value="0.5" v-model="selectedX"><label for="X0h">0.5</label>

      <input id="X1" type="checkbox" class="checkboxClass" value="1" v-model="selectedX"><label for="X1">1</label>

      <input id="X1h" type="checkbox" class="checkboxClass" value="1.5" v-model="selectedX"><label for="X1h">1.5</label>

      <input id="X2" type="checkbox" class="checkboxClass" value="2" v-model="selectedX"><label for="X2">2</label>
    </div>
    <br>
    <div>
      <label for="y-input">Y<br>[-5;3]</label><br>
      <input v-model="y" id="y-input" type="text" placeholder="Введите значение Y">
      <span style="color: red" v-show="
          isNaN(Number(this.y.replaceAll(',', '.'))) ||
          Number(this.y.replaceAll(',', '.')) > 3 ||
          Number(this.y.replaceAll(',', '.')) < -5" id="y-error">
        <br>Y должен быть вещественным числом от -5 до 3!</span>
    </div>
    <br>
    <div>
      <span v-if="selectedR.length === 0">Выберите значение R</span>
      <span style="color: red" v-else-if="selectedR.length !== 1">Выберите только одно значение R!</span>
      <span style="color: red" v-else-if="selectedR[0] <= 0">Радиус не может быть отрицательным или равным нулю!</span>
      <span v-else>Текущее значение R: {{ selectedR.toString() }}</span>
      <br>
      <input id="R-2" type="checkbox" class="checkboxClass" value="-2" v-model="selectedR"><label for="R-2">-2</label>

      <input id="R-1h" type="checkbox" class="checkboxClass" value="-1.5" v-model="selectedR"><label
        for="R-1h">-1.5</label>

      <input id="R-1" type="checkbox" class="checkboxClass" value="-1" v-model="selectedR"><label for="R-1">-1</label>

      <input id="R-0h" type="checkbox" class="checkboxClass" value="-0.5" v-model="selectedR"><label
        for="R-0h">-0.5</label>

      <input id="R0" type="checkbox" class="checkboxClass" value="0" v-model="selectedR"><label for="R0">0</label>

      <input id="R0h" type="checkbox" class="checkboxClass" value="0.5" v-model="selectedR"><label for="R0h">0.5</label>

      <input id="R1" type="checkbox" class="checkboxClass" value="1" v-model="selectedR"><label for="R1">1</label>

      <input id="R1h" type="checkbox" class="checkboxClass" value="1.5" v-model="selectedR"><label for="R1h">1.5</label>

      <input id="R2" type="checkbox" class="checkboxClass" value="2" v-model="selectedR"><label for="R2">2</label>
    </div>
    <br>
    <div>
      <span id="error" style="color: red"></span>
      <button v-on:click="submit">Отправить</button>
      <button v-on:click="eraseCanvas()">Очистить холст</button>
      <button v-on:click="clearTable(); eraseCanvas()">Очистить таблицу</button>
      <button v-on:click="$router.go(0)">Выход</button>
    </div>
  </div>
</template>

<script>
import {eraseCanvas} from "@/drawCoordinatePlane"
import {eventBus} from "@/main";
import Canvas from "@/components/main/Canvas";

async function postData(url, data) {
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
}

export default {
  name: "Form",
  components: {Canvas},
  data() {
    return {
      selectedX: [],
      y: "",
      selectedR: []
    }
  },
  mounted() {
    eventBus.$on('click-shot', this.submitShot)
  },
  methods: {
    correctParams() {
      return this.selectedX.length === 1 && this.selectedR.length === 1 && this.y !== "" && !isNaN(Number(this.y))
    },
    eraseCanvas,
    submit() {
      this.y = document.getElementById("y-input").value.replaceAll(',', '.')
      if (this.correctParams()) {
        postData('/api/data/submit', {
          x: this.selectedX.toString(),
          y: this.y,
          r: this.selectedR.toString(),
          login: sessionStorage.getItem('login')
        }).then(response => response.json())
            .then(data => {
              console.log(data)
              eventBus.$emit('refresh')
            })
      } else {
        this.flashMessage.error({title: 'Ошибка!', message: 'Проверьте введенные данные!'})
      }
    },
    submitShot(shot) {
      postData('/api/data/submit', shot)
          .then(response => response.json())
          .then(data => {
            console.log(data)
            eventBus.$emit('refresh')
          })
    },
    clearTable() {
      postData('/api/result/clear', {
        login: sessionStorage.getItem("login")
      }).then(response => response.json())
          .then(data => {
            console.log(data)
            eventBus.$emit('refresh')
          }).then(this.flashMessage.info({title: '', message: 'Таблица очищена'}))
    }
  }
}
</script>

<style>
button {
  font-size: 3vh;
  margin-left: 1vh;
}

#y-input {
  font-size: 3vh;
}

.checkboxClass {
  display: none;
}

.checkboxClass:hover + label {
  background-color: #ef9e9e;
  transition: .25s;
}

.checkboxClass + label {
  display: inline-block;
  width: 7vh;
  border-radius: 0.75vh;
  background-color: lightgrey;
  margin: 0.75vh;
}

.checkboxClass:checked + label {
  display: inline-block;
  width: 7vh;
  border-radius: 0.75vh;
  background-color: lightgreen;
}
</style>