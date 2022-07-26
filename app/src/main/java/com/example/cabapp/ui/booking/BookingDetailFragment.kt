package com.example.cabapp.ui.booking

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.cabapp.databinding.FragmentBookingDetailBinding
import java.util.*

class BookingDetailFragment : Fragment() {

    private var fragmentBookingDetailBinding: FragmentBookingDetailBinding? = null
    private val binding get() = fragmentBookingDetailBinding!!
    private var datePickerDialog: DatePickerDialog? = null
    private lateinit var bookingDetailViewModel: BookingDetailViewModel

    val args: BookingDetailFragmentArgs by navArgs()
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBookingDetailBinding = FragmentBookingDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingDetailViewModel = ViewModelProvider(this).get(BookingDetailViewModel::class.java)
        binding.textCarName.text = args.car.modelName
        binding.textVehicleNumber.text = args.car.vehicleNo
        binding.editTextVehno.showSoftInputOnFocus = false

        binding.editTextVehno.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                showDatePicker(view)
            }
        }
    }

    private fun showDatePicker(view: View) {
        val inputMethodManager =
            (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(
                activity!!,
                { view, year, month, dayOfMonth ->
                    var da = dayOfMonth.toString() + ""
                    if (da.length == 1) da = "0$da"
                    //                        date.setText(da + "-" + months.get(month) + "-" + year)
                    //                        EXP_Date = year.toString() + "-" + months.get(month)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog?.datePicker?.minDate = calendar.timeInMillis
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            datePickerDialog?.datePicker?.maxDate = calendar.timeInMillis
            datePickerDialog?.datePicker?.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
        }
        datePickerDialog?.show()
    }


}