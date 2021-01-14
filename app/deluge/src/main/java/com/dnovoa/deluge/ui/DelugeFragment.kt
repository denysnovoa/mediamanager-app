package com.dnovoa.deluge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dnovoa.deluge.databinding.DelugeFragmentBinding
import com.dnovoa.deluge.di.DelugeServiceLocator
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        binding.delugeLogin.setOnClickListener {
            lifecycleScope.launch {
                viewModel.login()
                    .catch { notifyError(it) }
                    .collect {
                        binding.message.text = it?.userSession?.session
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun notifyError(exception: Throwable) {
        Toast.makeText(requireContext(), exception.message.orEmpty(), Toast.LENGTH_LONG).show()
    }
}