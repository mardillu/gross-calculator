package com.mardillu.grosscalculator.model

import java.io.Serializable

/**
 * Created on 13/05/2022 at 16:23.
 * @author Ezekiel Sebastine.
 */
data class Income(
        var grossIncome: Double = 0.0,
        val tax: Double = 0.0,
        val net: Double = 0.0,
        val allowances: List<Allowance> = listOf(),
        var basicIncome: Double = 0.0,
        val pension: Double = 0.0,
        val taxableIncome: Double = 0.0,
): Serializable

data class Allowance(
        val name: String = "",
        val value: Double = 0.0,
)
