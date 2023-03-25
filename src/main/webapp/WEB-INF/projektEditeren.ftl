<html>
<head><title>Projekt Editieren</title>
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
    <button type="submit">back to Main menu</button>
</form>
<h1>Projekt Editieren</h1>

<body>
	
      <form action="/projektEditeren" method="POST"> 
  <label for="titel">Titel:</label>
            <input titel="titel" type="text" required name="titel" >
            <br>  
  <label for="finanzierungslimit">Finanzierungslimit:</label>
            <input  type="text" 
            name="finanzierungslimit" >
            <br>
   <p>Kategorie::
	<input type="radio" required value="1"
     name="kategorie">
    <label for="1">Health & Wellness</label>

    <input type="radio" required name="kategorie" value="2" >
    <label for="2">Art & Creative Works</label>
    </p>  <p>
    <input type="radio" required value="3"
     name="kategorie" >
    <label for="3">Education</label>	
     <input type="radio" required value="4"
     name="kategorie" >
    <label for="4">Tech & Innovation</label>	
    </p>
---------------------------------------------------------------------------------
   <p>Vorgänger</p>
     <#list PrecedeList as vorgaenger>
	<input type="radio" 
     name="vorgaenger" required value=${vorgaenger.kennung}>
    <label for=${vorgaenger.kennung} >1${vorgaenger.titel}</label> 
 <input type="radio" 
	 </#list> 
	    name="vorgaenger" value=${kennung}>
    <label for=${kennung}>Kein Vorgänger</label> 
	</p>
---------------------------------------------------------
<p> Beschreibung
  <textarea name="beschreibung" rows="10" cols="30" placeholder=" write something to describe your Project !!"></textarea></p>
  <br>
-----------------------------------------------------------
    <input type="hidden" name="kennung" value=${kennung}>
<button class="button" type="submit" >Aktualieren</button> 
 </form>

</body>
 	
</html>
