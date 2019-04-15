<?php

include 'conexion.php';
$reparacion_id=$_POST['reparacion_id'];
$fecha_rep=$_POST['fecha_rep'];
$observaciones=$_POST['observaciones'];
$costo=$_POST['costo'];

$consulta="insert into reparaciones values('".$reparacion_id."','".$fecha_rep."','".$observaciones."','".$costo."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>