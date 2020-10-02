
# Restaurant Management System

Java-based application developed as part of an assignment in my second year of studies. The application facilitates restaurant management by providing a specialized interface for each of it's possible users (administrator, waiter and chef). Depending on the user/view selected, the actor can perform CRUD operations on the restaurant's menu, place an order and generate a bill for a given order. Moreover, whenever an order is placed by the waiter the chef will be notified.

The user interaction is done through a Swing GUI. "Server-side" the application is implemented using Java with a 3-tier layered architecture. The state of the application is serialized and deserialized as needed (no database used). The following design patterns were used:
- Observer Design Pattern
- Singleton Design Pattern
- Composite Design Pattern

 For more information please check the [documentation](https://github.com/radusocaci/restaurant-management-system/blob/master/Restaurant%20Documentation.pdf).
