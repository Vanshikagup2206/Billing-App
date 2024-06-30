package com.vanshika.billingapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanshika.billingapp.databinding.CustomDialogBinding
import com.vanshika.billingapp.databinding.CustomDialogForUpdateBinding
import com.vanshika.billingapp.databinding.FragmentItemsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentItemsBinding? = null
    var mainActivity: MainActivity? = null
    var itemArray = arrayListOf<DataAdapterClass>()
    var adapter = BaseAdapterClass(itemArray)

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
        binding = FragmentItemsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity?.binding?.lvItems?.adapter = adapter
        binding?.fabItems?.setOnClickListener {
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                show()
            }
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etEnterName.text.toString().trim().isEmpty()) {
                    dialogBinding.etEnterName.error = resources.getString(R.string.enter_name)
                } else if (dialogBinding.etQuantity.text.toString().trim().isEmpty()) {
                    dialogBinding.etQuantity.error = resources.getString(R.string.enter_quantity)
                } else {
                    itemArray.add(DataAdapterClass(
                            dialogBinding.etEnterName.text.toString(),
                            dialogBinding.etQuantity.text.toString().toInt()
                        )
                    )
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }
        mainActivity?.binding?.lvItems?.setOnItemClickListener { adapterView, view, i, l ->
            val dialogBinding = CustomDialogForUpdateBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                show()
            }
            dialogBinding.btnUpdate.setOnClickListener {
                if (dialogBinding.etEnterNameForUpdate.text.toString().trim().isEmpty()) {
                    dialogBinding.etEnterNameForUpdate.error = resources.getString(R.string.enter_name)
                } else if (dialogBinding.etQuantityForUpdate.text.toString().trim().isEmpty()) {
                    dialogBinding.etQuantityForUpdate.error = resources.getString(R.string.enter_quantity)
                } else {
                    itemArray.set(i, DataAdapterClass(
                            dialogBinding.etEnterNameForUpdate.text.toString(),
                            dialogBinding.etQuantityForUpdate.text.toString().toInt()
                        )
                    )
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }
        mainActivity?.binding?.lvItems?.setOnItemLongClickListener { adapterView, view, i, l ->
            var alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle(resources.getString(R.string.are_you_sure_you_want_to_delete_this_list))
            alertDialog.setPositiveButton(resources.getString(R.string.update)) { _, _ ->
                itemArray.removeAt(i)
                adapter.notifyDataSetChanged()
            }
            return@setOnItemLongClickListener true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}