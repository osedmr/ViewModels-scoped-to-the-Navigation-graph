package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.test.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {
    
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    
    // Navigation Graph'a scoped ViewModel
    private val testViewModel: TestViewModel by navGraphViewModels(R.id.nav_graph)
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // LED test durumunu gözlemle
        testViewModel.ledTestStatus.observe(viewLifecycleOwner) { status ->
            binding.tvLedStatus.text = testViewModel.getTestStatusText(status)
            val color = if (status == TestViewModel.TestStatus.COMPLETED) {
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            }
            binding.tvLedStatus.setTextColor(color)
        }
        
        // Wing test durumunu gözlemle
        testViewModel.wingTestStatus.observe(viewLifecycleOwner) { status ->
            binding.tvWingStatus.text = testViewModel.getTestStatusText(status)
            val color = if (status == TestViewModel.TestStatus.COMPLETED) {
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            }
            binding.tvWingStatus.setTextColor(color)
        }
        
        // Roof test durumunu gözlemle
        testViewModel.roofTestStatus.observe(viewLifecycleOwner) { status ->
            binding.tvRoofStatus.text = testViewModel.getTestStatusText(status)
            val color = if (status == TestViewModel.TestStatus.COMPLETED) {
                ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            }
            binding.tvRoofStatus.setTextColor(color)
        }
        
        // Tüm testlerin tamamlanma durumunu gözlemle
        testViewModel.allTestsCompleted.observe(viewLifecycleOwner) { allCompleted ->
            binding.btnFinishTest.isEnabled = allCompleted
            
            if (allCompleted) {
                binding.tvOverallStatus.text = "Tüm testler tamamlandı! Şimdi testi bitirebilirsiniz."
                binding.tvOverallStatus.setTextColor(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
                )
            } else {
                binding.tvOverallStatus.text = "Tüm testleri tamamlayın"
                binding.tvOverallStatus.setTextColor(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
                )
            }
        }
        
        // Testi bitir butonuna tıklama
        binding.btnFinishTest.setOnClickListener {
            // Test tamamlandı mesajı göster ve ana ekrana dön
            findNavController().navigate(R.id.action_summaryFragment_to_infoFragment)
        }
        
        // Testi yeniden başlat butonuna tıklama
        binding.btnRestartTest.setOnClickListener {
            testViewModel.resetAllTests()
            findNavController().navigate(R.id.action_summaryFragment_to_infoFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 