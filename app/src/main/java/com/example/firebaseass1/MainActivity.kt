package com.example.firebaseass1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebaseass1.databinding.ActivityMainBinding

import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.progressBar2.visibility = View.GONE
        binding.btnshow.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }
        binding.btnsave.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            var name = binding.name.text.toString()
            var address = binding.address.text.toString()
            var number = binding.number.text.toString()

            if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(this, "The field is empty add data in field", Toast.LENGTH_SHORT)
                    .show()


            } else {

                val person = hashMapOf(
                    "number" to "$number",
                    "name" to "$name",
                    "address" to "$address"

                )
                db.collection("person").add(person).addOnSuccessListener { e ->
                    binding.progressBar2.visibility = View.GONE

                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
//                    this.name.text = this.age.text = this.id.text = "";

                   this.binding.number.text.clear()
                    this.binding.address.text.clear()
                    this.binding.name.text.clear()

                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                }
            }

        }


    }
}