<?php
include 'top.php';
?>
<main>
    <p>Create Table SQL</p>
    
<pre>
CREATE TABLE tblShopHours(
    pmkShopHoursId INT AUTO_INCREMENT PRIMARY KEY,
    fldDayofWeek VARCHAR(10),
    fldOpen VARCHAR(10),
    fldClose VARCHAR(10)
);
INSERT INTO tblShopHours
(fldDayofWeek,fldOpen,fldClose)
VALUES
('Monday','Closed','Closed'),
('Tuesday','Closed','Closed'),
('Wednesday','6:00 am','2:00 pm'),
('Thursday','6:00 am','2:00 pm'),
('Friday','6:00 am','2:00 pm'),
('Saturday','6:00 am','2:00 pm'),
('Sunday','6:00 am','2:00 pm');


</pre>
    <h3>Select data for display</h3>
    <pre>
SELECT fldDayofWeek, fldOpen, fldClose FROM tblShopHours;
    </pre>
    
    <h3>Create table to save forms data</h3>
        <pre>
CREATE TABLE tblCakeOrders (
        pmktblCakeOrdersId INT AUTO_INCREMENT PRIMARY KEY,
        fldFirstName varchar(50),
        fldLastName varchar(50),
        fldEmail varchar(50),
        fldType varchar (50),
        fldVanilla boolean,
        fldChocolate boolean,
        fldStrawberry boolean,
        fldMaple boolean,
        fldSprinkle varchar(20),
        fldSI text
);
</pre>

<h3>Populate table with data</h3>
        <pre>
        INSERT INTO tblCakeOrders (fldFirstName, fldLastName, fldEmail, fldType, fldVanilla, fldChocolate, fldStrawberry, fldMaple, fldSprinkle, fldSI) VALUES
        ('Will', 'Jackson', 'example@gmail.com', '1', '1', '1', '1', '0', 'Yellow', 'Half and Half');
</pre>

<h3>Select data for display</h3>
        <pre>
SELECT fldFirstName, fldLastName, fldEmail, fldType, fldVanilla, fldChocolate, fldStrawberry, fldMaple, fldSprinkle, fldSI FROM tblCakeOrders
        </pre>
</main>
<?php
include 'footer.php';
?>

