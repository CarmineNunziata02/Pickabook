<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
  <meta http-equiv = “Content-type” content = “text/html; charset=utf-8” />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name=" description" content="Vendita di libri online" />
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta name="robots" content="index,follow" />
  <meta name="robots" content="index,follow" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <%
    String currentPage = request.getServletPath(); // Percorso JSP corrente
    String cssFile = "";
    boolean AdditionalCss = false;

    if (currentPage.equals("/index.jsp")) {
      cssFile = "index.css";
      AdditionalCss = true;
    } else if(currentPage.equals("/prodotto.jsp")){
    	cssFile = "prodotto.css";
    	AdditionalCss = true;
    } else if(currentPage.equals("/catalogo.jsp")){
    	cssFile = "catalogo.css";
    	AdditionalCss = true;
    } else if (currentPage.equals("/login.jsp")){
      cssFile = "login.css";
      AdditionalCss = true;
    } else if (currentPage.equals(("/loginAdmin.jsp"))) {
      cssFile = "loginAdmin.css";
      AdditionalCss = true;
    } else if (currentPage.equals(("/signup.jsp"))) {
      cssFile = "signup.css";
      AdditionalCss = true;
    } else if (currentPage.equals(("/richiestaPassword.jsp"))) {
        cssFile = "richiestaPassword.css";
        AdditionalCss = true;
    } else if (currentPage.equals(("/eliminaProdotto.jsp"))) {
        cssFile = "eliminaProdotto.css";
        AdditionalCss = true;
    }
   
  %>
  <% if(AdditionalCss){%>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/global.css">
  <%}%>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/<%=cssFile%>">
  <link rel="icon" href="<%=request.getContextPath()%>/images/logo.ico"/>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Pattaya&display=swap"  rel="stylesheet"/>

  <title>Pickabook</title>
</head>
