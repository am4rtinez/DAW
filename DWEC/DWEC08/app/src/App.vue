<template>
<!--  <AplicacioMostra v-bind:aplicacio="whatsapp"/>-->
  <LlistaAplicacions/>
  <FormApp/>
</template>

<script>
// import AplicacioMostra from './components/AplicacioMostra.vue';
import LlistaAplicacions from "@/components/LlistaAplicacions";
import FormApp from "@/components/FormApp";

export default {
  name: 'App',
  components: {
    FormApp,
    LlistaAplicacions,
    // AplicacioMostra
  },

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
