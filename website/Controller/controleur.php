<?php 

	/*
	Ce fichier assure la liaison entre les vues et le modele
	*/

	session_start();

	define("INDEX", "index.php");
	define("LIST", "../Vue/listArticles.php");
    define("ARTICLE", "../Vue/article.php");
    define("CATEGORIE", "../Vue/categorie.php");
    define("MODEL", "../Model/model.php");

	if (isset($_REQUEST['action'])) {
        include MODEL;

		$action = $_GET['action'];
        $bdd = dbConnect();
		switch ($action) {
            case 'listArticles': 
                if (isset($_GET['page'])) {
                    $page = $_GET['page'];
                }else{
                    $page = 1;
                }
                if ($bdd != null)
                {
                    echo "page".$page;
                    $_SESSION['articles'] = getList($bdd, $page);
                    $_SESSION['categories'] = getList($bdd, $page, 'Categorie');
				    include("../Vue/listArticles.php");
                }
				break;
            case 'article': 
                if (isset($_GET['id'])) {
                    $id = $_GET['id'];
                    $article = getItem($bdd, $id);
                    include(ARTICLE);
                }
				break;
			case 'categorie': 
                if  (isset($_GET['id']))
                {
                    $catId = (int) $_GET['id'];
                    $articles = getItemByCategorie($bdd, $catId);
                    include(CATEGORIE);
                }
                break;
            case 'login': 
                if(isset($_POST) && !empty($_POST['username']) && !empty($_POST['pass'])) {
                    extract($_POST);
                    $sql = "select password from Utilisateur where username='".$username."'";
                    $role = "select role from Utilisateur where username='".$username."'";
                    $req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>');
                  
                    $data = mysql_fetch_assoc($req);
                  
                    if($data['password'] != $pass) {
                      echo '<p>Mauvais username / password. Merci de recommencer</p>';
                      include("../Vue/login.php");
                      exit;
                    }
                    else {
                      session_start();
                      $_SESSION['login'] = $username;
                      $_SESSION['role'] = $role;
                      echo 'Vous etes bien connecté'; 
                      include("../Vue/listArticles.php");
                    }    
                  }
                  else {
                    echo '<p>Vous avez oublié de remplir un champ.</p>';
                    include("../Vue/login.php");
                    exit;
                  }
                break;
            case 'editer': 
                if(!isset($_SESSION['login'])) {
                    echo 'Vous n\'êtes pas autoris´ à acceder à cette zone';
                    include('../Vue/login.php');
                    exit;
                } 
                else {
                    include('../Vue/editer.php');
                }
                break;
            case 'admin': 
                if(!isset($_SESSION['login'])) {
                    echo 'Vous n\'êtes pas autoris´ à acceder à cette zone';
                    include('../Vue/login.php');
                    exit;
                } 
                else {
                    if($_SESSION['role']!='admin') {
                        echo 'Vous n\'êtes pas autoris´ à acceder à cette zone';
                        include('../Vue/login.php');
                        exit;
                    }
                    else {
                        include('../Vue/admin.php');
                    }
                }
                break;
            case 'ajouter': 
                if(isset($_POST) && !empty($_POST['titre']) && !empty($_POST['contenu']) && !empty($_POST['categorie'])) {
                    extract($_POST);
                    $sql = "INSERT INTO Article (titre, contenu, categorie) VALUES ('".$titre."', '".$contenue."', '".$categorie."')";
                    $req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>');
                
                    include("../Vue/listArticles.php");  
                }
                break;
            case 'supprimer': 
                if (isset($_GET['id'])) {
                    $id = $_GET['id'];
                    $sql = "delete password * from Article where id='".$id."'";
                    include("../Vue/listArticles.php");
                }
				break;
			default: 
				header("location:".INDEX);
				break;
		}
	}else{ 
        header("location:".INDEX);
    }	
 ?>