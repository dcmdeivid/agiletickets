package br.com.caelum.agiletickets;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class PreencheBanco {    

	public static void main(String[] args) {
		EntityManagerCreator managerCreator = setUp();
		EntityManager manager = managerCreator.getInstance();

		manager.getTransaction().begin();
		
		limpaBanco(manager);
		
		Estabelecimento estabelecimento = criaEstabelecimento(manager);
		
		Espetaculo espetaculo = criaEspetaculo(manager, estabelecimento);

		criaSessoesParaEspetaculo(manager, espetaculo);

		manager.getTransaction().commit();
		manager.close();
	}

	private static void criaSessoesParaEspetaculo(EntityManager manager,
			Espetaculo espetaculo) {
		for (int i = 0; i < 10; i++) {
			Sessao sessao = proximaSessao(espetaculo, i);
			manager.persist(sessao);
		}
	}

	private static Sessao proximaSessao(Espetaculo espetaculo, int i) {
		Sessao sessao = new Sessao();
		sessao.setEspetaculo(espetaculo);
		sessao.setInicio(new DateTime().plusDays(7+i));
		sessao.setDuracaoEmMinutos(60 * 3);
		sessao.setTotalIngressos(10);
		sessao.setIngressosReservados(10 - i);
		return sessao;
	}

	private static Espetaculo criaEspetaculo(EntityManager manager,
			Estabelecimento estabelecimento) {
		Espetaculo espetaculo = populaEspetaculoInicial(estabelecimento);
		manager.persist(espetaculo);
		return espetaculo;
	}

	private static Espetaculo populaEspetaculoInicial(
			Estabelecimento estabelecimento) {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome("Depeche Mode");
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);
		return espetaculo;
	}

	private static Estabelecimento criaEstabelecimento(EntityManager manager) {
		Estabelecimento estabelecimento = criaEstabelecimentoInicial();
		manager.persist(estabelecimento);
		return estabelecimento;
	}

	private static Estabelecimento criaEstabelecimentoInicial() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome("Casa de shows");
		estabelecimento.setEndereco("Rua dos Silveiras, 12345");
		return estabelecimento;
	}

	private static void limpaBanco(EntityManager manager) {
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
	}

	private static EntityManagerCreator setUp() {
		EntityManagerFactoryCreator creator = new EntityManagerFactoryCreator();
		creator.create();
		EntityManagerCreator managerCreator = new EntityManagerCreator(creator.getInstance());
		managerCreator.create();
		return managerCreator;
	}
}
