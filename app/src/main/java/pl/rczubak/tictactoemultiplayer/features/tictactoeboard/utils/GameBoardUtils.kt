package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.utils

fun <T> List<List<T?>>.transpose(): List<List<T?>> {
    val listOfColumns = List(this.getOrNull(0)?.size ?: 0) { MutableList<T?>(this.size) { null } }
    for (rowIndex in 0 until this.size) {
        for (colIndex in indices) {
            listOfColumns[rowIndex][colIndex] = this[colIndex][rowIndex]
        }
    }
    return listOfColumns
}