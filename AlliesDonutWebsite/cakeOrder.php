<?php
include 'top.php';

function getData($field)
{
    if (!isset($_POST[$field])) {
        $data = "";
    } else {
        $data = trim($_POST[$field]);
        $data = htmlspecialchars($data);
    }
    return $data;
}

function verifyAlphaNum($testString)
{
    return (preg_match("/^([[:alnum:]]|-|\.| |\'|&|;|#)+$/", $testString));
}

$sprinkleList = array('Yellow', 'Blue', 'Dark Green', 'Light Green', 'Purple', 'Pink', 'Brown', 'Red', 'White');

$dataIsGood = false;

$firstName = '';
$lastName = '';
$email = '';
$type = '';
$vanilla = false;
$chocolate = false;
$strawberry = false;
$maple = false;
$sprinkle = '';
$SI = '';

?>
<main>
    <article>
        <h1 class="article-header">Cake Order Form</h1>
        <section class="debug">
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                $dataIsGood = true;
                // Server side sanitization
                $firstName = getData("txtFirstName");
                $lastName = getData("txtLastName");
                $email = filter_var($_POST['txtEmail'], FILTER_SANITIZE_EMAIL);
                $type = getData("radCake");
                $vanilla = (int) getData("chkVanilla");
                $chocolate = (int) getData("chkChocolate");
                $strawberry = (int) getData("chkStrawberry");
                $maple = (int) getData("chkMaple");
                $sprinkle = getData("lstsprinkle");
                $SI = getData("txtSI");

                //validate data
                if ($firstName == '') {
                    print '<p class="mistake">Please enter your first name.</p>';
                    $dataIsGood = false;
                } elseif (!verifyAlphaNum($firstName)) {
                    print '<p class="mistake">Your first name appears to have extra character that are not allowed. Please use letters and numbers only.</p>';
                    $dataIsGood = false;
                }

                if ($lastName == '') {
                    print '<p class="mistake">Please enter your last name.</p>';
                    $dataIsGood = false;
                } elseif (!verifyAlphaNum($lastName)) {
                    print '<p class="mistake">Your last name appears to have extra character that are not allowed. Please use letters and numbers only.</p>';
                    $dataIsGood = false;
                }

                if ($email == '') {
                    print '<p class="mistake">Please enter your email.</p>';
                    $dataIsGood = false;
                } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                    print '<p class="mistake">Your email address appears to be incorrect.</p>';
                    $dataIsGood = false;
                }

                if ($type != "Allies's Donut Cake Large" and $type != "Allies's Donut Cake Extra Large" and $type != "Allies's Novelty Cake Large" and $type != "Allies's Novelty Cake Extra Large") {
                    print '<p class="mistake">Please choose your cake size.</p>';
                    $dataIsGood = false;
                }

                $totalChecked = $vanilla + $chocolate + $strawberry + $maple;
                if ($totalChecked < 1) {
                    print '<p class="mistake">Please choose a frosting.</p>';
                    $dataIsGood = false;
                }

                if (!in_array($sprinkle, $sprinkleList)) {
                    print '<p class = "mistake">Please choose a sprinkle color</p>';
                    $dataIsGood = false;
                }

                if ($SI == '') {
                    print '<p class="mistake">Please enter a comment.</p>';
                    $dataIsGood = false;
                } elseif (!verifyAlphaNum($SI)) {
                    print '<p class="mistake">Your comments appears to have extra character that are not allowed. Please use letters and numbers only.</p>';
                    $dataIsGood = false;
                }

                //save the data
                if ($dataIsGood) {
                    try {
                        $sql = 'INSERT INTO tblCakeOrders (fldFirstName, fldLastName, fldEmail, fldType, fldVanilla, fldChocolate, fldStrawberry, fldMaple, fldSprinkle, fldSI) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';

                        $statement = $pdo->prepare($sql);

                        $params = array($firstName, $lastName, $email, $type, $vanilla, $chocolate, $strawberry, $maple, $sprinkle, $SI);

                        if ($statement->execute($params)) {
                            print '<p>(Thank you your information has been recived.)</p>';
                            $to = $email;
                            $frosting = '';
                            if ($chocolate){
                              $frosting .= 'Chocolate, ';
                            };
                            if ($strawberry){
                              $frosting .= 'Strawberry, ';
                            };
                            if ($maple){
                              $frosting .= 'Maple, ';
                            };
                            if ($vanilla){
                              $frosting .= 'Vanilla, ';
                            };
                            $subject = 'Your Cake Order - Allie\'s Donuts';
                            $headers = "From: " . strip_tags($_POST['req-email']) . "\r\n";
                            $headers .= "Reply-To: ". strip_tags($_POST['req-email']) . "\r\n";
                            $headers .= "MIME-Version: 1.0\r\n";
                            $headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";
                            $message = '<html><body><p>Thank you for ordering a cake from Allie\'s Donut Shop.</p> <table style="border:thick solid red;">';
                            $message .= '<tr style="color:navy;"><td>First Name: </td><td>' . $firstName .'</td></tr>';
                            $message .= '<tr><td>Last Name: </td><td>'. $lastName .'</td></tr>';
                            $message .= '<tr style="color:navy;">><td>Email: </td><td>' .$email .'</td></tr>';
                            $message .= '<tr><td>Cake Size: </td><td>' . $type .'</td></tr>';
                            $message .= '<tr style="color:navy;">><td>Frosting: </td><td>' . $frosting .'</td></tr>';
                            $message .= '<tr><td>Sprinkles: </td><td>'. $sprinkle .'</td></tr>';
                            $message .= '<tr style="color:navy;">><td>Special Instructions: </td><td>' . $SI .'</td></tr></table></body></html>';
                            mail($to, $subject, $message, $headers);

                        } else {
                            print '<p>(There was a sever problem, please try again later.)</p>';
                        }
                    } catch (PDOException $e) {
                        print '<p>Couldn\'t insert the record, please conatact someone.</p>';
                    } //end try
                } // ends data is good

            }
            ?>
        </section>
        <form action="#" id="frmRegister" method="post" class="form1 fontsform">

            <fieldset class="contact">
                <legend>Contact Information</legend>
                <p>
                    <label class="required" for="txtFirstName">First Name</label>
                    <input id="txtFirstName" maxlength="30" name="txtFirstName" onfocus="this.select()" tabindex="100" type="text" value="<?php print $firstName; ?>" required>
                </p>

                <p>
                    <label class="required" for="txtLastName">Last Name</label>
                    <input id="txtLastName" maxlength="30" name="txtLastName" onfocus="this.select()" tabindex="110" type="text" value="<?php print $lastName; ?>" required>
                </p>

                <p>
                    <label class="required" for="txtEmail">Email</label>
                    <input id="txtEmail" maxlength="30" name="txtEmail" onfocus="this.select()" tabindex="110" type="email" value="<?php print $email; ?>" required>
                </p>
            </fieldset> <!-- ends contact -->

            <fieldset class="radio">
                <legend>What type of donut cake would you like?</legend>
                <p>
                    <input type="radio" id="radCakeTypeRL" name="radCake" value="Allies's Donut Cake Large" tabindex="210" <?php if ($type == "Allies's Donut Cake Large") print 'checked'; ?> required>
                    <label class="radio-field" for="radCakeTypeRL">Allies's Donut Cake (Large)</label>
                </p>

                <p>
                    <input type="radio" id="radCakeTypeRXL" name="radCake" value="Allies's Donut Cake Extra Large" tabindex="230" <?php if ($type == "Allies's Donut Cake Extra Large") print 'checked'; ?> required>
                    <label class="radio-field" for="radCakeTypeRXL"> Allies's Donut Cake (Extra Large)</label>
                </p>

                <p>
                    <input type="radio" id="radCakeTypeNL" name="radCake" value="Allies's Novelty Cake Large" tabindex="240" <?php if ($type == "Allies's Novelty Cake Large") print 'checked'; ?> required>
                    <label class="radio-field" for="radCakeTypeNL"> Allies's Novelty Cake (Large)</label>
                </p>

                <p>
                    <input type="radio" id="radCakeTypeNXL" name="radCake" value="Allies's Novelty Cake Extra Large" tabindex="240" <?php if ($type == "Allies's Novelty Cake Extra Large") print 'checked'; ?> required>
                    <label class="radio-field" for="radCakeTypeNXL"> Allies's Novelty Cake (Extra Large)</label>
                </p>

            </fieldset>

            <fieldset class="checkbox">
                <legend>What frosting flavors would you like? (choose at least one and check all that apply):</legend>

                <p>
                    <input id="chkVanilla" name="chkVanilla" tabindex="310" type="checkbox" value="1" <?php if ($vanilla) print 'checked'; ?>>
                    <label for="chkVanilla">Vanilla</label>
                </p>

                <p>
                    <input id="chkChocolate" name="chkChocolate" tabindex="320" type="checkbox" value="1" <?php if ($chocolate) print 'checked'; ?>>
                    <label for="chkChocolate">Chocolate</label>
                </p>

                <p>
                    <input id="chkStrawberry" name="chkStrawberry" tabindex="330" type="checkbox" value="1" <?php if ($strawberry) print 'checked'; ?>>
                    <label for="chkStrawberry">Strawberry</label>
                </p>

                <p>
                    <input id="chkMaple" name="chkMaple" tabindex="340" type="checkbox" value="1" <?php if ($maple) print 'checked'; ?>>
                    <label for="chkMaple">Maple</label>
                </p>
            </fieldset>

            <fieldset class="listbox">

                <legend>Sprinkle Color</legend>
                <p>
                    <select id="lstsprinkle" name="lstsprinkle" tabindex="420">
                        <option value="Yellow" <?php if ($location == 'Yellow') print 'selected'; ?>>Yellow</option>
                        <option value="Blue" <?php if ($location == 'Blue') print 'selected'; ?>>Blue</option>
                        <option value="Dark Green" <?php if ($location == 'Dark Green') print 'selected'; ?>>Dark Green</option>
                        <option value="Light Green" <?php if ($location == 'Light Green') print 'selected'; ?>>Light Green</option>
                        <option value="Purple" <?php if ($location == 'Purple') print 'selected'; ?>>Purple</option>
                        <option value="Pink" <?php if ($location == 'Pink') print 'selected'; ?>>Pink</option>
                        <option value="Brown" <?php if ($location == 'Brown') print 'selected'; ?>>Brown</option>
                        <option value="Red" <?php if ($location == 'Red') print 'selected'; ?>>Red</option>
                        <option value="White" <?php if ($location == 'White') print 'selected'; ?>>White</option>

                    </select>
                </p>
            </fieldset>

            <fieldset class="textarea">
            <legend>Special Instructions</legend>
                <p>
                    <textarea id="txtSI" name="txtSI" tabindex="500" required><?php print $SI; ?></textarea>
                </p>
            </fieldset>

            <fieldset class="buttons">
                <input id="btnSubmit" name="btnSubmit" tabindex="900" type="submit" value="Order">
            </fieldset>

        </form>
    </article>
</main>
<?php
include 'footer.php';
?>