package org.d3if3118.minikasir.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3118.minikasir.R
import org.d3if3118.minikasir.databinding.ItemHistoriBinding
import org.d3if3118.minikasir.db.MiniKasir
import org.d3if3118.minikasir.model.PurchaseResult
import org.d3if3118.minikasir.model.hitungTotal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoriAdapter : ListAdapter<MiniKasir, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MiniKasir>() {
                override fun areItemsTheSame(
                    oldData: MiniKasir, newData: MiniKasir
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: MiniKasir, newData: MiniKasir
                ): Boolean {
                    return oldData == newData
                }
            }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: MiniKasir) = with(binding) {
            val purchaseResult= item.hitungTotal()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            hasilTextView.text = root.context.getString(
                R.string.hasil_x,
                item.namaPelanggan,
                purchaseResult.totalBelanja
            )
            dataTextView.text = root.context.getString(
                R.string.data_x,
                item.namaBarang, item.harga, item.jumlahBeli,item.uangBayar
            )
        }
    }
}