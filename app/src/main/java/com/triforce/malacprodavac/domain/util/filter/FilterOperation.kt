package com.triforce.malacprodavac.domain.util.filter

enum class FilterOperation (val operation: String){
    // Numeric Operations
    Eq ("="),
    Ne("!="),
    Lt("<"),
    Lte("<="),
    Gt(">"),
    Gte(">="),
    //String Operations
    Contains("contains"),
    IContains("icontains"),
    Search("search"),
    ISearch("isearch"),
    StartsWith("startswith"),
    IStartsWith("istartswith"),
    EndsWith("endswith"),
    IEndsWith("iendswith"),
    EqString("eqstring"),
    NeString("nestring"),
    // Array Operations
    In("in"),
    NotIn("notin"),
    InStrings("instrings"),
    NotInStrings("notinstrings"),
    // Null Operations
    EqNull("eqnull"),
    NeNull("nenull");

    override fun toString(): String {
        return this.operation
    }
}