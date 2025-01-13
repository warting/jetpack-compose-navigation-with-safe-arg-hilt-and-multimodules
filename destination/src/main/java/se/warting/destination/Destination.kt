package se.warting.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

const val baseuri = "deeplinks://warting"

interface Destination {
    fun host(navGraphBuilder: NavGraphBuilder, navController: NavHostController)
}