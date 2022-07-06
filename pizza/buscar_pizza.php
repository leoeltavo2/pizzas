<?php
include 'conexion.php';
// $id=$_POST['id'];
//$id='17121073';

$consulta="select * from pedido";
$resultado=$conexion->query($consulta);

while ($fila=$resultado->fetch_array()) {
  $pedido[]= array_map('utf8_encode',$fila);
}

echo json_encode($pedido);

$resultado->close();
?>
