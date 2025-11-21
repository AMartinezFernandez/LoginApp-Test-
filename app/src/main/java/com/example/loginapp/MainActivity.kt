package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.loginapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

lateinit var binding: ActivityMainBinding
private lateinit var auth: FirebaseAuth;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias();
        acciones();

    }

    private fun instancias(){
        auth = FirebaseAuth.getInstance();
    }
    private fun acciones() {
        binding.google.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.mail.text.toString(),
                binding.contrasena.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.root, "Usuario creado", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(binding.root, "Usuario NO creado", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.botonLogin.setOnClickListener {
            //analiza los datos de la interfaz-> procesar los datos
            val correo = binding.mail.text.toString()
            val contraseña = binding.contrasena.text.toString()
            val recordar = binding.checkRecordar.isChecked
            val plataforma = binding.plataforma.selectedItem.toString()

            if (correo.isEmpty() || contraseña.isEmpty()) {
                Snackbar.make(it, "Datos vacios, rellena todos los campos", Snackbar.LENGTH_SHORT)
                    .show()
            } else {

               auth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener {
                   if (it.isSuccessful){
                       val intent = Intent (this, SecondActivity::class.java)

                       intent.putExtra("uid", auth.currentUser!!.uid.toString())

                       intent.putExtra("pass", contraseña)

                       intent.putExtra("correo", correo)

                       startActivity (intent)
                   }else{
                       Snackbar.make(binding.root, "Fallo de credenciales", Snackbar.LENGTH_SHORT).show()
                   }
               }
            }
        }
        binding.plataforma.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.google.visibility = View.INVISIBLE
                binding.git.visibility = View.INVISIBLE
                binding.facebook.visibility = View.INVISIBLE

                when (position) {
                    0 -> binding.google.visibility = View.VISIBLE
                    1 -> binding.facebook.visibility = View.VISIBLE
                    2 -> binding.git.visibility = View.VISIBLE
                    else -> { /* todos ocultos */ }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }
}