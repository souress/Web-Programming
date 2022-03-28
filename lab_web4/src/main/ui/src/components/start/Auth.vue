<template>
  <div>
    <div>
      <input id="login" style="font-size: 3vh; margin: 1vh 0 0.5vh 0;" type="text" placeholder="login"/>
    </div>
    <div>
      <input id="password" style="font-size: 3vh; margin: 0.5vh 0 1vh 0;" type="password" placeholder="password"/>
    </div>
    <div>
      <button v-on:click="sign_in">Sign in</button>
      <button v-on:click="sign_up">Sign up</button>
    </div>
  </div>
</template>

<script>
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
  name: "Auth",
  data() {
    return {
      login: "",
      password: ""
    }
  },
  methods: {
    sign_in() {
      this.login = document.getElementById("login").value
      sessionStorage.setItem('login', this.login)
      this.password = document.getElementById("password").value
      postData('/api/auth/sign-in', {
        login: this.login,
        password: this.password
      }).then(response => response.json())
          .then(data => {
            console.log(data)
            if (data.status.toString() === "true") {
              this.$router.push({name: 'main'})
              this.flashMessage.success({title: '', message: 'Добро пожаловать!'})
            } else this.flashMessage.error({title: 'Ошибка входа!', message: data.data})
          })
    },
    sign_up() {
      this.login = document.getElementById("login").value
      sessionStorage.setItem('login', this.login)
      this.password = document.getElementById("password").value
      postData('/api/auth/sign-up', {
        login: this.login,
        password: this.password
      }).then(response => response.json())
          .then(data => {
            console.log(data)
            if (data.status.toString() === "true") {
              this.$router.push({name: 'main'})
              this.flashMessage.success({title: '', message: 'Добро пожаловать!'})
            } else this.flashMessage.error({title: 'Ошибка регистрации!', message: data.data})
          })
    }
  }
}
</script>

<style>

</style>