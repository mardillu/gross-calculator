package com.mardillu.grosscalculator.model

import java.io.Serializable

/**
 * Created on 13/05/2022 at 16:23.
 * @author Ezekiel Sebastine.
 */
data class Income(
        var grossIncome: Double = 0.0,
        var tax: Double = 0.0,
        var net: Double = 0.0,
        var allowances: List<Allowance> = listOf(),
        var totalAllowance: Double = 0.0,
        var basicIncome: Double = 0.0,
        var pension: Double = 0.0,
        var employerPension: Double = 0.0,
        var employeePension: Double = 0.0,
        var taxableIncome: Double = 0.0,
): Serializable

data class Allowance(
        val name: String = "",
        val value: Double = 0.0,
)
