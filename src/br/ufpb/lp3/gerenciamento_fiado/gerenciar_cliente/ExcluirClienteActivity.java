package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;
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

public class ExcluirClienteActivity extends Activity {

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
	private Button excluirCliente = null;

	private List<String> listUF = null;

	private Cliente clienteSerializable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_excluir_cliente);

			Bundle dados = getIntent().getExtras();
			clienteSerializable = (Cliente) dados.getSerializable("cliente");

			// edit text
			nomeCliente = (EditText) findViewById(R.id.editTextNomeClienteTelaExcluirCliente);
			rgCliente = (EditText) findViewById(R.id.editTextRGClienteTelaExcluirCliente);
			cpfCliente = (EditText) findViewById(R.id.editTextCPFClienteTelaExcluirCliente);
			telefoneCliente = (EditText) findViewById(R.id.editTextTelefoneClienteTelaExcluirCliente);
			enderecoCliente = (EditText) findViewById(R.id.editTextEnderecoClienteTelaExcluirCliente);
			numeroCliente = (EditText) findViewById(R.id.editTextNumeroClienteTelaExcluirCliente);
			cepCliente = (EditText) findViewById(R.id.editTextCEPClienteTelaExcluirCliente);
			cidadeEdit = (EditText) findViewById(R.id.editTextCidadeClienteTelaExcluirCliente);
			bairroCliente = (EditText) findViewById(R.id.editTextBairroClienteTelaExcluirCliente);

			spinnerUF = (Spinner) findViewById(R.id.spinnerUFTelaExcluirCliente);

			listUF = Utils.getUFList();
			ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listUF);
			spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spinnerUF.setAdapter(spinnerList);
			spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());

			// button
			excluirCliente = (Button) findViewById(R.id.buttonExcluirTelaExcluirCliente);
			excluirCliente.setOnClickListener(new ExcluirButton());

			preenncherCampos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atualizar_cliente, menu);
		return true;
	}

	private class ExcluirButton implements OnClickListener {

		@Override
		public void onClick(View v) {

			Endereco end = new Endereco(enderecoCliente.getText().toString(),
					numeroCliente.getText().toString(), cepCliente.getText()
							.toString(),
					spinnerUF.getSelectedItem().toString(), cidadeEdit
							.getText().toString(), bairroCliente.getText()
							.toString());

			if (atualizarDados(clienteSerializable.getId(), nomeCliente
					.getText().toString(),
					telefoneCliente.getText().toString(), rgCliente.getText()
							.toString(), cpfCliente.getText().toString(), end)) {

				Utils.mostrarMensagens(ExcluirClienteActivity.this,	"Cliente excluído com sucesso");

				retornarTelaAnterior();

			}
		}

	}

	private void retornarTelaAnterior() {
		Utils.goToActivity(this, BuscarClienteExcluir.class);
	}

	private boolean atualizarDados(Long id, String nome, String telefone,
			String rg, String cpf, Endereco endereco) {

		Cliente cliente = new Cliente(id, nome, telefone, rg, cpf, endereco);

		ClienteDAO cadastrarCliente = ClienteBDFactory.getClienteBD(this);

		if (cadastrarCliente.deletar(cliente)) {

			return true;
		}

		return false;

	}

	private class SpinnerUFInfo implements OnItemSelectedListener {
		private boolean isFirst = true;

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			if (isFirst) {
				isFirst = false;
			} else {
				cidadeEdit = (EditText) findViewById(R.id.editTextCidadeClienteTelaExcluirCliente);
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

	private void preenncherCampos() {

		nomeCliente.setText(clienteSerializable.getNome());
		rgCliente.setText(clienteSerializable.getRg());
		cpfCliente.setText(clienteSerializable.getCpf());
		telefoneCliente.setText(clienteSerializable.getTelefone());
		enderecoCliente.setText(clienteSerializable.getEndereco().getRua());
		numeroCliente.setText(clienteSerializable.getEndereco().getNumero());
		cepCliente.setText(clienteSerializable.getEndereco().getCep());
		cidadeEdit.setText(clienteSerializable.getEndereco().getCidade());
		bairroCliente.setText(clienteSerializable.getEndereco().getBairro());

		spinnerUF.setSelection(getPositionDefaultSpinner());

	}

	private int getPositionDefaultSpinner() {
		int posicao = 0;
		for (int i = 0; i < listUF.size(); i++) {
			if (clienteSerializable.getEndereco().getEstado()
					.equalsIgnoreCase(listUF.get(i))) {
				posicao = i;
			}

		}

		return posicao;
	}


}
