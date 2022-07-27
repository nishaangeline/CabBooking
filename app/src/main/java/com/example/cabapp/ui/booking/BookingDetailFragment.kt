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
import com.example.cabapp.data.entity.BookingDetail
import com.example.cabapp.databinding.FragmentBookingDetailBinding
import java.util.*

class BookingDetailFragment : Fragment() {

    private var fragmentBookingDetailBinding: FragmentBookingDetailBinding? = null
    private val binding get() = fragmentBookingDetailBinding!!
    private var datePickerDialog: DatePickerDialog? = null
    private lateinit var bookingDetailViewModel: BookingDetailViewModel

    val args: BookingDetailFragmentArgs by navArgs()
    private val calendar: Calendar = Calendar.getInstance()
    private var months = listOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    var departDateTimeInMillis = 0L

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
        binding.editTextDepartDate.showSoftInputOnFocus = false

        val onFocusChangeLister = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePicker(view)
            }
        }
        binding.editTextDepartDate.onFocusChangeListener = onFocusChangeLister
        binding.editTextArrivalDate.onFocusChangeListener = onFocusChangeLister

        binding.buttonBookCar.setOnClickListener {
            bookingDetailViewModel.bookCar(BookingDetail(
                binding.editTextDepartDate.text.toString(),
                binding.editTextArrivalDate.text.toString(),
                binding.editTextFromloc.text.toString(),
                binding.editTextToloc.text.toString(),
                binding.editTextPickup.text.toString(),
                args.car.vehicleNo
            ))
        }
    }

    private fun showDatePicker(view: View) {
        val inputMethodManager =
            (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(
                activity!!,
                { view, year, month, dayOfMonthInInt ->
                    var dayOfMonth = dayOfMonthInInt.toString() + ""
                    if (dayOfMonth.length == 1) { dayOfMonth = "0$dayOfMonth" }
                    val dateInString = dayOfMonth + "-" + months[month] + "-" + year
                    if (binding.editTextDepartDate.hasFocus()) {
                        calendar.apply {
                            set(Calendar.YEAR, year)
                            set(Calendar.MONTH, month)
                            set(Calendar.DAY_OF_MONTH, dayOfMonthInInt)
                        }
                        departDateTimeInMillis = calendar.timeInMillis
                        binding.editTextDepartDate.setText(dateInString)
                    } else {
                        binding.editTextArrivalDate.setText(dateInString)
                    }
                    //                        EXP_Date = year.toString() + "-" + months.get(month)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog?.datePicker?.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
//        }
        if (binding.editTextDepartDate.hasFocus()) {
            datePickerDialog?.datePicker?.minDate = Calendar.getInstance().also {
                calendar.timeInMillis = it.timeInMillis
            }.timeInMillis
        } else {
            datePickerDialog?.datePicker?.minDate = departDateTimeInMillis
        }
        datePickerDialog?.show()
    }
}