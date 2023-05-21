package org.d3if3118.minikasir.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if3118.minikasir.databinding.FragmentPrintBinding
import org.d3if3118.minikasir.model.BonusType

class PrintFragment : Fragment() {
    private var _binding: FragmentPrintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalBelanja = arguments?.getString("totalBelanja")
        val keterangan = arguments?.getString("keterangan")
        val bonusType = arguments?.getSerializable("bonusType") as? BonusType
        val bonusDescription = bonusType?.description ?: "Tidak ada bonus"
        binding.tvBonus.text = bonusDescription

        binding.tvTotalBelanja.text = totalBelanja
        binding.tvKeterangan.text = keterangan
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//val bonusType = arguments?.getSerializable("bonusType") as? BonusType
//val bonusDescription = bonusType?.description ?: "Tidak ada bonus"
//binding.tvBonus.text = bonusDescription
