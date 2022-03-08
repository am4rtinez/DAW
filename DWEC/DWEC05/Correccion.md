Ex 1: Ok

Ex 2.1: Podries eliminar les variables i assignar l'esdeveniment directament al getElementById

SI programes l'onchange no s'executaria tantes vegades el codi.

Podries fer els copyXXValue en una sola funció recuperant l'id des de l'esdeveniment.

Ex 2.2: killListeners hauria d'eliminar l'esdeveniment als elements de facturació, no tornar-los a afegir.

Ex 3.1: El proxim formulari tendrà 100 camps.

Bubbling: programes el keydown del formulari i s'executa sigui quin sigui l'element que ha disparat l'esdeveniment. Amb el paràmetre event pots saber quin element l'ha disparat si et fa falta.

Ex 3.2: Ok

Ex 4: Pitjar el botó només és una de les maneres d'enviar un formulari. A l'exercici 1 ho heu fet per codi. En aquest cas la validació del formulari no es faria. Els formularis es validen a l'onsubmit.

Per impedir que els listeners assignats al mateix eesdeveniment del mateix element no s'executen necessites stopImmediatePropagation.

El return no fa falta.

Ex 5: Ok, també podries haver creat un objecte Javascript i utilitzar JSON.stringify()