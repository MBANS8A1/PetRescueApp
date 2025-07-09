package com.example.petrescueapp

import android.app.Application

class PetRescueApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}