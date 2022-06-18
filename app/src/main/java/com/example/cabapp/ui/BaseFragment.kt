package com.example.cabapp.ui

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    open fun onBackPressed(): Boolean {
        return false
    }
}