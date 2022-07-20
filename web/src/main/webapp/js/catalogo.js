function getPriceSliderValue() {
    let val = $("#priceSlider").val();
    $("current").change(val);
}

function getChoice(){
    const ch = document.getElementsByName('choice');
    let choice = 'noChoice';
    for (let i = 0; i < ch.length; i++) {
        if (ch[i].checked)
            choice = ch[i].value;
    }
    return choice;
}
$(document).ready(function() {
    let oldCatalog = $('#divCatalogo').html();
    $('#input_cerca').keyup(function () {
        if($('#input_cerca').val() !== '') {
            $.ajax({
                url: 'FilterName',
                type: 'POST',
                data: {input_cerca: $('#input_cerca').val(), radio_scelta: getChoice()
                },
                success: function (response) {
                    $('#divCatalogo').html(response);
                }
            });
        }
        else {
            $.ajax({
                url: 'resetFilterCatalog',
                type: 'GET',
            });
            $('#divCatalogo').html(oldCatalog);
        }
    });
    $("#priceSlider").change(function(){
        $.ajax({
            url: 'FilterPrice',
            type: 'POST',
            data: {input_prezzo: $("#priceSlider").val(),
            },
            success: function (response) {
                $('#divCatalogo').html(response);
            }
        });
    });
    $("#priceSlider").change(function(){
        $("#current").html($("#priceSlider").val());
    });
});

function changePlaceholder(){
    let choice = document.getElementsByName('choice');
    for(let i = 0; i < choice.length; i++){
        if(choice[i].checked){
            document.getElementById('input_cerca').placeholder = choice[i].value + " da cercare";
        }
    }
}

function clsFilterPanel(){
    var panel = document.getElementById('filter-panel');
    panel.className = "hide";
}