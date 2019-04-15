<?php

include 'conexion.php';
$fecha_viaje=$_POST['fecha_viaje'];

$consulta="delete from viajes where fecha_viaje='".$fecha_viaje."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>