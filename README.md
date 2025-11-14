🍜 # White Dragon Noodle Bar

A console-based Java application for ordering ramen, drinks, and appetizers. Supports custom orders, signature ramen, receipt generation, and order review.

---

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [UML Diagrams](#uml-diagrams)

---

## Features

- Create new orders with multiple items (ramen, drinks, appetizers)  
- Signature ramen with default toppings  
- Fully customizable ramen with toppings and spiciness  
- Dynamic pricing calculation  
- Checkout dialog with review and confirmation  
- Receipt generation saved as text files  

---

## Technologies

- Java 17+  
- Console-based UI, with Swing dialog for checkout  
- Standard Java I/O for saving receipts  

---

## Installation

1. Clone the repository:  

```bash
git clone https://github.com/yourusername/white-dragon-noodle-bar.git

2. Navigate to the project directory and compile:
bash
Copy code
cd white-dragon-noodle-bar
javac -d bin src/com/pluralsight/**/*.java

3. Run the application:

bash
Copy code
java -cp bin com.pluralsight.WhiteDragonNoodleBar

## Usage
1. Launch the program.
2. Choose New Order to start an order.
3. Add signature ramen, custom ramen, drinks, or appetizers.
4. Review your order at checkout.
5. Confirm to save a receipt in src/main/resources/receipts/.

## Project Structure
bash
Copy code
com.pluralsight
│
├── WhiteDragonNoodleBar.java        # Main entry point
├── ui
│   ├── UserInterface.java           # Console menus and input handling
│   └── CheckoutDialog.java          # Swing-based order review
├── items
│   ├── Order.java                   # Represents an order with multiple items
│   ├── Ramen.java                   # Customizable ramen
│   ├── TonkotsuDeluxe.java          # Signature ramen subclass
│   ├── SpicyMiso.java               # Signature ramen subclass
│   ├── Drink.java                   # Drink item
│   ├── Appetizer.java               # Appetizer item
│   └── Topping.java                 # Enum for ramen toppings
└── util
    ├── ReceiptWriter.java           # Writes receipts to disk
    └── OrderItem.java               # Interface for items with price & description

## UML Diagrams (Text-Based)
1. Main Class Relationships
pgsql
Copy code
+---------------------------+
| WhiteDragonNoodleBar      |
+---------------------------+
| +main(args: String[])     |
+---------------------------+
           |
           v
+---------------------------+
| UserInterface             |
+---------------------------+
| -scanner: Scanner         |
| -yesNo: int[]             |
+---------------------------+
| +homeScreen()             |
| +orderMenuScreen()        |
+---------------------------+
           |
           v
+---------------------------+
| Order                     |
+---------------------------+
| -items: List<OrderItem>   |
+---------------------------+
| +addRamen(Ramen)          |
| +addDrink(Drink)          |
| +addAppetizer(Appetizer)  |
| +getTotal(): double       |
| +getOrderSummary(): String|
+---------------------------+
           |
           v
+---------------------------+
| OrderItem <<interface>>   |
+---------------------------+
| +price(): double          |
| +description(): String    |
+---------------------------+
           |
   ------------------------
   |          |           |
   v          v           v
+-------+  +--------+  +-----------+
| Ramen |  | Drink  |  | Appetizer |
+-------+  +--------+  +-----------+

2. Ramen & Toppings
diff
Copy code
Ramen
+ size: bowlSize
+ brothType: broth
+ noodleType: noodle
+ meats: List<meat>
+ vegetables: List<vegetable>
+ premiums: List<premium>
+ spicy: boolean
+ price(): double
+ description(): String

Topping
+ meat {CHASHU, KAKUNI, NIKUSOBORO, STEAK}
+ vegetable {NORI, BOKCHOY, SPINACH, SHIITAKE}
+ premium {EGG, KIMCHI, SPAM, NATTO}

Signature Ramen
+ TonkotsuDeluxe : Ramen
+ SpicyMiso : Ramen
3. Receipt Writer
vbnet
Copy code
ReceiptWriter
+ saveReceipt(order: Order): String
- generateTimestamp(): String

4. Sequence Diagram (Typical Order Flow)
pgsql
Copy code
User -> UserInterface : Start order
UserInterface -> User : Show order menu
User -> UserInterface : Select items
UserInterface -> Order : addRamen()/addDrink()/addAppetizer()
User -> UserInterface : Checkout
UserInterface -> CheckoutDialog : showCheckout(orderSummary)
CheckoutDialog -> User : Display summary
User -> CheckoutDialog : Confirm
CheckoutDialog -> UserInterface : Return true
UserInterface -> ReceiptWriter : saveReceipt(order)
ReceiptWriter -> FileSystem : Save .txt file
UserInterface -> User : Show receipt path

5. Enum Relationships
rust
Copy code
Ramen.bowlSize    -> SMALL, MEDIUM, LARGE
Ramen.broth       -> TONKOTSU, SHIO, SHOYU, MISO
Ramen.noodle      -> REGULAR, WHOLEWHEAT, CRISPY, UDON, SOBA

Drink.drinkType   -> WATER, COKE, PEPSI, MATCHA, RAMUNE, SAPPORO, ASAHI, SAKE, SOJU
Drink.drinkSize   -> SMALL, MEDIUM, LARGE

Appetizer.appetizerType -> EDAMAME, YAKITORI, TEMPURA, GYOZA, TOFU, ONIGIRI, FRIEDRICE

Topping.meat      -> CHASHU, KAKUNI, NIKUSOBORO, STEAK
Topping.vegetable -> NORI, BOKCHOY, SPINACH, SHIITAKE
Topping.premium   -> EGG, KIMCHI, SPAM, NATTO
