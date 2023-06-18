package com.example.cryptocompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cryptocompose.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val repository: CryptoRepository) :
    ViewModel() {
        // compose da live data ya gerek yok zaten mutable stateler var

       // val cryptoList= mutableStateOf<Crypto>()

    }