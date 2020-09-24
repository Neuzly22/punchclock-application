<?php
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:8081/entries/all");
    curl_setopt($curl, CURLOPT_HTTPHEADER,  array('Content-Type:application/json'));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_HEADER, 1);
    $response = curl_exec($curl);
    curl_close($curl);
?>