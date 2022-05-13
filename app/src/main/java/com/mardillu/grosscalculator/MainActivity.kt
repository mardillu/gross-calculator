package com.mardillu.grosscalculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.mardillu.grosscalculator.databinding.ActivityMainBinding
import com.mardillu.grosscalculator.databinding.DialogResultDataBinding
import com.mardillu.grosscalculator.model.Income

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var bottomSheet: RoundedBottomSheetDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel._income.value = Income()

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
                val allowance = viewModel.calculateAllowances(
                        transportationAllowEdit.text.toString().toDouble(),
                        lunchAllowEdit.text.toString().toDouble(),
                        housingAllowEdit.text.toString().toDouble(),
                )
                val gross = viewModel.getGrossSalary(
                        basic,
                        allowance,
                )
                val pension = viewModel.calculatePensionValue(gross)
                val taxableIncome = viewModel.calculateTaxableIncome(gross, pension)
                val tax = viewModel.calculateTax(taxableIncome)
                showData()
            }
        }

        val nameObserver = Observer<Income> {
            Log.e("LOG","Income ${it.grossIncome}")
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel._income.observe(this, nameObserver)
    }

    private fun showData(){
        val view = DialogResultDataBinding.inflate(layoutInflater)
        bottomSheet = RoundedBottomSheetDialog(this)
        bottomSheet!!.setContentView(view.root)

        view.basicSalaryText.text = getString(R.string.currency_format, viewModel._income.value?.basicIncome)
        view.netSalaryText.text = getString(R.string.currency_format, binding.netValueEdit.text.toString().toDouble())
        view.grossSalaryText.text = getString(R.string.currency_format, viewModel._income.value?.grossIncome)
        view.payeText.text = getString(R.string.currency_format, viewModel._income.value?.tax)
        view.employeePensionText.text = getString(R.string.currency_format, viewModel._income.value?.employeePension)
        view.employerPensionText.text = getString(R.string.currency_format, viewModel._income.value?.employerPension)
        view.totalAllowanceText.text = getString(R.string.currency_format, viewModel._income.value?.totalAllowance)
        view.totalPensionText.text = getString(R.string.currency_format, viewModel._income.value?.pension)


        view.close.setOnClickListener { bottomSheet!!.dismiss() }
        bottomSheet!!.show()
    }
}