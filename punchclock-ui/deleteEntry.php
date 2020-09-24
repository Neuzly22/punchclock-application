<?php
session_start();
$curl = curl_init();
//Delete Entry with given ID
curl_setopt($curl, CURLOPT_URL, "http://localhost:8081/entries/" . $_POST['entryId']);
curl_setopt($curl, CURLOPT_HTTPHEADER,  array('Authorization: ' . $_SESSION['token']));
curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "DELETE");
$result = curl_exec($curl);
curl_close($curl);
header("Location: overview.php");
?>