package ru.agima.agimageekbrains



import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class Test {
    @Test
    fun buttonClick() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()


        clickMock().subscribe { activity.button.performClick() }

        assert(activity.textView.text=="Button Clicked")
    }

    @Test
    fun some() {}


}
