<html>
<head><title>Projekt Details</title>
<style type="text/css">
* {
   margin:0;
   padding:0;
}
body{
   text-align:center;
   background: #efe4bf none repeat scroll 0 0;
}
#wrapper{
   width:960px;
   margin:0 auto;
   text-align:left;
   background-color: #fff;
   border-radius: 0 0 10px 10px;
   padding: 20px;
   box-shadow: 1px -2px 14px rgba(0, 0, 0, 0.4);
}
#header{
 color: #fff;
 background-color: #2c5b9c;
 height: 3.5em;
 padding: 1em 0em 1em 1em;
}
#site{
    background-color: #fff;
    padding: 20px 0px 0px 0px;
}
.centerBlock{
	margin:0 auto;
}
</style>
<form method="get" action="/viewServlet">
    <button type="submit"  name="kennung" value=${kennung}>back to Main menu</button>
</form>
<h1>Informationen</h1>
<h2>by Group 013</h2>
<body>
	 <img src="icons\${Projekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
    <p>
    <h4>${Projekt.titel}</h4> 
   <br>
   von: ${Projekt.ersteller}
  <br>
   ${Projekt.beschreibung}
	<br>
	Finanzirungslimit: ${Projekt.finanzierungslimit}
	<br>
   Aktuelle Spenensumme: ${Projekt.spendenbetrag}
   <br>
   Status: ${Projekt.status}
   <br>
   Vorgaenger-Projekt: ${PreTitle}
   <br>
---------------------------------------------------------------------------------
   <h2> Aktionliste</h2>
  <style>
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.button2 {background-color: #f44336;} 
.button3 {background-color: #008CBA;} 
.button4 {background-color: #66ccff;} 
</style>
<form method="GET" action="/New_Project_Fund">
<button class="button" type="submit" name="kennung" value=${kennung}>Spenden</button> 
        </form>
<form method="GET" action="/deleteProject">
<button class="button button2" type="submit" name="kennung" value=${kennung}>Projekt Loeschen</button> 
        </form>
        <form method="GET" action="/projektEditeren">
			<button class="button button3" type="submit" name="kennung" value=${kennung}>Edit Project</button>
		</form>
      <h1>${kennung}</h1>
<br>
---------------------------------------------------------
<br>
<h2>Liste der Spender</h2>
        <#list spende as spenden>
       
 <br>
${spenden.spender} :: ${spenden.spendenbetrag}

            </#list>
<br>
-----------------------------------------------------------
 <h2>Kommentare</h2>
 <#list comments as comment>

<br>

${comment.commentWriter}: ${comment.text} 
<br>
 </#list>
<form method="GET" action="/New_Comment">
			<button class="button button4" type="submit" name="kennung" value=${kennung}>Kommentieren!</button>
		</form>
</body>
</html>
