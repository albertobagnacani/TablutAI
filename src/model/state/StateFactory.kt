package model.state

interface StateFactory {
    fun createFromGameVersion(version: String, boardTypePath: String, boardContentPath: String): State
    //fun createInitialState(): State
}