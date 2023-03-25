<html>
<title> Projekt Spende </title>
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
<h1>${kennung}</h1>
<body>
  <form action="/New_Project_Fund" method="POST"> 
    Spendenbetrag(€€): 
    <input type="number" min="1.00" size="7" " name="spendenbetrag" >
    <input type="checkbox" name="sichtbarkeit" value="privat">anonym ?<br>
       <input type="hidden" name="kennung" value=${kennung}>
<button class="button" type="submit" >Spenden</button> 
              ${feedback}
  </form>
</body>
</html>