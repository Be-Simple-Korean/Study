package com.example.studytestcodecalculator

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studytestcodecalculator.ui.xml.MainActivity2
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Main2Test {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity2::class.java)

    @Test
    fun inputAndSubmit_showsResultText() {
        // 입력
        onView(withId(R.id.inputA)).perform(typeText("9"))
        onView(withId(R.id.inputB)).perform(typeText("8"))

        // 버튼 클릭
        onView(withId(R.id.btnAdd)).perform(click())

        // 결과 확인
        onView(withId(R.id.textResult)).check(matches(withText("Result: 17.0")))
    }
}