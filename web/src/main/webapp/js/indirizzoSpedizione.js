function testCap() {
    const cerca = /[1-9][0-9]{4}/;
    console.log("cap"+cerca.test($("#cap").val()));
    return cerca.test($("#cap").val());
}

function testProv() {
    const cerca = /[A-Z]{2}/;
    console.log("provincia"+cerca.test($("#provincia").val()));
    return cerca.test($("#provincia").val());
}

function testVia() {
    const cerca = /[a-zA-Z ]{1,100}[0-9]{1,3}/;
    console.log("via"+cerca.test($("#via").val()));
    return cerca.test($("#via").val());
}

function testCitta() {
    const cerca = /[a-zA-Z]{1,100}/;
    console.log("citta"+cerca.test($("#citta").val()));
    return cerca.test($("#citta").val());
}

function validateInfoSped(){
    return testCap() && testProv() && testVia() && testCitta();
}