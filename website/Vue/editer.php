<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Actualités MGLSI</title>
	<link rel="stylesheet" type="text/css" href="vue/design/style.css">
</head>
<body>
<div id="entete">
			<h1>Site d'actualité du MGLSI</h1>
			<!-- <hr> -->
		</div>
	<div id="contenu">
        <form action="/projetNews/ajouter.php" method='post'>
            <table style="align=center; border=0;">
                <tr>
                    <td>Titre :</td>
                    <td><input type="text" name="titre" maxlength="255"></td>
                </tr>
                <tr>
                    <td>Contenu</td>
                    <td><input type="text"name="contenu"></td>
                </tr>
                <tr>
                    <td>Cotegorie</td>
                    <td><input type="text"name="cotegorie"></td>
                </tr>
                <tr>
                    <td colspan="2" style="align=center;"><input type="submit" value="Se connecter"></td>
                </tr>
            </table>
        </form>
	</div>
</body>
</html>

