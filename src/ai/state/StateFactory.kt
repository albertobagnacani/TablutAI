package ai.state

interface StateFactory {
    // TODO use enum insted of String?
    fun createFromGameVersion(version: String): State
}