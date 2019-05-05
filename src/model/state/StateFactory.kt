package model.state

interface StateFactory {
    // TODO1 use enum instead of String?
    fun createFromGameVersion(version: String, boardTypePath: String, boardContentPath: String): State
    //fun createInitialState(): State
}