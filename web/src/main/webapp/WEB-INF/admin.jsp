<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel = "stylesheet" type = "text/css" href = "../css/style.css">
    <script src="https://code.jquery.com/jquery-git.js"></script>
    <script src="js/admin.js"></script>
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
    <button onclick="nuovo()">Nuovo Prodotto</button>
    <button onclick="aggiorna()">Mod. Quantità Prodotto</button>
    <a href="Logout"> <button>Logout</button> </a>
    <br>
    <div id="stampa"><p>Premi un pulsante.</p></div>

    <iframe id="frameCatalogo" name="iframeCatalogo"
            style="display: none;       /*set to block if modify product is pressed */
            border: none;         /* Reset default border */
            height: 100vh;        /* Viewport-relative units */
            width: 100vw;">
    </iframe>
</body>
</html>
