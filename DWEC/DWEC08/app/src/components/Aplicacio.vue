<template>
  <section>
    <div class="aplicacio">
      <h3 class="text-center">{{ aplicacio.nom }}</h3>
      <p><span class="bold">Descripció: </span>{{ aplicacio.descripcio }}</p>
      <p>Instal·lada: <input type="checkbox" :checked="aplicacio.instalada" disabled></p>
      <p class="text-center"><button @click="eliminar" type="button">Eliminar</button> </p>
    </div>
  </section>
</template>

<script>
  /* eslint-disable */
  export default {
    name: "Aplicacio",
    props:["aplicacio"],
    methods:{
      eliminar(){
        this.$store.commit("esborrar",this.$props.aplicacio);
      }
    }
  }
</script>

<style scoped>
  .aplicacio {
    width: 30%;
    background: #d6daa0;
    padding: 1em;
    margin-top: 1em;
    position: relative;
    left: 35%;
    right: 35%;
    border: 1px solid #2e2d2d;
    border-radius: 5px;
  }
  .bold {
    font-weight: bold;
  }
</style>