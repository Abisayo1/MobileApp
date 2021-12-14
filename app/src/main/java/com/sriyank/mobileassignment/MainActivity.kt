package com.sriyank.mobileassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sriyank.mobileassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.retrieve.setOnClickListener{

            val intent = Intent(this,ReadData::class.java)
            startActivity(intent)

        }

        binding.add.setOnClickListener {

            //if (binding.date.text.toString() !=)

            val name = binding.name.text.toString()
            val date = binding.date.text.toString()
            val time = binding.time.text.toString()
            val location = binding.location.text.toString()
            val description = binding.description.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Events")
            val User = User(name, date, location, time, description)
            database.child(name).setValue(User).addOnSuccessListener {
                binding.name.text.clear()
                binding.date.text.clear()
                binding.time.text.clear()
                binding.location.text.clear()
                binding.description.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}