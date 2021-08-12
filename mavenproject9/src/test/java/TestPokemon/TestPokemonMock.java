/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPokemon;

import com.pokedex.ec.bo.PokemonBO;
import com.pokedex.ec.dao.PokemonDAO;
import com.pokedex.ec.dao.Methods;
import com.pokedex.ec.entity.Pokemon;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author
 */

@RunWith(MockitoJUnitRunner.class)
public class TestPokemonMock {

	@Mock
	DataSource ds;

	@Mock
	Connection con;

	@Mock
	PreparedStatement pst;

	@Mock
	ResultSet rs;

	@Mock
	private ArrayList<Pokemon> mockArrayList;

	@Mock
	private Methods mdao;

	@Mock
	private PokemonDAO pdao;

	PokemonBO pbo = new PokemonBO();
	private Pokemon p;

	@Before
	public void setUp() throws Exception {
		// prueba mockito con conexiones
		assertNotNull(ds);
		Mockito.when(con.prepareStatement(Mockito.any(String.class))).thenReturn(pst);
		Mockito.when(ds.getConnection()).thenReturn(con);

		p = new Pokemon();
		p.setIdpokemon(96);
		p.setName("weezing");
		p.setType("poison");
		p.setType2("poison");
		p.setLevel(15);
		p.setEvolution(1);
		p.setUser("Blue");

		/*
		 * Mockito.when(mockRs.getInt(1)).thenReturn(1);
		 */
		Mockito.when(rs.getString(1)).thenReturn(p.getName());
		Mockito.when(rs.getString(2)).thenReturn(p.getType());
		Mockito.when(rs.getString(3)).thenReturn(p.getType2());
		Mockito.when(rs.getInt(4)).thenReturn(15);
		Mockito.when(rs.getInt(5)).thenReturn(1);
		Mockito.when(rs.getString(6)).thenReturn(p.getUser());
		Mockito.when(pst.executeQuery()).thenReturn(rs);
	}

	// test de mock insert pokemon
	@Test
	public void TestInsertPokemonMock() throws SQLException, ClassNotFoundException {

		new PokemonDAO(ds).addPokemon(con, p);

	}

	// test insert pokemon throw exception
	@Test(expected = Exception.class)
	public void insertnullException() {

		new PokemonDAO().addPokemon(con, null);

	}

	@Test
	public void TestInsertPokemonEqual() {

		Mockito.when(pdao.addPokemon(Mockito.any(Connection.class), Mockito.any(Pokemon.class)))
				.thenReturn("There is already a pokemon with that name");
		String Esperado = pdao.addPokemon(con, p);

		String agregado = pbo.addPokemon(p);

		assertEquals(Esperado, agregado);

	}

	@Test
	public void testFindById() {

		/* Pokemon m = Mockito.mock(Pokemon.class); */

		// establecer valor de retorno
		Mockito.when(mdao.searchbyname(Mockito.any(Connection.class), Mockito.any(String.class))).thenReturn(82);

		int result = mdao.searchbyname(con, "Charmander");
		int esperado = pbo.serchaId("Charmander");

		assertEquals(esperado, result);
	}

	@Test
	public void TestListPokemon() {

		// creo arraylist para retornar y le agrego el parametro del before
		ArrayList<Pokemon> plist = new ArrayList<Pokemon>();
		plist.add(p);

		// establezco como valor de rotorno
		Mockito.when(pdao.getPokemon(con)).thenReturn(plist);

		ArrayList<Pokemon> pokemons = pdao.getPokemon(con);

		// verifico
		Mockito.verify(pdao).getPokemon(con);
		int result = pokemons.size();
		if (result > 0) {
			result = 1;
		}

		// testeo funcionamiento
		assertEquals(pokemons.get(0).getName(), "weezing");
		assertEquals(result, 1);

	}

	@Test
	public void TestOnePokemon() {

		Mockito.when(pdao.getPokemon(Mockito.any(Connection.class), Mockito.any(int.class))).thenReturn(p);
		Pokemon pokemon = pdao.getPokemon(con, 15);

		Mockito.verify(pdao).getPokemon(con, 15);

		Pokemon e = pbo.loadPokemon(96);

		assertEquals(e.getIdpokemon(), pokemon.getIdpokemon());

	}

