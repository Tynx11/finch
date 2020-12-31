package com.kernel.finch.core.manager

internal class MemoryStorageManager {

    val booleans = mutableMapOf<String, Boolean>()
    val integers = mutableMapOf<String, Int>()
    val strings = mutableMapOf<String, String>()
    val stringSets = mutableMapOf<String, Set<String>>()
}