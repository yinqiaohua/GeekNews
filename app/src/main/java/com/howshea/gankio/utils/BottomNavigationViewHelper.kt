package com.howshea.gankio.utils

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log


/**
 * Created by 陶海啸
 * on 2018/6/7.
 */
/**
 * 去除该控件超过4个item时的夸张动画
 */
@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
    val menuView = this.getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        (0 until menuView.childCount).forEach {
            (menuView.getChildAt(it) as BottomNavigationItemView).let {
                it.setShiftingMode(false)
                // set once again checked value, so view will be updated
                it.setChecked(it.itemData.isChecked)
            }
        }
    } catch (e: NoSuchFieldException) {
        Log.e("BNVHelper", "Unable to get shift mode field", e)
    } catch (e: IllegalAccessException) {
        Log.e("BNVHelper", "Unable to change value of shift mode", e)
    }

}