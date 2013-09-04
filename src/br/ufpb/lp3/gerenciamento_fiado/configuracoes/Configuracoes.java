package br.ufpb.lp3.gerenciamento_fiado.configuracoes;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.content.SharedPreferences;
import br.ufpb.lp3.gerenciamento_fiado.R;

public class Configuracoes extends PreferenceActivity {
	
	private static final String OPT_BACKGRAUND_PADRAO = "padrao";
	private static final boolean OPT_BACKGRAUND_PADRAO_DEF = true;
	
//	private static final String OPT_BACKGRAUND_ROSA = "rosa";
//	private static final boolean OPT_BACKGRAUND_ROSA_DEF = false;
//	
//	private static final String OPT_BACKGRAUND_AMARELO = "padrao";
//	private static final boolean OPT_OPT_BACKGRAUND_AMARELO_DEF = false;
//	
//	private static final String OPT_BACKGRAUND_VERDE = "verde";
//	private static final boolean OPT_BACKGRAUND_VERDE_DEF = false;
//	

	private static final String OPT_SALVAR_DADOS_REMOTO = "salvarRemoto";
	private static final boolean OPT_SALVAR_DADOS_REMOTO_DEF = true;
	
	private CheckBoxPreference fundoPadrao  = null;
	private CheckBoxPreference salvarDadosRemotamnete  = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_configuracoes);
		
		fundoPadrao = (CheckBoxPreference) findPreference(getString(R.string.backgroundPadraoKey));
		fundoPadrao.setOnPreferenceClickListener(new FundoPadraoListener());
//		fundoRosa.setOnPreferenceChangeListener(new OnPreferenceChangeListenerTrue());
		
		salvarDadosRemotamnete = (CheckBoxPreference) findPreference(getString(R.string.salvarDadosRemotamenteKey));
		salvarDadosRemotamnete.setOnPreferenceClickListener(new SalvarDadosRemotos());
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.configuracoes, menu);
		return true;
	}
	
	public static boolean getBackGroundPadrao(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).
				getBoolean(OPT_BACKGRAUND_PADRAO, OPT_BACKGRAUND_PADRAO_DEF);
	}
	
//	public static boolean getBackGroundRosa(Context context){
//		return PreferenceManager.getDefaultSharedPreferences(context).
//				getBoolean(OPT_BACKGRAUND_ROSA, OPT_BACKGRAUND_ROSA_DEF);
//	}
//	
	public static boolean getSalvarDadosRemoto(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).
				getBoolean(OPT_SALVAR_DADOS_REMOTO, OPT_SALVAR_DADOS_REMOTO_DEF);
	}
	
	class FundoPadraoListener implements OnPreferenceClickListener{
		SharedPreferences.Editor editor = getPreferences(MODE_WORLD_WRITEABLE).edit();

		@Override
		public boolean onPreferenceClick(Preference arg0) {
			if(fundoPadrao.isChecked()){
			editor.putBoolean(fundoPadrao.getKey(), true);
		}else{
			editor.putBoolean(fundoPadrao.getKey(), false);
		}
			editor.commit();
			return false;
		
	  }
	}
	
	class SalvarDadosRemotos implements OnPreferenceClickListener{
		SharedPreferences.Editor editor = getPreferences(MODE_WORLD_WRITEABLE).edit();

		@Override
		public boolean onPreferenceClick(Preference arg0) {
			if(fundoPadrao.isChecked()){
			editor.putBoolean(fundoPadrao.getKey(), true);
		}else{
			editor.putBoolean(fundoPadrao.getKey(), false);
		}
			editor.commit();
			return false;
		
	  }
	}

	
//	class OnPreferenceChangeListenerTrue implements android.preference.Preference.OnPreferenceChangeListener{
//		
//		boolean cor;
//		
//		SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
//		@Override
//		public boolean onPreferenceChange(Preference preference, Object newValue) {
//			if(fundoRosa.isChecked()){
//				editor.putBoolean(fundoRosa.getKey(), true);
//			}else{
//				editor.putBoolean(fundoRosa.getKey(), false);
//			}
//			
//			editor.commit();
//			return false;
//		}
//		
//	}
	
}
