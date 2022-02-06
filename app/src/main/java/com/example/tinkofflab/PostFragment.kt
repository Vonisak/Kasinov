package com.example.tinkofflab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinkofflab.databinding.FragmentPostBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*

const val ARG_OBJECT = "object"

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val postService: DevelopersLifeAPI = DevelopersLifeRetrofitClient.retrofit.create(
        DevelopersLifeAPI::class.java
    )
    private var compositeDisposable = CompositeDisposable()
    private var position = -1
    private var section = ""
    private var page = 0
    private var currentPost = 0
    private val posts = mutableListOf<Post>()
    private val glideProvider = GlideProvider()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(layoutInflater)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            position = getInt(ARG_OBJECT)
        }
        when (position) {
            0 -> section = "random"
            1 -> section = "latest"
            2 -> section = "hot"
            3 -> section = "top"
        }
        buttonsListeners()

        if (section == "random") {
            getRandomPost()
        } else getPosts(section, page)

        return binding.root
    }

    private fun getRandomPost() {
        compositeDisposable.add(
            postService.getRandomPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        posts.add(it)
                        showPost(currentPost)
                    },
                    {
                        replaceFragment()
                    }
                )
        )
    }

    private fun getPosts(section: String, page: Int) {
        compositeDisposable.add(
            postService.getPosts(section, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        posts.addAll(it.posts)
                        this.page++
                        showPost(currentPost)
                    },
                    {
                        replaceFragment()
                    }
                )
        )
    }

    private fun showPost(position: Int) {
        if (position >= 0) {
            val post = posts[position]
            glideProvider.loadGif(post.gifUrl, binding.image, this, R.drawable.internet_error)
            binding.desc.text = post.description
        }
    }

    private fun buttonsListeners() {
        binding.nextButton.setOnClickListener {
            if (posts.isEmpty()) return@setOnClickListener
            currentPost++
            if (section == "random") {
                getRandomPost()
                return@setOnClickListener
            }
            if (currentPost > posts.size-3) {
                getPosts(section, page)
            }
            showPost(currentPost)
        }

        binding.backButton.setOnClickListener {
            if (currentPost > 0) {
                currentPost--
                showPost(currentPost)
            }
        }
    }

    private fun replaceFragment() {
        val frag = ErrorFragment()
        frag.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame, frag)
            .commit()
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

}