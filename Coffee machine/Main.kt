package machine

enum class RecipeCoffee(val water: Int, val milk: Int, val beans: Int, val cups: Int, val price: Int) {
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);
}


class CoffeeMachine(
    var water: Int = 400, //ml
    var milk: Int = 540,  //ml
    var beans: Int = 120, //g
    var cups: Int = 9,
    var money: Int = 550  //$
) {

    fun menu() {
        while (true) {
            println("Write action (buy, fill, take, remaining, exit):")
            when (readln().toString()) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> remaining()
                "exit" -> break
            }
            continue
        }
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        while (true) {
            when (readln().toString()) {
                "1" -> cupCoffee(RecipeCoffee.ESPRESSO)
                "2" -> cupCoffee(RecipeCoffee.LATTE)
                "3" -> cupCoffee(RecipeCoffee.CAPPUCCINO)
                "back" -> break
            }
            break
        }
    }

    private fun cupCoffee(recipeCoffee: RecipeCoffee) {
        // To check the quantity of ingredients
        val remainderIngredientsList = mutableListOf<Int>(
            water - recipeCoffee.water,
            milk - recipeCoffee.milk,
            beans - recipeCoffee.beans,
            cups - recipeCoffee.cups
        )
        // Check the list of missing ingredients, if it has ingredients, display the first ingredient
        // Otherwise make coffee
        val checked = checkIngredients(remainderIngredientsList)
        when (checked.size) {
            in 1..4 -> println("Sorry, not enough ${checked[0]}")
            0 -> {
                water -= recipeCoffee.water
                milk -= recipeCoffee.milk
                beans -= recipeCoffee.beans
                cups -= recipeCoffee.cups

                money += recipeCoffee.price
                println("I have enough resources, making you a coffee!")
            }
        }
    }
    /*
    * Send the list of ingredients after the change,
    * if there is a negative number,
    * add it to the list of missing ingredients
    * */
    private fun checkIngredients (remainderIngredientsList: MutableList<Int>): MutableList<String> {
        // list of missing ingredients
        val missIngredients = mutableListOf<String>()
        for (i in 0..3) {
            if(remainderIngredientsList[i] < 0)
                when (i) {
                    0 -> missIngredients.add (0, "water")
                    1 -> missIngredients.add (0, "milk")
                    2 -> missIngredients.add (0, "coffee beans")
                    3 -> missIngredients.add (0, "cups")
                }
        }
        return missIngredients
    }

    private fun fill() {
        println("Write how many ml of water do you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk do you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans do you want to add:")
        beans += readln().toInt()
        println("Write how many disposable cups of coffee do you want to add:")
        cups += readln().toInt()
    }

    private fun take(){
        println("I gave you $money$")
        money = 0
    }

    private fun remaining() {
        println("""
            The coffee machine has:
            $water of water
            $milk of milk
            $beans of coffee beans
            $cups of disposable cups
            $$money of money
            """.trimIndent()
        )
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.menu()
}
