package android.example.contactlesscheckout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class FragmentHomeViewModel : ViewModel() {
    private val barcodeQuery: MutableLiveData<List<DocumentSnapshot>> = MutableLiveData()

    val barcodeResult: MutableLiveData<List<DocumentSnapshot>> get() = barcodeQuery

    init {
        FirebaseFirestore.getInstance()
            .collection("items")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    barcodeQuery.value = task.result!!.documents
                }
            }
    }
}