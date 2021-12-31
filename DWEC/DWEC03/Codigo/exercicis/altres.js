window.onload = () => {
    const nmax = 10; // constante que contiene el numero de maximo. En este caso de 1 a 10.
    arrels(nmax, "arrels") // Calcula y pinta las raices cuadradas.
    maxim("maxim")
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

/* Funcion que calcula el número máximo introducido por parametros a traves del prompt.
Parametro parent - el elemento padre del cual dependeran los elementos p. */
function maxim(parent) {
    let nums = prompt("Insertar numeros separados por espacio: ");
    let sarray = nums.split(" ") //Divide los números introducidos a partir del espacio y los almacena en un array.
    let maxim = Math.max(...sarray) //Obtiene el maximo.

    let p1 = document.createElement("p");
    let t1 = document.createTextNode("Numeros introducidos: " + sarray);
    let p2 = document.createElement("p");
    let t2 = document.createTextNode("Maxim: " + maxim);
    
    p1.appendChild(t1);
    p2.appendChild(t2);
    document.getElementById(parent).appendChild(p1);
    document.getElementById(parent).appendChild(p2);
}