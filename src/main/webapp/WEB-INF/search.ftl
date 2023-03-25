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
    <button type="submit" >back to Main menu</button>
</form>

<h1>Search</h1>
<h2>by Group 013</h2>
	<h3>search results:</h3>
            <#list projektList as Projekt>
    <div>
    <img src="icons\${Projekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
    <p>
    <a href="projektDetails?kennung=${Projekt.kennung}">${Projekt.titel}</a>
      Finanzierungslimit::<p>${Projekt.finanzierungslimit}</p>
	<a href="view_profile?email=${Projekt.ersteller}">${Projekt.ersteller}</a>
        </div>
            </#list>
</html>




