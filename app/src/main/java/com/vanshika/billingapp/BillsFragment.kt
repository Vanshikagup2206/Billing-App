package com.vanshika.billingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.vanshika.billingapp.databinding.FragmentBillsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BillsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding : FragmentBillsBinding ?= null
    var mainActivity : MainActivity ?= null
    lateinit var arrayAdapter : ArrayAdapter<DataAdapterClass>
    var array = arrayListOf<DataAdapterClass>()
    var number = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            mainActivity = activity as MainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1,array)
        binding?.spinner?.adapter = arrayAdapter
//        binding?.tvItems?.setText(mainActivity?.binding?.toString())
//        binding?.tvQuantity?.setText(mainActivity?.binding?.lvItems?.toString())
        fun numberIncrement(){
            number++
            binding?.tvQuantity?.setText(number.toString())
        }
        fun numberDecrement(){
            number--
            binding?.tvQuantity?.setText(number.toString())
        }
        binding?.btnPlus?.setOnClickListener {
            if (binding?.tvQuantity?.text?.toString()?.trim()!! >= mainActivity?.binding?.lvItems?.toString().toString()){
                binding?.tvQuantity?.error = resources.getString(R.string.out_of_limit)
            }else{
                numberIncrement()
            }
        }
        binding?.btnMinus?.setOnClickListener {
            if (binding?.tvQuantity?.text?.toString()?.trim()!! <= "0"){
                binding?.tvQuantity?.error = resources.getString(R.string.out_of_limit)
            }else{
                numberDecrement()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BillsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BillsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}