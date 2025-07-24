package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.test.databinding.FragmentWingTestBinding

class WingTestFragment : Fragment() {
    
    private var _binding: FragmentWingTestBinding? = null
    private val binding get() = _binding!!
    
    // Navigation Graph'a scoped ViewModel
    private val testViewModel: TestViewModel by navGraphViewModels(R.id.nav_graph)
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWingTestBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // ViewModel'den Wing test durumunu gözlemle
        testViewModel.wingTestStatus.observe(viewLifecycleOwner) { status ->
            val statusText = "Durum: ${testViewModel.getTestStatusText(status)}"
            binding.tvWingStatus.text = statusText
            
            // Durum rengini güncelle
            val color = if (status == TestViewModel.TestStatus.COMPLETED) {
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            }
            binding.tvWingStatus.setTextColor(color)
        }
        
        // Wing testini tamamla butonuna tıklama
        binding.btnWingTest.setOnClickListener {
            testViewModel.completeWingTest()
        }
        
        // Sonraki test butonuna tıklama
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_wingTestFragment_to_roofTestFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 