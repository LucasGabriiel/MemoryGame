package com.example.memorygame

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>

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

        buttons = imageButtonIds.map { findViewById<ImageButton>(it) }

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Toast.makeText(this, "Clicado!", Toast.LENGTH_SHORT).show()

                val card = cards[index]
                card.isFaceUp = !card.isFaceUp

                if (card.isFaceUp) {
                    button.setImageResource(images[index])
                } else {
                    button.setImageResource(R.drawable.ic_grass)
                }
            }
        }
    }
}