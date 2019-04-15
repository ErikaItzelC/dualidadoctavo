<?php

include 'conexion.php';
$fecha=$_POST['fecha'];
$litros=$_POST['litros'];
$precio=$_POST['precio'];

$consulta="update diesel set litros=  '".$litros."',  precio=  '".$precio."'   where fecha=  '".$fecha."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
	
?>