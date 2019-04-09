package com.e.vaillant_port

import android.util.Log

class MachineState() {

    private val all_states = Array<Int>(50, { i -> (i + 1) })
    private var current_state = all_states[0]

    fun getCurrentState(): Int {

        return current_state
    }

    fun setCurrentState(state: Int) {
        Log.d("debug_message", "state changed from $current_state to $state")
        current_state = all_states[current_state]
    }
}