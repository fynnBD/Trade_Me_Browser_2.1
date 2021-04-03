import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.trademetest20.R
import com.example.trademetest20.datatypes.Listing
import com.squareup.picasso.Picasso
import java.util.zip.Inflater

class RecyclerAdapter(var listings : ArrayList<Listing>, context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.ListingHolder>()
{
    val context = context

    class ListingHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        var image = view.findViewById<ImageView>(R.id.image)
        var title = view.findViewById<TextView>(R.id.title)
        var desc = view.findViewById<TextView>(R.id.desc)
        var holder = view.findViewById<CardView>(R.id.carView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)

        return ListingHolder(v)
    }

    override fun onBindViewHolder(holder: ListingHolder, position: Int) {
        var selectListing = listings[position]
        Picasso.get().load(selectListing.PictureHref).into(holder.image)
        holder.title.text = listings[position].Title
        holder.desc.text = listings[position].ListingId.toString()
        holder.holder.setOnClickListener(View.OnClickListener {
            //gotoListing(listings[position].ListingId)
        })
    }

//    private fun gotoListing(listingId: Long) {
//        var intent = Intent(context, SingleListing::class.java)
//        intent.putExtra("LISTING_ID", listingId.toString())
//        context.startActivity(intent)
//    }
//
    override fun getItemCount(): Int {
        return listings.size
    }
}

