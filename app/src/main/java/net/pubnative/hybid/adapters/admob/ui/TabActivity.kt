package net.pubnative.hybid.adapters.admob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import net.pubnative.hybid.adapters.admob.R

abstract class TabActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private lateinit var container: androidx.viewpager.widget.ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var debugFragment: DebugFragment
    private lateinit var adFragment: androidx.fragment.app.Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = getActivityTitle()

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container = findViewById(R.id.container)
        tabs = findViewById(R.id.tabs)

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        debugFragment = DebugFragment()
        adFragment = getAdFragment()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    inner class SectionsPagerAdapter(fm: androidx.fragment.app.FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            val fragment = when (position) {
                0 -> adFragment
                else -> debugFragment
            }
            return fragment
        }

        override fun getCount(): Int {
            return 2
        }
    }

    fun notifyAdCleaned() {
        debugFragment.cleanLogs()
    }

    fun notifyAdUpdated() {
        debugFragment.updateLogs()
    }

    abstract fun getAdFragment(): androidx.fragment.app.Fragment

    abstract fun getActivityTitle(): String
}
