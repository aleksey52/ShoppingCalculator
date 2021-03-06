package com.example.shoppingcalculator.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.shoppingcalculator.Expense
import com.example.shoppingcalculator.firebaseDB.FirebaseDB

class ExpensesRepository {
    companion object {
        val instance : ExpensesRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = ExpensesRepository()
    }

    var expenses: MutableLiveData<List<Expense>> = MutableLiveData()

    var firebaseDB = FirebaseDB()

    fun getExpenses(eventName:String, callBack: (MutableList<Expense>) -> Unit) {
        //TODO somehow retrieve data from DB
        //val current = EventRepository.instance
        if (eventName.isNotEmpty()) {
            firebaseDB.getExpenses(eventName, callBack)
        }
    }
}