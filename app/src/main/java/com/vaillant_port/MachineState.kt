package com.vaillant_port

class MachineState(val name: String) {

    private var current_state = 1

    val all_states = Array<Int>(50, { i -> (i + 1) })

    fun getCurrentState(): Int {

        return current_state
    }

    fun setCurrentState(state: Int) {

        current_state = state
    }
}
