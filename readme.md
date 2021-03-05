## My Voucher store

### Tests

![Java CI](https://github.com/rpietrzak/vouchershop-N3511/workflows/Java%20CI/badge.svg)

in order to perform tests on your own, feel free to execute

```bash
mvn test
```

### Ci via github actions

* https://github.com/actions/starter-workflows/blob/b20b76eb795a7c869d4c2a1adf166d10448ec758/ci/maven.yml
* https://github.com/actions/create-release
* https://github.com/actions/upload-release-asset

### testing via CURL

#### CRUD
##### Create
add client
```bash
curl -X POST http://localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Rafał", "lastname": "Pietrzak", "address": {"street": "lesna", "zip": "32-002", "city": "krakow"}}'
```

##### Read
get single client
```bash
curl http://localhost:9999/api/clients/3 
```

get list
```bash
curl http://localhost:9999/api/clients 
```

##### Update
update by id
```bash
curl -X POST http://localhost:9999/api/clients/4 -H 'content-type: application/json' -d '{"firstname": "Michał", "lastname": "Brzeczyszczykiewicz", "address": {"street": "nibylandia", "zip": "31-222", "city": "whatever"}}'
```
##### Delete
delete by id
```bash
curl -X DELETE http://localhost:9999/api/clients/1 
```


