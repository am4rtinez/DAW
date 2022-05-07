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

  .btn {
    margin: 1em;
    padding: 0.375rem 0.75rem;
    font-family: 'Ubuntu', sans-serif;
    font-weight: bold;
    text-transform: uppercase;
    color: #212529;
    border-radius: 0.25rem;
    border: 1px solid transparent;
    background-color: transparent;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    display: inline-block;
    width: 75px;
    line-height: 1.5;
    text-align: center;
    text-decoration: none;
    vertical-align: middle;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
    font-size: 1rem;
  }

  .btn-success {
      color: #fff;
      background-color: #198754;
      border-color: #198754;
  }

  .btn-success:hover {
      color: #fff;
      background-color: #157347;
      border-color: #146c43;
  }

  .btn-danger {
      color: #fff;
      background-color: #dc3545;
      border-color: #dc3545;
  }

  .btn-danger:hover {
      color: #fff;
      background-color: #bb2d3b;
      border-color: #b02a37;
  }

  .btn-secondary {
    color: #fff;
    background-color: #120061;
    border-color: #120061;
  }

  .btn-secondary:hover {
      color: #fff;
      background-color: #0a0036;
      border-color: #0a0036;
  }
</style>
