package com.example.giniappstask.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giniappstask.R
import com.example.giniappstask.adapters.ItemSpacingDecoration
import com.example.giniappstask.adapters.NumbersAdapter
import java.util.*


class NumbersFragment : Fragment() {

    private lateinit var numbersViewModel: NumbersViewModel
    private lateinit var numberAdapter: NumbersAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        setObserve()
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNumbers)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing_10dp)
        recyclerView.addItemDecoration(ItemSpacingDecoration(spacingInPixels))

    }

    private fun setObserve() {
        numbersViewModel = ViewModelProvider(this).get(NumbersViewModel::class.java)

        numbersViewModel.numbersLiveData.observe(viewLifecycleOwner, Observer { numbers ->

            numberAdapter = NumbersAdapter(numbers)

            recyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = numberAdapter
            }

            numberAdapter.submitList(numbers)
        })

        numbersViewModel.fetchNumbers()
    }
}
