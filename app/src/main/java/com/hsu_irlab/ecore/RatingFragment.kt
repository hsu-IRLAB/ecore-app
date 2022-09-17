package com.hsu_irlab.ecore

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.databinding.FragmentDailyBinding
import com.hsu_irlab.ecore.databinding.FragmentHomeBinding
import com.hsu_irlab.ecore.databinding.FragmentRatingBinding
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@AndroidEntryPoint
class RatingFragment : Fragment() {
    lateinit var binding : FragmentRatingBinding
    private val mainModel : MainViewModel by activityViewModels()
    private val args: RatingFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRatingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvReportTitle.text = args.title
        binding.btnPass.setOnClickListener{
            uploadFin()
        }
        binding.btnErr.setOnClickListener {
            uploadFin()
        }
    }

    private fun uploadFin(){
        Toast.makeText(requireContext(),"일일도전 참여가 완료 되었습니다.",1000).show()
        findNavController().navigate(R.id.action_ratingFragment_to_homeFragment)
    }

    private fun BitmapConvertFile(bitmap: Bitmap,filePath: String){
        val file: File = File(filePath)

        var out : OutputStream? = null

        try {
            file.createNewFile()
            out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out)
        }catch (e:Exception){
            e.printStackTrace()
        }
        finally {
            try {
                out?.close()
            }
            catch (e:IOException){
                e.printStackTrace()
            }
        }
    }

}