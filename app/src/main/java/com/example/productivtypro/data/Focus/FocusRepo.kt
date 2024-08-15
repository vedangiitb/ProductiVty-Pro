package com.example.productivtypro.data.Focus

class FocusRepo(private val focusDao: FocusDao){
    suspend fun insertItem(item: FocusData) = focusDao.insert(item)
}