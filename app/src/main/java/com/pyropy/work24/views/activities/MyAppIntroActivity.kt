package com.pyropy.work24.views.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.pyropy.work24.R

public class MyAppIntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment

        isColorTransitionsEnabled = true
        addSlide(

                AppIntroFragment.newInstance(
                        title = "The Perfect Platform",
                        description = "Access unlimited up to date resources",
                        titleColor = Color.WHITE,
                        imageDrawable = R.drawable.work2,
                        descriptionColor = Color.WHITE,
                        backgroundColor = R.color.splash_background_color,

                        ))


        addSlide(AppIntroFragment.newInstance(
                title = "Stay Connected",
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                imageDrawable = R.drawable.welcome2,
                backgroundColor = R.color.splash_background_color,
                description = "Easy messaging to help you stay connected! "
        ))


        addSlide(AppIntroFragment.newInstance(
                title = "Connect and Learn",
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                imageDrawable = R.drawable.welcome,
                backgroundColor = R.color.splash_background_color,
                description = ""
        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
        val intent = Intent(this, MyJobScreen::class.java)
        startActivity(intent)

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
        val intent = Intent(this, MyJobScreen::class.java)
        startActivity(intent)
    }
}