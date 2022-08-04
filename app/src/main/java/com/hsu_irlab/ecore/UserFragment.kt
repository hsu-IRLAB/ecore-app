package com.hsu_irlab.ecore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class UserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_user, container, false)
        val button = inflate.findViewById<Button>(R.id.main_test_btn)

        button.setOnClickListener {
            val intent : Intent = Intent(activity,BadgeActivity::class.java)
            startActivity(intent)

        }
        return inflate
    }

}