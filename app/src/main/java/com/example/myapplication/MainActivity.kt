package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragments= arrayListOf<Fragment>(FirstFragment(),SecondFragment(),ThirdFragment())
        viewpager.adapter=Adapter(fragments,this)
        val tabs= arrayListOf<String>("1","2","3")
        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                //设置motionLayout的progress
                header.progress=(position+positionOffset)/2
            }
        })
        TabLayoutMediator(tabLayout,viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text=tabs[position]
            }).attach()

    }
}
class Adapter(val datas:ArrayList<Fragment>,activity: AppCompatActivity): FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun createFragment(position: Int): Fragment {
        return datas[position]
    }
}