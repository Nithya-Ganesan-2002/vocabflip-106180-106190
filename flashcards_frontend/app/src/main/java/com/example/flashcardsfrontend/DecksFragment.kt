package com.example.flashcardsfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class DecksFragment : Fragment() {
    /**
     * Fragment that lists flashcard sets, lets user open one for review/flip, edit, or delete.
     * Minimal for initial scaffolding.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_decks, container, false)
    }
}
