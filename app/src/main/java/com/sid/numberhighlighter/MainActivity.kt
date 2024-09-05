package com.sid.numberhighlighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.sid.numberhighlighter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    data class NumberModel(val value: Int, var isHighlighted: Boolean = false)

    private val viewModel: NumberViewModel by viewModels()
    private lateinit var adapter: NumberAdapter
    private lateinit var binding: ActivityMainBinding // Generated binding class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NumberAdapter()

        binding.numberGrid.layoutManager = GridLayoutManager(this, 4)
        binding.numberGrid.adapter = adapter

        viewModel.numbers.collectInLifecycle(this) { numbers ->
            adapter.submitList(numbers)
        }

        binding.oddRuleBtn.setOnClickListener {
            viewModel.applyRule(RuleType.Odd)
        }

        binding.evenRuleBtn.setOnClickListener {
            viewModel.applyRule(RuleType.Even)
        }

        binding.primeRuleBtn.setOnClickListener {
            viewModel.applyRule(RuleType.Prime)
        }

        binding.fibonacciRuleBtn.setOnClickListener {
            viewModel.applyRule(RuleType.Fibonacci)
        }
    }

    fun <T> Flow<T>.collectInLifecycle(
        lifecycleOwner: LifecycleOwner,
        collector: suspend (T) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect(collector)
            }
        }
    }

}
