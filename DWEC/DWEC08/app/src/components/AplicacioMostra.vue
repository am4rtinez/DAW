<template>
  <div>
    <h3>{{ aplicacio.nom }}</h3>
    <p>{{ aplicacio.descripcio }}</p>
    <p>Instal·lada: <input type="checkbox" :checked="aplicacio.instalada"></p>
    <p><button type="button" @click="eliminar()"> Elimina </button></p>
  </div>
</template>

<script>
export default {
  name: "AplicacioMostra",
  props:["aplicacio"],
  methods: {
    eliminar:function() {
        this.$store.commit("eliminaApp", this.$props.aplicacio);
    }
  }
}
</script>

<style scoped>
div{
  margin-top: 2em;
  margin-bottom: 2em;
}
</style>