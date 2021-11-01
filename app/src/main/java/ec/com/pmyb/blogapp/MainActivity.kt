package ec.com.pmyb.blogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        //Consulta Informacion
//        db.collection("ciudades").document("LA").get().addOnSuccessListener {
//            document->
//            document?.let {
//                val color =document.getString("color")
//                val population =document.getLong("population")
//                Log.d("Firebase", "Population: $population")
//                Log.d("Firebase", "Color: $color")
//            }
//        }.addOnFailureListener {
//            error->
//            Log.e("FireBaseError", error.toString())
//        }
    }
}