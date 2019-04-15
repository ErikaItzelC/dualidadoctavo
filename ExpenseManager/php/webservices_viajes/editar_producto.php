<?php

include 'conexion.php';
$fecha_viaje=$_POST['fecha_viaje'];
$millas=$_POST['millas'];
$pago=$_POST['pago'];

$consulta="update viajes set millas=  '".$millas."',  pago=  '".$pago."'   where fecha_viaje=  '".$fecha_viaje."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>