package com.example.fintrack.utils

import android.app.Activity
import android.view.View

class AppUtils {


    companion object {

        val TAG = "TAG"

        fun back(activity: Activity) {
            activity.finish()
        }

    }
}