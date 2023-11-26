package com.streafy.amphibians.data

import com.streafy.amphibians.network.Amphibian
import com.streafy.amphibians.network.AmphibiansApiService

interface AmphibiansRepository{
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> =  amphibiansApiService.getAmphibians()
}