# Seminar Search System Using Binary and Bin Trees

## Project Description

This repository showcases my Seminar Search System, developed as part of the CS3114/5040 course. The project enhances the search capabilities of a seminar database by replacing hash tables with binary search trees (BST) and Bintrees, facilitating efficient and dynamic search functionalities.

### Key Features

- **Multiple Search Trees**: Implementation of BSTs for searching seminars by IDs, costs, dates, and keywords.
- **Bintree for Spatial Data**: Utilizes a Bintree to manage 2D location-based searches, allowing efficient insertion, deletion, and range queries.
- **Dynamic Data Handling**: Supports various search operations, including exact match and range queries for different data types (integers and strings).

## Learning Outcomes

- **Advanced Data Structures**: Deepened understanding of binary search trees and Bintrees for managing and searching complex data.
- **Spatial Data Handling**: Gained insights into handling and querying 2D spatial data, particularly for location-based searches.
- **Efficiency in Data Retrieval**: Learned to optimize search algorithms for various types of queries, enhancing the system's overall efficiency.

## Usage

Run the program via the command line:

```java SemSearch {world-size} {command-file}```

- `{world-size}`: Integer specifying the size of the world for seminar locations, must be a power of two.
- `{command-file}`: Text file containing a series of commands for managing seminar records.

## Supported Commands

- **Insert**: Adds new seminar records to the trees.
- **Search**: Various forms for searching by ID, cost, date, keyword, and location.
- **Delete**: Removes a record by ID from all trees.
- **Print**: Traverses and prints the trees based on different criteria.

## Output

The system provides feedback for each command executed, indicating the success or failure of operations and displaying relevant data.

---

This project demonstrates my ability to implement and manipulate complex data structures for efficient data handling and retrieval. Feel free to explore the code to understand its detailed implementation. For any queries or feedback, feel free to reach out to me at brettn@vt.edu!
