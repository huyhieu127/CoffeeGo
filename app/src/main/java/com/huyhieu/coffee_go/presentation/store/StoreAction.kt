package com.huyhieu.coffee_go.presentation.store

interface StoreAction {
    data class TabSelected(val tab: Int) : StoreAction
}