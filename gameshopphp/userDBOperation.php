<?php

    require('include/PHPMailer.php');
    require('include/SMTP.php');
    require('include/Exception.php');

    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\STMP;
    use PHPMailer\PHPMailer\Exception;

    class USERSProgress{
        private $host = 'localhost';
        private $user = 'root';
        private $db = 'shopgame';
        private $pass = '';
        private $conn;
        private $mail;
        

        public function __construct() {

            $this -> conn = new PDO("mysql:host=".$this -> host.";dbname=".$this -> db, $this -> user, $this -> pass);

            $this -> mail = new PHPMailer();
        }


        public function registerUser($username, $name, $password) {
         
            $sqlcheck = "SELECT * FROM users WHERE username = '$username'";
            $querycheck = $this -> conn -> query($sqlcheck);
            $data = $querycheck -> fetchObject();
            if ($data) return "Email đã tồn tại";
            else
            {            
                $sql = "INSERT INTO `users`(`username`, `name`, `password`, `money`) VALUES ('$username', '$name', '$password', 200)";
                $query = $this->conn->query($sql);
                if($query)
                {
                    return "Đăng ký thành công";
                }
                else  return "Đăng ký thất bại";
            }
        }

        public function changepassword($userId, $oldpassword, $newpassword)
        {
            $sqlcheckpass = "SELECT * FROM users WHERE userId = '$userId'";
            $querycheckpass = $this -> conn -> query($sqlcheckpass);
            $data = $querycheckpass-> fetchObject();
            if ($data)
            {
                if($oldpassword==$newpassword) return "Mật khẩu cũ và mới giống nhau";
                else 
                {
                    $sqlupdatepass= "UPDATE users SET password= $newpassword WHERE userId = $data->userId";
                    $updatepassquery = $this -> conn -> query($sqlupdatepass);
                    if($updatepassquery)
                    {
                        $mail_result = $this -> sendEmail($data->username, $data->password);
                        return "Cập nhật mật khẩu thành công";
                    }
                    else return "Cập nhật mật khẩu thất bại";
                }
            }
            else return "Sai mật khẩu cũ";
        }

    

    public function checkLogin($username, $password) {

        $sql = "SELECT * FROM users WHERE username = '$username'";
        $query = $this -> conn -> query($sql);
        $data = $query -> fetchObject();
        if($data)
        {
            
            if ($password == $data->password) {
        
        
                $user["name"] = $data -> name;
                $user["username"] = $data -> username;
                $user["userId"] = $data -> userId;
                $user["money"] = $data -> money;
                $user["password"] = $data->password;
                return $user;
        
            } 
            else 
            {
        
                return "Sai mật khẩu";
            }
        }
        else return "Email không tồn tại";
    }



     public function resetPasswordRequest($username){
        $sql = "SELECT * FROM users WHERE username = '$username'";
        $query = $this -> conn -> query($sql);
        $data = $query -> fetchObject();
      
        if ($data) {
      
          $result =  $this -> passwordResetRequest($username);
      
          if(!$result){
            return "Reset password thất bại";
      
          } 
          else 
          {
      
            $mail_result = $this -> sendEmail($result["username"],$result["password"]);
      
            if($mail_result){
              return "Reset password thành công ! Vui lòng kt email";
      
            } 
            else 
            {
      
                
              return "Reset password thất bại ! Vui lòng kt email";
            }
          }
      
      
        } else {
      

          return "Email không tồn tại";
      
        }
      
      }

      public function passwordResetRequest($username){
        $random_string = substr(str_shuffle(str_repeat("0123456789abcdefghijklmnopqrstuvwxyz", 6)), 0, 6);
        $sql = "SELECT COUNT(*) from users WHERE username ='$username'";
        $query = $this -> conn -> query($sql);
    
        if($query)
        {
            $row_count = $query -> fetchColumn();
    
            if ($row_count == 0){
                return "Email không tồn tại";
            } 
            else 
            {
                $update_sql = "UPDATE users SET password='$random_string' WHERE username = '$username'";
                $update_query = $this -> conn -> query($update_sql);
    
                if ($update_query) {
    
                    $user["username"] = $username;
                    $user["password"] = $random_string;
                    return $user;
    
                } else {
    
                    return false;
                }
            }
        } 
        else 
        {
            return "Email không tồn tại";
        }
    
     }

     public function getMoney($userId){
 
        $sql = "SELECT * FROM users WHERE userId = '$userId'";
        $query = $this -> conn -> query($sql);
        $data = $query -> fetchObject();
        if ($data)
        {
            $newmoney = $data -> money + 20;
            $sqlGetMoney = "UPDATE users GET money = '$newmoney' WHERE userId = '$data-> userId'";
            $kq = $this-> conn -> query($sqlGetMoney);
            if ($kq)
            {
                echo "Đã thêm tiền thành công";
            }
            else echo "Thêm thất bại";
        }
        else "Không tìm được tài khoản";

     }

     public function sendEmail($username,$password){

        $mail = $this -> mail;
        $mail->isSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'haolcps14498@fpt.edu.vn';
        $mail->Password = 'hahaha113';
        $mail->SMTPSecure = 'ssl';
        $mail->Port = "465";
      
        $mail->SetFrom("haolcps14498@fpt.edu.vn");
        $mail->addAddress($username);
      
        $mail->addReplyTo('haolcps14498@fpt.edu.vn', 'Your Name');
      
        $mail->WordWrap = 50;
        $mail->isHTML(true);
      
        $mail->Subject = 'Shop game notification';
        $mail->Body    = 'Hi,<br><br> Your new password is <b>'.$password.'</b> . Please login your account by new password.';
      
        if(!$mail->send()) {
      
         return $mail->ErrorInfo;
      
        } else {
      
          return true;
      
        }
      
      }




}






