package org.expodev.testkotlinapp.data

object SearchRepositoryProvider {
    fun provideSearchRepository():SearchRepository{
        return SearchRepository(BashImApiService.create())
    }
}