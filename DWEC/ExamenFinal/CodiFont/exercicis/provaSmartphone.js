window.onload = function(){
  let parent = document.getElementById('mostrador')
  let p = document.createElement('p')
  let sp = new Smartphone("iphone XR", "Apple", 4, "iOS", "15.4.1", false)

  let text = document.createTextNode(sp.infoJSON())

  p.appendChild(text)
  parent.appendChild(p)
}