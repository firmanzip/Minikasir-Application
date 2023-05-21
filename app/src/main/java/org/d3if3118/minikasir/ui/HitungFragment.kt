package org.d3if3118.minikasir.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3118.minikasir.R
import org.d3if3118.minikasir.databinding.FragmentHitungBinding
import org.d3if3118.minikasir.model.Purchase

class HitungFragment : Fragment() {
    private var _binding: FragmentHitungBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HitungViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHitungBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(HitungViewModel::class.java)

        binding.switchDarkMode.isChecked = viewModel.isDarkMode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isDarkMode = isChecked
            viewModel.setDarkMode()
            requireActivity().recreate()
        }

        binding.btnReset.setOnClickListener {
            resetInput()
        }

        binding.btnProses.setOnClickListener {
            val namaPelanggan = binding.etNamaPelanggan.text.toString()
            if (!viewModel.validateInput(namaPelanggan)) {
                showToast(getString(R.string.nama_invalid))
                return@setOnClickListener
            }
            val namaBarang = binding.etNamaBarang.text.toString()
            val jumlahBeli = binding.etJumlahBeli.text.toString().toInt()
            val harga = binding.etHarga.text.toString().toInt()
            val uangBayar = binding.etUangBayar.text.toString().toInt()
            viewModel.hideKeyboard(requireActivity())
            val purchase = Purchase(namaPelanggan, namaBarang, jumlahBeli, harga, uangBayar)
            val purchaseResult = viewModel.calculatePurchaseResult(purchase)
            val bundle = Bundle().apply {
                putString("totalBelanja", purchaseResult.getTotalBelanjaText())
                putString("keterangan", purchaseResult.keterangan)
            }
            findNavController().navigate(
                R.id.action_hitungFragment_to_printFragment,
                bundle
            )

//            binding.tvTotalBelanja.text = purchaseResult.getTotalBelanjaText()
//            binding.tvKeterangan.text = purchaseResult.keterangan
//
//            val intent = Intent(requireContext(), PrintActivity::class.java)
//            intent.putExtra("totalBelanja", purchaseResult.getTotalBelanjaText())
//            intent.putExtra("keterangan", purchaseResult.keterangan)
//            intent.putExtra("bonusType", purchaseResult.bonusType)
//            startActivity(intent)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun resetInput() {
        with(binding) {
            etNamaPelanggan.text?.clear()
            etNamaBarang.text?.clear()
            etJumlahBeli.text?.clear()
            etHarga.text?.clear()
            etUangBayar.text?.clear()
            tvTotalBelanja.text = ""
            tvKeterangan.text = ""
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
