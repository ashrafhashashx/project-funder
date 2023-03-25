<html>
<head><title>Projekt erstellen</title>
<h1>Hallo </h1>
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
<body>
	  <form action="/ProjektErstellen" method="post">  
	  <fieldset id="enter Project1">
   Titel:
    <input type="text" required name="titel">
         <br>
   Finanzirungslimit:
    <input type="number" min="100" required size="7" name="finanzirungslimit" >€
    <p>Kategorie</p>
	<input type="radio" required value="1"
     name="kategorie" >
    <label for="1">Health & Wellness</label>

    <input type="radio"
     name="kategorie" required value="2">
    <label for="2">Art & Creative Works</label>

    <input type="radio"
     name="kategorie" required value="3">
    <label for="3">Education</label>	
     <input type="radio" 
     name="kategorie" required value="4">
    <label for="4">Tech & Innovation</label>	
    
    <p>Vorgänger</p>
     <#list PrecedeList as vorgaenger>
	<input type="radio" 
     name="vorgaenger" required value=${vorgaenger.kennung}>
    <label for=${vorgaenger.kennung} >1${vorgaenger.titel}</label> 
 <input type="radio" 
	 </#list> 
	    name="vorgaenger" value=1>
    <label for=1>Kein Vorgänger</label>  
	<#--  <input type="radio" 
     name="vorgaenger" required value=1>
    <label for=1 >1</label>  

   <input type="radio"
     name="vorgaenger" required value=2>
    <label for=2>2</label>

    <input type="radio" 
     name="vorgaenger" required value=3>
    <label for=3>3</label>   --> 
	<p>Beschreibung</p>
  <textarea name="beschreibung" size="1000" rows="10" cols="30"></textarea>
  <br>
    </fieldset>
    <button type="submit" id="submitform" name="submitform1" value="Submit">Submit</button>
    
</form>
<style>
.button {
  background-color: #008CBA;
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
</body>
</html>