<?php

include 'conexion.php';
$fecha_rep=$_GET['fecha_rep'];

$consulta="select * from reparaciones where fecha_rep = '$fecha_rep'";
$resultado=$conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
$reparaciones[]=array_map('utf8_encode',$fila);
}

echo json_encode($reparaciones);
$resultado->close();
	
?>