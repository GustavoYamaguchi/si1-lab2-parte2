import static org.junit.Assert.*;
import models.Meta;
import models.Semana;

import org.junit.*;

public class SemanaTest {

	private Semana semana1;
	private Meta meta1;
	private Meta meta2;
	private Meta meta3;
	private Meta meta4;
	private Meta meta5;
	
	@Before
	public void instanciaSemanas(){
		meta1 = new Meta("terminar o lab2", 0, 2);
		meta2 = new Meta("assistir o jogo da copa", 0, 0);
		meta3 = new Meta("estudar", 1, 5);
		meta4 = new Meta("dormir", 0, 3);
		meta5 = new Meta("abc", 0, 5);
		//semana 1
		semana1 = new Semana();
		semana1.addMeta(meta1);
		semana1.addMeta(meta2);
		semana1.addMeta(meta3);
	}
	
	@Test
	public void deveListarMaiorPrecedenciaAntes(){
		System.out.println(semana1.getMetas());
		assertTrue(semana1.getMetas().get(0).equals(meta3));
		assertTrue(semana1.getMetas().get(2).equals(meta2));
	}
	
	@Test
	public void deveAdicionarERemoverMetasOrdenandoDeAcordoComAPrioridade(){
		semana1.addMeta(meta4);
		semana1.addMeta(meta5);
		semana1.removeMeta(meta3);
		assertTrue(semana1.getMetas().get(0).equals(meta5));
		semana1.removeMeta(meta2);
		assertTrue(semana1.getMetas().get(2).equals(meta1));
	}
}
