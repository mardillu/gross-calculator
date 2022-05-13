package com.mardillu.grosscalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mardillu.grosscalculator.model.Income
import java.lang.Exception

class MainViewModel : ViewModel() {

    val _income: MutableLiveData<Income> by lazy {
        MutableLiveData<Income>()
    }

    fun isInputAmountValid(amount: String): Boolean {
        return if (amount.isEmpty()) {
            false
        } else {
            try {
                val netDouble = amount.toDouble()
                netDouble > 0.0
            } catch (e: Exception) {
                false
            }
        }
    }

    fun getGrossSalary(
            basic: Double,
            transAllowance: Double,
            lunchAllowance: Double,
            housingAllowance: Double,
    ): Double {
        val gs = basic + transAllowance + lunchAllowance + housingAllowance
        _income.value!!.grossIncome = gs
        return gs
    }


    fun calculateAllowances(
            basic: Double,
            allowance: Double,
    ): Double {
        return basic + allowance
    }

    /**
     * Calculate basic salary as 60% of the net salary
     *
     * @param net the net salary
     */
    fun calculateBasicSalary(
            net: Double,
    ): Double {
        val basic = (60.0/100.0) * net
        _income.value!!.basicIncome = basic
        return basic
    }

    /**
     * The objective here is to calculate each separately and sum them up at the end
     *
     * @param gross User's gross pay
     * @see getGrossSalary
     */
    fun calculatePensionValue(
            gross: Double
    ): Double {
        //Tier 1 13% of gross

        val t1 = (13.0/100.0) * gross

        //Tier 2 5% of gross
        val t2 = (5.0/100.0) * gross

        //Tier 2 5% + 5% = 10% of gross
        val t3 = (10.0/100.0) * gross

        return t1 + t2 + t3
    }

    fun calculateTaxableIncome(
            gross: Double,
            pension: Double,
    ): Double {
        return gross - pension
    }

    fun calculateTax(
            taxableIncome: Double,
    ): Double {
        var tax = 0.0
        if (taxableIncome >= 110){
            tax = (5.0/100) * 110
        }

        if (taxableIncome >= 130){
            tax = (10.0/100) * 130
        }

        if (taxableIncome >= 3000){
            tax = (17.5/100) * 3000
        }

        if (taxableIncome >= 16395){
            tax = (25.0/100) * 16395
        }

        if (taxableIncome >= 20000){
            tax = (30.0/100) * 20000
        }

        return tax
    }

}