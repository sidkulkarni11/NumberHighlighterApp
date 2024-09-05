package com.sid.numberhighlighter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor() : ViewModel() {

    private val _numbers = MutableStateFlow<List<NumberModel>>(emptyList())
    val numbers: StateFlow<List<NumberModel>> = _numbers

    init {
        generateNumbers()
    }

    private fun generateNumbers() {
        val numberList = (1..100).map { NumberModel(it) }
        _numbers.value = numberList
    }

    fun applyRule(rule: RuleType) {
        _numbers.value = _numbers.value.map { number ->
            number.copy(isHighlighted = rule.checkCondition(number.value))
        }
    }
}


