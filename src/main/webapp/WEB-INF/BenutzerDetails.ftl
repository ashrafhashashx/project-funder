<html>
<head><title>Mein Profile</title>
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
#list {
  display: inline-block; 
  background-color: lightgrey;
  width: 200px;
  border: 15px solid blue;
  padding: 50px;
  margin: 10px;
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
<body>
	<div id="wrapper">
		<div id="header">
		<h3> Profile von :${bn.email}</h3> 
		Benutzer Name: ${bn.name}
		<br>
		anzahl erstellter Projekte:${SumOfCreatedProjects}
		<br>
		anzahl unterstützte Projekte:${SumOfSupporetedProjects}
		</div>

		<div id="site">
		
		 
    <h1> Erstellerte Projekte </h1>
           <#list UsertProjecstList as Projekt>
        <#if Projekt.ersteller == bn.email>
    <div id="list">
    <td>
     <img src="icons\${Projekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
    <br>
    <a href="projektDetails?kennung=${Projekt.kennung}" >${Projekt.titel}</a>
	<br>	finanzirungslimit:${Projekt.spendenbetrag}
	<br>	Status:${Projekt.status}
    </td>
    </div>
     <#else>
   </#if>
            </#list>
                     <h1> unterstützte Projekte </h1>
               <#list DonatedProjectsList as dProjekt>
               
               <#if bn.email == spender>
          <div id="list">
             <img src="icons\${dProjekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
     <p><a href="projektDetails?kennung=${dProjekt.kennung}" >${dProjekt.titel}</a></p>
        <br> Limit:${dProjekt.finanzierungslimit}
        <br> Status:${dProjekt.status}
        <br> Gespendet:${dProjekt.spendenbetrag}
		</div>
		</form>
	<#else>
         </#if>
              </#list>
   
		</div>
	</div>
</body>
</html>
