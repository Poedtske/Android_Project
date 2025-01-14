package com.example.android_project.classes

import com.example.android_project.R

enum class Course(val displayName: Int) {
    NONE(R.string.none),
    STARTER(R.string.starter),
    MAIN(R.string.main_course),
    DESSERT(R.string.dessert);

    // Simplified comparison function
    private fun isType(type: Course): Boolean {
        return this == type
    }
}
