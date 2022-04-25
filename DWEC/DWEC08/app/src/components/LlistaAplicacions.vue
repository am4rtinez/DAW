<template>
  <div class="view">
    <div class="aplicacions">
      <Aplicacio v-for="(item, index) in this.$store.state.aplicacions" v-bind:key="index" v-bind:aplicacio="item"></Aplicacio>
    </div>
  </div>
</template>

<script>
  import Aplicacio from "@/components/Aplicacio";

  export default {
    name: "LlistaAplicacions",
    components: {Aplicacio}
  }
</script>

<style scoped>
  /* .aplicacions {
    text-align: center;
  } */
</style>