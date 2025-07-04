package com.example.flashcardsfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageButton
import android.widget.Toast

// Data class for a flashcard deck
data class MockDeck(
    val id: Int,
    val name: String,
    val description: String,
    val cardCount: Int
)

// Adapter for deck item list
class DecksAdapter(
    private var decks: MutableList<MockDeck>,
    private val onView: (MockDeck) -> Unit,
    private val onEdit: (MockDeck) -> Unit,
    private val onDelete: (MockDeck) -> Unit
) : RecyclerView.Adapter<DecksAdapter.DeckViewHolder>() {

    inner class DeckViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.deck_name)
        val desc: TextView = view.findViewById(R.id.deck_desc)
        val cards: TextView = view.findViewById(R.id.deck_card_count)
        val viewBtn: ImageButton = view.findViewById(R.id.deck_view_btn)
        val editBtn: ImageButton = view.findViewById(R.id.deck_edit_btn)
        val delBtn: ImageButton = view.findViewById(R.id.deck_delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_deck, parent, false)
        return DeckViewHolder(view)
    }

    override fun getItemCount(): Int = decks.size

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val deck = decks[position]
        holder.name.text = deck.name
        holder.desc.text = deck.description
        holder.cards.text = "${deck.cardCount} cards"

        holder.viewBtn.setOnClickListener { onView(deck) }
        holder.editBtn.setOnClickListener { onEdit(deck) }
        holder.delBtn.setOnClickListener {
            onDelete(deck)
            // Remove from adapter's backing list and update UI
            val index = holder.adapterPosition
            if (index != RecyclerView.NO_POSITION) {
                decks.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }

}

// PUBLIC_INTERFACE
class DecksFragment : Fragment() {
    /**
     * Fragment that lists flashcard sets, lets user open one for review/flip, edit, or delete.
     * Shows modern, minimal UI with RecyclerView, using mock data for now.
     */
    private val mockDecks = mutableListOf(
        MockDeck(1, "SAT Essentials", "Most frequent SAT words", 45),
        MockDeck(2, "French Basics", "Common French words", 30),
        MockDeck(3, "Biology Terms", "Intro bio definitions", 22),
        MockDeck(4, "Custom Deck", "Your custom vocab", 10),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_decks, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.decks_list)
        val emptyText = root.findViewById<TextView>(R.id.empty_decks)
        // Defensive: If fragment view inflation failed or missing IDs, return early to avoid crash/blank
        if (recycler == null || emptyText == null) {
            // Display basic fallback message
            val fallback = TextView(requireContext())
            fallback.text = "Unable to load decks UI."
            return fallback
        }

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        // Ensure data list is populated at fragment creation
        if (mockDecks.isEmpty()) {
            mockDecks.addAll(
                listOf(
                    MockDeck(1, "SAT Essentials", "Most frequent SAT words", 45),
                    MockDeck(2, "French Basics", "Common French words", 30),
                    MockDeck(3, "Biology Terms", "Intro bio definitions", 22),
                    MockDeck(4, "Custom Deck", "Your custom vocab", 10),
                )
            )
        }
        val adapter = DecksAdapter(
            decks = mockDecks,
            onView = { deck ->
                Toast.makeText(requireContext(), "Viewing \"${deck.name}\"", Toast.LENGTH_SHORT).show()
            },
            onEdit = { deck ->
                Toast.makeText(requireContext(), "Editing \"${deck.name}\"", Toast.LENGTH_SHORT).show()
            },
            onDelete = { deck ->
                Toast.makeText(requireContext(), "Deleted \"${deck.name}\"", Toast.LENGTH_SHORT).show()
                if (mockDecks.isEmpty()) emptyText.visibility = View.VISIBLE
            }
        )
        recycler.adapter = adapter

        // Ensure display state is consistent after adapter set
        if (mockDecks.isEmpty()) {
            recycler.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        } else {
            recycler.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
        }

        return root
    }
}
