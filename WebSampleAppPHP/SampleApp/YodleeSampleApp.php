<?php

require_once('jwt/vendor/autoload.php');

use \Firebase\JWT\JWT;

error_reporting(E_ALL ^ E_ALL);
ob_end_flush();
session_start();
$ini_array = parse_ini_file(__DIR__."/myapp.ini");

$url = $ini_array["BASE_URL"];
$apiKey = $ini_array["API_KEY"];
$node_url = $ini_array["NODE_URL"];
$linked_acc_url = $ini_array["BASE_URL"].$ini_array["GET_ACCOUNTS_URL"];
$trans_url = $ini_array["BASE_URL"].$ini_array["GET_TRANSACTIONS_URL"];
$cobrand = $ini_array["COBRAND_NAME"];
$apiVersion=$ini_array["API_VERSION"];
$allDataSet = "";

$privateKey = <<<EOD
-----BEGIN RSA PRIVATE KEY-----
MIIEowIBAAKCAQEAzeORV6Z6wlJk9EJ4LC4FYh6Qpd+ZNtNT4irYnoSS3bzkJMHI
B3Kva5K9P0Sn4NUwOl3uCiDilCfSkYpOkUa0zFqVqbFo9jnqA2fykZbE1YEA0mG5
qEHENa4h42yUvMbzkjUx2xgR0kxTypr4ao3M/ZC3lA3wTf6J28A0vSmZnwIGEcFG
gHsVXSSsiq6RHrd3HXp+qjMEZNLOProJ3bEI4Q631St4/8zcW9BcgNpnyo0QIVYQ
zRuFcCuvhYGl0oHvU569n+CpJq+cdcf8d/iZjDMw+1oHEmK0JAXME+4qOy8JnyDi
IqGwcFA5dgxcPmQ4sUwFaORxPKJUQmz8g6So8wIDAQABAoIBABE2cEU6ptKuHsCI
+hD/e/W7Bm7HpcB+pGmpGVkPTlP5Yb1s8fMldGLy7p1meP6D9Dc8+RShsS+fsKv4
Ox9MIPtgq1h3e4XyS40jWJUxXJ1Y3p+4CdR2QxgVycGAp2Ks41oa1mMxvPOu6+Zp
VFFcL4IXYjGfhLKQkCfuBrq1yqEPXK48Nh3S7D815QpmobakRNRiLlV6kyoAR5aN
dYfi0hB6Fdy9cCosLMHW2AD+kzlT6naqRZhv1NjWRgjmLe2YRhSVA7ZXHKxonAQ3
hvHRpJ0eC0WVmcyI9MYmDpMOCPhaqBzvuh3f55uBiJ5R5ARj5sivFdPoxraUAbxF
JJZUvbkCgYEA/TK30Hj/Jqus2CoPZZ8NmLdfk6M7YMk0+xZwcSWSLeHSDkAPvySY
6PrdvXDz+g/w3Ej9HlakqYDcmACvTzX7HzhCXITWhlCV7e5BemxesmXzBMwq9HwP
3RKKrtit2l3iipYMeMaUjHWVlm9DScyQY2mkNRrfbuxN1WCqRNJWzM8CgYEA0CrT
/hZjHmRd+sVHvoagUKeogSq3IVCanr21Soi54zTec4f3AWiew8Q1Nc0C342D3oAN
PgdhugdduPESH9yTWZ82DVAsPm0BUZqQOBC2ni2OOt96m29eChX5a/S3aO/dmUQ9
VtQtZ3nnoEbR7w/CBfBVsisKuhvfTCxuV0d1kp0CgYA5I+cDryq1yWx6ZBORdXGW
E1wIb4AI+Z9xQsEhZ78zh5DmEmL7WgEAIw1DoWgN+Ob8b6Rf4mHKtwARdaYalC4V
/5ZKVtgq+6i6R1iTjj1YuVU8se7Hue/Cjlqp1tB1RVbIu9AYuYfwouCdUIK49udm
6AJc0bG20LFIBEaovWdFVQKBgBn+vypjsU0CS5odOLBaL3ri9O/sXaITZrucBR+h
o1T0LTNQL1BFuE3KGrr1Nv6jtWv7oau1UEbi1JlbwV+pM91lPGK9OEkzY47FNm1J
6QQnvDMt5Smz5pJr3BHXJXA9Q/b3xpEpPeJGHt6s+VpZJWmIcasmJNjI0kG46q6L
heshAoGBAMqoaEOqHGHxyWTX4+85yUnSdTIEXSslHBygT/GsTxIRCpCsZhuVf+qK
t8nJ9Xt3RcRingW57nXPMaELIvZ8P+X/5Q+pn4SNEGmgNuRwIEpQ/3U4QsWlfAct
23eoVV7Eh8ngASrhZdjv1NiE3/0exsbdBlOGvr4oIQlXzviMZArq
-----END RSA PRIVATE KEY-----
EOD;

