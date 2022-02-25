package com.gile.beautysaloon.utils

import com.gile.beautysaloon.model.CartModel
import com.gile.beautysaloon.model.CartSelectedModel

class HelperClass {
    companion object{
        var cartList: MutableList<CartSelectedModel> = mutableListOf()
        var groupedCartList: MutableList<CartModel> = mutableListOf()
    }
}