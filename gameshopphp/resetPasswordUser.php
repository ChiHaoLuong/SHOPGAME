<?php

require_once 'userDBOperation.php';
    $fun = new USERSProgress();

    $username = $_GET['edtUsername'];

    $kq = $fun -> resetPasswordRequest($username);
    
  
        echo  json_encode(array('result'=>$kq));




?>