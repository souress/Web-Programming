<template>
  <div id="outer">
    <div id="inner">
      <Header/>
      <Time/>
      <Auth/>
    </div>
  </div>
</template>

<script>
import Header from "@/components/start/Header";
import Time from "@/components/start/Time";
import Auth from "@/components/start/Auth";

export default {
  name: "Start",
  components: {Auth, Time, Header}
}
</script>

<style scoped>
#outer {
  margin: 30vh 0;
}
</style>