	@Test
	public void TestListPokemonEvolves() {

		ArrayList<Pokemon> plist = new ArrayList<Pokemon>();
		plist.add(p);
		// agrego un pokemon conocido que posee evolucion
		Mockito.when(pdao.getPokemon(con)).thenReturn(plist);

		ArrayList<Pokemon> pokemons = pdao.getPokemon(con);

		// busco todos los pokemones ultima evolucion
		List<Pokemon> pokemonsevos = pbo.loadLastPokemon();

		// compruebo que los valores de evolucion seran diferentes
		assertNotEquals(pokemonsevos.get(1).getEvolution(), pokemons.get(0).getEvolution());

	}

	@Test
	public void TestListPokemonEvolvesSize() {

		ArrayList<Pokemon> plist = new ArrayList<Pokemon>();
		plist.add(p);
		plist.add(p);
		plist.add(p);
		plist.add(p);
		plist.add(p);
		// agrego un pokemon conocido que posee evolucion
		Mockito.when(pdao.getPokemon(con)).thenReturn(plist);

		ArrayList<Pokemon> pokemons = pdao.getPokemon(con);

		Mockito.verify(pdao).getPokemon(con);
		;

		int Pokemonsize = pokemons.size();

		// busco todos los pokemones ultima evolucion
		List<Pokemon> pokemonsevos = pbo.loadLastPokemon();

		int pokemonsEvossize = pokemonsevos.size();

		// compruebo que los valores de evolucion seran diferentes
		assertNotSame(pokemonsEvossize, pokemonsevos);

	}

	@Test
	public void TestModifyPokemon() {

		String esperado = "Edited OK";
		Mockito.when(pdao.modifyPokemon(Mockito.any(Connection.class), Mockito.any(Pokemon.class)))
				.thenReturn(esperado);

		String resultMock = pdao.modifyPokemon(con, p);

		String result = pbo.modifyPokemon(p);

		assertEquals(resultMock, result);

	}

	@Test
	public void TestModifyPokemonTypeNull() {

		String esperado = "Edited OK";
		Mockito.when(pdao.modifyPokemon(Mockito.any(Connection.class), Mockito.any(Pokemon.class)))
				.thenReturn(esperado);
		// el resultado da bien en el mock
		String resultMock = pdao.modifyPokemon(con, p);
		// cambiamos el pokemon para que de error de guardado
		p.setType(null);
		// iniciamos servicio para encontrar resultado
		String result = pbo.modifyPokemon(p);

		// ambos resultados deben ser opuestos (edited ok y error save)
		assertNotEquals(resultMock, result);

	}

	@Test
	public void TestModifyPokemonNameisNull() {
		String esperado = "Edited OK";
		Mockito.when(pdao.modifyPokemon(Mockito.any(Connection.class), Mockito.any(Pokemon.class)))
				.thenReturn(esperado);
		// el resultado da bien en el mock
		String resultMock = pdao.modifyPokemon(con, p);
		// cambiamos el pokemon para que de error de guardado
		p.setName(null);
		// iniciamos servicio para encontrar resultado
		String result = pbo.modifyPokemon(p);

		// ambos resultados deben ser opuestos (edited ok y error save)
		assertNotEquals(resultMock, result);

		if (result != "Edited OK") {
			result = null;
		}

		assertNotNull(resultMock);
		assertNull(result);

	}

	@Test
	public void TestFindIdPokemonByNameNotExist() {

		String esperado = null;
		Mockito.when(mdao.searchbyname(Mockito.any(Connection.class), Mockito.any(String.class))).thenReturn(0);
		int ResultadoDao = mdao.searchbyname(con, p.getName());
		if (ResultadoDao != 0) {
			esperado = String.valueOf(ResultadoDao);
		}

		String resultado = null;
		String Pokemon = "Cindaquil";

		int idPokemon = pbo.serchaId(Pokemon);
		if (idPokemon != 0) {
			resultado = String.valueOf(idPokemon);
		}

		assertEquals(esperado, resultado);
		assertNull(resultado);

	}

	@Test
	public void TestListAbilities() {

		List mockedList = Mockito.mock(List.class);

		Mockito.when(pdao.listAbilities(Mockito.any(int.class)))
				.thenReturn("select name from ability where idpokemon =" + "'" + 1 + "'");

		String name = pdao.listAbilities(99);

		Mockito.when(mdao.list(Mockito.any(Connection.class), Mockito.any(String.class))).thenReturn(mockedList);

		mockedList = mdao.list(con, name);

		mockedList.add("Buble");// lista mock con 1 elemento
		mockedList.add("Punch");// lista mock con 2 elementos

		Mockito.verify(mockedList).add("Buble");
		Mockito.verify(mockedList, Mockito.times(1)).add("Buble");

		List result = pbo.listAbilities(99);// deberia ser una lista vacia

		assertNotSame(mockedList, result);

	}

}
