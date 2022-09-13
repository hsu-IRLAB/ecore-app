package com.hsu_irlab.ecore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.presentation.adapter.BadgeAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.BadgeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BadgeFragment : Fragment() {
    lateinit var binding : FragmentBadgeBinding
    private val model: BadgeViewModel by viewModels()
    private lateinit var adapter: BadgeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBadgeBinding.inflate(inflater,container,false)
        setView()
        setObserver()
        return binding.root
    }

    private fun setView(){
        adapter =  BadgeAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvBadge.adapter = adapter
    }

    private fun setObserver() {
        model.itemList.observe(viewLifecycleOwner) {
            Log.e("d", "setObserver: $it", )
            adapter.setData(it)
        }
    }
}