//print_r($_GET);
//var_dump($_REQUEST);
if (isset($_GET["action"]) && !empty($_GET["action"]))
{
    $action = $_GET["action"];
    $jwtToken=$_SESSION['jwtToken'];
            if($jwtToken != null && strlen($jwtToken) > 0)
            {
                if($action === 'getAccounts')
                {
                    $getAccounts = getUserAccounts($linked_acc_url,$cobrand,$apiVersion,$jwtToken);
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($getAccounts);

                }
                if($action === 'getFastLinkToken')
                {

                    $data = ['jwtToken'=>$jwtToken,'nodeUrl'=>$node_url,'dataset'=>$allDataSet];
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($data);

                }
                if($action === 'getTransactions')
                {
                    $accountId = $_GET['accountId'];
                    $getTransactions = getTransactions($trans_url, $accountId,$cobrand,$apiVersion,$jwtToken);
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($getTransactions);


                }
                if($action === 'deleteAccount')
                {
                    $accountId = $_GET['accountId'];
                    echo "this is the number of account:$accountId";
                    $deleteAccount = deleteAccounts($linked_acc_url, $accountId,$cobrand,$apiVersion,$jwtToken);
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($deleteAccount);

                }
            }

}
if (isset($_POST) && !empty($_POST)) {

    $userLogin = $_POST["username"];
    $issuer=$apiKey;

    $iat = time()-90;
    $exp = strtotime("+10 minutes");

    $token = array(
        "iat" => $iat,
        "exp" => $exp,
        "iss"=> $issuer,
        "sub"=>$userLogin
    );

    $jwt = JWT::encode($token, $privateKey, 'RS512');

    $_SESSION['jwtToken']="Bearer ".$jwt;

    $jwtToken=$_SESSION['jwtToken'];

    $getAccounts = getUserAccounts($linked_acc_url, $cobrand,$apiVersion,$jwtToken);


    if ($getAccounts!=null) {

        $data = ['error' => 'false', 'message' => 'User authentication successfull.'];
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($data);

    } else {
        $data = ['error' => 'true', 'message' => 'Error in user Login, Invalid user credentials.'];
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($data);
    }
}



    function getUserAccounts($url,$cobrand,$apiVersion,$token)
        {
            $linked_acc_url = $url;
            $jwtToken= 'Authorization:'.$token ;
            $ch2 = curl_init($url);
            curl_setopt($ch2, CURLOPT_URL, $linked_acc_url);
            curl_setopt($ch2, CURLOPT_HEADER, 0);
            curl_setopt($ch2, CURLOPT_HTTPHEADER, array('Content-Type:application/json', 'Cobrand-Name:'.$cobrand, 'Api-Version:'.$apiVersion, $jwtToken));
            curl_setopt($ch2, CURLOPT_RETURNTRANSFER, 1);
            $response = curl_exec($ch2);
            curl_close($ch2);
            $errorDetails = checkForError($response);
            if (!empty($errorDetails)) {
                echo "error in the function";
            } else {
                return $response;

            }
        }


function getTransactions($url, $accountId,$cobrand,$apiVersion,$token)
{
    $newDate = date("Y-m-d", strtotime("-3 month"));
    //echo $newDate;
    $jwtToken= 'Authorization:'.$token ;

    $transactions_acc_url = $url.'?fromDate='.$newDate.'+&accountId='.$accountId;
    //echo $userTokenSession;
    $ch3 = curl_init($url);
    curl_setopt($ch3, CURLOPT_URL, $transactions_acc_url);
    curl_setopt($ch3, CURLOPT_HEADER, 0);
    curl_setopt($ch3, CURLOPT_HTTPHEADER, array('Content-Type:application/json', 'Cobrand-Name:'.$cobrand, 'Api-Version:'.$apiVersion, $jwtToken));
    curl_setopt($ch3, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch3);
    curl_close($ch3);
      //print_r($response);
    //echo("<script>console.log('Transactions response:".$response."');</script>");


    $errorDetails = checkForError($response);
    if (!empty($errorDetails)) {
        echo "error in the function";
    } else {
        return $response;
    }

}
function deleteAccounts($url, $accountId,$cobrand,$apiVersion, $token)
{
    $jwtToken= 'Authorization:'.$token ;
    $transactions_acc_url = $url.$accountId;
    $ch3 = curl_init($transactions_acc_url);
    curl_setopt($ch3, CURLOPT_URL, $transactions_acc_url);
    curl_setopt($ch3, CURLOPT_HEADER, 0);
    curl_setopt($ch3, CURLOPT_CUSTOMREQUEST, 'DELETE');
    curl_setopt($ch3, CURLOPT_HTTPHEADER, array('Cobrand-Name:'.$cobrand, 'Api-Version:'.$apiVersion, $jwtToken));
    curl_setopt($ch3, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch3);
    curl_close($ch3);
    //echo "in delete account\n\n";
   // print_r($response);
    echo "\n\n";
    $errorDetails = checkForError($response);
    if (!empty($errorDetails)) {
        echo "error in the function";
    } else {
        return $response;
    }

}


    function checkForError($response)
    {
        $body = $response;
        $responseObj = parseJson($body);
        if (!empty($responseObj['errorCode']))
            return $responseObj;
        else
            return null;
    }

    function parseJson($json)
    {
        return json_decode($json, true);
    }

    ?>
