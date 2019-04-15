<?php

include 'conexion.php';


$consulta2="update suma set suma_viajes=(select distinct total from viaje_suma)";
$consulta="select suma_viajes from suma";

$resultado=$conexion -> query($consulta);

mysqli_query($conexion,$consulta2) or die (mysqli_error());
mysqli_close($conexion);
while($fila=$resultado->fetch_array()){
$suma[]=array_map('utf8_encode',$fila);
}

echo json_encode($suma);
$resultado->close();




	
?>