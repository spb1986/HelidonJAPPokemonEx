
package pokemon.test;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import helidon.service.pokemon.entity.Pokemon;
import io.helidon.microprofile.tests.junit5.HelidonTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@HelidonTest
class TestPokemon {

	@Inject
	private WebTarget target;

	/*
	 * To test getPokemon with max arge value
	 */
	public void testGetWithMaxArge() {
		Pokemon pokemon = target.path("pokemon/maxArge").request().get(Pokemon.class);
		assertNotNull(pokemon);
	}
	
	
	/*
	 * To test getPokemon with min arge value
	 */
	public void testGetWithMinArge() {
		Pokemon pokemon = target.path("pokemon/minArge").request().get(Pokemon.class);
		assertNotNull(pokemon);
	}
	
	/*
	 * To test getPokemon by ID
	 */
	@Test
	public void testGetWithID() {
		Pokemon pokemon = target.path("pokemon/1").request().get(Pokemon.class);
		assertThat(pokemon.getName(), is("Caterpie"));
	}

	/*
	 * To test getPokemon by name starts with a string and in ascending order by arge
	 */
	@Test
	public void testGetWithNameStarts() {
		JsonArray pokemons = target.path("pokemon/name/C").request().get(JsonArray.class);
		assertTrue(pokemons.size() >= 1);
	}

	/*
	 * To test get pokemon list
	 */
	@Test
	void testPokemonList() {
		assertTrue(getPokemonCount() >= 1);
	}

	/*
	 * To test a pokemon creation
	 */
	@Test
	public void testCreatePokemon() {
		Pokemon test = new Pokemon();
		test.setType("Poison");
		test.setId("7");
		test.setName("Ekans");
		test.setArge(23);
		try (Response response = target.path("pokemon/create").request()
				.post(Entity.entity(test, MediaType.APPLICATION_JSON))) {
			assertThat(response.getStatus(), is(200));
			assertTrue(getPokemonCount() >= 1);
		}
	}

	/*
	 * To test the health and metrics
	 */
	@Test
	void testHealthMetrics() {
		try (Response response = target.path("health").request().get()) {
			assertThat(response.getStatus(), is(200));
		}
		try (Response response = target.path("metrics").request().get()) {
			assertThat(response.getStatus(), is(200));
		}
	}

	/*
	 * To get the count of pokemon records in DB.
	 */
	private int getPokemonCount() {
		JsonArray pokemons = target.path("pokemon/getList").request().get(JsonArray.class);
		return pokemons.size();
	}

}
