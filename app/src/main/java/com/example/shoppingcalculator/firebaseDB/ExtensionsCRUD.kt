package com.example.shoppingcalculator.firebaseDB

import com.example.shoppingcalculator.Event
import com.example.shoppingcalculator.Expense
import com.example.shoppingcalculator.PaymentUser
import com.example.shoppingcalculator.User

interface ExtensionsCRUD {

    fun createEvent(eventName: String, secretCode: String)
    fun joinEvent(code: String)
    fun getEvents(callBack: (MutableList<Event>) -> Unit)

    fun createExpense(eventName: String, expenseName: String, price: Double)
    fun joinExpense(eventName: String, expenseName: String)
    fun getExpenses(eventName:String, callBack: (MutableList<Expense>) -> Unit)
    fun getSharingUsers(eventName:String, expenseName: String, callBack: (MutableList<String>) -> Unit)

    fun createUser()
    fun getUsers(callBack: (MutableList<User?>) -> Unit) //TODO Возможно поменять класс??
    fun getUser(userId: String, callBack: (MutableList<User>) -> Unit)

    fun listenEventChange(users: ArrayList<String?>, callBack: ((Event?) -> Unit))
    fun detachListEvents(listEvents: ArrayList<Event?>)

    fun listenExpenseChange(users: ArrayList<String?>, callBack: ((Expense?) -> Unit))
    fun detachListExpenses(listExpenses: ArrayList<Expense?>)
}