package com.sriyank.mobileassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sriyank.mobileassignment.databinding.ActivityUpdateDataBinding

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.create.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.updateBtn.setOnClickListener {

            val name = binding.name.text.toString()
            val date = binding.date.text.toString()
            val time = binding.time.text.toString()
            val location = binding.location.text.toString()
            val description = binding.description.text.toString()

            updateData(name,date,time,location,description)

        }

    }

    private fun updateData(name: String, date: String, time: String, location: String, description: String) {

        database = FirebaseDatabase.getInstance().getReference("Events")
        val user = mapOf<String,String>(
            "name" to name,
            "date" to date,
            "time" to time,
            "location" to location,
            "description" to description
        )

        database.child(name).updateChildren(user).addOnSuccessListener {

            binding.name.text.clear()
            binding.date.text.clear()
            binding.time.text.clear()
            binding.location.text.clear()
            binding.description.text.clear()
            Toast.makeText(this,"Successfuly Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}
}