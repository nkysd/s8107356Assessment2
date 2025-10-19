package com.example.s8107356assessment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8107356assessment2.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Show the Login Fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views
        val usernameEditText: EditText = view.findViewById(R.id.editTextUsername)
        val passwordEditText: EditText = view.findViewById(R.id.editTextPassword)
        val loginButton: Button = view.findViewById(R.id.loginButton)

        // When button is clicked
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both fields", Toast.LENGTH_SHORT).show()
            } else {
                // Call login() in ViewModel
                viewModel.login("sydney", username, password)
            }
        }

        // Login success
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { keypass ->
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(keypass)
                findNavController().navigate(action)
            }
        }

        // Observe error message (from ViewModel)
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}
