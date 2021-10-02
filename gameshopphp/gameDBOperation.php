<?php
    require_once 'DBConn.php';
   
    class GAMESProgress{
    private $db;


    public function __construct() {

         $db = new DB_CONNECT();

   
    }
    
    public function getAllGame(){
        $db = new DB_CONNECT();
        $conn = $db->connect();
        $sql = "SELECT * FROM games";
        $kq = $conn-> query($sql);

        $response = array();
        if($kq->num_rows>0)
        {
            $response['games'] = array();
            while($row = $kq->fetch_assoc())
            {
                $game = array();
               $game['gameId'] = $row['gameId'];
               $game['name'] = $row['name'];
               $game['price'] = $row['price'];
               $game['describle'] = $row['describle'];
               $game['developer'] = $row['developer'];
               $game['categoryId'] = $row['categoryId'];
               $game['rating'] = $row['rating'];
               $game['img'] = $row['img'];
               array_push($response['games'], $game);
            }
            $response['result'] = "Success";
        }
        else $response['result'] = "fail";

        echo json_encode($response);
        
    }

}

?>