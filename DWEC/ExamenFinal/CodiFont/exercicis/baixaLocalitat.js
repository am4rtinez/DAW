const server = '52.178.39.51:8082'

window.onload = function () {
  getLocalitats()
}

//La funcion ha quedado fea por tiempo.
//Se podria hacer aun más generica.
function getLocalitats(){
  let url = `http://${server}/localitats`
  fetch(url)
  .then(response => response.json())
  .then(localitats => {
    let tbody = document.getElementById('taula')
    // let select = document.getElementById('illes')
    for (loc of localitats){
      let tr = document.createElement('tr')
      let tdIlla = document.createElement('td')
      let tdId = document.createElement('td')
      let tdNom = document.createElement('td')
      let textIlla = document.createTextNode(loc['idIlla'])
      let textId = document.createTextNode(loc['idLocalitat'])
      let textNom = document.createTextNode(loc['nomLocalitat'])
      let button = document.createElement('button')
      button.setAttribute('id', 'bElimina')
      button.setAttribute('idLocalitat',loc['idLocalitat'])
      button.appendChild(document.createTextNode('Elimina'))
      button.addEventListener('click', function(e){
        console.log('Clic Button')
        console.log(e.target.getAttribute('idLocalitat'))
        deleteLocalitat(e.target.getAttribute('idLocalitat'))
      })

      tdIlla.appendChild(textIlla)
      tdId.appendChild(textId)
      tdNom.appendChild(textNom)
      tdNom.appendChild(button)
      
      tr.appendChild(tdIlla)
      tr.appendChild(tdId)
      tr.appendChild(tdNom)
      
      tbody.appendChild(tr)
    }
  });
}

/**
 * Funcion que se encarga de realizar la eliminación de un editor.
 * Operacion de DELETE a traves del API Rest.
 * @param {*} editor 
 */
deleteLocalitat = (idLoc) => {
  let url = `http://${server}/localitats/${idLoc}`
  const req = new XMLHttpRequest();
  req.addEventListener("readystatechange", function () {
      if (req.readyState == 4) {
          if (req.status == 200) {
              alert("Localidad eliminada.");          
              document.location = "baixaLocalitat.html";
          } else {
              const missatge = JSON.parse(req.responseText);
              alert(missatge.error);
          }
      }
  });
  req.open("DELETE", url);
  req.setRequestHeader('Content-Type', "application/json");
  req.setRequestHeader('Accept', "application/json");
  req.send(JSON.stringify({'idLocalitat': idLoc}));
}