# Tarea para PROG05
Detalles de la tarea de esta unidad. 
---

## Enunciado.

A lo largo de esta unidad has ido aprendiendo a crear tus propias clases así como sus distintos miembros (atributos y métodos). Has experimentando con la encapsulación y accesibilidad (modificadores de acceso a miembros), has creado miembros estáticos (de clase) y de instancia (de objeto), has escrito constructores para tus clases, has sobrecargado métodos y los has utilizado en pequeñas aplicaciones. También has tenido tu primer encuentro el concepto de herencia, que ya desarrollarás en unidades más avanzadas junto con otros conceptos avanzados de la Programación Orientada a Objetos.

Una vez finalizada la unidad se puede decir que tienes un dominio adecuado del lenguaje Java como para desarrollar tus propias clases y utilizarlas en una aplicación final que sea capaz de manipular un conjunto de datos simple. Dada esa premisa, esta tarea tendrá como objetivo escribir una pequeña aplicación en Java empleando algunos de los elementos que has aprendido a utilizar.

Se trata de desarrollar una aplicación Java en consola que permita gestionar dos cuentas bancarias: cuenta A y cuenta B. Mediante un menú se podrán realizar determinas operaciones, todas sobre la cuenta A:

 1. Ver el número de cuenta completo (CCC - Código Cuenta Cliente).
 2. Ver el titular de la cuenta.
 3. Ver el código de la entidad.
 4. Ver el código de la oficina.
 5. Ver el número de la cuenta (solamente el número de cuenta, sin entidad, oficina ni dígitos de control).
 6. Ver los dígitos de control de la cuenta.
 7. Realizar un ingreso. Habrá que solicitar por teclado la cantidad que se desea ingresar. Siempre se mostrará el saldo final.
 8. Retirar efectivo. Una vez mostrado el saldo inicial, habrá que solicitar por teclado la cantidad que se desea retirar. Siempre se mostrará el saldo final.
 9. Transferencia entre cuentas.  Se transferirá la cantidad deseada a la cuenta B. Siempre se mostrará el saldo final de las dos cuentas.
 10. Salir de la aplicación.

Antes de que aparezca este menú, el programa tendrá que solicitar al usuario los siguientes datos para la Cuenta A y para la Cuenta B:

 - Nombre del titular de la cuenta (con un máximo de caracteres).
 - Código cuenta cliente (CCC) de la cuenta completo (entidad-oficina-dígitos de control-cuenta).

El programa deberá asegurarse que el CCC es válido mediante la comprobación de:

 - El formato (cuatro dígitos de entidad, cuatro dígitos de oficina, dos dígitos de control y diez dígitos de número de cuenta).
 - Los dígitos de control son válidos.

Además del programa principal de la aplicación (clase con una función main), habrá que escribir una clase CuentaBancaria que proporcione todas las herramientas necesarias para trabajar con este tipo de información:

- Constructor (o constructores) adecuados.
- Almacenamiento del nombre del titular (atributos).
- Almacenamiento del código de cuenta (atributos).
- Almacenamiento del saldo actual (atributos).
- Gestión de ingresos y depósitos (métodos de interfaz pública).
- Obtención del saldo (métodos de interfaz pública).
- Obtención de información sobre la cuenta: número de la cuenta, entidad, oficina, titular, etc. (métodos de interfaz pública).
- Aquellas herramientas auxiliares necesarias para poder trabajar cómodamente con el objeto. Algunas de esas herramientas podrán ser públicos y otras quizá no. Algunas podrán ser específicas de clase y otras podrán ser de objeto (métodos de objeto privados, métodos estáticos públicos, etc.).

Para trabajar con el número de cuenta debes utilizar el modelo de **Código Cuenta Cliente (CCC)**, que está formado por cuatro campos: entidad - sucursal - dígito de control - número de cuenta. La idea es que puedas introducir el código de cuenta completo y que la clase disponga de un mecanismo para comprobar que ese código es válido. Si el código no es válido, se debería generar una **excepción** (y por supuesto no almacenar ese código de cuenta). Para ello podrías tener, por ejemplo, un **método estático** que permita validar códigos de cuenta.

En general, deberías incluir **excepciones** para controlar aquellos casos en los que el uso de un método no sea posible (intentar sacar más dinero del que hay en el saldo, intentar introducir un titular con más caracteres de los permitidos, intentar ingresar o retirar una cantidad negativa, etc.).

