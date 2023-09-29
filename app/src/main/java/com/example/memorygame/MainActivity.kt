package com.example.memorygame

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectCard: Int? = null
    private var matchedPairsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = mutableListOf(
            R.drawable.ic_brokenhearth,
            R.drawable.ic_lightning,
            R.drawable.ic_plane,
            R.drawable.ic_rabbit
        )
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

                updateModels(index)
                updateViews()
            }
        }
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched){
                button.alpha = 0.1f
            }
            if (card.isFaceUp) {
                button.setImageResource(card.identifier)
            } else {
                button.setImageResource(R.drawable.ic_grass)
            }
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        if (card.isFaceUp){
            Toast.makeText(this, "Invalid Move!", Toast.LENGTH_SHORT).show()
            return
        }
        if (indexOfSingleSelectCard == null){
            restoreCards()
            indexOfSingleSelectCard = position
        } else {
            checkForMatch(indexOfSingleSelectCard!!, position)
            indexOfSingleSelectCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
       for (card in cards){
           if (!card.isMatched){
               card.isFaceUp = false
           }
       }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier){
            Toast.makeText(this, "Match Foubd!!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
    }
}