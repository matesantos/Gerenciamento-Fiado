package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorDAO;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AtualizarVendedorActivity extends Activity {

	//objeto que recebe os dados do vendedorSerializable de outras telas.
	Vendedor vendedorSerializable = null;

	//edit text
	private EditText nomeVendedor = null;
	private EditText rgVendedor = null;
	private EditText cpfVendedor = null;
	private EditText telefoneVendedor = null;
	private EditText loginVendedor = null;
	private EditText senhaVendedor = null;
	private EditText enderecoVendedor = null;
	private EditText numeroVendedor = null;
	private EditText cepVendedor = null;
	private EditText cidadeEdit = null;
	private EditText bairroVendedor = null;

	//spinner
	private Spinner spinnerUF = null;

	//button
	private Button atualizarVendedor = null;
	
	private List<String> listUF = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atualizar_vendedor);

		Bundle dados = getIntent().getExtras();

		vendedorSerializable = (Vendedor) dados.getSerializable("vendedor");

		// edit text
		nomeVendedor = (EditText) findViewById(R.id.editTextNomeVendedorTelaAtualizarVendedor);
		rgVendedor = (EditText) findViewById(R.id.editTextRGVendedorTelaAtualizarVendedor);
		cpfVendedor = (EditText) findViewById(R.id.editTextCPFVendedorTelaAtualizarVendedor);
		telefoneVendedor = (EditText) findViewById(R.id.editTextTelefoneVendedorTelaAtualizarVendedor);
		loginVendedor = (EditText) findViewById(R.id.editTextLoginVendedorTelaAtualizarVendedor);
		senhaVendedor = (EditText) findViewById(R.id.editTextSenhaVendedorTelaAtualizarVendedor);
		enderecoVendedor = (EditText) findViewById(R.id.editTextEnderecoVendedorTelaAtualizarVendedor);
		numeroVendedor = (EditText) findViewById(R.id.editTextNumeroVendedorTelaAtualizarVendedor);
		cepVendedor = (EditText) findViewById(R.id.editTextCEPVendedorTelaAtualizarVendedor);
		cidadeEdit = (EditText) findViewById(R.id.editTextCidadeVendedorTelaAtualizarVendedor);
		bairroVendedor = (EditText) findViewById(R.id.editTextBairroVendedorTelaAtualizarVendedor);

		spinnerUF = (Spinner) findViewById(R.id.spinnerUFTelaAtualizarVendedor);

		listUF = Utils.getUFList();
		ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listUF);
		spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerUF.setAdapter(spinnerList);
		spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());
		
		atualizarVendedor = (Button) findViewById(R.id.buttonAtualizarTelaAtualizarVendedor);
		
		atualizarVendedor.setOnClickListener(new AtualizarButton());

		preencherCamposAtualizar();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atualizar_vendedor, menu);
		return true;
	}
	
	private class AtualizarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Endereco end = new Endereco(enderecoVendedor.getText().toString(),numeroVendedor.getText().toString(),cepVendedor.getText().toString(),
					spinnerUF.getSelectedItem().toString(),cidadeEdit.getText().toString(), bairroVendedor.getText().toString());
			
			if(atualizarVendedor(1l,nomeVendedor.getText().toString(),telefoneVendedor.getText().toString(),rgVendedor.getText().toString(),
							  cpfVendedor.getText().toString(),end,loginVendedor.getText().toString(),senhaVendedor.getText().toString())){
				
				Utils.mostrarMensagens(AtualizarVendedorActivity.this, "Os dados foram atualizados com sucesso");
				
				retornarTela();
			}
		}
		
	}
	
	private void retornarTela(){
		Utils.goToActivityVendedor(this, GerenciarVendedorActivity.class, vendedorSerializable);
	}
	
	private boolean atualizarVendedor(Long id,String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		if(validarCampos(id, nome, telefone, rg, cpf, endereco, login, senha) == false){;
			return false;
		}
		
		Vendedor vendedor = new Vendedor(id, nome, telefone, rg, cpf, endereco, login, senha);
		
//		try {
			
			VendedorDAO atualizarDados = VendedorBDFactory.getVendedorBD(this);

			if(atualizarDados.atualizar(vendedor)){
				
				vendedorSerializable = vendedor;
				
				return true;
			}
			
//		}catch (Exception e) {
//			Log.e("Exception1", e.getMessage());
//			Log.e("Exception2",Log.getStackTraceString(e.fillInStackTrace()));
//		}
		
		return false;
	}

	private void preencherCamposAtualizar() {
		nomeVendedor.setText(vendedorSerializable.getNome());
		rgVendedor.setText(vendedorSerializable.getRg());
		cpfVendedor.setText(vendedorSerializable.getCpf());
		telefoneVendedor.setText(vendedorSerializable.getTelefone());
		loginVendedor.setText(vendedorSerializable.getLogin());
		senhaVendedor.setText(vendedorSerializable.getSenha());
		enderecoVendedor.setText(vendedorSerializable.getEndereco().getRua());
		numeroVendedor.setText(vendedorSerializable.getEndereco().getNumero());
		cepVendedor.setText(vendedorSerializable.getEndereco().getCep());
		cidadeEdit.setText(vendedorSerializable.getEndereco().getCidade());
		bairroVendedor.setText(vendedorSerializable.getEndereco().getBairro());

		spinnerUF.setSelection(getPositionDefaultSpinner());
	}

	private int getPositionDefaultSpinner() {
		int posicao = 0;
		for (int i = 0; i < listUF.size(); i++) {
			if (vendedorSerializable.getEndereco().getEstado()
					.equalsIgnoreCase(listUF.get(i))) {
				posicao = i;
			}

		}

		return posicao;
	}

	private class SpinnerUFInfo implements OnItemSelectedListener {
		private boolean isFirst = true;

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			if (isFirst) {
				isFirst = false;
			} else {
				cidadeEdit = (EditText) findViewById(R.id.editTextCidadeVendedorTelaCadastrarVendedor);
				if (isFirst) {
					isFirst = false;
				} else {
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
	
	private boolean validarCampos(Long id,String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
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
		}else if(login.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoLoginVendedorVazio);
			return false;
		}else if(senha.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoSenhaVendedorVazio);
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
