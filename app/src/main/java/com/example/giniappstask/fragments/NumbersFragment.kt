package com.example.giniappstask.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.giniappstask.R
import com.example.giniappstask.adapters.NumbersAdapter
import com.example.giniappstask.data.NumbersUiState
import com.example.giniappstask.databinding.FragmentNumbersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NumbersFragment : Fragment() {

    private lateinit var binding: FragmentNumbersBinding
    private lateinit var numbersViewModel: NumbersViewModel
    private lateinit var numberAdapter: NumbersAdapter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumbersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
    }

    private fun setObserve() {
        numbersViewModel = ViewModelProvider(this).get(NumbersViewModel::class.java)

        lifecycleScope.launch {
            numbersViewModel.numbersUiState.collect { uiState ->
                when (uiState) {
                    is NumbersUiState.Success -> {
                        val numbers = uiState.numbers
                        numberAdapter = NumbersAdapter(numbers)
                        binding.recyclerViewNumbers.adapter = numberAdapter
                    }
                    is NumbersUiState.Error -> {
                        val exception = uiState.exception
                        Toast.makeText(context, "Error: $exception", Toast.LENGTH_LONG).show()
                    }
                    is NumbersUiState.Loading -> {
                        val isLoaded = uiState.isLoad
                        if (isLoaded)
                            showCustomProgressDialog()
                        else
                            hideProgressDialog()
                    }
                }
            }
        }
    }

    private fun showCustomProgressDialog() {
        progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.dialog_custom_progress)
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.dismiss()
    }
}
