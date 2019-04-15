<?php

include 'conexion.php';
$diesel_id=$_POST['diesel_id'];
$fecha=$_POST['fecha'];
$litros=$_POST['litros'];
$precio=$_POST['precio'];

$consulta="insert into diesel values('".$diesel_id."','".$fecha."','".$litros."','".$precio."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>