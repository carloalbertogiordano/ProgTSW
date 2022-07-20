$(document).ready(function(){
    $('#open-button').click(function (){
        $('#filter-container').slideDown();
        $('#open-button').slideUp();
    });

    $('#close-button').click(function (){
        $('#filter-container').slideUp();
        $('#open-button').slideDown();
    });
});