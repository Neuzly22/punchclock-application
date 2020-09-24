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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <div>
            <h2> Entries </h2>
            <div class="row" style="font-weight:bold;">
                <div class="col-3">ID</div>
                <div class="col-3">Check-In Time</div>
                <div class="col-3">Check-Out Time</div>
            </div>
        </div>


<?php

        foreach($foundEntries as $key => $value) {
            $foundEntries[$key] = (array) $value;?>
            <div class="single-entry row">
                <div class="entry-id entry-data col-3"><?= $foundEntries[$key]['id']?></div>
                <div class="entry-checkin entry-data col-3"><?= $foundEntries[$key]['checkIn']?></div>
                <div class="entry-checkout entry-data col-3"><?= $foundEntries[$key]['checkOut']?></div>
                <form action="deleteEntry.php" method="post">
                    <input type="hidden" name="entryId" value="<?=$foundEntries[$key]['id']?>">
                    <button type="submit">delete</button>
                </form>

            </div>


            <?php }
?>
</div>
</body>
</html>