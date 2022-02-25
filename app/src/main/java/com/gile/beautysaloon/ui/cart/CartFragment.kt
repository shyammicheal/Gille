package com.gile.beautysaloon.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.gile.beautysaloon.databinding.FragmentCartBinding
import com.gile.beautysaloon.ui.MainViewModel
import com.gile.beautysaloon.service.network.ViewModelFactory
import com.gile.beautysaloon.databinding.FragmentCategoryBinding
import com.gile.beautysaloon.model.CartModel
import com.gile.beautysaloon.model.ChallengeEmployeeData
import com.gile.beautysaloon.model.ChallengeServiceData
import com.gile.beautysaloon.utils.HelperClass
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder

class CartFragment : Fragment() {
    lateinit var mViewBinding: FragmentCartBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                MainViewModel::class.java
            )
        mViewBinding = FragmentCartBinding.inflate(inflater)

        if (!HelperClass.cartList.isNullOrEmpty()) {
            mViewBinding.rvCart.visibility = View.VISIBLE
            mViewBinding.imgEmptyCart.visibility = View.GONE

            HelperClass.groupedCartList.clear()
            val result = HelperClass.cartList.groupBy { it.challengeServiceData.id }

            val cartList: MutableList<CartModel> = mutableListOf()

            for (data in result.values) {
                val serviceList: MutableList<ChallengeServiceData> = mutableListOf()
                val employeeList: MutableList<ChallengeEmployeeData> = mutableListOf()
                for (value in data) {
                    serviceList.add(value.challengeServiceData)
                    employeeList.addAll(value.selectedEmployees)
                }
                cartList.add(CartModel(serviceList.distinctBy { it.id }
                    .toMutableList(), employeeList.distinctBy { it.id }.toMutableList()))
            }

            val groupedResult = cartList.groupBy { it.selectedEmployees.map { it.id } }

            for (data in groupedResult.values) {
                val serviceList: MutableList<ChallengeServiceData> = mutableListOf()
                val employeeList: MutableList<ChallengeEmployeeData> = mutableListOf()
                for (value in data) {
                    serviceList.addAll(value.challengeServiceData)
                    employeeList.addAll(value.selectedEmployees)
                }
                HelperClass.groupedCartList.add(CartModel(serviceList.distinctBy { it.id }
                    .toMutableList(), employeeList.distinctBy { it.id }.toMutableList()))
            }

            mViewBinding.rvCart.apply {
                adapter = CartAdapter(HelperClass.groupedCartList)
            }
        } else {
            mViewBinding.rvCart.visibility = View.GONE
            mViewBinding.imgEmptyCart.visibility = View.VISIBLE
        }

        return mViewBinding.root
    }

}
