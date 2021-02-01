# CharleneCoffee

Products
- Coffee (small, medium, large) 2.50 CHF, 3.00 CHF, 3.50 CHF
- Bacon Roll 4.50 CHF
- Freshly squeezed orange juice (0.25l) 3.95 CHF

Extras:
- Extra milk 0.30 CHF
- Foamed milk 0.50 CHF
- Special roast coffee 0.90 CHF

Use Cases
Offer a customer stamp card, where every 5th beverage is for free
If a customer orders a beverage and a snack, one of the extra's is free

Application - A maven application is created for the same. As per specifications only Java SE libraries are used, so only Junit library is used in pom.

Sample Output:
Use Case: When customer orders a small coffee with foamed milk. Below is the output
OrderId: 1971114838
Customer Phone: 123
COFFEE------2.5
FOAMEDMILK------0.5
TOTAL PRICE --------- 3.0

How to run this application ?

In Application.java through placeOrder API, we need to place an order. For e.g. if an juice needs to be ordered, below needs to be added.

FreshJuice juice = new FreshJuice(1); // 1 is the quantity

similarly other products can be ordered as shown below.
Coffee coffee = new Coffee(Size.SMALL, 1); // we have to pass size and quantity
Bacon bacon = new Bacon(1); // similar to juice it can be ordered.

To order an extras, it needs to be added with product as shown below.
    List<Extra> extras = new ArrayList<Extra>();
    extras.add(new FoamedMilk());
    coffee.setExtras(extras);
    
Run Application.java class and output can be seen on console.

Other way to test this through Junit test cases.
