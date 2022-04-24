<template>
<div>
  <Aplicacio v-for="(item, index) in this.$store.state.aplicacions" v-bind:key="index" v-bind:aplicacio="item"></Aplicacio>
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

</style>