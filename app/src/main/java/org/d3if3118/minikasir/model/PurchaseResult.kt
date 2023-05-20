package org.d3if3118.minikasir.model

data class PurchaseResult(
    val totalBelanja: Int,
    val uangKembali: Int,
    val bonusType: BonusType,
    val keterangan: String
) {
    fun getTotalBelanjaText(): String {
        return "Total belanja: Rp. $totalBelanja"
    }
}
