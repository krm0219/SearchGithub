package com.kurly.task.searchgithub.ui.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
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


        // 키보드에서 검색 버튼 눌렀을 때, 검색이 가능하도록
        edit_main_keyword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    viewModel.clickSearch()
                    return true
                }

                return false
            }
        })

        // 이벤트 발생시, 키보드 숨기기
        viewModel.hideKeyboard.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let {

                hideKeyboard()
            }
        })

        // Toast Event
        viewModel.showEmptyToast.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let {

                Toast.makeText(activity, R.string.msg_empty_keyword, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.showErrorToast.observe(viewLifecycleOwner, Observer {

            it.getContentIfNotHandled()?.let {

                Toast.makeText(activity, R.string.msg_error, Toast.LENGTH_SHORT).show()
            }
        })

        setRecyclerView()
    }


    private fun hideKeyboard() {

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_main_keyword.windowToken, 0)
    }

    private fun setRecyclerView() {

        val adapter = RepositoryAdapter()

        binding.recyclerMainList.adapter = adapter
        binding.recyclerMainList.setHasFixedSize(true)

        viewModel.githubRepositories.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
        })
    }
}