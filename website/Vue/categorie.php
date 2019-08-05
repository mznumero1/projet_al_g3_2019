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
			
		<?php


		foreach ($articles as $article)
		{?>
			<div class="article">
				<h1><a href="/projetNews/articles-<?= $article->id ?>"><?= $article->titre ?></a></h1>
				<p><?= substr($article->contenu, 0, 300) . '...' ?></p>
		</div><?php
		}

		
	?>
	</div>
	<?php 
		if (isset($_SESSION['articles'])) 
		{
			$articles=$_SESSION['articles'];
			$categories = $_SESSION['categories']; ?>
			<div id="menu">
				<h1>Catégories</h1><hr style="width=20%;">
				<ul>
					<li><a href="/projetNews/articles">Tout</a></li>
					<?php foreach ($categories as $categorie): ?>
					<li><a href="/projetNews/categorie-<?= $categorie->id ?>"><?= $categorie->libelle ?></a></li>
					<?php endforeach ?>
				</ul>
			</div>
		<?php
		} 
	?>
</body>
</html>