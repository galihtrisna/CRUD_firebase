package com.galih.crudfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var pressedTime: Long = 0
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userName=currentUser?.displayName

        Glide.with(this).load(currentUser?.photoUrl).into(profile_image)

        val current = LocalDateTime.now()


        tanggal.text="${current.get(ChronoField.DAY_OF_MONTH)}"

        val currentTime = Calendar.getInstance().time
        val formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime)

        val splitDate = formattedDate.split(",").toTypedArray()

        hari.setText(splitDate[0])

        halonama.text="Halo, ${userName} !!"

        tambahbarang.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        caribarang.setOnClickListener {
            val intent = Intent (this, show::class.java)
            startActivity(intent)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclient_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        logout.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
    }
}