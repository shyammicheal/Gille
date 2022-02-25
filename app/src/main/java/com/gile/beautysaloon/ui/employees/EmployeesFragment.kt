package com.gile.beautysaloon.ui.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.FragmentEmployeesBinding
import com.gile.beautysaloon.model.CartSelectedModel
import com.gile.beautysaloon.model.ChallengeEmployeeData
import com.gile.beautysaloon.model.ChallengeServiceData
import com.gile.beautysaloon.service.network.ViewModelFactory
import com.gile.beautysaloon.showToast
import com.gile.beautysaloon.ui.MainViewModel
import com.gile.beautysaloon.utils.HelperClass
import com.gile.beautysaloon.utils.Status
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder

class EmployeesFragment : Fragment() {
    lateinit var mViewBinding: FragmentEmployeesBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var challengeServiceData: ChallengeServiceData
    var selectedEmployees: MutableLiveData<MutableList<ChallengeEmployeeData>> =
        MutableLiveData(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                MainViewModel::class.java
            )
        mViewBinding = FragmentEmployeesBinding.inflate(inflater)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val challengeServiceData =
            arguments?.getSerializable("challengeServiceData") as ChallengeServiceData
        mViewBinding.tvName.text = challengeServiceData.name
        mViewBinding.tvPrice.text = "$${challengeServiceData.price}"

        Glide.with(this).load(challengeServiceData.image)
            .placeholder(R.drawable.dummy_image).dontAnimate()
            .into(mViewBinding.imageService)
        selectedEmployees.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                mViewBinding.buttonAddCart.alpha = 0.5f
                mViewBinding.buttonAddCart.isEnabled = false
            } else {
                mViewBinding.buttonAddCart.alpha = 1f
                mViewBinding.buttonAddCart.isEnabled = true
            }
        }

        mViewBinding.buttonAddCart.setOnClickListener {
            HelperClass.cartList.add(CartSelectedModel(challengeServiceData, selectedEmployees.value!!))
            findNavController().navigate(R.id.actionToCart)
        }

        getEmployees()
    }

    private fun getEmployees() {
        mainViewModel.getEmployees().observe(requireActivity()) {
            it.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        mViewBinding.rvEmployees.visibility = View.VISIBLE
                        mViewBinding.progressBar.visibility = View.GONE
                        mViewBinding.rvEmployees.apply {
                            adapter = EmployeeAdapter(response.data?.data!!, object :
                                EmployeeAdapter.IEmployeeListener {
                                override fun onEmployeeClicked(challengeEmployeeData: ChallengeEmployeeData) {
                                    val tempList = selectedEmployees.value
                                    if (selectedEmployees.value?.contains(challengeEmployeeData) == true) {
                                        tempList?.remove(challengeEmployeeData)
                                        selectedEmployees.value = tempList!!
                                    } else {
                                        tempList?.add(challengeEmployeeData)
                                        selectedEmployees.value = tempList!!
                                    }
                                }
                            })
                        }
                    }
                    Status.ERROR -> {
                        mViewBinding.rvEmployees.visibility = View.VISIBLE
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