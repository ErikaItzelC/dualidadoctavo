<?php

include 'conexion.php';
$reparacion_id=$_POST['viaje_id'];
$fecha_viaje=$_POST['fecha_viaje'];
$millas=$_POST['millas'];
$pago=$_POST['pago'];

$consulta="insert into viajes values('".$reparacion_id."','".$fecha_viaje."','".$millas."','".$pago."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>