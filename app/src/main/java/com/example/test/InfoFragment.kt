package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.test.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    
    // Navigation Graph'a scoped ViewModel
    private val testViewModel: TestViewModel by navGraphViewModels(R.id.nav_graph)
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Teste başla butonuna tıklama
        binding.btnStartTest.setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_ledTestFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 