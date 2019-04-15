<?php

include 'conexion.php';
$fecha_rep=$_POST['fecha_rep'];
$observaciones=$_POST['observaciones'];
$costo=$_POST['costo'];

$consulta="update reparaciones set observaciones=  '".$observaciones."',  costo=  '".$costo."'   where fecha_rep=  '".$fecha_rep."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>