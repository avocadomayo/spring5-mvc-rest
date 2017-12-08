# spring5-mvc-rest
An attempt to implement a mock API in [Fruit Shop API](https://api.predic8.de/shop/docs)

## Usage
Implemented methods include GET, PUT, POST, PATCH, DELETE. 

```GET``` to ```localhost:8080/api/v1/customers``` to get all customer data

```POST``` to ```localhost:8080/api/v1/customers``` with the following information
```json
{
  "first_name": "Bobby",
  "last_name": "Brown"
}
```

```DELETE``` to ```localhost:8080/api/v1/customers/{id}```

----

```POST``` to ```localhost:8080/api/v1/vendors``` with the following information
```json
{
  "name": "Kin's Farm"
}
```
