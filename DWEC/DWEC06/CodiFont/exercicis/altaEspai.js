/**
 * Exercici 2 (5 punts)
 * ==============================================================================
 * A la pàgina altaEspai.html teniu un formulari per donar d'alta un nou espai al sistema. Heu de programar el següent:
 * 
 * Exercici 2.1 (2 punts)
 * ----------------------------------
 * Recuperau les dades que torna la funció del servidor getServeis(). Per a cada una d'elles heu de generar un checkbox amb el corresponent label 
 * dins el div anomenat serveis.
 * 
 * L'estructura que heu de generar és la següent:
 * <label for="s1">Adaptat discapacitats</label>
 * <input type="checkbox" id="s1" name="servei" value="1">
 * 
 * Fixau-vos que:
 *     El contingut del label és una de les descripcions del servei, la que volgueu però utilitzau sempre la mateixa.
 *     El value del checkbox és l'identificador del servei.
 *     L'id del checkbox és una s concatenada amb l'identtificador del servei.
 *     L'atribut for del label té el mateix valor que l'identificador del checkbox.
 * 
 * Exercici 2.2 (2 punts)
 * ----------------------------------
 * Recuperau les dades que torna la funció del servidor getTipus(). Per a cada una d'elles heu de generar un radio amb el corresponent label 
 * dins el div anomenat tipus.
 * 
 * L'estructura que heu de generar és la següent:
 * 
 * <label for="t4">Centre Cultural</label>
 * <input type="radio" id="t4" name="tipus" value="4">
 * 
 * Fixau-vos que:
 *     El contingut del label és una de les descripcions del tipus de centre, la que volgueu però utilitzau sempre la mateixa.
 *     El value del radio és l'identificador del tipus de centre.
 *     L'id del radio és una t concatenada amb l'identtificador del tipus de centre.
 *     L'atribut for del label té el mateix valor que l'identificador del radio.
 * 
 * Exercici 2.3 (1 punt)
 * ----------------------------------
 * Recuperau les dades que torna la funció del servidor getModalitats(). Per a cada una d'elles heu de generar un checkbox amb el corresponent label 
 * dins el div anomenat modalitats.
 * 
 * L'estructura que heu de generar és la següent:
 * <label for="m2">Escultura</label>
 * <input type="checkbox" id="m2" name="modalitat" value="2">
 * 
 * Fixau-vos que:
 *     El contingut del label és una de les descripcions de la modalitat, la que volgueu però utilitzau sempre la mateixa.
 *     El value del checkbox és l'identificador de la modalitat.
 *     L'id del checkbox és una m concatenada amb l'identificador de la modalitat.
 *     L'atribut for del label té el mateix valor que l'identificador del checkbox.
 */

window.onload = () =>{
    getData('serveis', 's', 'checkbox', 'servei')
    getData('tipus', 't', 'radio', 'tipus')
    getData('modalitats', 'm', 'checkbox', 'modalitat')

    // obtenerServeis()
    // obtenerTipus()
    // obtenerModalitats()
}

getData = (parentId, idPrefix, type, name) => {
    let parent = document.getElementById(parentId)    
    switch (parentId) {
        case 'serveis':
            data = JSON.parse(getServeis())
            break;
        case 'tipus':
            data = JSON.parse(getTipus())
            break;
        case 'modalitats':
            data = JSON.parse(getModalitats())
            break;
    }
    data.forEach(element => {
        let label = document.createElement('label')
        let input = document.createElement('input')
        let did = idPrefix + element['id']
        label.setAttribute('for', did)
        label.textContent = element['cat']
        input.setAttribute('type', type)
        input.setAttribute('id', did)
        input.setAttribute('name', name)
        input.setAttribute('value', element['id'])
        parent.appendChild(label)
        parent.appendChild(input)
    })
    
}

// obtenerServeis = () => {
//     let parent = document.getElementById('serveis')
//     const serveis = JSON.parse(getServeis())
//     serveis.forEach(element => {
//         let label = document.createElement('label')
//         let input = document.createElement('input')
//         let sid = 's' + element['id']
//         label.setAttribute('for', sid)
//         label.textContent = element['cat']
//         input.setAttribute('type', 'checkbox')
//         input.setAttribute('id', sid)
//         input.setAttribute('name', 'servei')
//         input.setAttribute('value', element['id'])
//         parent.appendChild(label)
//         parent.appendChild(input)
//     });
// }

// obtenerTipus = () => {
//     let parent = document.getElementById('tipus')
//     const tipus = JSON.parse(getTipus())
//     tipus.forEach(element => {
//         let label = document.createElement('label')
//         let input = document.createElement('input')
//         let tid = 't' + element['id']
//         label.setAttribute('for', tid)
//         label.textContent = element['cat']
//         input.setAttribute('type', 'radio')
//         input.setAttribute('id', tid)
//         input.setAttribute('name', 'servei')
//         input.setAttribute('value', element['id'])
//         parent.appendChild(label)
//         parent.appendChild(input)
//     });
// }

// obtenerModalitats = () => {
//     let parent = document.getElementById('modalitats')
//     const tipus = JSON.parse(getModalitats())
//     tipus.forEach(element => {
//         let label = document.createElement('label')
//         let input = document.createElement('input')
//         let mid = 'm' + element['id']
//         label.setAttribute('for', mid)
//         label.textContent = element['cat']
//         input.setAttribute('type', 'checkbox')
//         input.setAttribute('id', mid)
//         input.setAttribute('name', 'servei')
//         input.setAttribute('value', element['id'])
//         parent.appendChild(label)
//         parent.appendChild(input)
//     });
// }