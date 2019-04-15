<?php

include 'conexion.php';
$fecha=$_GET['fecha'];

$consulta="select * from diesel where fecha = '$fecha'";
$resultado=$conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
$diesel[]=array_map('utf8_encode',$fila);
}

echo json_encode($diesel);
$resultado->close();
	
?>