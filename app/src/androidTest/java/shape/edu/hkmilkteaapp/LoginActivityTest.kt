package shape.edu.hkmilkteaapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextEmailAddress),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("trytry@try.com"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editTextPassword),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("trytry123"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.buttonLogin), withText("LOGIN"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val imageButton = onView(
            allOf(
                withContentDescription("Open"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open"),
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Open"),
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.textViewUserEmail), withText("trytry@try.com"),
                withParent(
                    allOf(
                        withId(R.id.navigation_header_container),
                        withParent(withId(R.id.navigation_header_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("trytry@try.com")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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
}
