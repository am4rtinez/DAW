import {createStore} from 'vuex'

const state = {
	aplicacions: [
		{nom: "Whatsapp", descripcio: "Aplicació de misatgeria", instalada: true},
		{nom: "Roblox", descripcio: "Joc de jocs", instalada: false},
		{nom: "Google maps", descripcio: "Per no estar perdut", instalada: true},
		{nom: "Minecraft", descripcio: "Joc", instalada: true}
	],
};

const mutations = {
	eliminaApp(state, eliminada) {
		state.aplicacions = state.aplicacions.filter(app => app.nom !== eliminada.nom);
	},
	afegeixApp(state, nova) {
		const initialValue = false;
		const trobada = state.aplicacions.map(actual => actual.nom === nova.nom)
			.reduce(
				(previousValue, currentValue) => previousValue || currentValue,
				initialValue
			);

		if (trobada) {
				throw new Error("Ja hi ha una aplicació amb el mateix nom");
		}
		state.aplicacions.push(nova);
	}
};

export default createStore({
	state,
	mutations,
});