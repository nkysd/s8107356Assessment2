package com.example.s8107356assessment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class DetailsFragment : Fragment() {

    // Get data from SafeArgs
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Load the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find TextViews by ID
        val titleTextView = view.findViewById<TextView>(R.id.textTitle)
        val artistTextView = view.findViewById<TextView>(R.id.textArtist)
        val descriptionTextView = view.findViewById<TextView>(R.id.textDescription)

        // Show data from arguments
        titleTextView.text = args.albumTitle
        artistTextView.text = args.artistName
        descriptionTextView.text = args.description
    }
}
