window.onload = () => {
    // constante que contiene el numero de maximo. En este caso de 1 a 10.
    const nmax = 10;

    // Loop del 1 al 10.
    for (let i = 1; i <= nmax; i++) {
        let p = document.createElement("P");
        let t = document.createTextNode("√" + i + " => " + Math.sqrt(i));
        p.appendChild(t);
        document.getElementById("arrels").appendChild(p);
    }
}