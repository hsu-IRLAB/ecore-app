package com.hsu_irlab.ecore

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.databinding.FragmentLoginBinding
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
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
//        mainModel.isLogin.observe(viewLifecycleOwner) {
//            if (it){
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
////                setupWithNavController(R.id.activity_main_navi,findNavController())
//            }
//        }
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
           // signInGoogle()
        }
    }

//    private fun signInGoogle(){
//        val googleSignInOptions = GoogleSignInOptions.Builder(
//            GoogleSignInOptions.DEFAULT_SIGN_IN
//        ).requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        val googleSignInClient = GoogleSignIn.getClient(requireActivity() , googleSignInOptions)
//        val signInIntent = googleSignInClient.signInIntent
//        launcher.launch(signInIntent)
//    }
//    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result ->
//        Log.e("TAG", ": $result", )
//        if (result.resultCode == Activity.RESULT_OK){
//            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//            handleResults(task)
//        }
//    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                Log.e("TAG", "handleResults: dddd", )
                mainModel.login()
            }
        }else{
            Log.e("res err", "handleResults: ${task.exception.toString()}", )
        }
    }

}