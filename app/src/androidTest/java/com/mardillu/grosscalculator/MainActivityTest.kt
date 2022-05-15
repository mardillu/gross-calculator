package com.mardillu.grosscalculator


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        var button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        onView(withId(R.id.notice_text)).check(matches(withText(R.string.input_notice_text)))

        val netEdit = onView(withId(R.id.net_value_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.net_value_edit)).check(matches(withHint("0.00")))
        netEdit.perform(scrollTo(), click())
        netEdit.perform(scrollTo(), replaceText("7223"), closeSoftKeyboard())
        netEdit.check(matches(withText("7223")))

        val lunchAllow = onView(withId(R.id.lunch_allow_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.lunch_allow_edit)).check(matches(withHint("0.00")))
        lunchAllow.perform(scrollTo(), click())
        lunchAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())
        lunchAllow.check(matches(withText("2215")))


        lunchAllow.perform(pressImeActionButton())

        button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        val transAllow = onView(withId(R.id.transportation_allow_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.transportation_allow_edit)).check(matches(withHint("0.00")))
        transAllow.perform(scrollTo(), click())
        transAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())
        transAllow.check(matches(withText("2215")))

        val houseAllow = onView(withId(R.id.housing_allow_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.housing_allow_edit)).check(matches(withHint("0.00")))
        houseAllow.perform(scrollTo(), click())
        houseAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())
        houseAllow.check(matches(withText("2215")))

        houseAllow.perform(pressImeActionButton())

        button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        onView(withText(R.string.salary_breakdown))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Clicking_the_Calculate_button_displays_bottom_sheet`(){
        val netEdit = onView(withId(R.id.net_value_edit)).check(matches(isDisplayed()))
        netEdit.perform(scrollTo(), replaceText("7223"), closeSoftKeyboard())

        val lunchAllow = onView(withId(R.id.lunch_allow_edit)).check(matches(isDisplayed()))
        lunchAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val transAllow = onView(withId(R.id.transportation_allow_edit)).check(matches(isDisplayed()))
        transAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val houseAllow = onView(withId(R.id.housing_allow_edit)).check(matches(isDisplayed()))
        houseAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        houseAllow.perform(pressImeActionButton())

        val button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        onView(withText(R.string.salary_breakdown))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `Clicking_the_Calculate_button_displays_bottom_sheet_with_correct_values`(){
        val netEdit = onView(withId(R.id.net_value_edit)).check(matches(isDisplayed()))
        netEdit.perform(scrollTo(), replaceText("7223"), closeSoftKeyboard())

        val lunchAllow = onView(withId(R.id.lunch_allow_edit)).check(matches(isDisplayed()))
        lunchAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val transAllow = onView(withId(R.id.transportation_allow_edit)).check(matches(isDisplayed()))
        transAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val houseAllow = onView(withId(R.id.housing_allow_edit)).check(matches(isDisplayed()))
        houseAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        houseAllow.perform(pressImeActionButton())

        val button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        onView(withText(R.string.salary_breakdown))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))


        onView(withId(R.id.net_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.net_salary_text)).check(matches(withText("GHS 7,223.00")))

        onView(withId(R.id.net_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.net_salary_text)).check(matches(withText("GHS 7,223.00")))

        onView(withId(R.id.gross_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.gross_salary_text)).check(matches(withText("GHS 10,978.80")))

        onView(withId(R.id.basic_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.basic_salary_text)).check(matches(withText("GHS 4,333.80")))

        onView(withId(R.id.employer_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.employer_pension_text)).check(matches(withText("GHS 1,976.18")))

        onView(withId(R.id.employee_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.employee_pension_text)).check(matches(withText("GHS 1,152.77")))

        onView(withId(R.id.total_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.total_pension_text)).check(matches(withText("GHS 3,128.96")))

        onView(withId(R.id.paye_text)).check(matches(isDisplayed()))
        onView(withId(R.id.paye_text)).check(matches(withText("GHS 543.50")))

        onView(withId(R.id.total_allowance_text)).check(matches(isDisplayed()))
        onView(withId(R.id.total_allowance_text)).check(matches(withText("GHS 6,645.00")))


        val img = onView(withId(R.id.close)).check(matches(isDisplayed()))
        img.perform(forceClick())
    }

    @Test
    fun `clicking_close_button_on_bottom_sheet_dismisses_the_bottom_sheet`(){
        val netEdit = onView(withId(R.id.net_value_edit)).check(matches(isDisplayed()))
        netEdit.perform(scrollTo(), replaceText("7223"), closeSoftKeyboard())

        val lunchAllow = onView(withId(R.id.lunch_allow_edit)).check(matches(isDisplayed()))
        lunchAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val transAllow = onView(withId(R.id.transportation_allow_edit)).check(matches(isDisplayed()))
        transAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        val houseAllow = onView(withId(R.id.housing_allow_edit)).check(matches(isDisplayed()))
        houseAllow.perform(scrollTo(), replaceText("2215"), closeSoftKeyboard())

        houseAllow.perform(pressImeActionButton())

        val button = onView(withId(R.id.proceedBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.proceedBtn)).check(matches(withText(R.string.calculate)))
        button.perform(forceClick())

        onView(withText(R.string.salary_breakdown))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))


        onView(withId(R.id.net_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.net_salary_text)).check(matches(withText("GHS 7,223.00")))

        onView(withId(R.id.net_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.net_salary_text)).check(matches(withText("GHS 7,223.00")))

        onView(withId(R.id.gross_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.gross_salary_text)).check(matches(withText("GHS 10,978.80")))

        onView(withId(R.id.basic_salary_text)).check(matches(isDisplayed()))
        onView(withId(R.id.basic_salary_text)).check(matches(withText("GHS 4,333.80")))

        onView(withId(R.id.employer_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.employer_pension_text)).check(matches(withText("GHS 1,976.18")))

        onView(withId(R.id.employee_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.employee_pension_text)).check(matches(withText("GHS 1,152.77")))

        onView(withId(R.id.total_pension_text)).check(matches(isDisplayed()))
        onView(withId(R.id.total_pension_text)).check(matches(withText("GHS 3,128.96")))

        onView(withId(R.id.paye_text)).check(matches(isDisplayed()))
        onView(withId(R.id.paye_text)).check(matches(withText("GHS 543.50")))

        onView(withId(R.id.total_allowance_text)).check(matches(isDisplayed()))
        onView(withId(R.id.total_allowance_text)).check(matches(withText("GHS 6,645.00")))


        val img = onView(withId(R.id.close)).check(matches(isDisplayed()))
        img.perform(forceClick())

        onView(isRoot()).inRoot(isDialog()).noActivity()
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun forceClick(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return AllOf.allOf(isClickable(), isEnabled(), isDisplayed())
            }

            override fun getDescription(): String {
                return "force click"
            }

            override fun perform(uiController: UiController, view: View) {
                view.performClick() // perform click without checking view coordinates.
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}



//bottom sheet
//        val textView8 = onView(
//                allOf(withId(R.id.net_salary_text), withText("GHS 7,223.00"),
//                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))),
//                        isDisplayed()))
//        textView8.check(matches(withText("GHS 7,223.00")))
//
//        onView(withId(R.id.net_salary_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.net_salary_text)).check(matches(withText("GHS 7,223.00")))
//
//        onView(withId(R.id.gross_salary_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.gross_salary_text)).check(matches(withText("GHS 10,978.80")))
//
//        onView(withId(R.id.basic_salary_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.basic_salary_text)).check(matches(withText("GHS 4,333.80")))
//
//        onView(withId(R.id.employer_pension_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.employer_pension_text)).check(matches(withText("GHS 1,976.18")))
//
//        onView(withId(R.id.employee_pension_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.employee_pension_text)).check(matches(withText("GHS 1,152.77")))
//
//        onView(withId(R.id.total_pension_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.total_pension_text)).check(matches(withText("GHS 3,128.96")))
//
//        onView(withId(R.id.paye_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.paye_text)).check(matches(withText("GHS 543.50")))
//
//        onView(withId(R.id.total_allowance_text)).check(matches(isDisplayed()))
//        onView(withId(R.id.total_allowance_text)).check(matches(withText("GHS 6,645.00")))
//
//
//        val img = onView(withId(R.id.close)).check(matches(isDisplayed()))
//
//        img.perform(forceClick())
//
//        val linearLayout4 = onView(
//                allOf(withId(R.id.proceedBtn),
//                        withParent(allOf(withId(R.id.included),
//                                withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java)))),
//                        isDisplayed()))
//        linearLayout4.check(matches(isDisplayed()))
//
//        val textView9 = onView(
//                allOf(withId(R.id.btnText), withText("Calculate"),
//                        withParent(allOf(withId(R.id.proceedBtn),
//                                withParent(withId(R.id.included)))),
//                        isDisplayed()))
//        textView9.check(matches(withText("Calculate")))
