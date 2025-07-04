package com.example.flashcardsfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.util.Log
// PUBLIC_INTERFACE
class ProgressFragment : Fragment() {
    /**
     * Fragment to show progress metrics to the user.
     * Will be updated with statistics and visuals.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_progress, container, false)
        Log.d("ProgressFragment", "Inflated progress fragment.")
        return v
    }
}
