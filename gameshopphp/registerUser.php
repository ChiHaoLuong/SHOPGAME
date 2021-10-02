<?php
    require_once 'userDBOperation.php';
    $fun = new USERSProgress();

    $username = $_GET['edtUsername'];
    $name = $_GET['edtName'];
    $password = $_GET['edtPassword'];

    $ketqua = $fun-> registerUser($username, $name, $password);

        echo json_encode(array('result'=>$ketqua));





?>