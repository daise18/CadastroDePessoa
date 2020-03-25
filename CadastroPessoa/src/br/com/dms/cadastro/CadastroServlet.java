package br.com.dms.cadastro;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dms.cadastro.dao.CadastroDAO;
import br.com.dms.cadastro.model.CadastroModel;
import br.com.dms.cadastro.viewmodel.CadastroViewModel;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CadastroServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CadastroViewModel cadastroViewModel = new CadastroViewModel();

		CadastroModel cadastroModel = new CadastroModel();

		cadastroViewModel.setNome(request.getParameter("nome"));
		cadastroViewModel.setEndereco(request.getParameter("endereco"));
		cadastroViewModel.setCep(request.getParameter("cep"));
		cadastroViewModel.setEmail(request.getParameter("email"));
		cadastroViewModel.setCelular(request.getParameter("celular"));
		cadastroViewModel.setSexo(request.getParameter("sexo"));
		cadastroViewModel.setCpf(request.getParameter("cpf"));
		cadastroViewModel.setRg(request.getParameter("rg"));
		cadastroViewModel.setDataNasc(request.getParameter("data_nasc"));
		cadastroViewModel.setDataCad(request.getParameter("dataCad"));

		cadastroModel.setNome(cadastroViewModel.getNome());
		cadastroModel.setEndereco(cadastroViewModel.getEndereco());
		cadastroModel.setCpf(cadastroViewModel.getCpf());
		cadastroModel.setRg(cadastroViewModel.getRg());
		cadastroModel.setCep(cadastroViewModel.getCep());
		cadastroModel.setSexo(cadastroViewModel.getSexo());
		cadastroModel.setEmail(cadastroViewModel.getEmail());
		cadastroModel.setCelular(cadastroViewModel.getCelular());
		cadastroModel.setDataNasc(cadastroViewModel.getDataNasc());
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		cadastroModel.setDataCad(date);

		CadastroDAO cadastroDAO = new CadastroDAO();

		try {
			cadastroDAO.inserirPessoa(cadastroModel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<CadastroViewModel> pessoas = toViewModel(cadastroDAO.consultarPessoas());
		request.setAttribute("pessoas", pessoas);
		request.getRequestDispatcher("/cadastro/listar_pessoas.jsp").forward(request, response);
		// doGet(request, response);
	}

	private List<CadastroViewModel> toViewModel(List<CadastroModel> listaPessoaModel) {
		List<CadastroViewModel> lista = new ArrayList<CadastroViewModel>();

		for (CadastroModel model : listaPessoaModel) {
			CadastroViewModel pessoa = new CadastroViewModel();

			pessoa.setNome(model.getNome());
			pessoa.setCpf(model.getCpf());
			pessoa.setRg(model.getRg());
			pessoa.setEndereco(model.getEndereco());
			pessoa.setCep(model.getCep());
			pessoa.setEmail(model.getEmail());
			pessoa.setCelular(model.getCelular());
			pessoa.setDataNasc(model.getDataNasc());
			pessoa.setSexo(model.getSexo());
			lista.add(pessoa);

		}

		return lista;
	}

}
