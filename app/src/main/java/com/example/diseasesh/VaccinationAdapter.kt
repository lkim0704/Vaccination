package com.example.diseasesh

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccinationAdapter(var dataSet: List<Vaccination>) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNumber : TextView
        val textViewCountry : TextView
        val textViewCountryDescription : TextView
        val layout : ConstraintLayout
        val line : View

        init {
            // Define click listener for the ViewHolder's View.
            textViewNumber = view.findViewById(R.id.textView_vaccinationItem_number)
            textViewCountry = view.findViewById(R.id.textView_vaccinationItem_country)
            textViewCountryDescription = view.findViewById(R.id.textView_vaccinationItem_countryDescription)
            layout = view.findViewById(R.id.layout_vaccinationItem)
            line = view.findViewById(R.id.view_vaccinationItem_line)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_vaccination, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val vaccination = dataSet[position]
        viewHolder.textViewNumber.text = (position+1).toString()
        viewHolder.textViewCountry.text = vaccination.country
        viewHolder.textViewCountryDescription.text =
            "Today Total Vaccines Rolled Out: " + vaccination.timeline.values.elementAt(4).toString()

        viewHolder.layout.setOnClickListener {
            val context = viewHolder.layout.context
            val vaccinationDetailIntent = Intent(context, VaccinationDetail::class.java).apply{
                putExtra(VaccinationDetail.EXTRA_VACCINATION, vaccination)
            }
            context.startActivity(vaccinationDetailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}