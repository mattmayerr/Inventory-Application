<strong>** DO NOT DISTRIBUTE OR PUBLICLY POST SOLUTIONS TO THESE LABS. MAKE ALL FORKS OF THIS REPOSITORY WITH SOLUTION CODE PRIVATE. PLEASE REFER TO THE STUDENT CODE OF CONDUCT AND ETHICAL EXPECTATIONS FOR COLLEGE OF INFORMATION TECHNOLOGY STUDENTS FOR SPECIFICS. ** </strong>

# WESTERN GOVERNORS UNIVERSITY 
## D287 – JAVA FRAMEWORKS

### C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.


Note: Do not remove any elements that were included in the screen. You may add any additional elements you would like or any images, colors, and styles, although it is not required.

### The following changes were made:
Shop name was changed to --> Cars Galore on line 19<br>
Product Names was changed to --> Vehicle on line 53<br>
Parts was changed to --> Car Parts on line 21<br>
Changed the browser tab title to --> Cars Galore on line 14<br>
Added 6 Car Parts<br>
Added 3 Vehicles

### D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

### The following changes were made:
About.html: Changed tab title to --> About - Cars Galore on line 14<br>
About.html: Added main header 'About' on line 19<br>
About.html: Added h3 'Our Goal' on line 21<br>
About.html: Added goal paragraph on line 22<br>
About.html: Added URL image of a car on line 23<br>
About.html: Added a button named 'Home' that links back to the mainscreen page on line 38-41

AboutPageController.java: Added controller file to allow navigation to the 'About' page<br>
AboutPageController.java: Added @Controller to class on line 6<br>
AboutPageController.java: Added @GetMapping inside the @Controller class to allow navigation to About.html on line 9

mainscreen.html: added styling for the 'About' page button on line 10-18<br>
mainscreen.html: added an 'About Us' button that links to the About.html page on line 39-41


### E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.


Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.

### The following changes were made:
BootStrapData.java: Added 5 products and 5 parts on line 60-137<br>
BootStrapData.java: 3 parts are in house, and the last 2 are outsourced from 'Wayne's Transmission Repair' and 'Jerry's Automotive'<br>
BootStrapData.java: Added logic to avoid duplicate sample data on line 59

### F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

### The following changes were made:
mainscreen.html: added a 'Buy Now' button to the parts table on line 64-67<br>
mainscreen.html: added a 'Buy Now' button to the products table on line 105-108<br>
InventoryController.java: created this controller file to handle the POST requests for buyPart and buyProduct<br>
purchaseSuccess.html: added html to validate purchase success<br>
purchaseError.html: added html to validate purchase errors


### G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

### The following changes were made:
Part.java: added minInv and maxInv field with validation on line 34 & 37<br>
Part.java: Created a new constructor, getter/setter methods, and isInvValid() method on line 48, 88, 92, 96, 100, 104<br>
Part.java: added @Override above the toString method on line 134<br>
BootStrapData.java: added minInv and maxInv values to sample inventory on line 46-47, 56-57, 66-67, 76-77, and 86-87<br>
InhousePartForm.html: added input fields for minInv and maxInv on lines 23 and 26<br>
OutsourcedPartForm.html: added input fields for minInv and maxInv on lines 30 and 35<br>
application.properties: renamed database to --> cars

### H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

### The following changes were made:
AddInhousePartController.java: added code to check inventory and reject invalid inputs with specific error messages on line 54-62<br>
AddOutsourcedPartController.java: added code to check inventory and reject invalid inputs with specific error messages on line 52-60<br>
EnufPartsValidator.java: added code to check if any parts would fall below the min inventory when a part is added or updated on line 30-44<br>
ValidEnufParts.java: changed the message for errors to --> "One or more associated parts would fall below their minimum inventory if this product is added or updated." on line 20


### I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

### The following changes were made:
PartTest.java: added 4 methods for testing, getMinInv(), setMinInv(), getMaxInv(), and setMaxInv() from line 161-195<br>
pom.xml: changed version number of maven compiler plugin to 3.8.1 for compatability on running tests


### J.  Remove the class files for any unused validators in order to clean your code.

### The following changes were made:
DeletePartValidator.java: removed this file due to no validators being used<br>
AddInhousePartController.java: added some code that will allow me to click on 'Add Inhouse Part' without a white label page error on line 36 & 45