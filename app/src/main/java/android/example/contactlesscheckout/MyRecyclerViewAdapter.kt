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
}

class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    fun bind(document: DocumentSnapshot) {
        val name = document.get("name").toString()
        val price = document.get("price").toString()

        view.barcode_name.text = document.id
        view.item_name.text = name
        view.item_price.text = "$$price"
    }
}