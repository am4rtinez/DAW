<template>
  <span>
    <button class="btn btn-success" type="button" @click="incrementLikes">
      <i class="fa-solid fa-thumbs-up"></i> - {{ $store.getters.getLikes(aplicacio) }}
    </button>
    <button class="btn btn-danger" type="button" @click="incrementDislikes">
      <i class="fa-solid fa-thumbs-down"></i> - {{ $store.getters.getDislikes(aplicacio) }}
    </button>
  </span>
</template>

<script>
/* eslint-disable */
export default {
  name: "Aplicacio",
  props: ["aplicacio"],
  methods: {
    incrementLikes() {
      this.$store.commit("incrementLikes", this.$props.aplicacio);
    },
    incrementDislikes() {
      this.$store.commit("incrementDislikes", this.$props.aplicacio);
    },
  },
};
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