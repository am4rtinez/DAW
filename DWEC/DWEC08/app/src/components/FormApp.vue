<template>
  <div>
    <h2>Afegir una aplicació</h2>
    <form v-on:submit.prevent="afegir">
      <p><label for="nom">Nom: </label><input type="text" id="nom" v-model="nova.nom"></p>
      <p><label for="desc">Descripció: </label><textarea id="desc" v-model="nova.descripcio"></textarea></p>
      <p><label for="instal">Instal·lada: </label><input type="checkbox" id="instal" v-model="nova.instalada"></p>
      <p><button type="submit">Afegir</button></p>
    </form>
  </div>
</template>

<script>
export default {
  name: "FormApp",
  data:function(){
    return {
      nova:{nom:null, descripcio:null, instalada:false}
    }
  },
  methods:{
    afegir(){
      this.$store.commit("afegir",this.nova);
      this.nova={nom:null, descripcio:null, instalada:false}
    }
  }
}
</script>

<style scoped>

</style>