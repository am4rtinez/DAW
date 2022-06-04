class Smartphone {
  constructor(modelo, marca, ram, so, version, conex5g) {
      this.modelo = modelo
      this.marca = marca
      this.ram = ram
      this.so = so
      this.version = version
      this.conex5g = conex5g
  }

  infoSO() {
    return this.so + " - " + this.version
  }

  infoJSON(){
    return JSON.stringify(
      {
        "model" : this.model,
        "marca" : this.marca,
        "ram" : this.ram,
        "so" : this.so,
        "version" : this.version,
        "conex5g" : this.conex5g
      }
    )
  }
}