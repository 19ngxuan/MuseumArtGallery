package com.example.improvedmuseumartgallery.data.repository

import androidx.compose.runtime.collectAsState
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.MuseumLocalDataSource
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource.MuseumRemoteDataSource
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import javax.inject.Inject


class MuseumRepositoryImp @Inject constructor(
    private val museumRemoteDataSource: MuseumRemoteDataSource,
    private val museumLocalDataSource: MuseumLocalDataSource,
) : MuseumRepository {

    override suspend fun searchArtworks(query: String): List<CheckedItem>? {

        val remoteDataSourceList: List<Int>? = museumRemoteDataSource.searchArtworks(query).objectIDs
        val favoriteArtworkList: List<Int> = museumLocalDataSource.getAllFavoriteArtworkIds().first()


        val checkedItemList = remoteDataSourceList?.map {  id ->

            CheckedItem(id,
                favoriteArtworkList.contains(id)

            )

        }

        return checkedItemList

        //First create an object other then Search Response and return it as a list. The object should have 2 fields;
        // 1 is to check if it is in favorite and other one is id of the search result.
        //SampleObject(isFavorite,id)


        // This function should return list of SampleObject = List<SampleObject>


        // Get all the favorite ids from the database here.


        // Check if the search results in the favorite id list or not. This control will be written to isFavorite field for each item of search result.



//        return museumRemoteDataSource.searchArtworks(query)
    }

    override suspend fun getArtwork(artworkId: Int): Artwork {

        return museumRemoteDataSource.getArtwork(artworkId)
    }

    override suspend fun insertFavoriteArt(favoriteArtwork: FavoriteArtwork) {
        return museumLocalDataSource.insertFavoriteArt(favoriteArtwork)
    }

    override suspend fun deleteFavoriteArt(favoriteArtwork: FavoriteArtwork) {
        return museumLocalDataSource.deleteFavoriteArt(favoriteArtwork)
    }

    override fun getFavoriteArtworkById(objectID: Int): Flow<FavoriteArtwork?> {
        return museumLocalDataSource.getFavoriteArtworkById(objectID)
    }

    override fun getAllFavoriteArtworkIds(): Flow<List<Int>> {
        return museumLocalDataSource.getAllFavoriteArtworkIds()
    }

}