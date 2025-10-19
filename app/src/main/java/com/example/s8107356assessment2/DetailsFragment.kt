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

        // Set value to each TextView
        view.findViewById<TextView>(R.id.textTitle).text = "Album Title: ${args.albumTitle}"
        view.findViewById<TextView>(R.id.textArtist).text = "Artist Name: ${args.artistName}"
        view.findViewById<TextView>(R.id.textYear).text = "Release Year: ${args.releaseYear}"
        view.findViewById<TextView>(R.id.textGenre).text = "Genre: ${args.genre}"
        view.findViewById<TextView>(R.id.textTrackCount).text = "Track Count: ${args.trackCount}"
        view.findViewById<TextView>(R.id.textPopularTrack).text = "Popular Track: ${args.popularTrack}"
        view.findViewById<TextView>(R.id.textDescription).text = "Description:\n${args.description}"
    }
}
