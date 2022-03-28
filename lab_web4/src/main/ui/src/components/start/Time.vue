<template>
  <div align="center">
    {{date}}
  </div>
</template>

<script>
export default {
  name: "Time",
  data() {
    return {
      date: ''
    }
  },
  created() {
    let options = {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      timezone: 'UTC',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric'
    };
    let time = new Date()
    this.date = time.toLocaleDateString('ru', options)
    setInterval(()=>{
      time = new Date()
      this.date = time.toLocaleDateString('ru', options)
    }, 1000)
  }
}
</script>

<style scoped>

</style>