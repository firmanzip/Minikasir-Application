package org.d3if3118.minikasir.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3118.minikasir.R
import org.d3if3118.minikasir.databinding.FragmentHitungBinding
//import org.d3if3118.minikasir.databinding.FragmentHitungBinding
import org.d3if3118.minikasir.db.KasirDb
import org.d3if3118.minikasir.model.PurchaseResult

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = KasirDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchDarkMode.isChecked = viewModel.isDarkMode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isDarkMode = isChecked
            viewModel.setDarkMode()
            requireActivity().recreate()
        }
        binding.btnProses.setOnClickListener{hitungTotal()
        }
        binding.btnReset.setOnClickListener { resetInput() }

        binding.btnList.setOnClickListener{it.findNavController().navigate(
            R.id.action_hitungFragment_to_tokoFragment
        )
    }
        viewModel.getHasil().observe(requireActivity(), { showResult(it) })
        binding.btnShare.setOnClickListener {shareData()}
        }

        private fun shareData() {
            val message = getString(R.string.bagikan_template,
            binding.etNamaPelanggan.text,
                binding.etNamaBarang.text,
                binding.etJumlahBeli.text,
                binding.etHarga.text,
                binding.etUangBayar.text,
                binding.tvTotalBelanja.text
            )

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
            if(shareIntent.resolveActivity(
                    requireActivity().packageManager) != null){
                startActivity(shareIntent)
            }
        }


    private fun showResult(result: PurchaseResult?) {
        if (result == null) return
        binding.btnShare.visibility = View.VISIBLE
        binding.tvTotalBelanja.visibility = View.VISIBLE
        binding.tvTotalBelanja.text = getString(R.string.result_x,result.totalBelanja)
        binding.tvKeterangan.visibility = View.VISIBLE
        binding.tvKeterangan.text = result.keterangan


    }

    private fun hitungTotal(){
        val namaPelanggan = binding.etNamaPelanggan.text
        if(namaPelanggan.isNullOrEmpty()){
        Toast.makeText(context,"Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }
        val namaBarang = binding.etNamaBarang.text
        if(namaBarang.isNullOrEmpty()){
            Toast.makeText(context, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }
        val jumlahBeli = binding.etJumlahBeli.text.toString()
        if(jumlahBeli.isNullOrEmpty()){
            Toast.makeText(context, "Jumlah Beli Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }
        val harga = binding.etHarga.text.toString()
        if (harga.isNullOrEmpty()){
            Toast.makeText(context, "Harga Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }
        val uangBayar = binding.etUangBayar.text.toString()
        if (uangBayar.isNullOrEmpty()){
            Toast.makeText(context, "Uang Bayar Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungTotal(
            namaPelanggan.toString(),
            namaBarang.toString(),
            jumlahBeli.toFloat(),
            harga.toFloat(),
            uangBayar.toFloat()
        )
    }


    private fun resetInput() {
        with(binding) {
            etNamaPelanggan.text?.clear()
            etNamaBarang.text?.clear()
            etJumlahBeli.text?.clear()
            etHarga.text?.clear()
            etUangBayar.text?.clear()
            binding.tvTotalBelanja.visibility = View.GONE
            binding.tvKeterangan.visibility = View.GONE
            binding.btnShare.visibility = View.GONE
        }
    }

//    private fun showToast(message: String) {
//        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
//    }
}
