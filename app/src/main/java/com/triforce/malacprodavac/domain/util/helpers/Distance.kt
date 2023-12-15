package com.triforce.malacprodavac.domain.util.helpers

import com.triforce.malacprodavac.domain.util.filter.FilterOperation
import com.triforce.malacprodavac.domain.util.filter.SingleFilter


enum class DistanceKMToCoordinateOffset(
    val distanceLatitude: Double,
    val distanceLongitude: Double
) {
    One(0.00901323142, 0.008984725964),
    TwoAndAHalf(0.02253307855, 0.02246181491),
    Five(0.04506615711, 0.04492362982),
    Ten(0.09013231423, 0.08984725965)
}

fun calculateLocationOffset(
    latitudeAndLongitude: Pair<Double, Double>,
    offset: DistanceKMToCoordinateOffset
): Pair<Pair<Double, Double>, Pair<Double, Double>> {
    val (latitude, longitude) = latitudeAndLongitude
    return Pair(
        Pair(
            latitude.minus(offset.distanceLatitude),
            latitude.plus(offset.distanceLatitude)
        ),
        Pair(
            longitude.minus(offset.distanceLongitude),
            longitude.plus(offset.distanceLongitude)
        )
    )
}

fun generateRouteStartAndEndFilters(
    routeStartCoords: Pair<Double, Double>,
    routeEndCoors: Pair<Double, Double>,
    offset: DistanceKMToCoordinateOffset
): List<SingleFilter<Double>> {
    val start =
        calculateLocationOffset(
            routeStartCoords, //latitude and longitude
            offset
        )
    val end = calculateLocationOffset(
        routeEndCoors, //latitude and longitude
        offset
    )

    return listOf(
        SingleFilter("routeStartLatitude", FilterOperation.Gte, start.first.first),
        SingleFilter("routeStartLatitude", FilterOperation.Lte, start.first.second),
        SingleFilter(
            "routeStartLongitude",
            FilterOperation.Gte,
            start.second.first
        ),
        SingleFilter(
            "routeStartLongitude",
            FilterOperation.Lte,
            start.second.second
        ),
        SingleFilter("routeEndLatitude", FilterOperation.Gte, end.first.first),
        SingleFilter("routeEndLatitude", FilterOperation.Lte, end.first.second),
        SingleFilter("routeEndLongitude", FilterOperation.Gte, end.second.first),
        SingleFilter("routeEndLongitude", FilterOperation.Lte, end.second.second),
    )
}