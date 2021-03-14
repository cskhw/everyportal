package com.swengineering.everyportal.utils.interfaces

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

interface RequestPermissions {
    private fun requestPermissions(activity: Activity, requestCode: Int, permissions: String) {
        if (ContextCompat.checkSelfPermission(
                activity.applicationContext,
                permissions
            ) != PackageManager.PERMISSION_GRANTED && !ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permissions
            )
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permissions),
                requestCode
            )
        }

    }
}