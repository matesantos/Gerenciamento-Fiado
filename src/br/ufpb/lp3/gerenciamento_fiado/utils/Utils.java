package br.ufpb.lp3.gerenciamento_fiado.utils;

import java.util.Arrays;
import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Utils {

	public static void goToActivity(Context currentActivity,
			Class<? extends Activity> newClass, Class<? extends Object> DAO) {
		Intent newActivity = new Intent(currentActivity, newClass);
		newActivity.putExtra(DAO.getCanonicalName(), DAO);
		currentActivity.startActivity(newActivity);
	}
	
	public static void goToActivity(Context currentActivity, Class<? extends Activity> newClass) {
		Intent newActivity = new Intent(currentActivity, newClass);
		currentActivity.startActivity(newActivity);
	}
	
	public static void goToActivityVendedor(Context currentActivity, Class<? extends Activity> newClass, Vendedor vendedor) {
		Intent newActivity = new Intent(currentActivity, newClass);
		newActivity.putExtra("vendedor", vendedor);
		currentActivity.startActivity(newActivity);
	}

	public static List<String> getUFList() {
		String[] list = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
				"MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
				"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
		List<String> ufList = Arrays.asList(list);

		return ufList;
	}

	public static void mostrarMensagens(Context currentActivity, String error) {
		Toast msg = Toast.makeText(currentActivity, error, Toast.LENGTH_SHORT);
		msg.show();
	}
	

}
