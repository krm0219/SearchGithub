package com.kurly.task.searchgithub.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kurly.task.searchgithub.R
import com.kurly.task.searchgithub.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //  return inflater.inflate(R.layout.fragment_main, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        inputView.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    viewModel.clickSearch()
                    return true
                }

                return false
            }
        })


        viewModel.hideKeyboard.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let {

                hideKeyboard()
            }
        })

           setRecyclerView()
    }


    private fun hideKeyboard() {

    }


    private fun setRecyclerView() {

        val adapter = RepositoryAdapter()

        binding.githubReposView.adapter = adapter
        binding.githubReposView.setHasFixedSize(true)

        viewModel.githubRepositories.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
        })
    }
}