<?php

require_once('vendor/autoload.php');


use \Firebase\JWT\JWT;


$publicKey = <<<EOD
-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8kGa1pSjbSYZVebtTRBLxBz5H
4i2p/llLCrEeQhta5kaQu/RnvuER4W8oDH3+3iuIYW4VQAzyqFpwuzjkDI+17t5t
0tyazyZ8JXw+KgXTxldMPEL95+qVhgXvwtihXC1c5oGbRlEDvDF6Sa53rcFVsYJ4
ehde/zUxo6UvS7UrBQIDAQAB
-----END PUBLIC KEY-----
EOD;



//$jwt = JWT::encode($token, $privateKey, 'RS256');
//echo "Encode:\n" . print_r($jwt, true) . "\n";

$jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJkZXZOYW1lIjoiUG9ydGFsRGV2ZWxvcGVyOCIsImlhdCI6MTU0MDUzMjY5NywiZXhwIjoxNTQwNTMzMjk3fQ.uj-Kb8l7sKCIr-9rDuLvWuHifflVdEjjg9-Dfv9gPKmXeaOmoJeAR9LE-idSAPCefMqeMrWOM5YMXPcqkH9TUdI2sfC6GOWMAgchB07pjGMu_9f18WsvzN7tHrarCTCugxOfFHQtS6khNrkjiZSAq9tLb0p8LnqFgfvnN2m543w";

 $decoded = JWT::decode($jwt, $publicKey, array('RS256'));

// /*
//  NOTE: This will now be an object instead of an associative array. To get
//  an associative array, you will need to cast it as such:
// */

$decoded_array = (array) $decoded;
echo "Decode:\n" . print_r($decoded_array, true) . "\n";
?>