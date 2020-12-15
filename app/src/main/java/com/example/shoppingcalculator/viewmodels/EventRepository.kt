package com.example.shoppingcalculator.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.shoppingcalculator.Event
import com.example.shoppingcalculator.Expense
import com.example.shoppingcalculator.firebaseDB.FirebaseDB

class EventRepository {
    companion object {
        var instance = EventRepository()
    }
    var event: MutableLiveData<List<Event>> = MutableLiveData()

    var firebaseDB = FirebaseDB()

    fun getEvents(callBack: (MutableList<Event>) -> Unit) {
        //TODO somehow retrieve data from DB
        //val current = EventRepository.instance
        firebaseDB.getEvents(callBack)
    }
}