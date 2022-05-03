package parking

const val SPOT = "SPOT"

object Parking {

    var parking = ParkingSpots.PLACES.list

    //create parking lot
    fun create(size: String): MutableList<MutableList<String>> {
        parking.clear()
        for (i in 0 until size.toInt()) {
            parking.add(mutableListOf(SPOT, "reg", "color"))
        }
        println("Created a parking lot with $size spots.")
        isParking = true
        return parking
    }

    //cars in the parking
    fun status() {
        if (checkEmpty()) return
        for (i in 0 until parking.size) {
            if (parking[i][0] != SPOT) println("${parking[i][0]} ${parking[i][1]} ${parking[i][2]}")
        }
    }

    fun park(car: Car) {
        if (checkParkingLotFree()) return
        for (i in 0 until parking.size) {
            if (SPOT == parking[i][0]) {
                parking[i][0] = (i + 1).toString() //id
                parking[i][1] = car.reg           //registration number
                parking[i][2] = car.color          //color
                println("${car.color} car parked in spot ${i + 1}.")
                break
            }
        }
    }

    //removing car from parking
    fun leave(spotNumber: String) {
        val spot = spotNumber.toInt() - 1

        if (parking[spot][0] == SPOT) {
            println("There is no car in spot $spotNumber.")
        }
        if (parking[spot][0] != SPOT) {
            println("Spot $spotNumber is free.")
            parking[spot][0] = SPOT
            parking[spot][1] = "reg"
            parking[spot][2] = "color"
        }
    }

    //function to search with parameters
    //0 - id, 1 - reg, 2 - color
    fun search(forSearch: Int, forResult: Int, parameterSearch: String, ) {
        val list = mutableListOf<String>() //list for print
        for (i in 0 until parking.size) {
            if (parking[i][forSearch].uppercase() == parameterSearch.uppercase()) {
                list.add(parking[i][forResult])
            }
        }
        if (list.size == 0) {
            if (forSearch == 1) println("No cars with registration number $parameterSearch were found.")
            else println("No cars with color $parameterSearch were found.")
        }
        else println(list.joinToString(", "))
    }

    //If there are no spot, return true
    private fun checkParkingLotFree(): Boolean {
        var count = 0
        for (i in 0 until parking.size) {
            if (parking[i][0] == SPOT) {
                ++count
            }
        }
        if (count == 0) {
            println("Sorry, the parking lot is full.")
            return true
        }
        return false
    }

    // if parking is free, return true
    private fun checkEmpty(): Boolean {
        var count = 0
        for (i in 0 until parking.size) {
            if (parking[i][0] == SPOT) ++count
        }
        if (count == parking.size) {
            println("Parking lot is empty.")
            return true
        }
        return false
    }
}






