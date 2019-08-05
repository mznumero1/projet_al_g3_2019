/*
*Rest web service
*Author MZ
*Since 03/07/2019
*/
var express = require('express');
var mysql = require('mysql');
var easyxml = require('easyxml');


//Configuration du parser xml
var xmlRenderer = new easyxml({
    singularize: true,
    rootElement: 'response',
    dateFormat: 'ISO',
    manifest: true
});

//Connexion a mysql
var con = mysql.createConnection({

    host:"localhost",
    user:"mglsi_user",
    password:"passer",
    database:"mglsi_news"
});

//Initialisation du serveur express
var app = express();

//Fonction permettant de choisir le type de reponse
app.use(function(req, res, next) {
  res.sendData = function(obj) {
    if (req.accepts('json') || req.accepts('text/html')) {
      res.header('Content-Type', 'application/json');
      res.json(obj);
    } else if (req.accepts('application/xml')) {
      res.header('Content-Type', 'text/xml');
      var xml = xmlRenderer.render(obj);
      res.send(xml);
    } else {
      res.send(406);
    }
  };
  next();
});

//Liste des articles
app.get("/rest/articles", (req, res, next) => {
    
    con.query("Select * from Article", function(err, result, fields){
        if(err) throw err;
        res.status(200).sendData(result);
    });

});

//Liste des  categories
app.get("/rest/categories", (req, res, next) =>{

    con.query("Select * from Categorie ", function(err, result, fields) {
        if(err) throw err;
        res.status(200).sendData(result);
    })
});

//Liste des articles d'unde categorie
 app.get("/rest/categories/:categorie/articles", (req, res, next) =>{

   var cat = req.params.categorie;
   con.query('Select * from Article where categorie = ?',[cat], function(err, result, fields) {
        if(err) throw err;
        res.status(200).sendData(result);
    })
});

//Demarrage du serveur
app.listen(8000, ()=> {
    console.log("Server running on localhost:8000");
})