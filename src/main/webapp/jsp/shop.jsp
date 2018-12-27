<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 03.12.2018
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Test Drive Club</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>

</head>
<script>
    function getCookie(name) {
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));

        if (matches) {
            return decodeURIComponent(matches[1]);
        } else {
            return undefined;
        }
    }
</script>


<script>
    function sendProductId(productId) {
        $.ajax({
            type: 'post',
            url: '/products',
            data: {
                productId: productId,
                userId: getCookie('userId')
            }
        }).done(function (data) {
            var contentTableHTML = "<table>";
            var count = 0;

            for (var i = 0; i < data.length; i++) {

                count++;

                contentTableHTML += "<tr>";
                contentTableHTML += "<td>Added to cart: " + data[i].title + "</td>";

                contentTableHTML += "<table>";
                contentTableHTML += "<tr>";
                contentTableHTML += '<td><button onclick="deleteProduct(' + data[i].id + ')" ">Delete </button></td>';
                contentTableHTML += "</tr>";
                contentTableHTML += "</table>";
            }
            contentTableHTML += "</table>";
            var contentTableDiv = document.getElementById("table");
            contentTableDiv.innerHTML = contentTableHTML;
            document.getElementById("counter").innerHTML = count;
        }).fail(function () {
            alert("НЕ ОЧ(((")
        });
    }
</script>

<script>

</script>
<script>
    function deleteProduct(productId) {
        $.ajax({
            type: 'post',
            url: '/deletecart',
            data: {
                productId: productId,
                userId: getCookie('userId')
            }
        }).done(function (data) {
            var contentTableHTML = "<table>";

            var count = 0;

            for (var i = 0; i < data.length; i++) {

                count++;

                contentTableHTML += "<tr>";
                contentTableHTML += "<td>Added to cart: " + data[i].title + "</td>";

                contentTableHTML += '<table id="delete">';
                contentTableHTML += "<tr>";
                contentTableHTML += '<td><button onclick="deleteProduct(' + data[i].id + ')" ">Delete </button></td>'
                contentTableHTML += "</tr>";
                contentTableHTML += "</table>";
            }
            contentTableHTML += "</table>";
            var contentTableDiv = document.getElementById("table");
            contentTableDiv.innerHTML = contentTableHTML;
            document.getElementById("counter").innerHTML = count;
        }).fail(function () {
            alert("НЕ ОЧ(((")
        });
    }
</script>
<body>
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">TEST-DRIVE CLUB</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="index">HOME</a></li>
                <li><a href="cars-info">CARS INFO</a></li>
                <li class="active"><a href="shop">YOUR CART</a></li>
                <li><a href="signUp">SIGN UP</a></li>
            </ul>
        </div>

    </div>
</div>

<div id="blue">
    <div class="container">
        <div class="row centered">
            <div class="col-lg-8 col-lg-offset-2">
                <h4>WE CAN DO A LOT OF THINGS</h4>
                <p>JUST TAKE A LOOK and CONTACT US</p>
            </div>
        </div>
    </div>
</div>


<div class="container w">
    <div class="row centered">
        <div id="products">


        </div>
        <div>
            Search: <input id="q" type="text" onkeyup="search(document.getElementById('q').value)">
            <br>
            <div id="products_table">

            </div>
        </div>
        <div id="table">
        </div>
    </div>
</div>


<div id="r">
    <div class="container">
        <div class="row centered">
            <div class="col-lg-8 col-lg-offset-2">
                <h4>SOMETIMES THE RIGHT PATH IS NOT THE EASTEST ONE. JUST CHOOSE YOUR CAR!</h4>
                <p>The cars we drive say a lot about us!</p>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER -->
<div id="f">
    <div class="container">
        <div class="row centered">
            <p>Subscribe to the developer!</p>
            <a href="https://vk.com/annchouss_s"><i class="fa fa-vk"></i></a><a href="https://www.instagram.com/anyana_t/"><i class="fa fa-instagram"></i></a><a href="#"></a>
        </div>
    </div>
</div>
<!-- Footer -->

<!-- COPYRIGHTS -->
<div id="copyrights">
    <div class="container">
        <p>
            Created by <strong>Anna Tugbaeva</strong>. Contact me!
        </p>
        <div class="credits">
            <strong>+7(917)897-35-32</strong>
        </div>
    </div>
</div>
<!-- copyrights -->
<!--
<script>
    function search(query) {
        $.ajax({
            type: 'GET',
            url: '/search',
            data: {
                q: query
            }
        }).done(function (data) {
            let tableHtml = "";
            tableHtml += '<table>';

            tableHtml + '<tr>';
            tableHtml += '<td>' + 'Found: ' + '</td>';
            let count = 0;
            for (let i = 0; i < data.length; i++) {
                count++;
                tableHtml +=
                    '<td>' + data[i].title + '</td>';

            }
            if (count == 0) {
                tableHtml += '<td>' + 'nothing :(' + '</td>';
            }
            tableHtml + '</tr>';
            tableHtml += '</table>';
            $("#products_table").html(tableHtml);
        }).fail(function () {
            alert('НЕ ОЧ((0(')
        });
    }
</script>
-->
</body>

</html>
