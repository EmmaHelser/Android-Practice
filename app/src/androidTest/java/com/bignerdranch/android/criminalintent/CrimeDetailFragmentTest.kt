package com.bignerdranch.android.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {
    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer<CrimeDetailFragment>()
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun shouldShowTitle() {
        onView(withId(R.id.crime_title))
            .check(matches(withText(R.string.crime_title_label)))
    }

    @Test
    fun shouldUpdateCrime() {
        scenario.onFragment { fragment ->
            assertThat(fragment.crime.isSolved).isFalse()
        }
        onView(withId(R.id.crime_solved)).perform(click())
        scenario.onFragment { fragment ->
            assertThat(fragment.crime.isSolved).isTrue()
        }
    }
}