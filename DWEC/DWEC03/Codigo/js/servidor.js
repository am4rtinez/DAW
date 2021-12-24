function getDestacades() {
    return ["imatges/birds.jpg","imatges/cars.jpg","imatges/dory.jpg","imatges/luxo.jpg","imatges/monstruos.jpg","imatges/soul.jpg","imatges/up.jpg"];
}

function getActors() {
    return '[{"actorId":1,"firstName":"PENELOPE","lastName":"GUINESS"},{"actorId":10,"firstName":"CHRISTIAN","lastName":"GABLE"},' +
        '{"actorId":20,"firstName":"LUCILLE","lastName":"TRACY"},{"actorId":30,"firstName":"SANDRA","lastName":"PECK"},' +
        '{"actorId":40,"firstName":"JOHNNY","lastName":"CAGE"},{"actorId":53,"firstName":"MENA","lastName":"TEMPLE"},' +
        '{"actorId":108,"firstName":"WARREN","lastName":"NOLTE"},{"actorId":162,"firstName":"OPRAH","lastName":"KILMER"},' +
        '{"actorId":188,"firstName":"ROCK","lastName":"DUKAKIS"},{"actorId":198,"firstName":"MARY","lastName":"KEITEL"},' +
        '{"actorId":57,"firstName":"JUDE","lastName":"CRUISE"},{"actorId":125,"firstName":"ALBERT","lastName":"NOLTE"},' +
        '{"actorId":10,"firstName":"ROCK","lastName":"GABLE"},{"actorId":55,"firstName":"FAY","lastName":"KILMER"},' +
        '{"actorId":97,"firstName":"MEG","lastName":"HAWKE"},{"actorId":130,"firstName":"GRETA","lastName":"KEITEL"},' +
        '{"actorId":132,"firstName":"ADAM","lastName":"HOPPER"},{"actorId":178,"firstName":"LISA","lastName":"MONROE"}, ' +
        '{"actorId":51,"firstName":"GARY","lastName":"PHOENIX"},{"actorId":88,"firstName":"KENNETH","lastName":"PESCI"},' +
        '{"actorId":182,"firstName":"DEBBIE","lastName":"AKROYD"},{"actorId":189,"firstName":"CUBA","lastName":"BIRCH"},' +
        '{"actorId":194,"firstName":"MERYL","lastName":"ALLEN"}]';
}

function getNomsActors(){
    let resultat=[];
    let actors=JSON.parse(getActors());
    for(actor of actors){
        resultat.push(actor.lastName+", "+actor.firstName);
    }
    return resultat;
}