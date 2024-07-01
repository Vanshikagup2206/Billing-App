package com.vanshika.billingapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vanshika.billingapp.databinding.CustomDialogBinding
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
    lateinit var adapter: BaseAdapterClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
        adapter = BaseAdapterClass(mainActivity?.itemArray?: arrayListOf())

        binding?.listView?.adapter = adapter
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
                    mainActivity?.itemArray?.add(DataAdapterClass(
                            dialogBinding.etEnterName.text.toString(),
                            dialogBinding.etQuantity.text.toString().toInt()
                        )
                    )
                    adapter.notifyDataSetChanged()
                    val bundle = Bundle()
                    bundle.putString("item",mainActivity?.itemArray?.toString())
                    findNavController().navigate(R.id.billsFragment,bundle)
                    dialog.dismiss()
                }
            }
        }
        binding?.listView?.setOnItemClickListener { adapterView, view, i, l ->
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                dialogBinding.btnAdd.setText(resources.getString(R.string.update))
                getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                show()
            }
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etEnterName.text.toString().trim().isEmpty()) {
                    dialogBinding.etEnterName.error = resources.getString(R.string.enter_name)
                } else if (dialogBinding.etQuantity.text.toString().trim().isEmpty()) {
                    dialogBinding.etQuantity.error = resources.getString(R.string.enter_quantity)
                } else {
                    mainActivity?.itemArray?.set(i, DataAdapterClass(
                            dialogBinding.etEnterName.text.toString(),
                            dialogBinding.etQuantity.text.toString().toInt()
                        )
                    )
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }
        binding?.listView?.setOnItemLongClickListener { adapterView, view, i, l ->
            var alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle(resources.getString(R.string.are_you_sure_you_want_to_delete_this_list))
            alertDialog.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                mainActivity?.itemArray?.removeAt(i)
                adapter.notifyDataSetChanged()
            }
            alertDialog.setNegativeButton(resources.getString(R.string.no)){ _,_ ->
            }
            alertDialog.show()
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