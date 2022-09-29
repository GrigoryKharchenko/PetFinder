package com.example.petfinder.presentation.ui.lisstpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.petfinder.databinding.FragmentListPetsBinding
import com.example.petfinder.presentation.base.BaseFragment
import com.example.petfinder.presentation.ui.lisstpets.adapter.ListPetsAdapter
import kotlinx.coroutines.flow.collect

class ListPetsFragment : BaseFragment<ListPetsViewModel>() {

    private var _binding: FragmentListPetsBinding? = null
    private val binding get() = _binding!!
    private val adapter: ListPetsAdapter = ListPetsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPetsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListPets.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel.photoFlow.collect(adapter::submitList)
        }
    }

    companion object {
        fun newInstance() = ListPetsFragment()
    }
}