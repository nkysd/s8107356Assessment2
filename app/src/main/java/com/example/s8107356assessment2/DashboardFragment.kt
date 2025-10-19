package com.example.s8107356assessment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.s8107356assessment2.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    // Get ViewModel
    private val viewModel: DashboardViewModel by viewModels()

    // Get arguments from SafeArgs
    private val args: DashboardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Load dashboard layout
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get keypass from arguments
        val keypass = args.keypass

        // Call API to get dashboard data
        viewModel.fetchDashboardData(keypass)

        // Watch entity list from ViewModel
        viewModel.entities.observe(viewLifecycleOwner) { list ->
            Toast.makeText(requireContext(), "Loaded ${list.size} items", Toast.LENGTH_SHORT).show()
        }

        // Watch error from ViewModel
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
