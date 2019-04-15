<?php

include 'conexion.php';
$fecha_viaje=$_GET['fecha_viaje'];

$consulta="select * from viajes where fecha_viaje = '$fecha_viaje'";
$resultado=$conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
$viajes[]=array_map('utf8_encode',$fila);
}

echo json_encode($viajes);
$resultado->close();
	
?>