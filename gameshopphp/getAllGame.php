<?php
    require_once "gameDBOperation.php";
    $fun = new GAMESProgress();

    $kq = $fun->getAllGame();
    if ($kq)
    {
        json_encode($kq);
    }


?>