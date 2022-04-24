import { createRouter, createWebHistory } from "vue-router";

import Home from "@/components/Home";
import LlistaAplicacions from "@/components/LlistaAplicacions";
import FormApp from "@/components/FormApp";

const routes = [
	{
		path: "/", 
		component: Home
	},
	{
		path: "/llista", 
		component: LlistaAplicacions
	},
	{
		path: "/alta", 
		component: FormApp
	},
	{
		path: "/modificar", 
		component: FormApp, 
		props: true
	}
];

export default createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});