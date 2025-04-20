package study.test.discountcalc

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DiscountCalculatorTest {

    private lateinit var viewModel : DiscountViewModel

    @Before
    fun setUp(){
        viewModel =  DiscountViewModel()
    }

    @Test
    fun 원가_10000원에서_20퍼센트_할인시_8000원이_나와야함() = runTest{
        viewModel.setOriginalPrice("10000")
        viewModel.setDiscountPer("20")
        viewModel.calculateFinalPrice()

        val result = viewModel.finalPrice.first()
        assertEquals(8000,result)
    }
}