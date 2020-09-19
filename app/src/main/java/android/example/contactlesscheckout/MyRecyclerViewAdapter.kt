package android.example.contactlesscheckout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.model.Document
import kotlinx.android.synthetic.main.list_item.view.*

class MyRecyclerViewAdapter(var documentSnapShots: List<DocumentSnapshot>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)

        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return documentSnapShots.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val document = documentSnapShots[position]
        holder.bind(document)
    }

    fun setData(newData : List<DocumentSnapshot>) {
        documentSnapShots = newData
        notifyDataSetChanged()
    }

    fun getReviewAt(position: Int) : DocumentSnapshot {
        return documentSnapShots.get(position)
    }
}

class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    fun bind(document: DocumentSnapshot) {
        val documentData = document.data
        view.barcode_name.text = document.id
        //view.description_text_view.text = review.description
    }
}