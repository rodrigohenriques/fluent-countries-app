package com.rodrigohenriques.countries.feature.countries

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigohenriques.countries.R
import com.rodrigohenriques.countries.data.valueobjects.Country
import com.rodrigohenriques.countries.util.load
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.Holder>() {

  private var data: List<Country> = emptyList()

  private val itemClicksSubject = PublishSubject.create<Country>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemView = layoutInflater.inflate(R.layout.item_country, parent, false)
    return Holder(itemView)
  }

  override fun onBindViewHolder(holder: Holder, position: Int) {
    val suggestion = data[position]
    holder.bind(suggestion)
  }

  override fun getItemCount(): Int = data.size

  fun changeData(newData: List<Country>) {
    val diffCallback = DiffCallback(data, newData)
    val diffResult = DiffUtil.calculateDiff(diffCallback)

    data = newData

    diffResult.dispatchUpdatesTo(this)
  }

  fun itemClicks(): Observable<Country> = itemClicksSubject

  inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(country: Country) {
      itemView.imageViewFlag.load(country.flagUrl())
      itemView.textViewName.text = country.name
      itemView.setOnClickListener { itemClicksSubject.onNext(country) }
    }
  }

  private inner class DiffCallback(
      val oldList: List<Country>,
      val newList: List<Country>
  ) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition].alpha3Code == newList[newItemPosition].alpha3Code
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }
  }
}