<template>
  <div id="app">
    <h1>Exercicis de Vue - route  de {{ nomComplet }}</h1>
    <p>
      <router-link to="/">Inici</router-link> -
      <router-link to="alta">Alta</router-link> -
      <router-link to="llista">Llista</router-link>
    </p>
    <router-view :key="$route.path"></router-view>
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {
  },
  data: function () {
    return {
      nomComplet: 'Jo Mateix',
      nova: {nom: "Signal", descripcio: "Una altra de missatgeria", instalada: true}
    }
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
