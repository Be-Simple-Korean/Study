package study.test.discountcalc

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import study.test.discountcalc.ui.DiscountScreen

@RunWith(AndroidJUnit4::class)
class DiscountUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 원가_할인_계산(){
        composeTestRule.setContent {
            DiscountScreen()
        }

        composeTestRule.onNodeWithTag("originalPriceInput").performTextInput("10000")
        composeTestRule.onNodeWithTag("discountPercentInput").performTextInput("30")
        composeTestRule.onNodeWithTag("calculateButton").performClick()
        composeTestRule.onNodeWithTag("resultText").assertTextEquals("최종 금액: 7000 원")
    }
}