package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.test.databinding.FragmentLedTestBinding

class LedTestFragment : Fragment() {
    
    private var _binding: FragmentLedTestBinding? = null
    private val binding get() = _binding!!
    
    // Navigation Graph'a scoped ViewModel
    private val testViewModel: TestViewModel by navGraphViewModels(R.id.nav_graph)
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLedTestBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // ViewModel'den LED test durumunu gözlemle
        testViewModel.ledTestStatus.observe(viewLifecycleOwner) { status ->
            val statusText = "Durum: ${testViewModel.getTestStatusText(status)}"
            binding.tvLedStatus.text = statusText
            
            // Durum rengini güncelle
            val color = if (status == TestViewModel.TestStatus.COMPLETED) {
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            }
            binding.tvLedStatus.setTextColor(color)
        }
        
        // LED testini tamamla butonuna tıklama
        binding.btnLedTest.setOnClickListener {
            testViewModel.completeLedTest()
        }
        
        // Sonraki test butonuna tıklama
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_ledTestFragment_to_wingTestFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 