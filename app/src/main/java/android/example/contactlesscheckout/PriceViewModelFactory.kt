package android.example.contactlesscheckout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PriceViewModelFactory(private val barcode: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PriceViewModel::class.java)) {
            return PriceViewModel(barcode) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}