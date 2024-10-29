package com.example.rickandmorty.features.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.use_cases.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase
): ViewModel() {

    private val _state = MutableStateFlow(LocationState.Idle)
    val state = _state
        .onStart { getLocations() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = LocationState.Idle,
        )

    private fun getLocations() {
        viewModelScope.launch {
            _state.update { it.onLoading() }
            try {
                val locations = locationUseCase.getLocations().getOrThrow()
                _state.update { it.onLocationsLoaded(locations) }
            } catch (e: Throwable) {
                println("Could not get locations. ex: $e")
                _state.update { it.onLoadingFinished() }
            }finally {
                _state.update { it.onLoadingFinished() }
            }
        }
    }
}