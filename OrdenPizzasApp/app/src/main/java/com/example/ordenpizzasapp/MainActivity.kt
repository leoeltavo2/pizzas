package com.example.ordenpizzasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private lateinit var pointsAdapterView: AdaptadorPedido
    val pedido = ArrayList<Pedido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buscarRoute()

        btnActualizar.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }

    private fun buscarRoute(){


        val URL = "http://192.168.56.1/pizza/buscar_pizza.php"
        val queue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Method.POST,URL,
            Response.Listener{response->
                if(!response.isEmpty()) {
                    val jsonArray = JSONArray(response)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = JSONObject(jsonArray.getString(i))

                        val tipo_pizaa = jsonObject.get("tipo_pizza").toString()
                        val tamaño = jsonObject.get("tamanio").toString()
                        val extra_queso = jsonObject.get("extra_queso").toString()
                        val extra_ingrediente = jsonObject.get("extra_ingrediente").toString()
                        val orilla_rellena = jsonObject.get("orilla_rellena").toString()
                        val total_pagar = jsonObject.get("total_pagar").toString()
                        val direccion = jsonObject.get("direccion").toString()

                        pedido.add(Pedido(tipo_pizaa, tamaño, extra_queso, extra_ingrediente, orilla_rellena, total_pagar, direccion))
                    }

                    pointsAdapterView = AdaptadorPedido(pedido)

                    recycler.adapter = pointsAdapterView
                    recycler.layoutManager = LinearLayoutManager(this)
                    recycler.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                }else{
                    pointsAdapterView = AdaptadorPedido(pedido)

                    recycler.adapter = pointsAdapterView
                    recycler.layoutManager = LinearLayoutManager(this)
                    recycler.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                }

            },Response.ErrorListener {error ->
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        }){
            @Throws(AuthFailureError::class)
            override fun getParams() : Map<String, String>{
                val params = HashMap<String, String>()
//                params.put("driver",userViewModel.id)
                return params
            }
        }
        queue.add(stringRequest)
    }





}