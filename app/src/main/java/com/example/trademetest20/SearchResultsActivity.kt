package com.example.trademetest20

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle

class SearchResultsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("yooooo")
        super.onCreate(savedInstanceState)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        println("fuk")
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            println("AKASDOKSODKSODK")
            println(query)
        }
        println("AAKAKAKA")
    }
}