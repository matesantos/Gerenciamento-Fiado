package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.Arrays;
import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CadastrarClienteActivity extends Activity {

	private Spinner spinnerTelefone = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_cliente);
		
		spinnerTelefone = (Spinner) findViewById(R.id.spinnerTelefone);
        
        List<String> listColor = getTelefoneList();
        ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listColor);
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTelefone.setAdapter(spinnerList);
        
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_cliente, menu);
		return true;
	}
	
	 private List<String> getTelefoneList(){
	    	String [] list = {"Casa","Celular","Trabalho","Fax do Trabalho", "Fax de casa"};
	    	List<String> telefoneList = Arrays.asList(list);
	    	
	    	return telefoneList;
	    }

}
