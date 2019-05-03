package model.state

import model.state.board.*
import model.state.board.NormalCoordinate.Companion.getThirdCoordinateFromTwo
import model.state.player.NormalPlayer
import model.state.player.Player

// TODO move there rules in a class
class NormalState(override val board: NormalBoard<NormalBoardCell>, override val player: Player) : State{
    // TODO add these?
    /*
    – Un giocatore non può muovere nessuna pedina in nessuna direzione: sconfitta di quel giocatore
    – Si raggiunge uno stato già raggiunto in precedenza: pareggio
    */
    override fun isTerminal(): Boolean {
        // TODO move this and change name
        // TODO mappa (anche sopra?)? O fare come fatto con caso 4)?
        val campAdjCoord = mutableListOf<Coordinate>()

        for(coord in board.getCampCoordinates()){
            campAdjCoord.addAll(NormalCoordinate(coord).adjCoordinates())
        }


        if(player == NormalPlayer.WHITE){ // The king arrives on the exit
            if(board.getKingCoordinate() in board.getExitCoordinates())
                return true
        }else{ // The king has been captured by blacks
            /*
            1) Se il Re è nel Castello deve essere circondato su tutti e 4 i lati
            2) Se il Re è adiacente al Castello, deve essere circondato sui 3 lati liberi
            3) Se una pedina (Re o Cavaliere) è adiacente a un Accampamento, è sufficiente circondarla con una pedina sul lato opposto all’Accampamento
            4) Se nessuna delle precedenti: il re diventa una normale pedina. Mangiabile come se fosse un cavaliere, da 2 pedine sulla stessa "linea"
            */
            // TODO if migliorabile
            if(board.getKingCoordinate().equals(NormalCoordinate.getMiddleCoordinate())){ // Case 1)
                val surroundedKingCoordinates = NormalCoordinate(board.getKingCoordinate()).adjCoordinates()

                if(board.getBlackCoordinates().containsAll(surroundedKingCoordinates)){
                    return true
                }
            }else if(NormalCoordinate(NormalCoordinate.getMiddleCoordinate()).adjCoordinates().contains(board.getKingCoordinate())){ // The king is in an castle's adj cell Case 2)
                // Count king's adj black. If >= 3, king's captured
                val kingAdjCoords = NormalCoordinate(board.getKingCoordinate()).adjCoordinates()
                val kingAdjBoardCells = board.getBoardCellsFromCoords(kingAdjCoords)
                var counter = 0

                for(boardCell in kingAdjBoardCells){
                    if(boardCell.content == NormalPlayer.BLACK)
                        counter++
                }

                if(counter>=3){
                    return true
                }
            }else if(board.getKingCoordinate() in campAdjCoord){ // Case 3)
                // Look for this configuration: camp - king - black, in any possible direction. If so, king's captured
                // Find the king's adj camp
                val kingAdjCoords = NormalCoordinate(board.getKingCoordinate()).adjCoordinates()
                val kingAdjBoardCells = board.getBoardCellsFromCoords(kingAdjCoords)
                val kingAdjCampBoardCell = mutableListOf<NormalBoardCell>()

                for(cell in kingAdjBoardCells){
                    if(cell.type == CellType.CAMP)
                        kingAdjCampBoardCell.add(cell)
                }

                // Look if in the other part there's a black
                for(kingAdjCamp in kingAdjCampBoardCell){
                    if(board.getBoardCellFromCoord(getThirdCoordinateFromTwo(board.getKingCoordinate(), kingAdjCamp.coordinate)).content == CellContent.BLACK)
                        return true
                }
            }else if(!board.getBlackAdjKing().isEmpty()){ // Case 4 // TODO cattura multipla? // TODO cattura attiva?
                // For each black that has the king adj, look if in the same line there's another black that captures the king
                for(black in board.getBlackAdjKing()){ // TODO ricalculated for nothing, use variable
                    if(board.getBoardCellFromCoord(getThirdCoordinateFromTwo(board.getKingCoordinate(), black.coordinate)).content == CellContent.BLACK){
                        return true
                    }
                }
            }
        }
        return false
    }
}