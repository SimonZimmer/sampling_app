package com.e.vaillant_port

import android.util.Log

class MachineState(val name: String) {

    private var current_state = 1

    val all_states = Array<Int>(50, { i -> (i + 1) })

    fun getCurrentState(): Int {

        return current_state
    }

    fun setCurrentState(state: Int) {
        Log.d("debug_message", "state changed from $current_state to $state")
        current_state = state
    }
}