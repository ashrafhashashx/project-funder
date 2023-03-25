	<html>
<style>
div {
  display: inline-block; 
  background-color: lightgrey;
  width: 200px;
  border: 15px solid blue;
  padding: 50px;
  margin: 10px;
}
</style>
<body> 
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
.button1 {background-color: #008CBA;} 
</style>
  <form method="GET" action="ProjektErstellen">
 <button class="button" type="submit" >Create new Project</button>
 		</form>
	   <form action="view_profile">
	  <input type="hidden" value=${loggedInUser} name="email" />
	  <input type="submit" value="show user profile">
	  </form>
	  
	   	  	<form action="/search" method="GET">
 
         <input type="search" id="mySearch" required name="stitel"
         placeholder="Search for projects...">
         <button>Search</button>
 
		</td>
     </p>  <p>
         <h1> Offnene Projekte </h1>
           <#list Projekts as Projekt>
        <#if Projekt.status == "offen">
    <div>
    <img src="icons\${Projekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
    <p>
    <a href="projektDetails?kennung=${Projekt.kennung}">${Projekt.titel}</a>
      SpendenSumme::<p>${Projekt.spendenbetrag}</p>
	<a href="view_profile?email=${Projekt.ersteller}">${Projekt.ersteller}</a>
        </div>
     <#else>
   </#if>
            </#list>
             <h1> abgeschlossene Projekte </h1>
               <#list Projekts as Projekt>
        <#if Projekt.status == "geschlossen">
        <div>
    <img src="icons\${Projekt.kategorie}.png"
    alt="Image description" style="width:88px;height:88px;border:25">
    <p>
    <a href="projektDetails?kennung=${Projekt.kennung}">${Projekt.titel}</a>
      SpendenSumme::<p>${Projekt.spendenbetrag}</p>
	<a href="view_profile?email=${Projekt.ersteller}">${Projekt.ersteller}</a>
        </div>
     <#else>
   </#if>
            </#list>
</body>
</html>
