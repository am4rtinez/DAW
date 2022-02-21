# Tarea Flask para entregar DWES05

## Introducción

En esta tarea utilizaremos la libreria WTForms para generar un formulario para inscribir nuevos usuarios a la base de datos del gimnasio. Con esto subimos una capa de abstracción en la gestion de los formularios.

Para ver como se gestionan los formularios con wtforms podéis ver los ejemplos de código de esta unidad.

Lo que haremos será generar una nueva página en la que se podra acceder sin login y que no estara vinculada con la página del gimnasio que utilizabamos hasta ahora.

## Formulario nuevo usuario

Los requisitos del formulario seran los siguientes:

* **Username**: Un camp de texte, mínim 6 lletres i màxim 15. No pot contenir paraules ofensives de la següent llista (en pots afegir més) ['caca','pedo','culo','pis','java','php']

* **Nom**: Màxim 50 caràcters

* **Llinatges**: Màxim 50 caràcters

* **Password**: Mínim 8 caràcters, com a mínim ha d'incloure una majúscula, una minúscula, un número i un caràcter especial (ni lletra ni numero)

* **Password repeat**: L'usuari haura de repetir el password i hauran de coincidir.

* **Fecha Alta**: Un camp date, inferior o igual al dia actual i amb format dia/mes/any

* **Email**: Ha de ser un camp correcte de correu electrònic.

* **Telèfon**: Pot ser en format "nacional" o "internacional", per tant ha de permetre números i els caràcters "( )+". També els espais, que eliminarà quan emmagatzemi el número a la base de dades.

## Emmagatzematge a la base de dades

Hauras de modificar la taula "usuaris" de la base de dades per afegir la data d'alta i el email. Per emmagatzemar el password seguirem fent el 'hash' com feiem abans.

## Lliurament

Has de lliurar un fitxer comprimit que contingui els documents de la tasca i una exportació de la base de dades utilitzada. El document ha de tenir el format 

**PrimerLLinatge_SegonLlinatge_DWES05.zip** o .rar

## Enlaces de interes

* Tutorial de referencia: https://j2logo.com/tutorial-flask-leccion-6-estructura-proyecto-flask-blueprints/