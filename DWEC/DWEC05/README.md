# Instruccions
------

* Crea un projecte al teu IDE, si utilitza projectes.

* Al final de l’enunciat trobareu un fitxer comprimit amb els fitxers del projecte. Conté els html i els js necessaris per resoldre els exercicis.

* Modifica només els arxius de la carpeta exercicis/js. Qualsevol altra fitxer no s’avaluarà.

* No es corregirà cap exercici que presenti més d’una versió.

* El codi comentat o que no s’utilitzi en l’execució dels exercicis no es tendrà en compte per a la correcció.

* Només podeu modificar els fitxers de la carpeta exercicis/js.

* No podeu utilitzar variables globals.

# Exercicis
------

## Exercici 1 ( 1,5 punts )

La pàgina _login.html_ conté un formulari d'entrada a l'aplicació.

Un usuari està fart de posar cada vegada l'usuari i la contrasenya. Vol tenir una còpia de la pàgina _login.html_ al seu escriptori de manera que en carregar la pàgina al navegador, automàticament, s'ompli el formulari amb les dades correctes i s'enviï el formulari.

Programau tot el necessari a _login.js_.

## Exercici 2 (3,5 punts)

Programa la pàgina adreces.html amb la següent funcionalitat.

1. (**1,5 punts**) En canviar el valor de qualsevol camp de l'adreça de contacte s'ha de modificar el camp corresponent de l'adreça de facturació amb el valor acabat de modificar.

    Per exemple, si l'usuari modifica el codi postal de l'adreça de contacte amb el valor 07300, s'ha d'actualitzar el valor de l'adreça de facturació a 07300.


2. (**2 punts**) Afegeix el necessari de manera que si l'usuari pitja qualsevol tecla dins d'un dels camps de l'adreça de facturació, ja no s'actualitzin els camps de facturació en modificar els de l'adreça de contacte.

    Per evitar sobrecàrregues, en pitjar una tecla dins qualsevol camp de l'adreça de facturació s'haurien d'anular tots els listeners que intervenen a l'exercici.

## Exercici 3 (2,5 punts)

### Exercici 3.1 (1,5 punts)

Al fitxer _oferta.js_ programa el necessari perquè a cap camp del formulari es pugui introduir la lletra a, ni majúscula ni minúscula. Si l'usuari la pitja no s'ha d'incloure al camp del formulari.

### Exercici 3.2 (1 punt)

Mostra un avís cada vegada que es pitgi una a o una A. Per mostrar l'avís pots mostrar l'element amb id avis canviant la seva classe a "_alert alert-danger_". Després de x segons aquest missatge ha de desapareixer. Ho pots fer canviant la classe d'avís a hidden.

## Exercici 4 (1,5 punts)

A _oferta.js_ assignau a l’esdeveniment que trobeu un listener que validi el formulari. Ha de controlar:

* Els elements marcats amb * són obligatoris.

* La data de publicació ha de ser anterior a la de finalització.

* La data de finalització ha de ser posterior a la data actual.

Si hi ha qualque errada heu de mostrar un alert que ha de dir simplement quin tipus d’errada hi ha: dades obligatòries sense emplenar o dates inconsistents.

Si hi ha errades també ha d’evitar l’enviament del formulari així com la propagació de l’esdeveniment, incloent a d’altres listeners del mateix esdeveniment.

## Exercici 5 (1 punt)

A _oferta.js_ afegeix un listener a l’esdeveniment adequat de manera que quan s’enviï el formulari, si no hi ha errades, es generi un objecte JSON que tengui per a cada camp del formulari un atribut amb el name del camp com a nom i el valor del camp com a valor de l’atribut.

El formulari no s’ha d’enviar.

Mostra aquest JSON amb un alert o a la consola.

El codi d'aquest exercici ha d'estar a un listener apart dels dels altres exercicis. No podeu incloure codi dels altres exercicis a aquest listener.

Per exemple s’hauria de mostrar un JSON com aquest, amb les dades del formulari, no cal amb aquest format en concret:
```json
{
    "idEmpresa": "8",
    "idOferta": "3",
    "titol": "Aslkda ",
    "dPublicacio": "2020-12-08",
    "dFinal": "2020-12-24",
    "tContracte": "",
    "localitat": "",
    "horari": "",
    "textOferta": "sdkf sldfjs ldkfs"
}
```

# Lliurament
-----

Heu de lliurar el contingut de la carpeta exercicis/js en un arxiu comprimit. La carpeta exercicis/js d’aquest arxiu es descomprimirà al meu projecte i serà el que es corregirà.

Per tant tot el que modifiqueu a l'html no es tendrà en compte.

# Avaluació
-----

Aquesta tasca representa el 6.5% de la nota de l’apartat d’activitats de l’assignatura.

A cada exercici trobareu la seva puntuació sobre 10.

El 60% de la nota de l’exercici s’obtindrà si la solució resol el cas general del problema.

El 40% restant de la nota s’obtindrà depenent de l’optimització del codi. Com més eficient sigui i més ben estructurat estigui, més nota tendrà en aquest apartat.

Es penalitzarà amb un 10% de la nota de l’exercici si el codi no està indentat o si els noms de les variables, funcions, ... no són descriptius.

No es tendrà en compte per a la correcció el codi comentat, el que no s’utilitzi en cap lloc de la pràctica, o les múltiples versions del codi.