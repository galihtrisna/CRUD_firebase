package com.galih.crudfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show.*

class show : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Users>
    lateinit var listView: ListView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        auth = FirebaseAuth.getInstance()

        ref = FirebaseDatabase.getInstance("https://crud-firebase-666-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("USERS/"+auth.currentUser?.uid)
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(Users::class.java)
                        list.add(user!!)
                    }
                    val adapter = Adapter(this@show,R.layout.users,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}