El código fuente Java de esta clase debería incluir **comentarios** en cada atributo (o en cada conjunto de atributos) y método (o en cada conjunto de métodos del mismo tipo) indicando su utilidad. El programa principal también debería incluir algunos comentarios explicativos sobre su funcionamiento y la utilización de objetos de la clase CuentaBancaria.

Además del programa deberás escribir también un informe con todas las consideraciones oportunas que se necesiten para entender cómo has realizado la tarea.

El proyecto deberá contener al menos dos archivos fuente Java:

 - **Programa principal** (clase con método main: **AplicacionCuentaBancaria.java**).
 - La clase **CuentaBancaria (CuentaBancaria.java)**.


**Criterios de puntuación. Total 10 puntos.**

Para poder empezar a aplicar estos criterios es necesario que la aplicación compile y se ejecute correctamente en un emulador. En caso contrario la puntuación será directamente de 0,00.

## Criterios de puntuación.

| Objeto | Puntuación |
|---------|---------|
| La clase CuentaBancaria dispone de todos los atributos necesarios. | 1,00 |
| La clase CuentaBancaria dispone de al menos un constructor y funciona correctamente. | 1,00 |
| La clase CuentaBancaria dispone de los métodos públicos de interfaz necesarios y funcionan correctamente. | 4,00 |
| La clase CuentaBancaria es capaz de validar un CCC. | 2,00 |
| Los métodos de la clase CuentaBancaria son capaces de lanzar excepciones si se produce alguna situación anómala. | 1,00 |
| La codificación es estructurada y eficiente. | 1,00 |
| No se han incluido comentarios en la clase CuentaBancaria tal y como se ha pedido en el enunciado. | -1,00 |
| No se han incluido comentarios apropiados en el programa principal describiendo el funcionamiento de éste. | -1,00 |
| No se ha entregado el informe explicativo o se trata de un informe explicativo insuficiente. | -2,00 |
| El programa principal no es capaz de crear un objeto de la clase CuentaBancaria. | -5,00 |
| Alguna de las opciones de menú pedidas en el enunciado (menú del programa principal) no funciona correctamente. | -1,00 por cada opción |
| **Total** | **10,00** |

Dado que algunos criterios de puntuación son negativos, podría suceder que el balance final fuera negativo. En tal caso la puntuación final será simplemente de 0,00.

## Recursos necesarios para realizar la Tarea.

* Entorno de desarrollo NetBeans.

## Consejos y recomendaciones.

Para realizar la aplicación te realizamos la siguiente serie de recomendaciones:

 - Básate en los diferentes ejemplos que has tenido que probar durante el estudio de la unidad. Algunos de ellos te podrán servir de mucha ayuda, así que aprovéchalos.
 - El ejercicio resuelto de la clase DNI, en el cual se hacen comprobaciones de entrada, puede servirte de base para la comprobación de la validez de un **CCC**.
 - Puedes obtener información acerca del funcionamiento de la CCC y de cómo calcular los dígitos de control del siguiente artículo de **Wikipedia**:
 [Wikipedia: Código Cuenta Cliente.](https://es.wikipedia.org/wiki/C%C3%B3digo_cuenta_cliente)
 - Puedes generar cuentas bancarias válidas (o comprobarlas) para hacer pruebas en tu programa desde el siguiente enlace:
 [Generador/validador de cuentas bancarias.](https://www.genware.es/index.php?ver=cuentasbancarias)

Indicaciones de entrega.

Se entregaran dos documentos:

 - El proyecto comprimido en **formato .zip**,  y
 - El informe explicativo en **formato pdf** (apellido1_apellido2_nombre_PROG05.pdf).

# Informe

El proyecto esta comprendido por cuatro clases.

    1. AplicacionCuentaBancaria.java.
    2. CuentaBancaria.java.
    3. InvalidOptionException.java.
    4. SaldoInsuficienteException.java.

Las clases tipo exception nacen de la necesidad de capturar tipos de error y no lanzar un Exception genérico.

Recursos utilizados:

    * http://www.luciano.es/utiles/ccc.htm
    * https://www.delftstack.com/es/howto/java/how-to-check-if-a-string-is-an-integer-in-java/