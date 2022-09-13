package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.databinding.FragmentLoginBinding
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    private val mainModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainModel.isLogin.observe(viewLifecycleOwner) {
            if (it){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                setupWithNavController(R.id.activity_main_navi,findNavController())
            }
        }
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            mainModel.login()
        }
    }

}