
Needs <b>git</b> for installation

1 - Clone project
```bash
git clone https://github.com/serkan1496/rental-portal.git
```

2 - Swich to snapshot location
```bash
cd rental-portal/target
```
3 - Run jar file
```bash
java -jar rental-0.0.1-SNAPSHOT.jar
```





## Request/Response Sample
Create Company  Request

```bash
curl -X POST http://localhost:8080/company/save \
  -H 'Content-Type: application/json' \
  -d '{
	"name" : "Company1",
	"city" : "Istanbul",
	"employeeNumber" : 12300
}'
```
Response
```javascript
{
    "message": "Success",
    "response": {
        "id": 1,
        "name": "Company1",
        "city": "Istanbul",
        "employeeNumber": 12300
    }
}
```

Create Meeting Room  Request
```bash
curl -X POST http://localhost:8080/meetingroom/save \
  -H 'Content-Type: application/json' \
  -d '{
	"name" : "Salon-1",
	"city" : "ISTANBUL",
	"district" : "KADIKÖY",
	"capacity" : 500
}'
```
Response
```javascript
{
    "message": "Success",
    "response": {
        "id": 2,
        "name": "Salon-1",
        "city": "ISTANBUL",
        "district": "KADIKÖY",
        "capacity": 500
    }
}
```
Create Reservation Request - 1
```bash
curl -X POST  http://localhost:8080/reservation/save \
  -H 'Content-Type: application/json' \
  -d '{
	"company" : {
        "id": 1
    },
    "meetingRoom" : {
        "id": 2
    },
    "capacity" : 66,
    "startDate" : "2020-06-26 15:00:00",
    "endDate" : "2020-06-26 17:00:00"
}'
```
Response
```javascript
{
    "message": "The meeting room has been reserved successfully",
    "response": {
        "id": 3,
        "company": {
            "id": 1,
            "name": "Company1",
            "city": "Istanbul",
            "employeeNumber": 12300
        },
        "meetingRoom": {
            "id": 2,
            "name": "Salon-1",
            "city": "ISTANBUL",
            "district": "KADIKÖY",
            "capacity": 500
        },
        "capacity": 6,
        "startDate": "2020-06-26 14:00:00",
        "endDate": "2020-06-26 15:00:00"
    }
}
```

Create Reservation Request - 2
```bash
curl -X POST  http://localhost:8080/reservation/save \
  -H 'Content-Type: application/json' \
  -d '{
	"company" : {
        "id": 1
    },
    "meetingRoom" : {
        "id": 2
    },
    "capacity" : 66,
    "startDate" : "2020-06-26 15:00:00",
    "endDate" : "2020-06-26 18:00:00"
}'
```
Response
```javascript
{
    "message": "Company allready reserved this meeting room",
    "response": null
}
```


Create Reservation Request - 3
```bash
curl -X POST  http://localhost:8080/reservation/save \
  -H 'Content-Type: application/json' \
  -d '{
	"company" : {
        "id": 1
    },
    "meetingRoom" : {
        "id": 2
    },
    "capacity" : 600,
    "startDate" : "2020-06-26 17:00:00",
    "endDate" : "2020-06-26 18:00:00"
}'
```
Response
```javascript
{
    "message": "This meeting room’s capacit  restricted 500 people",
    "response": null
}
```


Full API's : https://www.postman.com/collections/8c7be3b813b89bb142ff
