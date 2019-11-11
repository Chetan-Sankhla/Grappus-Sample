package com.android.grappussample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.android.grappussample.ui.gallery.GalleryFragment
import com.android.grappussample.ui.home.HomeFragment
import com.android.grappussample.ui.send.SendFragment
import com.android.grappussample.ui.share.ShareFragment
import com.android.grappussample.ui.slideshow.SlideshowFragment
import com.android.grappussample.ui.tools.ToolsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displaySelectedScreen(R.id.nav_home)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    fun displaySelectedScreen(itemId: Int) {
        var fragment: Fragment? = null

        when (itemId) {
            R.id.nav_gallery -> {
                fragment = GalleryFragment()
            }
            R.id.nav_home -> {
                fragment = HomeFragment()
            }
            R.id.nav_slideshow -> {
                fragment = SlideshowFragment()
            }
            R.id.nav_send -> {
                fragment = SendFragment()
            }
            R.id.nav_share -> {
                fragment = ShareFragment()
            }
            R.id.nav_tools -> {
                fragment = ToolsFragment()
            }
        }

        fragment?.let {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
            fragmentTransaction.commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displaySelectedScreen(item.itemId)
        return true
    }
}
