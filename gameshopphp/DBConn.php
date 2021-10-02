<?php


    class DB_CONNECT{
        function __construct(){
            $this -> connect();
        }

        function _destruct(){
            $this -> close();
        }

        function connect(){
            // Create connection
            require_once __DIR__ .'/config.php';

            // Create connection
            $conn = new mysqli(DB_SEVER, DB_USER, DB_PASSWORD, DB_DATABASE);

            // Check connection
            if ($conn->connect_error) 
            {
                die("Connection failed: " .$conn->connect_error);
            }   
            return $conn;
        }
        function close(){
            // mysql_close();
        }
    }




?>