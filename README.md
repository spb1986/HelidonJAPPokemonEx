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
curl -X GET curl -X GET http://localhost:8080/pokemon/getlist
```
To get a pokeman with max arge 
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/maxArge
```
To get a pokeman with min arge 
```sh
curl -X GET curl -X GET http://localhost:8080/pokemon/minArge
```
