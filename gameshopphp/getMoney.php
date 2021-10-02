<?php
    require_once 'userDBOperation.php';
    $fun = new USERSProgress();
    
    $userId = $_GET['edtUserId'];
    
    $kq = $fun-> getMoney($userId);
    echo json_encode(array('result'=>$kq));
    


?>