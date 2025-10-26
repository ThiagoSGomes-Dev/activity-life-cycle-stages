package br.com.statelab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.statelab.databinding.BottomSheetdialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface OnButtonClickedListener {
    fun onButtonToSheetClick()
}

class BottomSheetDialogFragment: BottomSheetDialogFragment() {

    private var listener: OnButtonClickedListener? = null
    private var _binding: BottomSheetdialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetdialogFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonConfirmr.setOnClickListener {
                listener?.onButtonToSheetClick()

                dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnButtonClickedListener) {
            listener = context
        } else {
            throw RuntimeException("$context deve implementar OnButtonClickedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}