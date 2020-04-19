<!-- Prajakta Bhosale TECOB201-->
<?php

header("Access-Control-Allow-Origin: *");   
header("Content-Type: application/json; charset=UTF-8");  
$conn = new mysqli("localhost", "root", "","Prajakta");   
$result = $conn->query("SELECT name, email,password FROM Student");     

$outp = "";
while($rs = $result->fetch_array(MYSQLI_ASSOC)) 
{
    if ($outp != "") {$outp .= ",";}
     $outp .= '{"name":"'  . $rs["name"] . '",';
     $outp .= '"email":"'. $rs["email"]     . '",';
     $outp .= '"password":"'. $rs["password"]     . '"}';
}
$outp ='{"records":['.$outp.']}';
$conn->close();

echo($outp);

?>
