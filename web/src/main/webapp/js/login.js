function validateForm() {
    const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    return (cerca.test($("#mail").val()) && $("#password").val() !== '');
}