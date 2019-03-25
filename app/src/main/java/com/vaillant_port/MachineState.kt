package com.vaillant_port

class MachineState(val name: String) {

    private var current_state = "no_state"

    val all_states = mapOf(
        "no_state" to null,
        "state_1" to "we5",
        "state_2" to "sdf"
    )

    fun getCurrentState(): String {

        return current_state
    }

    fun setCurrentState(state: String) {

        current_state = state
    }
}
