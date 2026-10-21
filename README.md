# OOP MiniBank Project (CEUE203)

This project is a console-based MiniBank application developed in Java (JDK 17+) for the Object-Oriented Programming (CEUE203) course at CHARUSAT.

## Project Structure
```
oop-minibank/
├── src/
│   └── minibank/
│       ├── BankInfo.java       (Record for bank metadata)
│       ├── MenuOption.java     (Enum for main menu)
│       ├── Customer.java       (Customer entity with nested Address class & clone)
│       ├── Account.java        (Account entity with deposit/withdraw logic & overrides)
│       ├── BankService.java    (In-memory storage & operations for accounts)
│       └── MiniBank.java       (Main interactive application shell)
└── README.md
```

## Features Implemented
- **Lab 01**: Record (`BankInfo`), Enum (`MenuOption`), switch expressions, Scanner loop.
- **Lab 02**: `Customer` & `Account` encapsulation, static ID generation (`CUST101`, `AC0001`), deposit and withdraw methods.
- **Lab 03**: `toString()`, `equals()`, `hashCode()`, static nested `Address` class, `Cloneable` interface implementation.
- **Interactive Banking**: Open accounts, deposit, withdraw, transfer between accounts, and view detailed account summaries.

## How to Compile & Run

### Prerequisites
- JDK 17 or higher installed.

### Compilation
From the project root directory (`oop-minibank`), run:
```bash
javac -d bin src/minibank/*.java
```

### Execution
Run the application using:
```bash
java -cp bin minibank.MiniBank
```
