### APEX - REST API Documentation

A Java Spring based REST server to expose Vantage ERP data from a secondary database.

#### Build:

```
git clone https://github.com/prashanta/apex.git

mvn clean install

curl --upload-file target/apex.war "http://username:password@192.168.1.99:8080/manager/text/deploy?path=/apex&update=true"
```
---

#### API Listing:

| Path | Description |
|---|---|
| /api/parts/{partnum} | Get part information of given part number. Includes part revisions. |
| /api/parts/{partnum}/detail | Get detail part information of given part number. Includes part revisions, primary bin, approved vendors. |
| /api/parts/{partnum}/revisions | Get revisions of given part number. |
| /api/parts/{partnum}/revisions/primary | Get primary revision of given part number. |
| /api/parts/{partnum}/{partrev}/materials | Get materials of a give part number and revision. |
| /api/parts/{partnum}/bins | Get list of bins containing part. |
| /api/parts/{partnum}/bins/primary | Get primary bin of part. |
| /api/parts/{partnum}/demands/jobs | Get list of jobs that demands part. |
| /api/parts/{partnum}/demands/orders | Get list of sales orders that demands part. |
| /api/parts/{partnum}/supplies/jobs | Get list of jobs that produces part. |
| /api/parts/{partnum}/supplies/pos | Get list of purchase orders that produces part. |
| /api/parts/{partnum}/salesorders| Get list of sales orders that demand part. |
| /api/jobs/{jobnum}| Get information on said Job Number |
| /api/jobs/{jobnum}/subs/{asmseq}| Get list of sub-assemblies for said Job Number and assembly sequence|
| /api/jobs/{jobnum}/mtls/{asmseq}| Get list of materials for said Job Number and assembly sequence|