$(document).ready(function(){
    function saveOldQuant() {
        let id = $('#submit').parent().attr("id");
        return $('#quantOf' + id).val();
    }
    let oldQuant = saveOldQuant();
    $('#submit').click(function(){
        let id = $(this).parent().attr("id");
        let quantOf = $('#quantOf'+id);
        let quant = Number(quantOf.val());
        if(Number(quantOf.attr("max"))>=quant) {
            console.log(quant + ' Id' + id + ' oldQuant' + oldQuant);
            $.ajax({
                url: 'modQuantCartDB',
                type: 'GET',
                data: {
                    attr_id: id, attr_newQuant: quant, attr_OldQuant: oldQuant
                },
            });
            oldQuant = quant;
        }
        else{
            quantOf.val(oldQuant);
        }
    });
});