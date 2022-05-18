<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel = "stylesheet" type = "text/css" href = "style.css">
</head>
<body>
    <div class = "img">
        <img src="Images/logo.png" alt="LOGO">
    </div>

    <div class="slide-container">
        <div class="slide fade">
            <img src='https://images.unsplash.com/photo-1590595978583-3967cf17d2ea?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ' alt=''>
        </div>
        <div class="slide fade">
            <img src='https://images.unsplash.com/photo-1588807308097-fb6e5047df8c?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ' alt=''>
        </div>
        <div class="slide fade">
            <img src='https://images.unsplash.com/photo-1589808710416-24cf7ac026f2?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ' alt=''>
        </div>
        <div class="slide fade">
            <img src='https://images.unsplash.com/photo-1588796388882-a4d533c47e5e?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjE0NTg5fQ' alt=''>
        </div>
        <a href="#" class="prev" title="Previous">&#10094</a>
        <a href="#" class="next" title="Next">&#10095</a>
    </div>
    <div class="dots-container">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>

</body>
</html>