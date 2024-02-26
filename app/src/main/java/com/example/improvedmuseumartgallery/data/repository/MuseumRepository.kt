package com.example.improvedmuseumartgallery.data.repository

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.MuseumLocalDataSource
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource.MuseumRemoteDataSource
import com.example.improvedmuseumartgallery.domain.model.ArtworkWithFavorite
import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject


class MuseumRepositoryImp @Inject constructor(
    private val museumRemoteDataSource: MuseumRemoteDataSource,
    private val museumLocalDataSource: MuseumLocalDataSource,
) : MuseumRepository {

    override suspend fun searchArtworks(query: String): Flow<List<CheckedItem>> {


        val favoriteArtworkListFlow = museumLocalDataSource.getAllFavoriteArtworkIds()
        val remoteDataSourceList = museumRemoteDataSource.searchArtworks(query).objectIDs

        val checkedItemListFlow = favoriteArtworkListFlow.map { favoriteArtworkIds ->

            remoteDataSourceList.map { id ->
                CheckedItem(id, favoriteArtworkIds.contains(id))


            }
        }

        return checkedItemListFlow

    }

    override suspend fun getArtwork(artworkId: Int): Flow<ArtworkWithFavorite> {


        val favoriteArtworkFlow = museumLocalDataSource.getFavoriteArtworkById(artworkId)

        val artwork = museumRemoteDataSource.getArtwork(artworkId)

        val artworkWithFavorite = favoriteArtworkFlow.map { favoriteArtwork ->
            ArtworkWithFavorite(
                artwork.objectID,
                artwork.title,
                artwork.artistDisplayName,
                artwork.primaryImage,
                artwork.additionalImages,
                artwork.department, favoriteArtwork?.objectID == artworkId
            )
        }


        return artworkWithFavorite


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

    override suspend fun downloadFile(
        url: String,
        file: File
        ):Result<Unit> {
        return museumRemoteDataSource.downloadFile(url, file)
    }


}