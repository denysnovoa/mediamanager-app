package com.dnovoa.deluge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dnovoa.deluge.R
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DelugeFragment : Fragment() {

    companion object {
        fun newInstance() = DelugeFragment()
    }

    private val viewModel by viewModels<DelugeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.deluge_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            viewModel.login()
                .catch { exception -> notifyError(exception) }
                .collect { text ->
                    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun notifyError(exception: Throwable) {
        Toast.makeText(requireContext(), exception.message.orEmpty(), Toast.LENGTH_LONG).show()
    }
}