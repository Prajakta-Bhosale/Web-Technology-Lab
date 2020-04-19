<!-- Prajakta Bhosale TECOB201-->
<?php
	$username="root";
	$password="";
	$dbname="Prajakta";
	$con=new mysqli("localhost",$username,$password,$dbname) or die("unable to connect");
	
	$name=$_POST["name"];		
	$email=$_POST["email"];
	$password=$_POST["password"];

	$Insert = isset($_POST['Insert']) ? $_POST['Insert'] : '';
	$Delete = isset($_POST['Delete']) ? $_POST['Delete'] : '';	
	$Select = isset($_POST['Select']) ? $_POST['Select'] : '';	
	$Update = isset($_POST['Update']) ? $_POST['Update'] : '';	

	
	if($con->connect_error)
	{
		echo "Connection Error <br>";
	}
	else
	{
		echo "Connection established <br>";
	}
	
	if(($Insert)==TRUE)
	{
		$insertquery="insert into Student values('$name','$email','$password')";
		if($con->query($insertquery)==TRUE)
		{
			echo "insertion successful<br>";
		}
		else
			echo "insertion failed<br>";
	
	}
	
	if(($Delete)==TRUE)
	{	
		$deletequery="delete from Student where name='$name'";
		if($con->query($deletequery)==TRUE)
		{
			echo "deletion successful<br>";
		}
		else
			echo "deletion failed<br>";
	}	
	
	if(($Select)==TRUE)
	{	
	
		$selectquery="select name,email,password from Student ";
		$result = $con->query($selectquery);
		
		if($result->num_rows >0)
		{
			while($row = $result->fetch_assoc()) 
			{
       			 	echo "Name : " . $row["name"]. "  Email : " . $row["email"]."  Password : " . $row["password"]. "<br>";
    			}
		
		
		}
		else 
		{
   			 echo "0 results";
		}
	}
	
	if(($Update)==TRUE)
	{	
		
		$updatequery="update Student set password='$password' where name='$name'";
		if($con->query($updatequery)==TRUE)
		{
			echo "updation successful";
		}
		else
			echo "updation failed";
	}		

?>
