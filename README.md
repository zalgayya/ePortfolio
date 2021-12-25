# User Guide

1. File location
      - The main is located in the src folder named Portfolio
   
2. Building/Running program
   - run with run button on InteliJ
   - compile using terminal using java (.java files), then using javac Portfolio to run my main
3. Testing program
   - Go to Test plan
   
# Program description
The program presents a user with fully fledged gui interface that allows the user to choose from 5 commands, Buying 
an investment, Selling an investment, Updating price of each investment, calculating the overall investment price and
searching for investments.

# Test Plan

- Test 1(Buy Gui Interface)
  - The User will be presented with 5 options for buying/adding a new investment to his portfolio(type, symbol, name, 
    quantity, price). In one example, the user can choose a type of stock, a symbol of "AAPL", a name of apple, a quantity
    of 100 and a price of 100 and then click the buy button, In this situation, all the parameters are correct 
    and the user will see the newly bought stock in the message box. In an instance where the user inputs a null string 
    for either name or symbol or inputs negative numbers for price and quantity, then an exceptions will be thrown for 
    each incorrect parameter and will be shown in the message box. The user will also be shown a button that resets all
    the text fields.

- Test 2(Sell Gui Interface)
  - The User will be presented with 3 options, symbol, quantity and price. In one example the user will enter an already
  existing stock such as "AAPL", input a quantity of say 50 and a price of 150 and then click the sell button. 
  The program will sell the amount the user specified and show which stock you have sold in the message box. In any 
  instance where those 3 parameters have given wrong values such as entering as invalid symbol, non-existing investment, 
  invalid price or invalid quantity. The program will throw a new exception in the message box to display the error to
  the user.The user will also be shown a button that resets all the text fields.

- Test 3(Update Gui Interface)
  - The User will be presented with 1 option, the price, In one example, the user will click next button, it will then
    show the user in the symbol and name text fields which investment he is on, the user will then for example input
    a new price of 120, the user then should click the save button to save that price to that investment. If the user
    wish's to update more investments, he should navigate them using the next and prev buttons, if the user inputs an
    invalid price, an exception will be thrown to the message box indicating the error.
    

- Test 4(Gain Gui Interface)
  - The User will be presented with no options, the interface will just calculate the total gains from all investments
    and will show it in the gain text field, and each individual gain for each investment will be shown in the message
    box.

- Test 5(Search Gui Interface)
  - The User will be presented with 4 options, symbol, name keywords, low price, high price. The user can search using 
  any field meaning he could use all fields for a specific search to find one or two investments or no fields to output 
  all investments located in his portfolio. To search, user much click the search button and if user wants to reset the 
  all text fields, user should click the reset button.

  
# limitations 
- can't search by any other field 
- it doesn't store any information other than what we stated
- it can't load from multiple files at a single time
- limitations to the interface
- limited to only 5 options that can be done with users portfolio

# implementation

- better interface organizations 
- add more functionality such as sorting your portfolio in different ways such as by alphabetic order or by time, etc..
- better java class organizations
