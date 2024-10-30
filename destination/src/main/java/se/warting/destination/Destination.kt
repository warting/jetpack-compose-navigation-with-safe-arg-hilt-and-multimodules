package se.warting.destination

import androidx.navigation.NavGraphBuilder

const val baseuri = "deeplinks://warting"

interface Destination {
    fun host(navGraphBuilder: NavGraphBuilder)
}