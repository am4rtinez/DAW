const server = '52.178.39.51:8082'

window.onload = function (){
  setIlles()

  // document.getElementById("formulari").addEventListener("submit", (e) => {
    // let localitat = {};
    // localitat.idLocalitat = document.getElementById('idLocalitat').value;
    // let select = document.getElementById('illes');
    // localitat.idIlla = select.options[select.selectedIndex].value;
    // localitat.nomLocalitat = document.getElementById('nomLocalitat').value;
  //   console.log(localitat)
  //   postLocalitat(localitat)
  // })

  document.getElementById("formulari").addEventListener("submit", (e) => {
    e.preventDefault();
    let localitat = {};
    localitat.idLocalitat = document.getElementById('idLocalitat').value;
    let select = document.getElementById('illes');
    localitat.idIlla = select.options[select.selectedIndex].value;
    localitat.nomLocalitat = document.getElementById('nomLocalitat').value;
    console.log(JSON.stringify(localitat));
    let url = `http://${server}/localitats`

    fetch(url,
      {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          },
          body: JSON.stringify(localitat)
      }
    )
        .then(resultat => {
            if (!resultat.ok) {
                throw new Error(resultat); // No consigo mostrar el error correctamente.
            }
            return resultat.json();
        })
        .then((resultat) => { //Si tot ha anat bé dins resultat tenim les dades Javascript.
            const missatge = `Inserció correcta`;
            alert(missatge);
            document.location = "altaLocalitat.html";
        })
        .catch(error => {
          alert(error);
        });
  });
}

function setIlles(){
  let url = `http://${server}/illes`
  fetch(url)
  .then(response => response.json())
  .then(illes => {
    let select = document.getElementById('illes')
    for (illa of illes){
      let option = document.createElement('option')
      let text = document.createTextNode(illa['nomIlla'])
      option.setAttribute('value', illa['idIlla'])
      option.appendChild(text)
      select.appendChild(option)
    }
  });
}

postLocalitat = (localitat) => {
  let url = `http://${server}/localitats`
  console.log(url)
  const req = new XMLHttpRequest();
  req.addEventListener("readystatechange", function () {
      if (req.readyState == 4) {
          if (req.status == 200) {
              const res = JSON.parse(req.responseText);
              console.log(res.idLocalitat)
              textAlert =  'Localidad insertada'
              alert(textAlert);
              // document.location = "index.html";
          } else {
              const missatge = JSON.parse(req.responseText);
              alert(missatge);
          }
      }
  });
  req.open("POST", url);
  req.setRequestHeader('Content-Type', "application/json");
  req.setRequestHeader('Accept', "application/json");
  req.send(JSON.stringify(localitat));
}