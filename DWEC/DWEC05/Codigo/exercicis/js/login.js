/*
  Exercici 1 ( 1,5 punts )
  ---------------------------
  La pàgina login.html conté un formulari d'entrada a l'aplicació.
  Un usuari està fart de posar cada vegada l'usuari i la contrasenya. Vol tenir una còpia de la pàgina login.html al seu escriptori de manera que en carregar la pàgina al navegador, automàticament, s'ompli el formulari amb les dades correctes i s'enviï el formulari.
  Programau tot el necessari a login.js. 
*/
window.onload = () => {
    // Se meten los datos directamente.
    document.getElementById("email").value = "usuario@example.com"
    document.getElementById("password").value = "123456"
    document.getElementById("fLogin").submit()  // Hace el submit con los datos. Directamente el usuario no verá el formularion de login.
}