function testNickname() {
    let reNick = /(?=.{2,50}$)^([a-zA-Z]{1,}){1}( [a-zA-Z]{1,})*$/ ;
    console.log("nick"+reNick.test($("#nick").val()));
    return reNick.test($("#nick").val());
}

function testMail() {
    const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    console.log("mail"+cerca.test($("#mail").val()));
    return cerca.test($("#mail").val());
}

function testPassword() {
    const cerca = /^.{5,130}$/;
    console.log("password"+cerca.test($("#password").val()));
    return cerca.test($("#password").val());
}

function testTel() {
    const cerca = /^\+[0-9]{2}[0-9]{10}$/;
    console.log("tel "+cerca.test($("#tel").val())+' length'+($("#tel").val().length===13));
    return ( (cerca.test($("#tel").val())) && ($("#tel").val().length===13) );
}

function testCap() {
    const cerca = /^[1-9][0-9]{4}$/;
    console.log("cap"+cerca.test($("#cap").val()));
    return cerca.test($("#cap").val());
}

function testProv() {
    const cerca = /^[A-Z]{2}$/;
    console.log("provincia"+cerca.test($("#provincia").val()));
    return cerca.test($("#provincia").val());
}

function testVia() {
    const cerca = /(?=.{3,100}$)^([a-zA-Z]{1,}){1}([a-zA-Z]{1,}\s)*[1-9]{1,3}$/;
    console.log("via"+cerca.test($("#via").val()));
    return cerca.test($("#via").val());
}

function testCitta() {
    const cerca = /(?=.{2,100}$)^([a-zA-Z]{1,}){1}( [a-zA-Z]{1,})*$/;
    console.log("citta"+cerca.test($("#citta").val()));
    return cerca.test($("#citta").val());
}

function validateForm(){
    return testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv()
        && testVia() && testCitta();
}

function validateInfoPers(){
    if(testNickname() && testTel())
        return true;
    alert("Info presonali non inserite correttamente. assicurati di rispettare tutti i campi");
    return false;
}

function validateInfoSped(){
    if(testCap() && testProv() && testVia() && testCitta()){
        return true;
    }
    alert("Info spedizione non inserite correttamente. Assicurati che tutti i campi siano stati rispettati");
    return false;
}