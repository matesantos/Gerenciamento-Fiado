package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorDAO;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class CadastrarClienteActivity extends Activity {

	private EditText nomeCliente = null;
	private EditText rgCliente = null;
	private EditText cpfCliente = null;
	private EditText telefoneCliente = null;
	private EditText enderecoCliente = null;
	private EditText numeroCliente = null;
	private EditText cepCliente = null;
	private EditText cidadeEdit = null;
	private EditText bairroCliente = null;

	private Spinner spinnerUF = null;
	private Button apagar = null;
	private Button cadastrarCliente = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_cliente);

		// edit text
		nomeCliente 		= (EditText) findViewById(R.id.editTextNomeClienteTelaCadastrarCliente);
		rgCliente 			= (EditText) findViewById(R.id.editTextRGClienteTelaCadastrarCliente);
		cpfCliente 			= (EditText) findViewById(R.id.editTextCPFClienteTelaCadastrarCliente);
		telefoneCliente 	= (EditText) findViewById(R.id.editTextTelefoneClienteTelaCadastrarCliente);
		enderecoCliente 	= (EditText) findViewById(R.id.editTextEnderecoClienteTelaCadastrarCliente);
		numeroCliente 		= (EditText) findViewById(R.id.editTextNumeroClienteTelaCadastrarCliente);
		cepCliente 			= (EditText) findViewById(R.id.editTextCEPClienteTelaCadastrarCliente);
		cidadeEdit 			= (EditText) findViewById(R.id.editTextCidadeClienteTelaCadastrarCliente);
		bairroCliente 		= (EditText) findViewById(R.id.editTextBairroClienteTelaCadastrarCliente);
		
		
		spinnerUF = (Spinner) findViewById(R.id.spinnerUFTelaCadastrarVendedor);

		List<String> listUF = Utils.getUFList();
		ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listUF);
		spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerUF.setAdapter(spinnerList);
		spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());
		
		//button
		cadastrarCliente 	= (Button) findViewById(R.id.buttonCadastrarTelaCadastrarCliente);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_cliente, menu);
		return true;
	}
	
	private class SpinnerUFInfo implements OnItemSelectedListener{
    	private boolean isFirst = true;
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			if(isFirst){
				isFirst = false;
			}else{
				cidadeEdit = (EditText) findViewById(R.id.editTextCidadeVendedorTelaCadastrarVendedor);
				if(isFirst){
					isFirst = false;
				}else{
					int cidade = (int) spinnerUF.getSelectedItemId(); 
					switch (cidade) {
					case 0:
						
					break;
					
					case 1:
						
					break;
					
					case 2:
						
					break;
					
					case 3:
						
					break;
					
					case 14:
						cidadeEdit.setText("JOAO PESSOA");
					break;

					default:
						
					}
				}			
				
			}
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
		
	}
	
	private boolean cadastrarCliente(Long id,String nome, String telefone, String rg, String cpf,Endereco endereco){
		
		if(validarCampos(id, nome, telefone, rg, cpf, endereco) == false){;
			return false;
		}
		
		Cliente cliente = new Cliente(id, nome, telefone, rg, cpf, endereco);
		
		try {
			
			ClienteDAO cadastrarVendedor = ClienteBDFactory.getClienteBD(this);

			//primeiro, vamos checar se não existe um outro login e senha igual na hora do cadastro
//			Cursor curso = cadastrarVendedor.buscarVendedorPorLoginSenha(vendedor.getLogin(), vendedor.getSenha());
//			if(curso.getCount() != 0){
//				Utils.mostrarMensagens(this, Mensagens.loginSenhaExistentes);
//				return false;
//			}
//			
//			if(cadastrarVendedor.salvar(vendedor)){
//				return true;
//			}
			
		}catch (Exception e) {
			Log.e("Exception1", e.getMessage());
			Log.e("Exception2",Log.getStackTraceString(e.fillInStackTrace()));
		}
		
		return false;
	}
	
	private boolean validarCampos(Long id,String nome, String telefone, String rg, String cpf,
			Endereco endereco){
		
		if(nome.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNomeVendedorVazio);
			return false;
		}else if(rg.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRGVendedorVazio);
			return false;
		}else if(cpf.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCPFVendedorVazio);
			return false;
		}else if(telefone.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoTelefoneVendedorVazio);
			return false;
		}else if(endereco.getRua().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRuaVendedorVazio);
			return false;
		}else if(endereco.getNumero().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNumeroVendedorVazio);
			return false;
		}else if(endereco.getCep().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCEPVendedorVazio);
			return false;
		}else{
			return true;
		}
		
	}


}
