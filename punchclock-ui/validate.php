<?php
session_start();
$jsondata = json_encode($_POST);

$curl = curl_init();
curl_setopt($curl, CURLOPT_URL, "http://localhost:8081/login");
curl_setopt($curl, CURLOPT_POSTFIELDS, $jsondata);
curl_setopt($curl, CURLOPT_HTTPHEADER,  array('Content-Type:application/json'));
curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($curl, CURLOPT_HEADER, 1);
$response = curl_exec($curl);
curl_close($curl);


$header = get_response_header($response);

//Check if response contains Authorization entry
if(array_key_exists('Authorization', $header)){
    $_SESSION['token'] = $header['Authorization'];
    header("Location: overview.php");
   
}
else{
    //redirect to login page if Login is unsuccesful
    //$POST_['unsuccessful'] = 'Login was not successful, please try again';
    echo $_POST['unsuccessful'];
    header("Location: login.php");
    
}



function get_response_header($response)
{
    $headers = array();

    $header_text = substr($response, 0, strpos($response, "\r\n\r\n"));

    foreach (explode("\r\n", $header_text) as $i => $line)
        if ($i === 0)
            $headers['http_code'] = $line;
        else
        {
            list ($key, $value) = explode(': ', $line);

            $headers[$key] = $value;
        }

    return $headers;
}
?>