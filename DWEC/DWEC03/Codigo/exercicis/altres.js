window.onload = () => {
    const nmax = 10; // constante que contiene el numero de maximo. En este caso de 1 a 10.
    arrels(nmax, "arrels") // Calcula y pinta las raices cuadradas.
}

/* Funcion que calcula y pinta las raices cuadradas.
Parametro n - el numero maximo de iteraciones del loop.
Parametro parent - el elemento padre del cual dependeran los elementos p. */
function arrels(n, parent) {
    // Loop del 1 al n pasado por parametro.
    for (let i = 1; i <= n; i++) {
        let p = document.createElement("P");
        let t = document.createTextNode("√" + i + " => " + Math.sqrt(i));
        p.appendChild(t);
        document.getElementById(parent).appendChild(p);
    }
}