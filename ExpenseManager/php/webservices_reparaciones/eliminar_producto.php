<?php

include 'conexion.php';
$fecha_rep=$_POST['fecha_rep'];

$consulta="delete from reparaciones where fecha_rep='".$fecha_rep."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>