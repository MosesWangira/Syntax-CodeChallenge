package com.example.syntaxcodechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.syntaxcodechallenge.R
import com.example.syntaxcodechallenge.adapter.JsonResultAdapter
import com.example.syntaxcodechallenge.databinding.FragmentHomeBinding
import com.example.syntaxcodechallenge.domain.JsonResult
import com.example.syntaxcodechallenge.viewmodels.HomeViewModel
import com.example.syntaxcodechallenge.utils.animate

class Home : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, HomeViewModel.Factory(activity.application))
            .get(HomeViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of items to cards.
     */
    private var viewModelAdapter: JsonResultAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.jsonlist.observe(viewLifecycleOwner, { myJsonResult ->
            myJsonResult?.apply {
                viewModelAdapter?.json = myJsonResult
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel


        viewModelAdapter = JsonResultAdapter(JsonClick {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
            layoutAnimation = animate(requireContext(), R.anim.layout_animation_fall_down)
        }


        // Observer for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
//            requireContext().toast("No Internet...loading previously loaded data")
            viewModel.onNetworkErrorShown()
        }
    }

}

/**
 * Click listener for Videos. By giving the block a name it helps a reader understand what it does.
 *
 */
class JsonClick(val block: (JsonResult) -> Unit) {

    fun onClick(attack: JsonResult) = block(attack)
}