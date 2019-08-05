/*
*Restful web service
*Author MZ
*Since 03/07/2019
*/
var express = require('express');
var mysql = require('mysql');
var session = require('express-session');
var bodyParser = require('body-parser');
var format = require('xml');
var path = require('path');
var con = mysql.createConnection({

    host:"localhost",
    user:"mglsi_user",
    password:"passer",
    database:"mglsi_news"
});

var app = express();
app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());


//Liste des articles
app.get("/rest/articles", (req, res, next) => {
    
    con.query("Select * from Article", function(err, result, fields){
        if(err) throw err;
        res.json(result);
    });

});

//liste des  categories
app.get("/rest/categories", (req, res, next) =>{

    con.query("SELECT * FROM Categorie ", function(err, result, fields) {
        if(err) throw err;
        res.json(result);
    })
});

//liste des articles d'unde categorie
 app.get("/rest/categories/:categorie/articles", (req, res, next) =>{

    console.log(req.params.categorie);
   con.query('SELECT * FROM Article WHERE categorie = ?',[req.params.categorie], function(err, result, fields) {
        if(err) throw err;
        res.json(result);
    })
});

//demarrage
app.listen(8000, ()=> {
    console.log("Server running on localhost:8000");
})