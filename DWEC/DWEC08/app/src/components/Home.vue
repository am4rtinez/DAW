<template>
  <div class="view">
    <section>
      <h2>Instruccions</h2>
      <ul>
        <li>
          Parteix del codi dels exercicis d'autoaprenentatge per fer la pràctica. Pots utilitzar el codi del exercicis resolts.
        </li>
        <li>
          No es corregirà cap exercici que presenti més d’una versió.
        </li>
        <li>
          El codi comentat o que no s’utilitzi en l’execució dels exercicis no es tendrà en compte per a la correcció.
        </li>
      </ul>
    </section>
    <section>
      <h2>Exercicis</h2>
      <p>Modificarem l'aplicació de manera que els usuaris puguin puntuar les aplicacions. Ho farem de la següent manera:</p>
      <h3>Modificació de l'estat (3 punts)</h3>
      <p>Als objectes de l'aplicació afegirem un atribut per comptar les puntuacions favorables i un altre per comptar les puntuacions desfavorables.</p>
      <p>Afegirem dues mutacions. Totes dues rebran com a paràmetre l'aplicació, però una incrementarà en una unitat les puntuacions favorables i un altra les desfavorables.</p>
      <p>Modifica la mutació d'alta de manera que inicialitzi les puntuacions a zero.</p>
      <article>
        <h3>Component puntuacions (2,5 punts)</h3>
        <p>Crea un component per puntuar les aplicacions amb les següents característiques:</p>
        <ul>
          <li>
            Rebrà l'aplicació com una propietat del component.
          </li>
          <li>
            L'element arrel serà span o similar per poder incloure el component en línia.
          </li>
          <li>
            Tendrà un botó ( o una imatge o un boto amb una imatge o ...) per a incrementar cada tipus de puntuació.
          </li>
          <li>
            Els botons ( o les imatges ...) executaran les mutacions de l'exercici anterior.
          </li>
        </ul>
      </article>
      <article>
        <h3>Component Llista de puntuacions (1,5 punts)</h3>
        <p>Crea un component nou. Ha de mostrar una llista ol on cada li conté el nom d'una de les aplicacions i el component puntuacions.</p>
        <h3>Ruta (2 punts)</h3>
        <p>Afegeix una opció al menú per mostrar la llista de puntuacions i tot el necessari perquè l'opció funcioni.</p>
        <h3>Component Llista d'aplicacions (1 punt)</h3>
        <p>Modifica'l de manera que inclogui el component de puntuacions.</p>
      </article>
    </section>
    <section>
      <h2>Lliurament</h2>
      <p>Heu de lliurar el contingut del projecte complet.</p>
    </section>
    <section>
      <h2>Avaluació</h2>
      <p>Aquesta tasca representa el 6.5% de la nota de l’apartat d’activitats de l’assignatura.</p>
      <p>A cada exercici trobareu la seva puntuació sobre 10.</p>
      <p>El 60% de la nota de l’exercici s’obtindrà si la solució resol el cas general del problema.</p>
      <p>El 40% restant de la nota s’obtindrà depenent de l’optimització del codi. Com més eficient sigui i més ben estructurat estigui, més nota tendrà en aquest apartat.</p>
      <p>Es penalitzarà amb un 10% de la nota de l’exercici si el codi no està indentat, si els noms de les variables, funcions, ... no són descriptius o si l’HTML que s’utilitzi per fer l’exercici i mostrar els resultats no és correcte.</p>
      <p>No es tendrà en compte per a la correcció el codi comentat, el que no s’utilitzi en cap lloc de la pràctica, o les múltiples versions del cod</p>
    </section>
  </div>
</template>

<script>
  /* eslint-disable */
  export default {
    name: "Home"
  }
</script>

<style scoped>

</style>