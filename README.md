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
