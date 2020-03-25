package br.com.dms.cadastro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.dms.cadastro.model.CadastroModel;

public class CadastroDAO {
	
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/";
	private static String BDNAME = "cadastro?useTimezone=true&serverTimezone=UTC";
	private static String USER = "root";
	private static String PASSWORD = "root";
   
	private static final String SQL_SELECT = "select * from cadastrar ";

    private static final String SQL_INSERT = "insert into cadastrar(nome, endereco, cpf, rg, sexo, email, celular, data_nasc, data_cad, cep) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE = "delete from archivo where id_archivo = ?";

    private static final String SQL_UPDATE = "update archivo set nombre = ? where id_archivo = ?";

	private  Connection con ;
	
	
	public Connection conect() {

       
        try {
           // Class.forName(DRIVER);
            return DriverManager.getConnection(URL + BDNAME,
                    USER, PASSWORD);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return null;

    }

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		CadastroModel cadastroModel = new CadastroModel();
		cadastroModel.setNome("lala");
		cadastroModel.setEndereco("vai");
		cadastroModel.setCpf("se");
		cadastroModel.setRg("fuder");
		cadastroModel.setSexo("f");
		cadastroModel.setEmail("vai");
		cadastroModel.setCelular("tomar");
		cadastroModel.setDataNasc("no cú");
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		cadastroModel.setDataCad(date);
		
		CadastroDAO cadastroDAO = new CadastroDAO();
		
		//cadastroDAO.inserirPessoa(cadastroModel);
		cadastroDAO.consultarPessoas();
		System.out.println(cadastroDAO);
	}
	
	 public List<CadastroModel> consultarPessoas() {
		 ResultSet rs;
	List<CadastroModel> resultado = new ArrayList<CadastroModel>();
	try {
		con = conect();
        PreparedStatement pstm = this.con.prepareStatement(SQL_SELECT);
        //pstm.setInt(1, paq.getId());
        rs = pstm.executeQuery();
        if (!rs.next()) {
          //  Logger.getLogger(ArchivoImpl.class.getName()).log(Level.INFO,
                   // "No hay Archivos");
        } else {
            do {
                CadastroModel cadastroModel = new CadastroModel();
                cadastroModel.setNome(rs.getString("nome"));
                cadastroModel.setEndereco(rs.getString("endereco"));
                cadastroModel.setCpf(rs.getString("cpf"));
                cadastroModel.setRg(rs.getString("rg"));
                cadastroModel.setSexo(rs.getString("sexo"));
                cadastroModel.setEmail(rs.getString("email"));
                cadastroModel.setCelular(rs.getString("celular"));
                cadastroModel.setDataNasc(rs.getString("data_nasc"));
                cadastroModel.setDataCad(rs.getDate("data_cad"));
                cadastroModel.setCep(rs.getString("cep"));
                
                System.out.println("**********************************************************************************");
                System.out.println(cadastroModel.getNome());
                System.out.println(cadastroModel.getEndereco());
                System.out.println(cadastroModel.getCpf());
                System.out.println(cadastroModel.getRg());
                System.out.println(cadastroModel.getSexo());
                System.out.println(cadastroModel.getEmail());
                System.out.println(cadastroModel.getCelular());
                System.out.println(cadastroModel.getDataNasc());
                System.out.println(cadastroModel.getDataCad());
                
                resultado.add(cadastroModel);

            } while (rs.next());
            
        }   
        if (!con.isClosed()) {
     		con.close();
     	}
        
        } catch (SQLException ex) {
            // Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return resultado;
     }
            
            
            public boolean inserirPessoa(CadastroModel cadastroModel) throws ClassNotFoundException, SQLException {

                boolean resultado = false;

                
                
                try {
                	
                	con = conect();
                    PreparedStatement pstm = con.prepareStatement(SQL_INSERT);
                    pstm.setString(1, cadastroModel.getNome());
                    pstm.setString(2, cadastroModel.getEndereco());
                    pstm.setString(3, cadastroModel.getCpf());
                    pstm.setString(4, cadastroModel.getRg());
                    pstm.setString(5, cadastroModel.getSexo());
                    pstm.setString(6, cadastroModel.getEmail());
                    pstm.setString(7, cadastroModel.getCelular());
                    pstm.setString(8, cadastroModel.getDataNasc());
                    pstm.setDate(9, cadastroModel.getDataCad());
                    pstm.setString(10, cadastroModel.getCep());
                    pstm.executeUpdate();
                    con.close();
                    resultado = true;
                    
                } catch (Exception ex) {
                	
                	ex.printStackTrace();
                   // Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }finally {
                	
                	if (!con.isClosed()) {
                		con.close();
                	}
                	
                }
                return resultado;
            }

}
