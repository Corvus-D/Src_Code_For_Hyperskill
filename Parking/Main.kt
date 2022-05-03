package parking

//for search
const val ID = 0
const val REG = 1
const val COLOR = 2

//flag for checking create parking
var isParking = false

fun main() {
    val parking = Parking
    processingCommand(parking)
}

fun processingCommand (parking: Parking) {
    while (true) {
        //split the string into command and value
        val input = readln().split(" ")
        //exit
        if (input[0] == CommandsParking.EXIT.command) break

        // Check created parking
        if (!isParking) {
            if (input[0] == CommandsParking.CREATE.command) {
                parking.create(input[1])
            } else {
                println("Sorry, a parking lot has not been created.")
            }
        } else {
            when (input[0]) {
                CommandsParking.CREATE.command -> parking.create(input[1])
                CommandsParking.STATUS.command -> parking.status()
                CommandsParking.PARK.command -> parking.park(Car(input[1], input[2]))
                CommandsParking.LEAVE.command -> parking.leave(input[1])

                //Parameters for search 0 = id, 1 = reg, 2 = color
                CommandsParking.REG_BY_COLOR.command -> parking.search(COLOR, REG, input[1])
                CommandsParking.SPOT_BY_COLOR.command -> parking.search(COLOR, ID, input[1])
                CommandsParking.SPOT_BY_REG.command -> parking.search(REG, ID, input[1])
                else -> println("Invalid command")
            }
        }
    }
}
