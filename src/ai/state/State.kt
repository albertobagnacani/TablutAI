package ai.state

/**
 * @param board actual board
 * @oaram player actual player
 */
interface State{
    val board: Board
    val player: Player
}