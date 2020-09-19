package android.example.contactlesscheckout

import android.example.contactlesscheckout.databinding.ActivityPriceBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class PriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceBinding
    private lateinit var viewModel: PriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val barcode = intent.getStringExtra("barcode")

        binding = ActivityPriceBinding.inflate(layoutInflater)

        val factory = PriceViewModelFactory(barcode)
        viewModel = ViewModelProvider(this, factory).get(PriceViewModel::class.java)
        viewModel.emptyMessageData.observe(this, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.addedMessageData.observe(this, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)
    }
}
