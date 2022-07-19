function testNickname() {
    let reNick = /[a-zA-Z0-9]{1,70}/ ;
    console.log("nick"+reNick.test($("#nick").val()));
    return reNick.test($("#nick").val());
}

function testMail() {
    const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    console.log("mail"+cerca.test($("#mail").val()));
    return cerca.test($("#mail").val());
}

function testPassword() {
    const cerca = /[a-zA-Z0-9]{1,130}/;
    console.log("password"+cerca.test($("#password").val()));
    return cerca.test($("#password").val());
}

function testTel() {
    const cerca = /[0-9]{2}[0-9]{10}/;
    console.log("tel"+cerca.test($("#tel").val())+$("#tel").val());
    return cerca.test($("#tel").val());
}

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

function validateForm(){
    return testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv()
        && testVia() && testCitta();
}