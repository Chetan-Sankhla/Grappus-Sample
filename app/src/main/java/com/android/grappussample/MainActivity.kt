package com.android.grappussample

import android.animation.AnimatorInflater
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, HomeActivity::class.java)

        createProfileButton.setOnClickListener { startActivity(intent) }
        skipButton.setOnClickListener { startActivity(intent) }

    }

    override fun onStart() {
        super.onStart()

        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        val flipSKip = AnimatorInflater.loadAnimator(this, R.animator.flip)
            .apply {
                setTarget(skipButton)
            }

        val flip = AnimatorInflater.loadAnimator(this, R.animator.flip)
            .apply {
                setTarget(createProfileButton)
            }

        //skipButton.startAnimation(bounceAnimation)
        //createProfileButton.startAnimation(bounceAnimation)
        logoImageView.startAnimation(zoomOut)
        //logoImageView.startAnimation(zoomIn)

        zoomOut.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

                createProfileButton.visibility = View.VISIBLE

                flip.start()

                Handler().postDelayed(Runnable {
                    skipButton.visibility = View.VISIBLE
                    flipSKip.start()
                }, 1000)
            }

        })
    }
}
