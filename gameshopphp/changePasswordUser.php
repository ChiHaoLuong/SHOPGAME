<?php
    require_once 'userDBOperation.php';
    $fun = new USERSProgress();

    $userId = $_GET['edtUserId'];
    $oldpass = $_GET['edtOldpass'];
    $newpass = $_GET['edtNewpass'];

    $kq = $fun -> changepassword($userId,$oldpass, $newpass);
    
  
        echo  json_encode(array('result'=>$kq));



?>