package com.example.tinkofflab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinkofflab.databinding.FragmentPostBinding
import com.example.tinkofflab.databinding.FragmentRootBinding

class RootFragment : Fragment() {

    private var _binding: FragmentRootBinding? = null
    private val binding get() = _binding!!
    var position = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRootBinding.inflate(layoutInflater)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            position = getInt(ARG_OBJECT)
        }
        val frag = PostFragment()
        frag.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.frame, frag)
            .commit()
        return binding.root
    }

}