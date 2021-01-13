package com.dnovoa.mediamanager.ui.deluge

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnovoa.mediamanager.R

class DelugeFragment : Fragment() {

    companion object {
        fun newInstance() = DelugeFragment()
    }

    private lateinit var viewModel: DelugeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.deluge_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DelugeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}