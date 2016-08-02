### APEX - REST API Documentation
 
#### Build:

_mvn clean compile package_ 

----------

#### API Listing

- __ /api/parts/{partnum} __ : Get part information of given part number. Includes part revisions.

- __ /api/parts/{partnum}/detail __ : Get detail part information of given part number. Includes part revisions, primary bin, approved vendors.
 
- __ /api/parts/{partnum}/revisions __ : Get revisions of given part number.

- __ /api/parts/{partnum}/revisions/primary __ : Get primary revision of given part number.

- __ /api/parts/{partnum}/{partrev}/materials __ : Get materials of a give part number and revision.

- __ /api/parts/{partnum}/bins __ : Get list of bins containing part.

- __ /api/parts/{partnum}/demands/jobs __ : Get list of jobs that demands part.

- __ /api/parts/{partnum}/demands/orders __ : Get list of sales orders that demands part.

- __ /api/parts/{partnum}/supplies/jobs__ : Get list of jobs that produces part.

- __ /api/parts/{partnum}/supplies/pos__ : Get list of purchase orders that produces part.


