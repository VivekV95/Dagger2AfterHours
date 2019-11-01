package com.vivekvishwanath.roomsprintchallenge.di

import android.app.Application
import com.vivekvishwanath.roomsprintchallenge.view.FavoritesActivity
import com.vivekvishwanath.roomsprintchallenge.view.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AsyncTaskModule::class,
        ViewModelsModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun injectSearchActivity(activity: SearchActivity)

    fun injectFavoritesActivity(activity: FavoritesActivity)
}