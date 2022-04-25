import { createStore } from "vuex";

const state = {
	aplicacions: [
		{nom: "Whatsapp", descripcio: "Una aplicació de missatgeria", instalada: true, positive: 0, negative: 0},
		{nom: "Minecraft", descripcio: "Joc", instalada: false, positive: 0, negative: 0},
		{nom: "Meet", descripcio: "Aplicació de videoconferències per la feina", instalada: true, positive: 0, negative: 0},
	]
}

const mutations = {
  afegir: function (state, aplicacio) {
		aplicacio.positive = 0
		aplicacio.negative = 0
		state.aplicacions.push(aplicacio);
	},
	esborrar: function (state, aplicacio) {
		const index = state.aplicacions.indexOf(aplicacio);
		state.aplicacions.splice(index, 1);
	},
	incrementLikes: function (state, aplicacio) {
    // Cercam l'APP votada i li afegim un vot
    const index = state.aplicacions.indexOf(aplicacio);
    state.aplicacions[index].positive++;
		console.log(state.aplicacions[index].positive)
  },
  incrementDislikes: function (state, aplicacio) {
    // Cercam l'APP votada i li afegim un vot
    const index = state.aplicacions.indexOf(aplicacio);
    state.aplicacions[index].negative++;
		console.log(state.aplicacions[index].negative)
  }
};

const getters = {
  getLikes: (state) => (aplicacio) => {
    const index = state.aplicacions.indexOf(aplicacio);
    return state.aplicacions[index].puntsFavorables;
  },
  getDislikes: (state) => (aplicacio) => {
    const index = state.aplicacions.indexOf(aplicacio);
    return state.aplicacions[index].puntsDesfavorables;
  }
};

export default createStore({
  state,
  mutations,
	getters
});
