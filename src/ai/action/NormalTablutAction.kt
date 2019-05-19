package ai.action

/*
    {
        "from":"e4",
        "to":"f4",
        "turn":"WHITE"
    }
    // e4: column e (4), row 4
    // f4: column f (5), row 4
*/
data class NormalTablutAction(override val from: String, override val to: String, override val turn: String) : TablutAction {
}