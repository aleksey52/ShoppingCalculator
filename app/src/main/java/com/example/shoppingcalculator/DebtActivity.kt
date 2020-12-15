package com.example.shoppingcalculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcalculator.firebaseDB.FirebaseDB
import com.example.shoppingcalculator.viewmodels.DebtViewModel
import com.example.shoppingcalculator.viewmodels.ExpensesViewModel

class DebtActivity: AppCompatActivity() {

    private lateinit var rvDebtList: RecyclerView
    var values = ArrayList<PaymentUser>()
    lateinit var viewModel: DebtViewModel
    lateinit var currEvent: String
    var firebaseDB = FirebaseDB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)

        rvDebtList = findViewById(R.id.rv_debt_list)

        currEvent = intent.getStringExtra("currEvent")

        viewModel = ViewModelProviders.of(this).get(DebtViewModel::class.java)

        //var values = ArrayList<PaymentUser>()
        //values.add(PaymentUser("Alex", 40.0, false))

        firebaseDB.getPaymentUsers(currEvent) {
            firebaseDB.listenPaymentUsersChange(currEvent, it) {
                viewModel.listenChange(currEvent)
            }
        }

        val adapter = DebtRecyclerAdapter(values, object: DebtRecyclerAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                //val intent = Intent(applicationContext, ExpensesActivity::class.java)


                //startActivity(intent)
            }

        })
        rvDebtList.adapter = adapter
        rvDebtList.layoutManager = LinearLayoutManager(this)

        viewModel.getUsers().observe(this, androidx.lifecycle.Observer {
            adapter.values = it as ArrayList<PaymentUser>
            adapter.notifyDataSetChanged()
            //progressBar.visibility = View.GONE
        })
        viewModel.updatePaymentUsers(currEvent)
    }
}