package com.example.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    
    // Test durumları için enum
    enum class TestStatus {
        PENDING,
        COMPLETED
    }
    
    // Her test için özel LiveData'lar
    private val _ledTestStatus = MutableLiveData(TestStatus.PENDING)
    val ledTestStatus: LiveData<TestStatus> = _ledTestStatus
    
    private val _wingTestStatus = MutableLiveData(TestStatus.PENDING)
    val wingTestStatus: LiveData<TestStatus> = _wingTestStatus
    
    private val _roofTestStatus = MutableLiveData(TestStatus.PENDING)
    val roofTestStatus: LiveData<TestStatus> = _roofTestStatus
    
    // Tüm testlerin tamamlanma durumunu kontrol eden LiveData
    private val _allTestsCompleted = MutableLiveData(false)
    val allTestsCompleted: LiveData<Boolean> = _allTestsCompleted
    
    // Test tamamlama fonksiyonları
    fun completeLedTest() {
        _ledTestStatus.value = TestStatus.COMPLETED
        checkAllTestsCompleted()
    }
    
    fun completeWingTest() {
        _wingTestStatus.value = TestStatus.COMPLETED
        checkAllTestsCompleted()
    }
    
    fun completeRoofTest() {
        _roofTestStatus.value = TestStatus.COMPLETED
        checkAllTestsCompleted()
    }
    
    // Tüm testlerin tamamlanıp tamamlanmadığını kontrol eden fonksiyon
    private fun checkAllTestsCompleted() {
        val allCompleted = _ledTestStatus.value == TestStatus.COMPLETED &&
                          _wingTestStatus.value == TestStatus.COMPLETED &&
                          _roofTestStatus.value == TestStatus.COMPLETED
        _allTestsCompleted.value = allCompleted
    }
    
    // Test durumlarını sıfırlama fonksiyonu
    fun resetAllTests() {
        _ledTestStatus.value = TestStatus.PENDING
        _wingTestStatus.value = TestStatus.PENDING
        _roofTestStatus.value = TestStatus.PENDING
        _allTestsCompleted.value = false
    }
    
    // Test durumlarını string olarak döndüren yardımcı fonksiyonlar
    fun getTestStatusText(status: TestStatus): String {
        return when (status) {
            TestStatus.PENDING -> "Beklemede"
            TestStatus.COMPLETED -> "Tamamlandı"
        }
    }
} 