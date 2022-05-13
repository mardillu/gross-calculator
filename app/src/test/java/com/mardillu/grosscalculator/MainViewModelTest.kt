package com.mardillu.grosscalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mardillu.grosscalculator.model.Income
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created on 13/05/2022 at 22:50.
 * @author Ezekiel Sebastine.
 */
class MainViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    val viewModel = MainViewModel()

    @Before
    fun setUp() {
        viewModel._income.value = Income()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `get_income() is non-null`() {
        assertNotNull(viewModel._income)
    }

    @Test
    fun `when isInputAmountValid() is called, return true iff string is valid Double greater than 0`() {
        assertFalse("Validator should return false for 0.0 but found true", viewModel.isInputAmountValid("0.0"))
        assertFalse("Validator should return false for -1 but found true", viewModel.isInputAmountValid("-1"))
        assertTrue("Validator should return true for 0.1 but found false", viewModel.isInputAmountValid("0.1"))
        assertTrue("Validator should return true for 1 but found false", viewModel.isInputAmountValid("1"))
        assertFalse("Validator should return false for 'Hi' but found true", viewModel.isInputAmountValid("Hi"))
        assertFalse("Validator should return false for 10.5e but found true", viewModel.isInputAmountValid("10.5e"))
    }

    @Test
    fun `when getGrossSalary() is called, should return sum`() {
        assertEquals(1350.0, viewModel.getGrossSalary(1000.0, 350.0), 0.0)
        assertEquals(1350.0, viewModel._income.value!!.grossIncome,0.0)
    }

    @Test
    fun `calculateAllowances() should return sum of allowances` (){
        assertEquals(350.0, viewModel.calculateAllowances(
                100.0, 100.0, 150.0
        ), 0.0)
        assertEquals(350.0, viewModel._income.value!!.totalAllowance,0.0)
    }

    @Test
    fun `calculateBasicSalary() should return 60 perc of net amount`() {
        assertEquals(60.0, viewModel.calculateBasicSalary(100.0), 0.0)
        assertEquals(60.0, viewModel._income.value!!.basicIncome, 0.0)
    }

    @Test
    fun `calculatePensionValue() returns sum of employee and employer pension`() {
        assertEquals(28.5, viewModel.calculatePensionValue(100.0), 0.0)
        assertEquals(28.5, viewModel._income.value!!.pension, 0.0)
        assertEquals(18.0, viewModel._income.value!!.employerPension, 0.0)
        assertEquals(10.5, viewModel._income.value!!.employeePension, 0.0)
    }

    @Test
    fun `calculateTaxableIncome() returns difference between gross and pension`() {
        assertEquals(80.0, viewModel.calculateTaxableIncome(100.0, 20.0), 0.0)
        assertEquals(80.0, viewModel._income.value!!.taxableIncome,0.0)
    }

    @Test
    fun `calculateTax() returns 0 if taxable income is less than 110`() {
        assertEquals(0.0, viewModel.calculateTax(100.0), 0.0)
        assertEquals(0.0, viewModel._income.value!!.tax, 0.0)
    }

    @Test
    fun `calculateTax() returns 5_5 if taxable income greater or = 110`() {
        assertEquals(5.5, viewModel.calculateTax(110.0), 0.0)
        assertEquals(5.5, viewModel._income.value!!.tax, 0.0)
    }

    @Test
    fun `calculateTax() returns 18_5 if taxable income greater or = 130`() {
        assertEquals(18.5, viewModel.calculateTax(130.0), 0.0)
        assertEquals(18.5, viewModel._income.value!!.tax, 0.0)
    }

    @Test
    fun `calculateTax() returns 543_5 if taxable income greater or = 3000`() {
        assertEquals(543.5, viewModel.calculateTax(3000.0), 0.0)
        assertEquals(543.5, viewModel._income.value!!.tax, 0.0)
    }

    @Test
    fun `calculateTax() returns 4642_25 if taxable income greater or = 16395`() {
        assertEquals(4642.25, viewModel.calculateTax(16395.0), 0.0)
        assertEquals(4642.25, viewModel._income.value!!.tax, 0.0)
    }

    @Test
    fun `calculateTax() returns 10642_25 if taxable income greater than 20000`() {
        assertEquals(10642.25, viewModel.calculateTax(20000.0), 0.0)
        assertEquals(10642.25, viewModel._income.value!!.tax, 0.0)
    }
}