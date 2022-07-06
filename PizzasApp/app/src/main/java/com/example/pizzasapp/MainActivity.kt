package com.example.pizzasapp

import android.app.Activity
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_dialog.*
import kotlinx.android.synthetic.main.edit_dialog.view.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var precioPizza = 0
    var precioTamaño = 0
    var tamaño = ""
    var pizza = ""
    var extraQueso = "No seleccionado"
    var extraPep = "No seleccionado"
    var orillaRe = "No seleccionado"
    var direccion = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.nombre)

        spnTipoPizza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (spnTipoPizza.selectedItem.toString()) {
                    "Pepperoni" -> {
                        imgPizza.setImageResource(R.drawable.peroni)
                        cbExtraPep.setText("Extra Pepperoni")
                        tvPrecioChica.setText("$75")
                        tvPrecioMediana.setText("$90")
                        tvPrecioGrande.setText("$100")
                        tvPrecioFamiliar.setText("$120")
                        pizza = "Pepperoni"
                    }
                    "Hawaiana" -> {
                        imgPizza.setImageResource(R.drawable.hawaiana)
                        cbExtraPep.setText("Extra Jamon")
                        tvPrecioChica.setText("$65")
                        tvPrecioMediana.setText("$70")
                        tvPrecioGrande.setText("$80")
                        tvPrecioFamiliar.setText("$100")
                        pizza = "Hawaiana"
                    }
                    "Vegetariana" -> {
                        imgPizza.setImageResource(R.drawable.vegetariana)
                        cbExtraPep.setText("Extra Jitomate")
                        tvPrecioChica.setText("$55")
                        tvPrecioMediana.setText("$62")
                        tvPrecioGrande.setText("$75")
                        tvPrecioFamiliar.setText("$90")
                        pizza = "Vegetariana"
                    }
                    "Mexicana" -> {
                        imgPizza.setImageResource(R.drawable.mexicana)
                        cbExtraPep.setText("Extra Picante")
                        tvPrecioChica.setText("$70")
                        tvPrecioMediana.setText("$80")
                        tvPrecioGrande.setText("$90")
                        tvPrecioFamiliar.setText("$100")
                        pizza = "Mexicana"
                    }
                    "Mariscos" -> {
                        imgPizza.setImageResource(R.drawable.mariscos)
                        cbExtraPep.setText("Extra Camarones")
                        tvPrecioChica.setText("$85")
                        tvPrecioMediana.setText("$100")
                        tvPrecioGrande.setText("$120")
                        tvPrecioFamiliar.setText("$140")
                        pizza = "Mariscos"
                    }
                    else -> {
                        return
                    }
                }


                rbChica.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        rbGrande.setChecked(false)
                        rbChica.setChecked(true)
                        rbMediana.setChecked(false)
                        rbFamiliar.setChecked(false)
                        precioPizza -= precioTamaño
                        precioTamaño = 0
                        precioTamaño = tvPrecioChica.text.toString().replace("$", "").toInt()
                        precioPizza += precioTamaño
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        tamaño = "Chica"

                    }
                })

                rbMediana.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        rbGrande.setChecked(false)
                        rbChica.setChecked(false)
                        rbMediana.setChecked(true)
                        rbFamiliar.setChecked(false)
                        precioPizza -= precioTamaño
                        precioTamaño = 0
                        precioTamaño = tvPrecioMediana.text.toString().replace("$", "").toInt()
                        precioPizza += precioTamaño
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        tamaño = "Mediana"
                    }
                })

                rbGrande.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        rbGrande.setChecked(true)
                        rbChica.setChecked(false)
                        rbMediana.setChecked(false)
                        rbFamiliar.setChecked(false)
                        precioPizza -= precioTamaño
                        precioTamaño = 0
                        precioTamaño = tvPrecioGrande.text.toString().replace("$", "").toInt()
                        precioPizza += precioTamaño
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        tamaño = "Grande"
                    }
                })

                rbFamiliar.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {

                        rbGrande.setChecked(false)
                        rbChica.setChecked(false)
                        rbMediana.setChecked(false)
                        rbFamiliar.setChecked(true)
                        precioPizza -= precioTamaño
                        precioTamaño = 0
                        precioTamaño = tvPrecioFamiliar.text.toString().replace("$", "").toInt()
                        precioPizza += precioTamaño
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        tamaño = "Familiar"
                    }
                })

