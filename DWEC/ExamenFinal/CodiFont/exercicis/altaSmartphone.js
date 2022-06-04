window.onload = function () {
  document.getElementById('formulari').addEventListener('submit', validarFormulario);
}

function validarFormulario(e) {
  e.preventDefault();
  let marca = document.getElementById('marca')
  let model = document.getElementById('model')
  let versio = document.getElementById('vesioSO')

  if(marca.value.trim().length == 0) {
    alert('Campo Marca es requerido');
    resaltar(marca)
    return;
  }
  if(model.value.trim().length == 0) {
    alert('Campo Model es requerido');
    resaltar(model)
    return;
  }
  if(versio.value.trim().length == 0) {
    alert('Campo Versio es requerido');
    resaltar(versio)
    return;
  }
  
  if(!document.querySelector('input[name="so"]:checked')) {
    alert('Campo SO requerido');
    return;
  }

  this.submit();
}

function resaltar(element) {
  let labels = element.parentElement.getElementsByTagName('label')
  labels[0].style.color = "red"

}