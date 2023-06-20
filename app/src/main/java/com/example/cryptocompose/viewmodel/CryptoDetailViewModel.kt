package com.example.cryptocompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cryptocompose.model.Crypto
import com.example.cryptocompose.repository.CryptoRepository
import com.example.cryptocompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
):ViewModel() {

    suspend fun getCrypto(id:String):Resource<Crypto>{
        return repository.getCrypto(id)
    }

}