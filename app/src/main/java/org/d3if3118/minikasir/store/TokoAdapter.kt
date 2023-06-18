package org.d3if3118.minikasir.store

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3118.minikasir.R
import org.d3if3118.minikasir.databinding.ListTokoBinding
import org.d3if3118.minikasir.internet.TokoApi
import org.d3if3118.minikasir.model.Toko

class TokoAdapter : RecyclerView.Adapter<TokoAdapter.ViewHolder>() {
    private val data = mutableListOf<Toko>()
    fun updateData(newData: List<Toko>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListTokoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toko: Toko) = with(binding) {
            namaTextView.text = toko.namaToko
            alamatTextView.text = toko.alamatToko
            Glide.with(imageView.context)
                .load(TokoApi.getTokoUrl(toko.imageId))
                .error(R.drawable.broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, toko.namaToko)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListTokoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}