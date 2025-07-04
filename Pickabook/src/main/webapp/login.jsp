<%@ page import="view.utente.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String status = (String) request.getAttribute("status");%>
<!DOCTYPE html>
<html>
  <jsp:include page="./header.jsp" flush="true"></jsp:include>
  <body>
    <script src="
    https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
    "></script>
    <link href="
    https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
    " rel="stylesheet">
    <script>
        function confermaLogin(event){
          event.preventDefault();

          var email = document.getElementById('email').value;
          var password = document.getElementById('password').value;
          var jspName = document.getElementById('jspName').value;
          $.ajax({
            url: '<%=request.getContextPath()%>/LoginServlet',
            type: 'POST',
            data:{
              email : email,
              password : password,
              jspName : jspName
            },
          }).done(function (response){
            var status = response.status;
            console.log(status);
            if(status === 'Invalid_Mail'){
              Swal.fire("E-MAIL NON VALIDA ", "L'email inserita non è in un formato corretto", "error");
            }else if(status == 'Blank'){
              Swal.fire("CAMPO VUOTO", "Inserire un valore nel campo", "error");
            }else if(status === 'failed'){
              Swal.fire("CREDENZIALI NON VALIDE", "E-mail o Password errati", "error");
            }else if(status === 'success'){
              window.location.assign(response.url);
            }
          })

        }
    </script>
    <jsp:include page="./nav.jsp" flush="true"></jsp:include>
    <main>
      <section id="login">
        <div class="login-img">
          <img src="./icons/logo.jpg" alt="Pickabook Logo">
        </div>
        <div class="form-wrapper">
          <h3>Accedi al tuo Account</h3>
          <form onsubmit="confermaLogin(event)" method="post">
            <input type="hidden" name="jspName" value="login" id="jspName">
            <input  name="email" placeholder="E-mail" id="email">
            <div class="password-row">
              <input type="password" name="password" placeholder="Password" id = "password">
              <img src="./icons/eye-close.png" alt="" id="eye-icon">
            </div>
            <button type="submit" id="loginButton">Invia</button>
          </form>
        </div>
        <div class="links">
          <a href="./richiestaPassword.jsp">Hai dimenticato la password?</a>
          <p>Non sei iscritto? <br> <a href="./signup.jsp">Iscriviti!!!</a> </p>

        </div>
      </section>
    </main>
    <jsp:include page="./footer.jsp" flush="true"></jsp:include>
  </body>
<script>
  let eyeicon = document.getElementById("eye-icon");
  let password = document.getElementById("password");
  eyeicon.onclick = function(){
    if(password. type == "password" ) {
      password.type = "text";
      eyeicon.src = "./icons/eye-open.png";
    }
    else{
      password. type = "password";
      eyeicon.src = "./icons/eye-close.png";
    }
  }
</script>
</html>
