<?php
$phpSelf = htmlspecialchars($_SERVER['PHP_SELF']);
$path_parts = pathinfo($phpSelf);
?>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title> Salmon Bear's AWTO </title>
    <meta name="author" content="Theodore Ruth">
    <meta name="description" content="Home page of the Salmon Bears Auto Water Top Off">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Description is 84 characters -->
    <link rel="stylesheet" href="css/custom.css?version=<?php print time(); ?>" type="text/css">
    <link rel="stylesheet" media="(max-wdith: 800px)" href="css/custom-tablet.css?version=<?php print time(); ?>" type="text/css">
    <link rel="stylesheet" media="(max-wdith: 600px)" href="css/custom-mobile.css?version=<?php print time(); ?>" type="text/css">
</head>
<?php
print '<body id="' . $path_parts['filename'] . '">' ;
include 'header.php';
print PHP_EOL;
include 'nav.php';
print PHP_EOL;
?>