package com.example.tinkofflab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinkofflab.databinding.FragmentErrorBinding
import com.example.tinkofflab.databinding.FragmentPostBinding

class ErrorFragment : Fragment() {

    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!
    var position = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(layoutInflater)

        binding.repeat.setOnClickListener {
            arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
                position = getInt(ARG_OBJECT)
            }
            val frag = PostFragment()
            frag.arguments = Bundle().apply {
                putInt(ARG_OBJECT, position)
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame, frag)
                .commit()
        }

        return binding.root
    }

}