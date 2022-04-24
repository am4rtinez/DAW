# DWES08

## Instruccions
* Parteix del codi dels exercicis d'autoaprenentatge per fer la pràctica. Pots utilitzar el codi del exercicis resolts.
* No es corregirà cap exercici que presenti més d’una versió.
* El codi comentat o que no s’utilitzi en l’execució dels exercicis no es tendrà en compte per a la correcció.

## Exercicis

Modificarem l'aplicació de manera que els usuaris puguin puntuar les aplicacions. Ho farem de la següent manera:

### Modificació de l'estat (3 punts)

Als objectes de l'aplicació afegirem un atribut per comptar les puntuacions favorables i un altre per comptar les puntuacions desfavorables.

Afegirem dues mutacions. Totes dues rebran com a paràmetre l'aplicació, però una incrementarà en una unitat les puntuacions favorables i un altra les desfavorables.

Modifica la mutació d'alta de manera que inicialitzi les puntuacions a zero.

### Component puntuacions (2,5 punts)

Crea un component per puntuar les aplicacions amb les següents característiques:

* Rebrà l'aplicació com una propietat del component.
* L'element arrel serà span o similar per poder incloure el component en línia.
* Tendrà un botó ( o una imatge o un boto amb una imatge o ...) per a incrementar cada tipus de puntuació.
* Els botons ( o les imatges ...) executaran les mutacions de l'exercici anterior.

### Component Llista de puntuacions (1,5 punts)

Crea un component nou. Ha de mostrar una llista ol on cada li conté el nom d'una de les aplicacions i el component puntuacions.

### Ruta (2 punts)
Afegeix una opció al menú per mostrar la llista de puntuacions i tot el necessari perquè l'opció funcioni.

### Component Llista d'aplicacions (1 punt)

Modifica'l de manera que inclogui el component de puntuacions.

## Lliurament
Heu de lliurar el contingut del projecte complet.

## Avaluació
Aquesta tasca representa el 6.5% de la nota de l’apartat d’activitats de l’assignatura.

A cada exercici trobareu la seva puntuació sobre 10.

El 60% de la nota de l’exercici s’obtindrà si la solució resol el cas general del problema.

El 40% restant de la nota s’obtindrà depenent de l’optimització del codi. Com més eficient sigui i més ben estructurat estigui, més nota tendrà en aquest apartat.

Es penalitzarà amb un 10% de la nota de l’exercici si el codi no està indentat, si els noms de les variables, funcions, ... no són descriptius o si l’HTML que s’utilitzi per fer l’exercici i mostrar els resultats no és correcte.

No es tendrà en compte per a la correcció el codi comentat, el que no s’utilitzi en cap lloc de la pràctica, o les múltiples versions del codi.

# app

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
