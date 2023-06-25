package com.example.giniappstask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.giniappstask.adapters.NumbersAdapter
import com.example.giniappstask.databinding.FragmentNumbersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NumbersFragment : Fragment() {

    private lateinit var binding: FragmentNumbersBinding
    private lateinit var numbersViewModel: NumbersViewModel
    private lateinit var numberAdapter: NumbersAdapter

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

        numbersViewModel.numbersLiveData.observe(viewLifecycleOwner, Observer { numbers ->
            numberAdapter = NumbersAdapter(numbers)
            binding.recyclerViewNumbers.adapter = numberAdapter
        })

        numbersViewModel.getNumbers()
    }
}
