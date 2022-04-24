<template>
  <div>
    <h1>Llista d'aplicacions</h1>
    <AplicacioMostra v-for="(item, index) in this.$store.state.aplicacions" v-bind:key="index" v-bind:aplicacio="item"/>
  </div>
</template>

<script>
import AplicacioMostra from '@/components/AplicacioMostra.vue'

export default {
  name: "LlistaAplicacions",
  components: {
    AplicacioMostra
  }
}
</script>

<style scoped>

</style>