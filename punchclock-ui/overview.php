<?php
    session_start();
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:8081/entries/all");
    curl_setopt($curl, CURLOPT_HTTPHEADER,  array('Authorization: ' . $_SESSION['token']));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($curl);
    curl_close($curl);

    $foundEntries = json_decode($response)

?>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    </head>
    <body>

    <div class="container">
    <div>
        Entries:
    </div>


<?php

        foreach($foundEntries as $key => $value) {
            $foundEntries[$key] = (array) $value;?>
            <div class="single-entry row">
                <div class="entry-id entry-data col"><?= $foundEntries[$key]['id']?></div>
                <div class="entry-checkin entry-data col"><?= $foundEntries[$key]['checkIn']?></div>
                <div class="entry-checkout entry-data col"><?= $foundEntries[$key]['checkOut']?></div>

            </div>


            <?php }
?>
</div>
</body>
</html>