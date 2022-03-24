# Tarea DWES06

## Instruccions

* Crea un projecte al teu IDE, si utilitza projectes.
* No es corregirà cap exercici que presenti més d’una versió.
* El codi comentat o que no s’utilitzi en l’execució dels exercicis no es tendrà en compte per a la correcció.
* A la pàgina índex heu de tenir enllaços per les opcions d'alta d'un editor i cerca d'editors per nom.
* Cada exercici conté les url's de l'api que heu d'utilitzar.
* Utilitzau AJAX o Jquery segons s'indiqui a cada exercici.

## Exercicis

### Exercici 1 ( 2,5 punts )

Utilitzau jQuery per fer aquest exercici.

Creau una pàgina que contengui un camp de text. Així com l'usuari vagi escrivint dins el camp de text s'ha d'omplir la ol amb id llista amb els editors tals que el seu nom contengui el que hi ha escrit al camp de text. A cada editor li heu d'afegir dos botons, un per modificar-lo i un per esborrar-lo.

El botó modificar ha de cridar la pàgina que fareu a l'exercici 2, passant-li al query string l'identificador de l'editor.

El botó esborrar ha de cridar la pàgina que fareu a l'exercici 3, passant-li al query string l'identificador de l'editor.

#### URL's de l'api

* Cercar editors per nom: http://52.178.39.51:8080/editors/partNom/TE

### Exercici 2 (5 punts)

Utilitzau AJAX per a fer aquest exercici

Crea una pàgina amb un formulari amb un camp de text per a cada un dels atributs d'un editor. En haver carregat la pàgina heu de mirar si ha arribat per el queryString l'identificador d'un editor.

* Si no arriba l'identificador en fer el submit del formulari haurà d'enviar una petició POST amb les dades de l'editor al cos de la petició. Al final haurà de mostrar un alert indicant si ha anat bé o no la inserció. Si ha anat bé tornarà a la pàgina de l'exercici 1.

* Si ha arribat l'identificador la pàgina carregarà les dades de l'api i omplirà el formulari amb aquestes dades. En fer el submit del formulari haurà d'enviar una petició PUT amb l'identificador al path i les dades de l'editor al cos de la petició. Al final haurà de mostrar un alert indicant si ha anat bé o no la modificació. Si ha anat bé tornarà a la pàgina de l'exercici 1.

En cap cas s'ha de poder modificar l'identificador de l'editor. Si es tracta d'una inserció l'heu de deixar en blanc.

#### URL's de l'api

* Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
* Insertar un editor nou: POST a http://52.178.39.51:8080/editors
* Modificar un editor: PUT a http://52.178.39.51:8080/editors/745

### Exercici 3 (2,5 punts)

Utilitzau AJAX o jQuery per fer aquest exercici.

Crea una pàgina per esborrar un editor de la base de dades. Ha de rebre
l'identificador al query string, ha de mostrar la informació completa de l'editor i ha de tenir un botó que faci la petició DELETE al servidor, amb l'identificador al path. Al final haurà de mostrar un alert indicant si ha anat bé o no l'esborrat. Si ha anat bé tornarà a la pàgina de l'exercici 1.

#### URL's de l'api

* Cercar editor per id: http://52.178.39.51:8080/editors/perId/745
* Eliminar l'editor: DELETE a a http://52.178.39.51:8080/editors/745

## Lliurament

Heu de lliurar el contingut del projecte complet.

## Avaluació

Aquesta tasca representa el 6.5% de la nota de l’apartat d’activitats de l’assignatura.

A cada exercici trobareu la seva puntuació sobre 10.

El 60% de la nota de l’exercici s’obtindrà si la solució resol el cas general del problema.

El 40% restant de la nota s’obtindrà depenent de l’optimització del codi. Com més eficient sigui i més ben estructurat estigui, més nota tendrà en aquest apartat.

Es penalitzarà amb un 10% de la nota de l’exercici si el codi no està indentat, si els noms de les variables, funcions, ... no són descriptius o si l’HTML que s’utilitzi per fer l’exercici i mostrar els resultats no és correcte.

No es tendrà en compte per a la correcció el codi comentat, el que no s’utilitzi en cap lloc de la pràctica, o les múltiples versions del codi.