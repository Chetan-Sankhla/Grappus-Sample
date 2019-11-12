package com.android.grappussample

import android.animation.AnimatorInflater
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, HomeActivity::class.java)

        createProfileButton.setOnClickListener { startActivity(intent) }
        skipButton.setOnClickListener { startActivity(intent) }

        val zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)

        val flipSKip = AnimatorInflater.loadAnimator(this, R.animator.flip)
            .apply {
                setTarget(skipButton)
            }

        val flip = AnimatorInflater.loadAnimator(this, R.animator.flip)
            .apply {
                setTarget(createProfileButton)
            }

        logoImageView.startAnimation(zoomOut)

        zoomOut.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

                titleTextView.visibility = View.VISIBLE
                subtitleTextView.visibility = View.VISIBLE
                titleTextView.alpha = 0.0f
                subtitleTextView.alpha = 0.0f

                titleTextView.animate().apply {
                    interpolator = LinearInterpolator()
                    duration = 600
                    alpha(1f)
                    startDelay = 0
                    start()
                }

                subtitleTextView.animate().apply {
                    interpolator = LinearInterpolator()
                    duration = 600
                    alpha(1f)
                    startDelay = 300
                    start()
                }

                Handler().postDelayed(Runnable {
                    createProfileButton.visibility = View.VISIBLE

                    flip.start()
                }, 900)

                Handler().postDelayed(Runnable {
                    skipButton.visibility = View.VISIBLE
                    flipSKip.start()
                }, 1350)

                acceptTermsTextView.visibility = View.VISIBLE
                acceptTermsTextView.alpha = 0.0f
                acceptTermsTextView.animate().apply {
                    interpolator = LinearInterpolator()
                    duration = 600
                    alpha(1f)
                    startDelay = 2250
                    start()
                }
            }

        })

    }
}
