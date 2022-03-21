package com.galih.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        FirebaseDatabase.getInstance("https://crud-firebase-666-default-rtdb.asia-southeast1.firebasedatabase.app")

        ref = FirebaseDatabase.getInstance("https://crud-firebase-666-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("USERS/"+auth.currentUser?.uid)

        val currentUser = auth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(profile_image2)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show::class.java)
            startActivity(intent)
        }
    }
    private fun savedata() {
        val nama = inputNama.text.toString()
        val status = inputStatus.text.toString()

        val userId = ref.push().key.toString()
        val user = Users(userId,nama,status)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputStatus.setText("")

        }
    }
}
