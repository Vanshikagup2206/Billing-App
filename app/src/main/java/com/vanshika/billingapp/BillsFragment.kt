package com.vanshika.billingapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
    private var binding : FragmentBillsBinding ?= null
    private var mainActivity : MainActivity ?= null
    private lateinit var arrayAdapter : ArrayAdapter<DataAdapterClass>
    private var item = ""
    var number = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            item = it.getString("item")?:""
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
        arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1,mainActivity?.itemArray?: arrayListOf())
        binding?.spinner?.adapter = arrayAdapter
        var selectedItem = binding?.spinner?.selectedItem as DataAdapterClass
        binding?.spinner?.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        var selectedItems = binding?.spinner?.selectedItem as DataAdapterClass
                        binding?.tvItems?.setText(selectedItems.name)
                        binding?.tvQuantity?.setText(selectedItems.quality.toString())
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
        binding?.btnPlus?.setOnClickListener {
            if (binding?.tvQuantity?.text?.toString()?.trim()?.toInt()!! >= selectedItem.quality.toString().toInt()){
                Toast.makeText(requireContext(), resources.getString(R.string.out_of_limit), Toast.LENGTH_SHORT).show()
            }else{
                number += 1
                binding?.tvQuantity?.setText(number.toString())
            }
        }
        binding?.btnMinus?.setOnClickListener {
            if (binding?.tvQuantity?.text?.toString()?.trim()?.toInt()!! <= 1){
                Toast.makeText(requireContext(), resources.getString(R.string.out_of_limit), Toast.LENGTH_SHORT).show()
            }else{
                number -= 1
                binding?.tvQuantity?.setText(number.toString())
            }
        }
        binding?.btnOrder?.setOnClickListener {
            if (binding?.tvItems?.text?.toString()?.isEmpty() == true){
                Toast.makeText(requireContext(), resources.getString(R.string.select_an_item), Toast.LENGTH_SHORT).show()
            }else if (binding?.tvQuantity?.text?.toString()?.isEmpty() == true){
                Toast.makeText(requireContext(), resources.getString(R.string.select_the_quantity), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), resources.getString(R.string.order_placed), Toast.LENGTH_SHORT).show()
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