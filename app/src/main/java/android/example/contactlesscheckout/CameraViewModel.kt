package android.example.contactlesscheckout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CameraViewModel : ViewModel() {
    private val barcode = MutableLiveData<String>()
    private val db = Firebase.firestore

    val barcodeData: LiveData<String> get() = barcode

}