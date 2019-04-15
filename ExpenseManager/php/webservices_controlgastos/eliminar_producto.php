<?php

include 'conexion.php';
$fecha=$_POST['fecha'];

$consulta="delete from diesel where fecha='".$fecha."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>