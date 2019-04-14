To build this application run - man clean install
Application at start up pulls up data from - https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh.
(For simplicity it fetches only 100 records. It can download all the records in advance from source and keep it in a file or some datastore.)

All pulled records are stored in a self-balanced red - black binary tree based on x/y coordinates. (Square of x and square of y added; forms the key). Assumption ther are no dups at same coordinates. 

For incoming request with x,y coordinate and radius. Application searches the records from - to (from - x2+y2 < r2 - r being radius)in a BST. 


Create a search application where you expose an endpoint for a client to search based on a certain radius for tree related data.

- You can find Street tree data from the TreesCount 2015 here => https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh
- The direct call to api is `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`
- You have to expose and API endpoint accepting two parameters 
    1. A Cartesian Point specifying a center point along the x & y plane
    2. A search radius in meters

Output
 - You have to retrieve the count of "common name" (please see in the documentation on the same page above which field to refer to) for all 
 the species of trees in that search radius
 - Expected outcome from the api
```json
{
    "red maple": 30,
    "American linden": 1,
    "London planetree": 3
}
```
