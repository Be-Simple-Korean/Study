package study.test.discountcalc

import org.junit.Assert.assertEquals
import org.junit.Test

class DiscountCalculatorTest {

    @Test
    fun 원가_10000원에서_20퍼센트_할인시_8000원이_나와야함(){
        val calculator = DiscountCalculator()
        val result = calculator.calculatorFinalPrice(
            originalPrice = 10000,
            discountPercent = 20.0
        )

        assertEquals(8000,result)
    }
}