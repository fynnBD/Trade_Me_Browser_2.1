package com.example.trademetest20.datatypes

data class Category(val Name: String?,
                    val Number: String?,
                    val Path: String?,
                    val Subcategories: ArrayList<Category>?,
                    val Count: Integer,
                    val IsRestricted: Boolean,
                    val HasLegalNotice: Boolean,
                    val HasClassifieds: Boolean,
                    val AreaOfBusiness: Areas,
                    val CanHaveSecondCategory: Boolean,
                    val CanBeSecondCategory: Boolean,
                    val isLeaf: Boolean) {
    enum class Areas{
        NotSpecified, All, Marketplace, Property, Motors, Jobs, Services
    }

    override fun toString(): String {
        if (Name.isNullOrEmpty())
        {
            return ""
        }
        else
        {
            return Name
        }
    }
}
