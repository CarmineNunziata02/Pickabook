<%@ page import="utenteManagement.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%
  Object user = session.getAttribute("user");
  if(user == null || (user instanceof User))
    response.sendRedirect("loginAdmin.jsp");
%>
<jsp:include page="./header.jsp" flush="true"></jsp:include>
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<script>
  function confermaModifica(event) {
    event.preventDefault(); // Impedisce l'invio automatico del modulo

    Swal.fire({
      title: 'Sei sicuro di voler effettuare i cambiamenti?',
      showDenyButton: true,
      confirmButtonText: 'Salva',
      denyButtonText: 'Non Salvare',
    }).then((result) => {
      if (result.isConfirmed) {
        var risposta = result.isConfirmed ? "conferma" : "annulla";
        var prodotto = document.getElementById('chooseProduct').value;
        var nome = document.getElementById('nome').value
        var autore = document.getElementById('autore').value
        var descrizione = document.getElementById('descrizione').value
        var prezzo = document.getElementById('prezzo').value
        var quantita = document.getElementById('quantita').value
        var genere = document.getElementById('genere').value
        var categoria = document.getElementById('categoria').value
        var formData = new FormData();
        formData.append("risposta", risposta);
        formData.append("scelta", prodotto);
        formData.append("nome", nome);
        formData.append("autore", autore);
        formData.append("descrizione", descrizione);
        formData.append("prezzo", prezzo);
        formData.append("quantita", quantita);
        formData.append("genere", genere);
        formData.append("categoria", categoria);
        formData.append("file", $("#upload-input")[0].files[0]);

        // Esegui la chiamata AJAX per inviare i dati al server
        $.ajax({
          url: '<%=request.getContextPath()%>/UpdateProdottoServlet',
          type: 'POST',
          contentType: false,
          processData: false,
          data: formData,
        }).done(function (response) {
          var status = response.status;
          console.log(status)
          if(status == 'Invalid_prodotto'){
            Swal.fire('Scegliere un prodotto da modificare','','error')
          }else if(status === 'Blank'){
            Swal.fire("CAMPO VUOTO" , "Inserire almeno un valore da modificare", "error");
          }else if(status === 'Invalid_nome'){
            Swal.fire({
              icon: 'error', title: 'ERRORE DI FORMATO', text: 'Inserire un nome valido', footer: 'Un nome è  valido senza caratteri speciali.'
            });
          }else if(status =='Invalid_prezzo'){
              Swal.fire({
                  icon: 'error', title: 'ERRORE', text: 'Inserire un prezzo valido', footer: 'Un prezzo è valido se è maggiore di 0'
                });
          }else if(status =='Invalid_autore'){
            Swal.fire({
              icon: 'error', title: 'ERRORE DI FORMATO', text: 'Inserire un autore valido', footer: 'Un autore è  valido è senza caratteri speciali.'
            });
          } else if(status =='Invalid_quantita'){
              Swal.fire({
                  icon: 'error', title: 'ERRORE', text: 'Inserire una quantità valida', footer: 'La quantità non può essere negativa'
                });
          }else if(status =='Invalid_genere'){
            Swal.fire('Scegliere genere prodotto!','','error')
          }else if(status =='File_Non_Caricato'){
            Swal.fire('ERRORE','Si è verificato un problema e l\'immagine non  è stata caricata con successo','error');
          }else if(status =='Invalid_categoria'){
            Swal.fire('Scegliere categoria!','','error')
          }else if(status =='success'){
            Swal.fire('Prodotto aggiornato correttamente!','','success')
            setTimeout(function() {
              window.location.assign(response.url);
            }, 3500); // Ritardo di 3,5 secondi (3500 millisecondi)
          }
          else{
            Swal.fire('Errore!','Il prodotto potrebbe non essere più presente sul sito','error')
          }
        })
      } else if (result.isDenied) {
        Swal.fire('Modifiche non salvate', '', 'info');
      }
    });
  }
</script>
<body>
  <button id="torna-indietro" onclick="window.location.assign('areapersonale.jsp')">
    <div class="bar"></div>
  </button>
  <script src="./Script/dynamicCode.js"></script>
  <script>
    document.addEventListener("DOMContentLoaded", dynamicModificaProdotto("<%=request.getContextPath()%>/NameServlet"));
  </script>
  <section id="new_product">
    <div class="form-wrapper">
      <form  enctype ="multipart/form-data" onsubmit="confermaModifica(event)" method="POST">
        <h2>Modifica un Prodotto</h2>
        <div class="form-row">
          <label for="scelta">Prodotto: </label>
          <select id="chooseProduct" name="scelta" id="scelta">

          </select>
        </div>
        <div class="form-row">
          <label for="nome">Nome: </label>
          <input type="text" name="nome" id="nome">
        </div>
        <div class="form-row">
          <label for="autore">Autore: </label>
          <input type="text"  id="autore" name="autore">
        </div>
        <div class="form-row">
          <label for="descrizione">Descrizione: </label>
          <textarea id ="descrizione" name="descrizione" ></textarea>
        </div>
        <div class="file-row">
          <label for="upload-input">File: </label>
          <input type="file" class = "input_container" name="file" id="upload-input">
        </div>
        <div class="form-row">
          <label for="prezzo">Prezzo: </label>
          <input type="number"  step="0.01" id="prezzo"name="prezzo">
        </div>
        <div class="form-row">
          <label for="quantita">Quantità: </label>
          <input type="number" name="quantita" id ="quantita">
        </div>
        <div class="form-row">
          <label for="genere">Genere: </label>
          <select name="genere" id ="genere">
            <option disabled selected value>-scegliere genere-</option>
		  <option>Autobiografia</option>
          <option>Avventura</option>
          <option>Azione</option>
          <option>Biografia</option>
          <option>Classici</option>
          <option>Commedia</option>
          <option>Crimine</option>
          <option>Distopico</option>
          <option>Drammatico</option>
          <option>Fantasy</option>
          <option>Filosofia</option>
          <option>Giallo</option>
          <option>Gotico</option>
          <option>Narrativa</option>
          <option>Romance</option>
          <option>Satira</option>
          <option>Scienza</option>
          <option>Storia</option>
          <option>Thriller</option>
          </select>
        </div>
        <div class="form-row">
          <label for="categoria">Categoria: </label>
          <select name="categoria" id ="categoria">
            <option disabled selected value>-scegliere categoria-</option>
          <option>Azione</option>
          <option>Biografie</option>
          <option>Classico</option>
          <option>Distopico</option>
          <option>Fantascienza</option>
          <option>Fantasy</option>
          <option>Filosofia</option>
          <option>Giallo</option>
          <option>Narrativa Per Ragazzi</option>
          <option>Ragazzi</option>
          <option>Romanzo Sentimentale</option>
          <option>Saga</option>
          <option>Saggistica</option>
          <option>Sentimentale</option>
          <option>Storia</option>
          </select>
        </div>
        <div class="sub-class">
          <button type="submit">Modifica</button>
        </div>
      </form>
    </div>
  </section>
</body>
</html>
