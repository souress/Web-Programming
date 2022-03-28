<template>
  <div>
        <table class="table">
          <thead>
          <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Time</th>
            <th>Duration</th>
            <th>Result</th>
          </tr>
          </thead>
          <tbody v-if="valid">
          <tr v-for="shot in shots" :key="shot.id">
            <td>{{ shot.x }}</td>
            <td>{{ shot.y }}</td>
            <td>{{ shot.r }}</td>
            <td>{{ shot.currentTime }}</td>
            <td>{{ shot.duration }}</td>
            <td>{{ shot.result }}</td>
          </tr>
          </tbody>
          <tbody v-if="!valid">
          <tr>
            <td colspan="6" style="color: red">
              {{error}}
            </td>
          </tr>
          </tbody>
        </table>
  </div>
</template>

<script>
import {eventBus} from "@/main";

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
  name: "Table",
  data() {
    return {
      shots: [],
      valid: false,
      error: "Результатов не найдено"
    }
  },
  mounted() {
    postData('/api/result/shots', {
      login: sessionStorage.getItem("login")
    }).then(response => response.json())
        .then(data => {
          this.shots = data.data
          eventBus.$emit('draw-circles', this.shots)
          if (data.status === "true") {
            this.error = ""
            this.valid = true
          }
        })
    eventBus.$on('refresh', this.refresh)
  },
  methods: {
    refresh() {
      postData('/api/result/shots', {
        login: sessionStorage.getItem("login")
      }).then(response => response.json())
          .then(data => {
            this.shots = data.data
            eventBus.$emit('draw-circles', this.shots)
            if (data.status === "true") {
              this.error = ""
              this.valid = true
            } else {
              this.error = data.data
              this.valid = false
            }
          })
    }
  }
}
</script>
<style>
.table {
  width: 100%;
  border: black;
  margin-bottom: 3vh;
}

.table thead th {
  font-weight: bold;
  text-align: center;
  border: none;
  padding: 1.5vh 2.25vh;
  background: #d8d8d8;
  font-size: 2.1vh;
  border-left: 0.15vh solid #ddd;
  border-right: 0.15vh solid #ddd;
}

.table tbody td {
  text-align: center;
  border-left: 0.15vh solid #ddd;
  border-right: 0.15vh solid #ddd;
  padding: 1.5vh 2.25vh;
  font-size: 2.1vh;
  vertical-align: top;
}

.table thead tr th:first-child, .table tbody tr td:first-child {
  border-left: none;
}

.table thead tr th:last-child, .table tbody tr td:last-child {
  border-right: none;
}

.table tbody tr:nth-child(even) {
  background: #f3f3f3;
}

.table tbody tr:nth-child(odd) {
  background: #a5a5a5;
}
</style>