package com.example.petwatcher.features.map.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petwatcher.core.presentation.Screen
import com.example.petwatcher.core.presentation.components.StandardToolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MapScreen(
    onNavigate: (String) -> Unit = {},
    viewModel: MapViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    val builder = LatLngBounds.Builder()
    viewModel.state.petSpots.forEach { bus ->
        val busLocation = LatLng(bus.lat, bus.lng)
        builder.include(busLocation)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = viewModel.cameraPosition.value
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigate(Screen.ProfileScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Go to settings"
                )
            }
        }
    ) {
        Column {
            StandardToolbar(
                backgroundColor = MaterialTheme.colors.secondary,
                onNavigateUp = { },
                showBackArrow = false,
                title = {
                    Text(
                        text = "Map",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                },
                navActions = {
                    IconButton(onClick = {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLngZoom(
                                viewModel.cameraPosition.value.target,
                                11f
                            )
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Pets,
                            contentDescription = "Go to last pet position"
                        )
                    }
                }
            )
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState,
                onMapLongClick = {
                    viewModel.onEvent(MapEvent.OnMapLongClick(it))
                }
            ) {
                viewModel.state.petSpots.forEachIndexed { index, spot ->
                    Marker(
                        position = LatLng(spot.lat, spot.lng),
                        title = "Your Pet (${spot.lat}, ${spot.lng})",
                        snippet = "Long click to delete",
                        onInfoWindowLongClick = {
                            viewModel.onEvent(
                                MapEvent.OnInfoWindowLongClick(spot)
                            )
                        },
                        onClick = {
                            it.showInfoWindow()
                            true
                        },
                        icon = if (index != viewModel.state.petSpots.lastIndex)
                            BitmapDescriptorFactory.fromResource(com.example.petwatcher.R.drawable.shiba_black_32)
                        else
                            BitmapDescriptorFactory.fromResource(com.example.petwatcher.R.drawable.shiba_32)
                    )
                }
            }
        }
    }
}