<?php
    require_once 'userDBOperation.php';
    $fun = new USERSProgress();

    $username = $_GET['edtUsername'];
    $password = $_GET['edtPassword'];


    $users = $fun -> checkLogin($username, $password);
    if($users!=null)
    {
        if($users=="Sai mật khẩu"||$users=="Email không tồn tại")
        {
            echo json_encode(array('result'=>$users));
        }
        else
        {
            echo json_encode(array('userId'=>$users["userId"], 
            'username'=>$users["username"],
            'name'=>$users["name"],
            'password'=>$users["password"],
            'money'=>$users["money"],
            'result'=>"OK"
        
            ));
        }
    }



?>