package com.example.improvedmuseumartgallery.presentation.screens.detail

import com.example.improvedmuseumartgallery.domain.model.Artwork

data class DetailUIModel(val artwork: Artwork, val isFavored: Boolean)