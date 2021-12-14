package com.sriyank.mobileassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sriyank.mobileassignment.databinding.ActivityReadDataBinding

class ReadData : AppCompatActivity() {

    private lateinit var binding : ActivityReadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener{

            val name : String = binding.etname.text.toString()
            if (name.isNotEmpty()){

                readData(name)

            }else{
                Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Events")
        database.child(name).get().addOnSuccessListener {

            if (it.exists()){

                val name = it.child("name").value
                val date = it.child("date").value
                val time = it.child("time").value
                val location = it.child("location").value
                val description = it.child("description").value
                Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()
                binding.etname.text.clear()
                binding.tvname.text = name.toString()
                binding.tvDate.text = date.toString()
                binding.tvTime.text = time.toString()
                binding.tvLocation.text = location.toString()
                binding.tvDescription.text = description.toString()
            }
            else
            {
                Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }
}