//                  INGREDIENTES
                cbExtraQueso.setOnClickListener {
                    if (cbExtraQueso.isChecked()) {
                        precioPizza += tvPrecioExtraQueso.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        extraQueso = tvPrecioExtraQueso.text.toString()
                    } else {
                        precioPizza -= tvPrecioExtraQueso.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        extraQueso = "No seleccionado"
                    }
                }
                cbExtraPep.setOnClickListener {
                    if (cbExtraPep.isChecked()) {
                        precioPizza += tvPrecioExtraPeperoni.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        extraPep = tvPrecioExtraPeperoni.text.toString()
                    } else {
                        precioPizza -= tvPrecioExtraPeperoni.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        extraPep = "No seleccionado"
                    }
                }
                cbOrillaRellena.setOnClickListener {
                    if (cbOrillaRellena.isChecked()) {
                        precioPizza += tvPrecioOrillaRellena.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        orillaRe = tvPrecioOrillaRellena.text.toString()
                    } else {
                        precioPizza -= tvPrecioOrillaRellena.text.toString().replace("$", "").toInt()
                        tvPrecioTotal.setText("Total a pagar: $${precioPizza}")
                        orillaRe = "No seleccionado"
                    }
                }

            }

        }
//        ALERTDIALOG
        btnPedir.setOnClickListener {
            if (rbChica.isChecked() == false && rbMediana.isChecked() == false && rbGrande.isChecked() == false && rbFamiliar.isChecked() == false) {
                Toast.makeText(this, "SELECCIONA UN TAMAÑO", Toast.LENGTH_SHORT).show()
            } else {
                val dialog = AlertDialog.Builder(this)
                        .setTitle("ORDENAR PIZZA")
                        .setMessage("Su pedido es:\n\nPizza de Tipo ${pizza} \nTamaño de la Pizza ${tamaño} ---------> $${precioTamaño}\n\n" +
                                "                       INGREDIENTES\nExtra Queso ---------> ${extraQueso}\n${cbExtraPep.getText()} ---------> ${extraPep}\n" +
                                "Orilla Rellena ---------> ${orillaRe}\n\n${tvPrecioTotal.text}")
                        .setNegativeButton("Cancelar") { view, _ ->
                            view.dismiss()
                        }
//                    positivo
                        .setPositiveButton("Proceder") { view, _ ->
                            alertDialog()
                        }
                        .setCancelable(false)
                        .create()

                dialog.show()
            }
        }

    }

//          ALERTDIALOG

//    fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return this?.let {
//        val builder = AlertDialog.Builder(this)
//        // Get the layout inflater
//        val inflater = startActivity().layoutInflater
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.edit_dialog, null))
//                // Add action buttons
//                .setPositiveButton("Mandar Pedido") { view, _ ->
////                                   singUp()
//                }
//                .setNegativeButton("Cancelar") { view, _ ->
//
//                    Toast.makeText(this, "SE CANCELO EL PEDIDO", Toast.LENGTH_SHORT).show()
//                    view.dismiss()
//                }
//        builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
    fun alertDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.edit_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("DIRECCIÓN")
                .setPositiveButton("Mandar Pedido") { view, _ ->
                    direccion = dialogView.etDialogDireccion.text.toString()
                    if (direccion.isEmpty())
                        Toast.makeText(this, "FAVOR DE PONER SU DIRECCIÓN", Toast.LENGTH_SHORT).show()
                    else{
                           singUp()

                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS, android.Manifest.permission.SEND_SMS),111)
                        }else{
                            recibirSMS()
                        }
                    }
                }
                .setNegativeButton("Cancelar") { view, _ ->

                    Toast.makeText(this, "SE CANCELO EL PEDIDO", Toast.LENGTH_SHORT).show()
                    view.dismiss()
                }
        dialogBuilder.show()

    }



    //    MANDAR DATOS A LA BASE DE DATOS
    private fun singUp(){
        val URL = "http://192.168.56.1/pizza/agregar_pizza.php"
        val queue = Volley.newRequestQueue(this)
        val tipoPizza = pizza
        val tamañop = tamaño
        val extraChess = extraQueso
        val extraIngrediente = extraPep
        val orilla = orillaRe
        val totalPagar = precioPizza
        val direccionp = direccion


        val stringRequest = object : StringRequest(Method.POST,URL,
                Response.Listener{ response ->
                    Toast.makeText(this,"OPERACIÓN EXITOSA", Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        }){
            @Throws(AuthFailureError::class)
            override fun getParams() : Map<String, String>{
                val params = HashMap<String, String>()
                params.put("tipo_pizza",tipoPizza)
                params.put("tamanio",tamañop)
                params.put("extra_queso",extraChess)
                params.put("extra_ingrediente",extraIngrediente)
                params.put("orilla_rellena",orilla)
                params.put("total_pagar",totalPagar.toString())
                params.put("direccion",direccionp)
                return params
            }
        }
        queue.add(stringRequest)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            recibirSMS()
        }
    }

    private fun recibirSMS() {
        var obj = SmsManager.getDefault()
        obj.sendTextMessage("4361012648", null, "welcome",null,null)
    }


}
