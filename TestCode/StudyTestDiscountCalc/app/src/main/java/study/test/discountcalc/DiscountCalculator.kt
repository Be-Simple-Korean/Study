package study.test.discountcalc

class DiscountCalculator {

    fun calculatorFinalPrice(originalPrice: Int, discountPercent: Double): Int {
        val discountAmount = originalPrice * (discountPercent/100)
        return (originalPrice - discountAmount).toInt()
    }
}