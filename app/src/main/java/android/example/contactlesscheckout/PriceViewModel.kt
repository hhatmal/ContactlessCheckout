package android.example.contactlesscheckout

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PriceViewModel(barcode: String) : ViewModel(), Observable {
    @Bindable
    private val name = MutableLiveData<String>()
    @Bindable
    private val price = MutableLiveData<String>()
    private val emptyMessage = MutableLiveData<Event<String>>()
    private val addedMessage = MutableLiveData<Event<String>>()
    private val barcode = barcode

    // getters
    val nameData: MutableLiveData<String> get() = name
    val barcodeData: String get() = barcode
    val emptyMessageData: MutableLiveData<Event<String>> get() = emptyMessage
    val addedMessageData: MutableLiveData<Event<String>> get() = addedMessage
    val priceData: MutableLiveData<String> get() = price

    fun addPrice() {
        if(price.value.isNullOrEmpty()) {
            emptyMessage.value = Event("Enter a Value!")
            return
        }

        val db = Firebase.firestore
        val priceValue = price.value?.toDouble()

        val item = hashMapOf(
            "name" to nameData.value,
            "price" to priceValue
        )

        db.collection("items").document(barcode)
            .set(item)
            .addOnSuccessListener {
                addedMessage.value = Event("$barcode has been added")
            }
            .addOnFailureListener { e -> Log.w("test", "Error writing document", e) }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}