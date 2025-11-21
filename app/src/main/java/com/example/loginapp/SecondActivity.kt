package com.example.loginapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.adapters.productoAdapter
import com.example.loginapp.databinding.ActivitySecondBinding
import com.example.loginapp.model.producto
import com.example.loginapp.ui.dialog.DialogoAyuda
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit  var correo: String
    lateinit var lista: ArrayList<producto>
    lateinit var adapter: productoAdapter
    lateinit var uid: String;
    lateinit var pass: String
    lateinit var dataBase: FirebaseDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        // creamos los metodos
        instancias()
        recuperarDatos()
        asociarDatos()
    }

    private fun instancias() {
        dataBase= FirebaseDatabase.getInstance("https://uaxandroid-default-rtdb.asia-southeast1.firebasedatabase.app/")

        lista = arrayListOf(
            producto("Pizza", "Italiana", R.drawable.facebook),
            producto("Hamburguesa", "US", R.drawable.burger),
            producto("Paella", "Española", R.drawable.paella),
            producto("Sushi", "Japonesa", R.drawable.facebook),
            producto("Pizza", "Italiana", R.drawable.facebook),

        )
        adapter = productoAdapter(lista)
    }

    private fun recuperarDatos() {
        correo = intent.getStringExtra("correo")!!
        pass = intent.getStringExtra("pass")!!
        uid = intent.getStringExtra("uid")!!
        binding.textoCorreo.text = correo
       val userRef = dataBase.reference.child("usuarios").child(uid)
        userRef.child("correo").setValue(correo)
        userRef.child("uid").setValue(uid)
    }


        private fun asociarDatos() {
        binding.listaProductos.adapter = adapter
        // ultimo como se muestran los objetos: linear (horizont, vert), grid, staggered
        if (resources.configuration.orientation == 1) {
            binding.listaProductos.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL, false
            )
        } else{
            binding.listaProductos.layoutManager = GridLayoutManager(
                applicationContext, 2,
                LinearLayoutManager.VERTICAL, false
        )
    }
}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(
            R.menu.main_menu,
            menu
        )//es la referencia al XML del menú creado en: res/menu/main_menu.xml.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_ayuda -> {
               val dialogo = DialogoAyuda()
                dialogo.show(supportFragmentManager,null)
                true
            }
            R.id.item_cerrar -> {
                Log.v("menu", "pulsado Salir")
                finish()
                true
            }
            R.id.item_insertar -> {
                dataBase.reference.child("nombreApp").setValue("ua")
            }

            R.id.item_borrar -> {
                dataBase.reference.child("nombreApp").setValue(null)

            }

            R.id.item_consulta -> {
                dataBase.reference.child("usuarios").child(uid).addChildEventListener (object : ChildEventListener{
                    override fun onChildAdded(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        Log.v("datos", "los datos han cambiado")
                        Log.v("datos",snapshot.value.toString())
                        for (i in snapshot.children){
                            Log.v("datos", i.toString())
                        }
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            }
        }
        return true

    }
}

//R es una clase autogenerada por Android Studio (en tiempo de compilación).
//	•	Contiene referencias únicas a todos los recursos de tu app: layouts, strings, drawables, menús, IDs, etc.