# HelidonJAPPokemonEx
This project constains the pokemon API for CRUD services

# Build and run
```
mvn package

java -jar target/PokemonAPI.jar
```

# Exercise the application
To get list of Pokemon data
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/getList
```
To get a pokeman with max arge 
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/maxArge
```
To get a pokeman with min arge 
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/minArge
```
To update a existing pokeman record
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/update

Input Ex:
{"arge":21,"id":"7","name":"Weedle2","type":"Ice2"}
```
To create a pokeman record
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/create

Input Ex:
{"arge":21,"id":"7","name":"Weedle2","type":"Ice2"}
```
To get a pokeman with specific ID 
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/{id}
```
To get a list of pokemon by name starting with a letter as input and arge ascending
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/name/{nameStartswith}
```
To get a pokeman with specific type
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/type/{type}
```

# Init DB data
```
{"id":"1","arge":10,"name":"Caterpie","type":"Bug"}
{"id":"2","arge":1,"name":"Bulbasaur","type":"Grass"}
{"id":"3","arge":8,"name":"Wartortle","type":"Water"}
{"id":"4","arge":87,"name":"Dewgong","type":"Ice"}
{"id":"5","arge":16,"name":"Pidgey","type":"Normal"}
{"id":"6","arge":5,"name":"Charmeleon","type":"Fire"}
```
