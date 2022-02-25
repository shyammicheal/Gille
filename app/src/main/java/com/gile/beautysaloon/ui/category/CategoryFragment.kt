package com.gile.beautysaloon.ui.category

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gile.beautysaloon.ui.MainViewModel
import com.gile.beautysaloon.service.network.ViewModelFactory
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.FragmentCategoryBinding
import com.gile.beautysaloon.utils.Status
import com.gile.beautysaloon.model.ChallengeServiceData
import com.gile.beautysaloon.showToast
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder

class CategoryFragment : Fragment() {
    lateinit var mViewBinding: FragmentCategoryBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                MainViewModel::class.java
            )
        mViewBinding = FragmentCategoryBinding.inflate(inflater)

        getMyFeedback()

        return mViewBinding.root
    }

    private fun getMyFeedback() {
        mainViewModel.getChallengeService().observe(requireActivity()) {
            it.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        mViewBinding.progressBar.visibility = View.GONE
                        if (!response.data?.data.isNullOrEmpty()) {
                            mViewBinding.rvCategory.visibility = View.VISIBLE
                            mViewBinding.tvNoServiceFound.visibility = View.GONE
                            mViewBinding.rvCategory.apply {
                                adapter = CategoryAdapter(response.data?.data!!,
                                    object : CategoryAdapter.ICategoryListener {
                                        override fun onCategoryClicked(challengeServiceData: ChallengeServiceData) {
                                            val bundle = Bundle()
                                            bundle.putSerializable(
                                                "challengeServiceData",
                                                challengeServiceData
                                            )
                                            findNavController().navigate(
                                                R.id.action_categoryFragment_to_employeesFragment,
                                                bundle
                                            )
                                        }
                                    })
                            }
                        }
                        else{
                            mViewBinding.rvCategory.visibility = View.GONE
                            mViewBinding.tvNoServiceFound.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        mViewBinding.rvCategory.visibility = View.VISIBLE
                        mViewBinding.progressBar.visibility = View.GONE
                        showToast(it.message.toString(), false)
                    }
                    Status.LOADING -> {
                        mViewBinding.progressBar.visibility = View.VISIBLE
                    }

                }
            }
        }

    }
}