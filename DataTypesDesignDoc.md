A description of how the new concept of data types will be implemented (i.e. object, text, number, GEO, etc.)

# Introduction #

In order to make Sempedia genuiinely useful, several things need to be addressed. Currently one particularly useful feature is that when a triple is described the object of the triple immediately becomes a new object within Sempedia. This is good except in cases where the object is not really an object but for example a number. If this were to be displayed in the object search then the list will be extremely long and messy.

Moreover users are unlikely to want to to "faceted browsing" for exact matches of non-object values like numbers. They are more likely going to want to specify a range of parameters (e.g. numbers) and match all items that fit the criteria.

# Details #

The solution is to introduce the concept of data types. When a user creates a triple he/she will have to specify a "type" for the object. The four initial types are:

**Object - which results in a new object being created** Number - which allows a decimal number to be stored along with a set of units (x, units)
**GeoSpatial - which allows location coordinates to be specified
> Latitude - Hr Min Sec
> Longitude - Hr Min Sec**

Ideally when being displayed the different data types will be displayed appropriately and will also have appropriate methods for inputting values for the purpose of faceted searching.

The following is a description of the "interface features" provided for the different data types in the faceted searching interface.

**Object Type - clicking the object will result in that object being specified** Number - the user can specify higher and lower bounds
