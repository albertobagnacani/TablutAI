package model.state.rules

import model.state.NormalState
import model.state.StandardStateFactory
import model.state.State
import model.state.board.CellContent
import model.state.board.CellType
import model.state.board.NormalBoardCell
import model.state.board.NormalCoordinate
import model.state.player.NormalPlayer

// TODO1 dep inj?
class NormalGameRules(val s: NormalState) : GameRules {
    // TODO add these?
    /*
    – Un giocatore non può muovere nessuna pedina in nessuna direzione: sconfitta di quel giocatore
    – Si raggiunge uno stato già raggiunto in precedenza: pareggio
    */

    /* The king has been captured by blacks
    1) Se il Re è nel Castello deve essere circondato su tutti e 4 i lati
    2) Se il Re è adiacente al Castello, deve essere circondato sui 3 lati liberi
    3) Se una pedina (Re o Cavaliere) è adiacente a un Accampamento, è sufficiente circondarla con una pedina sul lato opposto all’Accampamento
    4) Se nessuna delle precedenti: il re diventa una normale pedina. Mangiabile come se fosse un cavaliere, da 2 pedine sulla stessa "linea"
    */
    fun isKingEscaped(): Boolean{
        if(s.board.getKingBoardCell() in s.board.getExitBoardCells())
            return true
        return false
    }

    fun isKingInCastleAndSurrounded(): Boolean{
        val surroundedKingCoordinates = NormalCoordinate(s.board.getKingBoardCell().coordinate).adjCoordinates()

        //if(board.getBlackBoardCellAdjKing().containsAll(surroundedKingCoordinates)){
        if(s.board.getBlackBoardCellAdjKing().size >= 4){
            return true
        }
        return false
    }

    fun isKingInAdjCastleAndSurrounded(): Boolean{
        // Count king's adj black. If >= 3, king's captured
        /*
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
        */
        if(s.board.getBlackBoardCellAdjKing().size >= 3){
            return true
        }
        return false
    }

    fun isKingCapturedAdjCamp(): Boolean{
        // Look for this configuration: camp - king - black, in any possible direction. If so, king's captured
        // Find the king's adj camp
        val kingAdjCoords = NormalCoordinate(s.board.getKingBoardCell().coordinate).adjCoordinates()
        val kingAdjBoardCells = s.board.getBoardCellsFromCoords(kingAdjCoords)
        val kingAdjCampBoardCell = mutableListOf<NormalBoardCell>()

        for(cell in kingAdjBoardCells){
            if(cell.type == CellType.CAMP)
                kingAdjCampBoardCell.add(cell)
        }

        // Look if in the other part there's a black
        for(kingAdjCamp in kingAdjCampBoardCell){
            if(s.board.getBoardCellFromCoord(
                    NormalCoordinate.getThirdCoordinateFromTwo(
                        s.board.getKingBoardCell().coordinate,
                        kingAdjCamp.coordinate
                    )
                ).content == CellContent.BLACK)
                return true
        }
        return false
    }

    fun isKingCapturedLikeKnight(): Boolean{ // TODO + cattura attiva?
        // For each black that has the king adj, look if in the same line there's another black that captures the king
        for(black in s.board.getBlackBoardCellAdjKing()){ // TODO1 ricalculated for nothing, use variable
            if(s.board.getBoardCellFromCoord(
                    NormalCoordinate.getThirdCoordinateFromTwo(
                        s.board.getKingBoardCell().coordinate,
                        black.coordinate
                    )
                ).content == CellContent.BLACK){
                return true
            }
        }
        return false
    }

    fun isTerminal(): Boolean {
        if(s.player == NormalPlayer.WHITE){ // The king arrives on the exit
            return isKingEscaped()
        }else{ // The king has been captured by blacks
            // TODO1 if migliorabile
            if(s.board.getKingBoardCell().coordinate.equals(NormalCoordinate.getMiddleCoordinate())){ // Case 1)
                return isKingInCastleAndSurrounded()
            }else if(NormalCoordinate(NormalCoordinate.getMiddleCoordinate()).adjCoordinates().contains(s.board.getKingBoardCell().coordinate)){ // The king is in an castle's adj cell Case 2)
                return isKingInAdjCastleAndSurrounded()
            }else if(s.board.getKingBoardCell() in s.board.getCampBoardCellsAdjKing()){ // Case 3)
                return isKingCapturedAdjCamp()
            }else if(!s.board.getBlackBoardCellAdjKing().isEmpty()){ // Case 4 // TODO cattura multipla?
                return isKingCapturedLikeKnight()
            }
        }
        return false
    }
}