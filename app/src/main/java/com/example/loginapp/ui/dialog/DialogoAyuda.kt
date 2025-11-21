package com.example.loginapp.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoAyuda: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //1 Crea un builder
        val builder= AlertDialog.Builder(requireContext())
        // 3 Configuracion comportamiento cuadro (predefinido)
        // titulo
        builder.setTitle("Dialogo de ayuda")

        // contenido O lista
        //builder.setMessage("Esta es la ventana de ayuda")
        val opciones = arrayOf("Opcion 1","Opcion 2","Opcion 3")

        /*builder.setSingleChoiceItems(opciones,-1, DialogInterface.OnClickListener{view, pos-> })*/
        val arraySelection: BooleanArray = booleanArrayOf(true,false,false)

        builder.setMultiChoiceItems(opciones,arraySelection, DialogInterface.OnMultiChoiceClickListener{ view, pos, check-> })

        /*
        builder.setItems(opciones, DialogInterface.OnClickListener{view,pos-> }) */

        // botones = neutral, negativo, positivo (es la posicion)
        builder.setPositiveButton("No", DialogInterface.OnClickListener{view,pos-> })

        builder.setNeutralButton("Cancelar", DialogInterface.OnClickListener{view,pos-> })
        builder.setNegativeButton("Sí", DialogInterface.OnClickListener{view,pos-> })

        // si quiero cuadro personalizado seria con un vista nueva

        //2 crea cuadro dialogo
        return builder.create()



    }
}