<template>
  <div>
    <h3>{{ aplicacio.nom }}</h3>
    <p>{{ aplicacio.descripcio }}</p>
    <p>Instal·lada: <input type="checkbox" :checked="aplicacio.instalada" disabled></p>
    <p><button @click="eliminar" type="button">Eliminar</button> </p>
  </div>
</template>

<script>
export default {
  name: "AplicacioInit",
  props:["aplicacio"],
  methods:{
    eliminar(){
      this.$store.commit("esborrar",this.$props.aplicacio);
    }
  }
}
</script>

<style scoped>

</style>