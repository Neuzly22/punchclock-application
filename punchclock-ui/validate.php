<?php

$jsondata = json_encode($_POST);

$curl = curl_init();
curl_setopt($curl, CURLOPT_URL, "http://localhost:8081/login");
curl_setopt($curl, CURLOPT_POSTFIELDS, $jsondata);
curl_setopt($curl, CURLOPT_HTTPHEADER,  array('Accept: application/json'));
curl_setopt($curl, CURLOPT_HEADER, 1);
$response = curl_exec($curl);
curl_close($curl);
?>