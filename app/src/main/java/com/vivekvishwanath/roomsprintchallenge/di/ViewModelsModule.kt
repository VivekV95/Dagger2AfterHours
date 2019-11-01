package com.vivekvishwanath.roomsprintchallenge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivekvishwanath.roomsprintchallenge.viewmodel.FavoritesViewModel
import com.vivekvishwanath.roomsprintchallenge.viewmodel.SearchViewModel
import com.vivekvishwanath.roomsprintchallenge.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelsModule {

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindsFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

//    @Singleton
//    @Binds
//    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}