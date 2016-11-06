package br.com.hackunivag.action;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.hackunivag.entidade.Professor;
import br.com.hackunivag.negocio.ProfessorNegocio;
import br.com.hackunivag.view.ProfessorLazyDataModel;

@Name("professorAcao")
@Scope(ScopeType.CONVERSATION)
public class ProfessorAcao extends BaseAcao {

	private static final String PROFESSOR_MANTER = "professor.manter";
	private static final String PROFESSOR_CADASTRAR = "professor.cadastrar";
	private static final String PROFESSOR_ALTERAR = "professor.alterar";

	@In(create = true)
	@Out(required = false)
	private Professor professor;

	@In(required = false)
	private ProfessorNegocio professorNegocio;

	private Professor professorPesquisa;

	private List<Professor> listaProfessor;

	private ProfessorLazyDataModel professorLazyDataModel;

	@Create
	public void init() {
		professorPesquisa = new Professor();
		professor = new Professor();
		professorLazyDataModel = new ProfessorLazyDataModel(professor, professorNegocio);

	}

	public void limpar() {
		professorPesquisa = new Professor();
		professor = new Professor();
		// listar();
	}

	public String exibirIncluir() {
		professor = new Professor();

		return PROFESSOR_CADASTRAR;
	}

	@Begin(join = true, flushMode = FlushModeType.MANUAL)
	public String exibirAlterar(Professor professor) {

		// this.professor = professor;
		return PROFESSOR_ALTERAR;
	}

	public String cancelar() {
		limpar();
		return PROFESSOR_MANTER;
	}

	public void listar() {
		listarProfessor();
	}

	public void listarProfessor() {
		professorLazyDataModel = new ProfessorLazyDataModel(professorPesquisa, professorNegocio);
	}

}
