
package helidon.service.pokemon;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import helidon.service.pokemon.entity.ErrorResponse;
import helidon.service.pokemon.entity.Pokemon;

/**
 * This Resource is Pokemon API which supports below services
 *
 * get a specific pokemon with max arge and minimum arge
 * 
 * get a specific pokemon by ID
 * 
 * update a specific pokemon by ID
 *
 * create a new pokemon
 *
 * get a list of pokemon by name starting with a letter as input and arge
 * ascending
 * 
 * get all list of pokemon
 */
@Path("pokemon")
public class PokemonResource {

	@PersistenceContext(unitName = "pokemon")
	private EntityManager entityMgr;

	/*
	 * GET /pokemon/maxArge: To get a specific pokemon with max arge</li>
	 * 
	 * @param maxArge - from request
	 * 
	 * @return Pokemon - pokemon data in json format
	 */
	@GET
	@Path("maxArge")
	@Produces(MediaType.APPLICATION_JSON)
	public Pokemon getPokemonByMaxArge() {

		Pokemon maxArgeP = entityMgr.createNamedQuery("getPokemonbyMaxArge", Pokemon.class).getSingleResult();
		return maxArgeP;
	}

	/*
	 * GET /pokemon/maxArge: To get a specific pokemon with min arge</li>
	 * 
	 * @param minArge - from request
	 * 
	 * @return Pokemon - pokemon data in json format
	 */
	@GET
	@Path("minArge")
	@Produces(MediaType.APPLICATION_JSON)
	public Pokemon getPokemonByMinArge() {

		Pokemon minArgeP = entityMgr.createNamedQuery("getPokemonbyMinArge", Pokemon.class).getSingleResult();
		return minArgeP;
	}

	/*
	 * GET /pokemon/{id}: Retrieve a single pokemon by ID
	 * 
	 * @param id - pokemon id
	 * 
	 * @return JSON formated Pokeman data
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getPokemonById(@PathParam("id") String id) {
		Pokemon pokemon = entityMgr.find(Pokemon.class, id);
		if (pokemon == null) {
			return new ErrorResponse(404, "Unable to find pokemon with ID:" + id);
		}
		return pokemon;
	}

	/*
	 * POST /pokemon/update : To update existing Pokemon with requested pokemon data
	 * 
	 * @param update -from request url
	 * 
	 * @return updated status
	 */
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional(Transactional.TxType.REQUIRED)
	public ErrorResponse updatePokemon(Pokemon pokemon) {
		try {
			Pokemon p = entityMgr.find(Pokemon.class, pokemon.getId());
			if (p != null) {
				entityMgr.merge(pokemon);
			} else {
				return new ErrorResponse(404, "Pokemon with ID:" + pokemon.getId() + " doesn't exist to update");
			}
		} catch (Exception e) {
			// Handle errorResponse
		}
		return new ErrorResponse(200, "Successfully updated pokemon with ID:" + pokemon.getId());
	}

	/*
	 * POST /pokemon/create : To create a new Pokemon with requested pokemon data
	 * 
	 * @param create -from request url
	 * 
	 * @return updated status
	 */
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional(Transactional.TxType.REQUIRED)
	public ErrorResponse createPokemon(Pokemon pokemon) {
		try {
			Pokemon p = entityMgr.find(Pokemon.class, pokemon.getId());
			if (p != null) {
				return new ErrorResponse(404, "Pokemon already existed with ID:" + pokemon.getId());
			} else {
				entityMgr.persist(pokemon);
			}
		} catch (Exception e) {
			// Handle errorResponse
		}
		return new ErrorResponse(200, "Successfully created pokemon with ID:" + pokemon.getId());
	}

	/*
	 * GET /pokemon/name/{nameStartswith}: To get a list of pokemon by name starting
	 * with a letter as input and arge ascending
	 * 
	 * @param nameStartswith -substring or full name of pokemon return list JSON
	 * formated pokemon data
	 */
	@GET
	@Path("name/{nameStartswith}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pokemon> getPokemonByNameStartsWith(@PathParam("nameStartswith") String nameStartswith) {
		List<Pokemon> list = entityMgr.createNamedQuery("getPokemonList", Pokemon.class).getResultList();
		List<Pokemon> listUpdated = list.stream().filter(p -> p.getName().startsWith(nameStartswith))
				.sorted((p1, p2) -> p1.getArge() > p2.getArge() ? 1 : -1).collect(Collectors.toList());
		if (listUpdated.isEmpty()) {
			// Handle errorResponse
		}
		return listUpdated;
	}

	/*
	 * GET /pokemon/type: To get list of all pokemons by type
	 * 
	 * @param type -pokemon type value
	 * 
	 * @return list of pokemon JSON data
	 */
	@GET
	@Path("type/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pokemon> getPokemonByType(@PathParam("type") String type) {

		List<Pokemon> list = entityMgr.createNamedQuery("getPokemonByType", Pokemon.class).setParameter("type", type)
				.getResultList();
		return list;
	}

	/*
	 * GET /pokemon/getList: To get list of pokemons
	 * 
	 * @param getList - argument from request url
	 * 
	 * @return list of all pokemon JSON data
	 */
	@GET
	@Path("getList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pokemon> getPokemonList() {
		return entityMgr.createNamedQuery("getPokemonList", Pokemon.class).getResultList();
	}

}
