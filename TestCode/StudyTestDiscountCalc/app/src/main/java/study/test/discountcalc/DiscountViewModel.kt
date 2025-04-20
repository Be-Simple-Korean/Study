package study.test.discountcalc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiscountViewModel : ViewModel() {
    private val _originalPrice = MutableStateFlow<String>("")
    val originalPrice: StateFlow<String> = _originalPrice

    private val _discountPer = MutableStateFlow<String>("")
    val discountPer: StateFlow<String> = _discountPer

    private val _finalPrice = MutableStateFlow<Int>(0)
    val finalPrice: StateFlow<Int> = _finalPrice

    fun setOriginalPrice(value: String) {
        _originalPrice.value = value
    }

    fun setDiscountPer(value: String) {
        _discountPer.value = value
    }

    fun calculateFinalPrice() {
        val origin = _originalPrice.value.toIntOrNull() ?: 0
        val percent = _discountPer.value.toDoubleOrNull() ?: 0.0

        val discountAmount = origin * (percent / 100)
        _finalPrice.value = (origin - discountAmount).toInt()
    }
}