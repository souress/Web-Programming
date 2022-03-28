<template>
  <div>
    <div class="flex-container">
      <div>
        <Form/>
      </div>
      <div>
        <Table/>
      </div>
    </div>
  </div>
</template>

<script>
import Form from "@/components/main/Form";
import Table from "@/components/main/Table";

export default {
  name: "Main",
  components: {Table, Form}
}
</script>

<style>
.flex-container {
  display: flex;
}

.flex-container > div {
  width: 50%;
  padding: 0.75vh 0;
  margin: 0.75vh;
}
</style>