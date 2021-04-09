package com.example.trademetest20

import RecyclerAdapter
import android.animation.LayoutTransition
import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trademetest20.datatypes.Category
import com.example.trademetest20.datatypes.SearchResults
import com.example.trademetest20.networking.ServiceBuilder
import com.example.trademetest20.networking.networkInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var rootCategory : Category? = null
    var SearchResults : SearchResults? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val l = LayoutTransition()
        l.enableTransitionType(LayoutTransition.CHANGING)
        var viewGroup = findViewById<View>(R.id.navBar) as ViewGroup
        viewGroup.setLayoutTransition(l)

        URLQuery("0")

        findViewById<LinearLayout>(R.id.navBarBottom).clipToOutline = true
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun onClickCollapse(view: View)
    {
        var navBarTop = findViewById<LinearLayout>(R.id.navBarTop)

        if(navBarTop.height == 0) {
            navBarTop.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600)
        }
        else{
            navBarTop.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        }
    }

    fun refreshListings()
    {
        var listings = findViewById<RecyclerView>(R.id.listingViews)
        if(SearchResults?.List != null)
        {
            var listingAdapter = SearchResults!!.List?.let { RecyclerAdapter(it, this) }
            listings.layoutManager = LinearLayoutManager(this)

            println("connecting adapter")
            listings.adapter = listingAdapter
        }


    }

    fun refreshCats()
    {
        println(rootCategory?.Name)
        var cats = findViewById<ListView>(R.id.cats)
        if (rootCategory?.Name == "Root")
        {
            var array = getCatArray()
            var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
            cats.adapter = arrayAdapter
        }
        else
        {
            var array = arrayOf("<--") + getCatArray()
            var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
            cats.adapter = arrayAdapter
        }

        cats.setOnItemClickListener(categoryOnClickListener(this))

    }

    fun getCatArray(): ArrayList<String> {
        var array = ArrayList<String>()
        for(i in rootCategory?.Subcategories!!)
        {
            array.add(i.Name.toString())
        }
        return array
    }


    /*
 
     */
    fun URLQuery(id: String?) {
        val request = ServiceBuilder.buildService(networkInterface::class.java)
        val call = request.getRoot(id)

        call.enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if (response.isSuccessful) {
                    rootCategory = response.body()
                    if(rootCategory?.Subcategories != null) {
                        refreshCats()
                    }
                    listingQuery(rootCategory?.Number, "")
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                var textView = findViewById<TextView>(R.id.text)
                println("---------------------------------------------------------")
                t.printStackTrace()
                println("---------------------------------------------------------")
            }

        })
    }

    fun listingQuery(id: String?, searchString: String?)
    {
        val request = ServiceBuilder.buildService(networkInterface::class.java)
        val call = request.getListing(id, 20, searchString)

        call.enqueue(object : Callback<SearchResults> {
            override fun onResponse(call: Call<SearchResults>, response: Response<SearchResults>) {
                if (response.isSuccessful) {
                    SearchResults = response.body()
                    refreshListings()
                } else {
                    Toast.makeText(baseContext, "Fail!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SearchResults>, t: Throwable) {
                Toast.makeText(baseContext, "Hard Fail", Toast.LENGTH_LONG).show()
                println("---------------------------------------------------------")
                t.printStackTrace()
                println("---------------------------------------------------------")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchButton : MenuItem = menu.findItem(R.id.search)
        (searchButton.actionView as SearchView).isQueryRefinementEnabled = true

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
       (searchButton.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        (searchButton.actionView as SearchView).setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listingQuery("0", query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

}