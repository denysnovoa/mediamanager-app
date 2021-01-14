package com.dnovoa.deluge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dnovoa.deluge.databinding.DelugeFragmentBinding
import com.dnovoa.deluge.di.DelugeServiceLocator
import kotlinx.coroutines.flow.collect

class DelugeFragment : Fragment() {

    companion object {
        fun newInstance() = DelugeFragment()
    }

    private val viewModel by viewModels<DelugeViewModel>(
        factoryProducer = {
            DelugeViewModelFactory(
                DelugeServiceLocator.initDelugeRepository()
            )
        }
    )

    private var _binding: DelugeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DelugeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.loginIsVisible.collect { binding.delugeLogin.isVisible = it }
            viewModel.showMessage.collect { notifyMessage(it) }
        }

        binding.delugeLogin.setOnClickListener {
            viewModel.login()
        }

        binding.delugeSpeed.setOnClickListener {
            viewModel.updatedSpeedDownload(binding.delugeSpeedValue.text.toString().toInt())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun notifyMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}