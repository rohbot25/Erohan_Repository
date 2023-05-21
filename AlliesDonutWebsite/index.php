<?php
include 'top.php'
?>
    <main>
    <p>Data from SQL shown in table below</p>
    <figure>
    <img class='top' src='images/logo2.jpg' alt='allielogo'>
    <figcaption><cite><a href='https://www.facebook.com/AlliesDonuts/'>Allie's Donuts</a></cite></figcaption>
    </figure>
        <p>Stop by and enjoy one or maybe a dozen of our famous handmade donuts, made the same way since 1968. We are open 5 days a week (Wednesday-Sunday) and our wonderful staff is dedicated to providing the best experience possible for YOU. We provide a wide assortment of donuts including season specials, check our menu page for a quick peak at what we offer. We also sell an assortment of coffees and other drinks. If one donut is just too small, fill out an order form and enjoy one of our delicious donut cakes. Our doors are recently reopened so stop on by and say hello.</p>
        <table>
            <caption>Hours of Operation</caption>
        <?php 
               $sql = 'SELECT fldDayofWeek, fldOpen, fldClose FROM tblShopHours';
               $statement = $pdo->prepare($sql);
               $statement->execute();
               
               $records = $statement->fetchAll();
               print('<!--before foreach-->');
               foreach ($records as $record) {
                   print('<!--within foreach-->');
                   print '<tr>';
                   print '<td>' . $record['fldDayofWeek'] . '</td>';
                   print '<td>' . $record['fldOpen'] . '</td>';
                   print '<td>' . $record['fldClose'] . '</td>';
                   print '</tr>' . PHP_EOL;
               }
               ?>
        </table>
        <p>
        </p>
    </main>
<?php
include 'footer.php'
?>

