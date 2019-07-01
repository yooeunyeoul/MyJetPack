package com.example.myjetpackwithandroidx.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpackwithandroidx.repository.PlantRepository
import com.example.myjetpackwithandroidx.viewmodels.PlantListViewModel

class PlantListViewModelFactory internal constructor(private val repository: PlantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PlantListViewModel(repository) as T

}