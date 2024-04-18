# Project Json to XML

## Overview

The project aims to provide a comprehensive solution for parsing and analyzing JSON files containing data about cars. It includes functionalities to extract various attributes from the JSON files, perform statistical calculations, and generate XML reports based on the extracted data.

### Main Entities

#### Car
Represents a car object with attributes such as brand, model, and year.

#### UtilService
Provides utility methods for parsing JSON files and extracting car data.

#### CalculationStatistics
Performs statistical calculations on car data, such as counting occurrences of attributes and tags.

#### XmlFactory
Generates XML reports based on the analyzed car data.

Example Input and Output Files

### Input JSON Files
* cars.json: Contains a list of car objects with attributes like brand, model, and year.
json
#### Copy code
```
[
  {
    "brand": "Toyota",
    "model": "Camry",
    "year": 2018
  },
  {
    "brand": "Honda",
    "model": "Civic",
    "year": 2019
  },
  ...
]
```

### Output XML Reports
* statistics.xml: An XML report generated based on the analyzed car data, containing statistical information about attributes and tags.
xml
#### Copy code
```
<statistics>
    <attribute name="brand">
        <count value="Toyota">5</count>
        <count value="Honda">3</count>
        ...
    </attribute>
    <tag name="carTags">
        <count value="SUV">10</count>
        <count value="Sedan">8</count>
        ...
    </tag>
</statistics>
```

Experiment Results with Thread Count <br/>
1 Thread: Execution time - 0.211463444 seconds <br/>
2 Threads: Execution time - 0.1703827 seconds <br/>
4 Threads: Execution time - 0.180821507 seconds <br/>
8 Threads: Execution time - 0.187926616 seconds <br/>
