package com.esgi.golf.data.mappers

import com.esgi.golf.data.dto.HoleDto
import com.esgi.golf.domain.models.Hole
import javax.inject.Inject

class HoleMapper @Inject constructor() {

    fun map(hole: HoleDto): Hole {
        return Hole(
            id = hole.id,
            order = hole.order,
            name = hole.name,
            par = hole.par,
        )
    }

    fun map(hole: Hole): HoleDto {
        return HoleDto(
            id = hole.id,
            order = hole.order,
            name = hole.name,
            par = hole.par,
        )
    }

}