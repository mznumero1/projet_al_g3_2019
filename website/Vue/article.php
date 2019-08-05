<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Affichage d'un article</title>
	<link rel="stylesheet" type="text/css" href="vue/design/style.css">
</head>
<body>

	<div id="contenu">
		<div id="entete">
			<h1>Site d'actualité du MGLSI</h1>
			<!-- <hr> -->
		</div>
			<div>
				<h1><?= $article->titre ?></h1>
				<span>Publié le <?= $article->dateCreation ?></span>
				<p><?= $article->contenu ?></p>
				<a href="/projetNews/ajouter">Supprimer</a>
				<a href="/projetNews/supprimer">Supprimer</a>
			</div>
			
	</div>
	<?php 
		if (isset($_SESSION['articles'])) 
		{
			$articles=$_SESSION['articles'];
			$categories = $_SESSION['categories']; ?>
			<div id="menu">
				<h1>Catégories</h1><hr width="20%">
				<ul>
				<li><a href="/projetNews/articles">Tout</a></li>
					<?php foreach ($categories as $categorie): ?>
					<li><a href="/projetNews/categorie-<?= $categorie->id ?>"><?= $categorie->libelle ?></a></li>
					<?php endforeach ?>
				</ul>
			</div>
		<?php
		} ?>
</body>
</html>