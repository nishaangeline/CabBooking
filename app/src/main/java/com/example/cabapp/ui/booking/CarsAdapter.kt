package com.example.cabapp.ui.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.cabapp.R
import com.example.cabapp.data.entity.Car


class CarsAdapter(context: Context, private val resourceId: Int, private var list: List<Car>) :
    ArrayAdapter<Car>(context, resourceId) {

//    var onItemClickListener: OnItemClickListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
        val car: Car = list[position]
        val textViewModelName = view?.findViewById(R.id.textView) as TextView
        textViewModelName.text = car.modelName// + " (" + car.vehicleNo + ")"
        return view
    }

    fun updateList(newList: List<Car>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Car {
        return list[position]
    }

    override fun getFilter(): Filter {
        return nameFilter
    }

    override fun getCount(): Int {
        return list.size
    }

//    interface OnItemClickListener {
//        fun onClick(car: Car)
//    }

    var nameFilter: Filter = object : Filter() {
        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as Car).modelName.toString()
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {}
    }
}