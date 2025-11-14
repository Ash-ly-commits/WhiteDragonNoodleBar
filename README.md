🍜 White Dragon Noodle Bar

A console-based (with optional GUI for checkout) Java application for ordering ramen, drinks, and appetizers. The system supports custom orders, signature ramen, receipt generation, and order review.

Table of Contents

Features

Technologies

Installation

Usage

Project Structure

UML Diagrams

Features

Create new orders with multiple items (ramen, drinks, appetizers)

Supports signature ramen with default toppings

Fully customizable ramen with toppings and spiciness

Dynamic pricing calculation based on selections

Checkout dialog with review and confirmation

Receipt generation saved as text files

Technologies

Java 17+

Console-based UI, with Swing dialog for checkout

Uses standard Java I/O for saving receipts

Installation

Clone the repository:

git clone https://github.com/yourusername/white-dragon-noodle-bar.git


Navigate to the project directory and build:

cd white-dragon-noodle-bar
javac -d bin src/com/pluralsight/**/*.java


Run the application:

java -cp bin com.pluralsight.WhiteDragonNoodleBar

Usage

Launch the program.

Choose New Order to start an order.

Add signature ramen, custom ramen, drinks, or appetizers.

Review your order at checkout.

Confirm to save a receipt in src/main/resources/receipts/.

Project Structure
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

UML Diagrams (Text-Based)
1. Class Diagram (Main classes)
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

2. Ramen & Toppings Relationships
Ramen
+ size: bowlSize
+ brothType: broth
+ noodleType: noodle
+ meats: List<meat>
+ vegetables: List<vegetable>
+ premiums: List<premium>
+ spicy: boolean

Topping (Enums)
+ meat {CHASHU, KAKUNI, ...}
+ vegetable {NORI, BOKCHOY, ...}
+ premium {EGG, KIMCHI, ...}

3. Receipt Writer
ReceiptWriter
+ saveReceipt(order: Order): String
- generateTimestamp(): String
