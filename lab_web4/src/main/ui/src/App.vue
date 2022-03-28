<template>
  <div id="app">
    <router-view></router-view>
    <FlashMessage ></FlashMessage>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: "Arial", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-size: 3vh;
}

@media (min-width : 1224px) {
  html {
    height: 100vh;
    background: linear-gradient(to bottom left, cornflowerblue, lightgreen) fixed;
  }
}

@media (min-width: 778px) and (max-width: 1224px) {
  html {
    height: 100vh;
    background: linear-gradient(to top left, lightgreen, #d293ef) fixed;
  }
}

@media (max-width : 778px) {
  html {
    height: 100vh;
    background: linear-gradient(to top right, #d293ef, #295fc2) fixed;
  }
}
</style>
