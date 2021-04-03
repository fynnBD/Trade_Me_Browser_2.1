package com.example.trademetest20.datatypes

data class SearchResults(val TotalCount : Int,
                         val TotalCountTruncated : Boolean,
                         val Page : Int,
                         val PageSize: Int,
                         val List : ArrayList<Listing>?)   {
}