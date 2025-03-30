package com.example.studytestcodecalculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studytestcodecalculator.ui.compose.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun 더하기버튼_클릭_onNodeWithText_사용(){
        // 숫자 입력
        composeTestRule.onNodeWithText("Number A").performTextInput("3")
        composeTestRule.onNodeWithText("Number B").performTextInput("5")
        // 버튼 클릭
        composeTestRule.onNodeWithText("+").performClick()
        // 결과 확인
        composeTestRule.onNodeWithText("Result: 8.0").assertIsDisplayed()
    }

    @Test
    fun 더하기버튼_클릭_onNodeWithTag_사용(){
        // 숫자 입력
        composeTestRule.onNodeWithTag("inputA").performTextInput("3")
        composeTestRule.onNodeWithTag("inputB").performTextInput("5")
        // 버튼 클릭
        composeTestRule.onNodeWithTag("+").performClick()
        // 결과 확인
        composeTestRule.onNodeWithTag("resultText")
            .assertTextEquals("Result: 8.0")
    }

    @Test
    fun whenDivideByZero_resultIsInvalidInput(){
        composeTestRule.onNodeWithText("Number A").performTextInput("10")
        composeTestRule.onNodeWithText("Number B").performTextInput("0")

        composeTestRule.onNodeWithText("÷").performClick()

        composeTestRule
            .onNodeWithText("Result: Invalid Input")
            .assertIsDisplayed()
    }


}