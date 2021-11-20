function obtenerCadena(nota){
    switch(nota){
        case 0:
        case 1:
        case 2:{
            //console.log(nota);
            return "Muy deficiente";
        }
        case 3:
        case 4:{
            //console.log(nota);
            return "Insuficiente";
        }
        case 5:{
            //console.log(nota);
            return "Aprobado";
        }
        case 6:{
            //console.log(nota);
            return "Bien";
        }
        case 7:
        case 8:{
            //console.log(nota);
            return "Notable";
        }
        case 9:
        case 10:{
            //console.log(nota);
            return "Excelente";
        }
        default:{
            //console.log("Default: " + nota);
            return "Valor introducido incorrecto."
        }
    }
}