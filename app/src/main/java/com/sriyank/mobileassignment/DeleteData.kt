package com.sriyank.mobileassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.DatabaseReference
import com.sriyank.mobileassignment.databinding.ActivityDeleteDataBinding
import com.sriyank.mobileassignment.databinding.ActivityMainBinding


class DeleteData : AppCompatActivity() {

    private lateinit var binding : ActivityDeleteDataBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener {
            val intent = Intent(this, DeleteData::class.java)
            startActivity(intent)
        }

        binding.deletedataBtn.setOnClickListener{

                val username: String = binding.etname.getText().toString()
                if (!username.isEmpty()) {
                    deleteData(username)
                } else {
                    Toast.makeText(this@DeleteData, "Please Enter Username", Toast.LENGTH_SHORT)
                        .show()
                }

        }
    }

    private fun deleteData(username: String) {
        database = FirebaseDatabase.getInstance().getReference("Events")
        database.child(username).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Successfuly Deleted", Toast.LENGTH_SHORT).show()
                binding.etname.setText("")
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}