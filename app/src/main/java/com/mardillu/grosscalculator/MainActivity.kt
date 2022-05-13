package com.mardillu.grosscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mardillu.grosscalculator.databinding.ActivityMainBinding
import com.mardillu.grosscalculator.model.Income
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var bttomSheetDialog: MyBottomSheet? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        binding.apply {
            included.proceedBtn.setOnClickListener {
                if (!viewModel.isInputAmountValid(netValueEdit.text.toString())){
                    netValueEdit.error = getString(R.string.invalid_input)
                    return@setOnClickListener
                }

                if (!viewModel.isInputAmountValid(lunchAllowEdit.text.toString())){
                    lunchAllowEdit.error = getString(R.string.invalid_input)
                    return@setOnClickListener
                }

                if (!viewModel.isInputAmountValid(transportationAllowEdit.text.toString())){
                    transportationAllowEdit.error = getString(R.string.invalid_input)
                    return@setOnClickListener
                }

                if (!viewModel.isInputAmountValid(housingAllowEdit.text.toString())){
                    housingAllowEdit.error = getString(R.string.invalid_input)
                    return@setOnClickListener
                }

                val basic = viewModel.calculateBasicSalary(netValueEdit.text.toString().toDouble())
                val gross = viewModel.getGrossSalary(
                        basic,
                        transportationAllowEdit.text.toString().toDouble(),
                        housingAllowEdit.text.toString().toDouble(),
                        lunchAllowEdit.text.toString().toDouble(),
                )
            }
        }

        val nameObserver = Observer<Income> {
            Log.e("LOG","Income ${it.grossIncome}")
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel._income.observe(this, nameObserver)
    }

    fun showData(){
        //bttomSheetDialog = MyBottomSheet.newInstance(null)
    }
}