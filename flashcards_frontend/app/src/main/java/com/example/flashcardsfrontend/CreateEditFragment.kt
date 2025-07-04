package com.example.flashcardsfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// PUBLIC_INTERFACE
class CreateEditFragment : Fragment() {
    /**
     * Fragment for creating a new flashcard set or editing an existing one.
     * UI to be expanded for minimal creation interface.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_edit, container, false)
    }
}
