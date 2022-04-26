<template>
	<div class="view">
		<ol>
			<li v-for="(item, index) in this.$store.state.aplicacions" v-bind:key="index" v-bind:aplicacio="item">
				<strong>{{ item.nom }}</strong>
				<Puntuacions v-bind:key="index" v-bind:aplicacio="item"></Puntuacions>
			</li>
		</ol>
	</div>
</template>

<script>
	import Puntuacions from "@/components/Puntuacions";

	export default {
		name: "LlistaPuntuacions",
		props: ["aplicacio"],
		components: {	Puntuacions	},
	};
</script>

<style scoped>
	li {
		margin: 1em;
	}
</style>