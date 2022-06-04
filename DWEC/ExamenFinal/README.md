# Examen Final Mayo 2022.
----

##Instruccions
Crea un nou projecte.

Inclou el codi que trobaràs a l'aula virtual al teu projecte.

Pots modificar els arxius de la carpeta exercicis. La resta no. Per corregir les pràctiques incorporaré el contingut de la vostra carpeta exercicis al codi font subministrat a l'aula virtual. Si necessiten modificacions fetes als fitxers que no podeu tocar no s'avaluarà l'exercici.

Per generar contingut a l’HTML només podeu utilitzar DOM a no ser que s’especifiqui el contrari.

Si un exercici indica que es resolgui utilitzant una llibreria o tècnica concreta, o prohibeix la utilització d'una tècnica o llibreria no es corregiran les solucions que no facin cas a l'enunciat.

Els exercicis han de resoldre el cas general, no han de funcionar només amb unes dades de mostra.

El servidor de l'API és a http://52.178.39.51:8082

##Criteris de qualificació
Cada exercici duu escrita la seva puntuació sobre deu.

Un 65% de la nota correspon a la correcció de la resposta i un 35% a l'eficiència.

Sort!

##Exercicis
###Exercici 1 (1,5 punts)
Al fitxer Smartphone.js crea una classe per mantenir les dades d’un telèfon intel·ligent: Ha de poder mantenir el model la marca, la quantitat de RAM, El sistema operatiu, la versió del SO i si té o no 5G.

També ha de tenir un mètode que torni una cadena concatenant el SO i la versió, i un altre que torni el JSON de l’objecte.

Al fitxer provaSmartphone.js crea un objecte d’aquesta classe i mostra el seu JSON dins de l’element amb id mostrador de provaSmartphone.html (podeu utilitzar innerHTML per fer-ho).

###Exercici 2 (2,5 punts)
Al fitxer altaSmartphone.js programa el necessari per que la pàgina altaSmartphone.html tengui el següent comportament:

###Exercici 2.1 (1,25 punts)
Crea una funció que validi els camps de tipus text i number. No s’accepten texts formats només per espais en blanc. Si les dades no es validen no es pot enviar el formulari. Els camps que no es validin, només aquests, han de tenir el label de color vermell.

Evidentment, si el formulari no es valida no s’ha d’enviar.

###Exercici 2.2 (1,25 punts)
Crea una funció que validi el camp de tipus radio. Si no n’hi ha cap de seleccionat no s’ha de validar el formulari. Si no valida el label amb el nom del camp ha de ser de color vermell.

Evidentment, si el formulari no es valida no s’ha d’enviar.

###Exercici 3 (3 punts)
Al fitxer altaLocalitat.js programa el necessari perquè la pàgina altaLocalitat.html tengui el següent comportament.

###Exercici 3.1 (1 punt)
En carregar-se la pàgina la llista desplegable s’ha d’omplir amb les illes que torna el servidor.

La url és: http://52.178.39.51:8082/illes

Torna les dades:

``` JSON
[
    {"idIlla":"071","nomIlla":"Mallorca"},{"idIlla":"072","nomIlla":"Menorca"},
    {"idIlla":"073","nomIlla":"Eivissa"},{"idIlla":"074","nomIlla":"Formentera"}
]
```

No podeu utilitzar jQuery. Podeu utilitzar XMLHttpRequest o fetch.

###Exercici 3.2 (2 punts)
En pitjar el botó alta s’ha de cridar a la url següent per fer la inserció. S’ha de mostrar un alert amb el resultat. Si no hi ha hagut cap errada s’ha de buidar el formulari.

La url és: http://52.178.39.51:8082/localitats

Torna les dades:

```JSON
{"missatge":"Contingut del missatge"}
```

No podeu utilitzar jQuery. Podeu utilitzar XMLHttpRequest o fetch.

###Exercici 4 (3 punts)
Al fitxer baixaLocalitat.js programa el necessari perquè la pàgina baixaLocalitat.html tengui el següent comportament.

###Exercici 4.1 (1,5 punts)
En carregar-se la pàgina la taula s’ha d’omplir amb les localitats que torna el servidor

La url és: http://52.178.39.51:8082/localitats

Torna les dades:

``` JSON
[
    {"idLocalitat":"070020001","idIlla":"072","nomLocalitat":"ALAIOR"},
    {"idLocalitat":"070010001","idIlla":"071","nomLocalitat":"ALARO"}, 
    ...
]
```
No podeu utilitzar jQuery. Podeu utilitzar XMLHttpRequest o fetch.

###Exercici 4.2(1,5 punts)
Al costat del nom de cada localitat hi ha d’haver un botó que permeti esborrar aquella localitat. Una vegada eliminada, s’ha de recarregar la pàgina.

La url és: http://52.178.39.51:8082/localitats/{idLocalitat}

No podeu utilitzar jQuery. Podeu utilitzar XMLHttpRequest o fetch.

Torna les dades:
``` JSON
{"missatge":"Contingut del missatge"}
```

##Lliurament
Has de lliurar a l'aula virtual la carpeta exercicis del projecte.