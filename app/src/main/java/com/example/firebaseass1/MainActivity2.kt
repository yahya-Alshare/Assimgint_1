package com.example.firebaseass1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.firebaseass1.databinding.ActivityMain2Binding
import com.example.firebaseass1.databinding.ActivityMainBinding


class MainActivity2 : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.btndelet.setOnClickListener {
            var results = false;
            binding.progressBar.visibility = View.VISIBLE
            db.collection("person")
                .get()
                .addOnSuccessListener { result ->


                    for (document in result) {
                        db.collection("person").document(document.id).delete()
                            .addOnSuccessListener { e ->


                            }
                        getData()
                        binding.progressBar.visibility = View.GONE


                    }


                }
        }

        binding.btnadd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }




        getData()


    }

    fun getData() {

        db.collection("person")
            .get()
            .addOnSuccessListener { result ->
                binding.progressBar.visibility = View.GONE
                val arrayAdapter: ArrayAdapter<*>
                val users = ArrayList<String>()
                for (document in result) {
                    var name = document.get("name").toString()
                    var number = document.get("number").toString()
                    var address = document.get("address").toString()
                    val result =
                        "The  Data is :id =${document.id}/ the name=$name / number:$number / address:$address \n"
                    users.add(result)


                }
                // access the listView from xml file
                var mListView = findViewById<ListView>(R.id.userlist)
                arrayAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, users
                )
                mListView.adapter = arrayAdapter
            }
            .addOnFailureListener { exception ->
            }

    }
}