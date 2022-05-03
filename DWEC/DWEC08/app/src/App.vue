<template>
  <div id="app">
    <header>
      <h1>Exercicis de Vue - route  de {{ nomComplet }}</h1>
    </header>
    <p class="nav">
      <router-link to="/">Inici</router-link> -
      <router-link to="alta">Alta</router-link> -
      <router-link to="llista">Llista</router-link> -
      <router-link to="llistapuntuacions">Llista Puntuacions</router-link>
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
        nomComplet: 'Ángel Martínez',
        nova: {nom: "Signal", descripcio: "Una altra de missatgeria", instalada: true}
      }
    },
  }
</script>

<style>
header,
.nav {
  text-align: center;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  margin: 2em;
  /* margin-top: 60px; */
}
.text-center {
  text-align: center;
}
.view {
  margin-top: 2em;
}
</style>
