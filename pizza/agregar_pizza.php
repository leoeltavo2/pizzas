<?php
  include 'conexion.php';

  $tipo_pizza=$_POST['tipo_pizza'];
  $extra_queso=$_POST['extra_queso'];
  $extra_ingrediente=$_POST['extra_ingrediente'];
  $orilla_rellena=$_POST['orilla_rellena'];
  $direccion=$_POST['direccion'];
  $tamanio=$_POST['tamanio'];
  $total_pagar=$_POST['total_pagar'];

  // $tipo_pizza="ha";
  // $extra_queso="s";
  // $extra_ingrediente="ee";
  // $orilla_rellena="ddd";
  // $direccion="kakaka";
  // $tamanio="t";
  // $total_pagar=12212;

  $consulta="insert into pedido values(null, '".$tipo_pizza."','".$tamanio."','".$extra_queso."','".$extra_ingrediente."','".$orilla_rellena."','".$total_pagar."','".$direccion."')";
  mysqli_query($conexion,$consulta) or die (mysqli_error());
  mysqli_close($conexion);
?>
