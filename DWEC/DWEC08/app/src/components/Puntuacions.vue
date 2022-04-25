<template>
  <strong>{{ aplicacio.nom }}</strong>
  <span>
    <button @click="incrementarFavorables">Like - {{ $store.getters.getLikes(aplicacio) }}</button>
    <button @click="incrementarDesfavorables">Dislike - {{ $store.getters.getDislikes(aplicacio) }}</button>
  </span>
</template>

<script>
  /* eslint-disable */
  export default {
    name: "Aplicacio",
    props:["aplicacio"],
    methods:{
      incrementLikes(){
        console.log('Like')
        this.$store.commit("incrementarFavorables",this.$props.aplicacio);
      },
      incrementDislikes(){
        console.log('Dislike')
        this.$store.commit("incrementarDesfavorables",this.$props.aplicacio);
      },
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
  span {
    margin-left: 1em;
  }
  span button {
    margin-right: 1em;
  }
</style>