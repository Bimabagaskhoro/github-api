package com.bimabagaskhoro.asigmentkompas

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bimabagaskhoro.asigmentkompas.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyUser = "bimabagaskhoro"

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testingUi() {
        onView(withId(R.id.edt_search)).perform(typeText(dummyUser))
        onView(withText(dummyUser)).perform(pressKey(KeyEvent.KEYCODE_ENTER),
            closeSoftKeyboard())

        onView(withId(R.id.rv_github_user)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_github_user)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_github_user)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.rv_github_user)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_username_detail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_bio)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.img_profile_detail)).check(matches(ViewMatchers.isDisplayed()))
    }

}