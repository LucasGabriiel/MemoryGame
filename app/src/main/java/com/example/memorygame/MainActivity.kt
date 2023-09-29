package com.example.memorygame

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = mutableListOf(R.drawable.ic_brokenhearth, R.drawable.ic_lightning, R.drawable.ic_plane, R.drawable.ic_rabbit)
        images.addAll(images)
        images.shuffle()

        val imageButtonIds = listOf(
            R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4,
            R.id.imageButton5, R.id.imageButton6, R.id.imageButton7, R.id.imageButton8
        )

        imageButtonIds.forEachIndexed { index, buttonId ->
            val button = findViewById<ImageButton>(buttonId)
            button.setOnClickListener {
                Toast.makeText(this, "Clicado!", Toast.LENGTH_SHORT).show()
                button.setImageResource(images[index])
            }
        }
    }
}