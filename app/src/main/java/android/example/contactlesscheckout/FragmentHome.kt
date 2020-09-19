package android.example.contactlesscheckout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.example.contactlesscheckout.R
import android.example.contactlesscheckout.databinding.FragmentHomeBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentHome : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: FragmentHomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.adapter = MyRecyclerViewAdapter(listOf())

        // Pulling from firestore, will take time to finish query. Add to recycler after done
        viewModel.barcodeResult.observe(viewLifecycleOwner, Observer { documentSnapshot ->
            (recycler_view.adapter as MyRecyclerViewAdapter).setData(documentSnapshot)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.fab.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_fragmentHome_to_cameraActivity)
        }

        viewModel = ViewModelProvider(this).get(FragmentHomeViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
