package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;

public class Calculadora {
	private static boolean pontoPermitido = true;
	private static JFrame janela = new JFrame();
	private static JTextField texto_equacao = new JTextField();
	private static Border botaoBorda =  BorderFactory.createRaisedBevelBorder();
	private static Font botaoFonte = new Font("TimesRoman", Font.PLAIN, 24);
	private static Color corAzul = new Color(77, 195, 255);
	private static Color corLaranja = new Color(255, 153, 102);
	private static Color corAzulEscuro = new Color(102, 102, 153);
	
	public static void main(String[] args) {
		//Criacao da tela e de super
		janela.setTitle("Calculadora");
		janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		janela.setBounds(100, 100, 400, 600);
		janela.setLayout(new BorderLayout());
		
		//Criacao de botoes 1 a 9 e 0
		JPanel container_centro = new JPanel();
		JPanel container_numeros = new JPanel();
		
		container_numeros.setLayout(new GridLayout(3,3));
		container_centro.setLayout(new BorderLayout());
		
		for(int numero_altura = 1;numero_altura <= 9;numero_altura++){
			JButton botao_numero = new JButton();
			botao_numero.setText(Integer.toString(numero_altura));
			botao_numero.addActionListener(e -> {BotaoNumero_Click(e.getSource());});
			botao_numero.setBackground(corAzul);
			botao_numero.setBorder(botaoBorda);
			botao_numero.setFont(botaoFonte);
			container_numeros.add(botao_numero);
		}		
		JPanel container_zero_ponto = new JPanel();
		container_zero_ponto.setLayout(new GridLayout());
		JButton botao_zero = new JButton();
		botao_zero.setText("0");
		botao_zero.setBackground(corAzul);
		botao_zero.setBorder(botaoBorda);
		botao_zero.setFont(botaoFonte);
		botao_zero.setPreferredSize(new Dimension(0,200));
		botao_zero.addActionListener(e -> {BotaoNumero_Click(e.getSource());});
		botao_zero.setPreferredSize(new Dimension(0, 90));
		
		//Criacao do botao .
		JButton botao_virgula = new JButton();
		botao_virgula.setText(".");
		botao_virgula.setBackground(corAzul);
		botao_virgula.setBorder(botaoBorda);
		botao_virgula.setFont(botaoFonte);
		botao_virgula.addActionListener(e -> {BotaoPonto_Click(e.getSource());});
		botao_virgula.setPreferredSize(new Dimension(0, 90));
		
		container_centro.add(container_numeros,BorderLayout.CENTER);
		container_zero_ponto.add(botao_zero);
		container_zero_ponto.add(botao_virgula);
		container_centro.add(container_zero_ponto,BorderLayout.SOUTH);
		janela.add(container_centro,BorderLayout.CENTER);
		
		//Criacao das operacoes + - / % e Enter
		JPanel container_operacoes = new JPanel();
		container_operacoes.setLayout(new GridLayout(7,1));
		container_operacoes.setPreferredSize(new Dimension(100,0));
		
		String[] texto_operacoes = {"+","-","*","/","%","C"};
		for(int numero_altura = 0;numero_altura <= 5;numero_altura++){
			JButton botao_operacao = new JButton();
			botao_operacao.setText(texto_operacoes[numero_altura]);
			botao_operacao.setBackground(corAzulEscuro);
			botao_operacao.setBorder(botaoBorda);
			botao_operacao.setFont(botaoFonte);
			botao_operacao.addActionListener(e -> {BotaoOperacao_Click(e.getSource());});
			container_operacoes.add(botao_operacao);
		}
		
		JButton botao_enter = new JButton();
		botao_enter.setText("Enter");
		botao_enter.setBackground(corLaranja);
		botao_enter.setBorder(botaoBorda);
		botao_enter.setFont(botaoFonte);
		botao_enter.setPreferredSize(new Dimension(0,100));
		botao_enter.addActionListener(e -> {BotaoEnter_CLick(e.getSource());});
		container_operacoes.add(botao_enter);
		
		janela.add(container_operacoes,BorderLayout.EAST);
		
		//Criacao do texto		
		texto_equacao.setPreferredSize(new Dimension(0,100));
		texto_equacao.setFont(new Font("SansSerif",Font.ITALIC,32));
		texto_equacao.setEditable(false);
		janela.add(texto_equacao,BorderLayout.NORTH);
		
		//Executa a tela
		janela.setVisible(true);
		
	}
	
	public static void BotaoNumero_Click(Object botao){
		JButton botaoReceptor = (JButton) botao;
		String texto = botaoReceptor.getText();
		texto_equacao.setText(texto_equacao.getText() + texto);
	}
	
	public static void BotaoPonto_Click(Object botao){
		//String textoEquacao = texto_equacao.getText().substring(texto_equacao.getText().length() -1);
		if(texto_equacao.getText().length() == 0) {
			texto_equacao.setText("0");
		}
		String textoEquacao = Character.toString(texto_equacao.getText().charAt(texto_equacao.getText().length()-1));
		if(!"+-/*%".contains(textoEquacao) && pontoPermitido){
			texto_equacao.setText(texto_equacao.getText() + ".");			
		}
		pontoPermitido = false;
	}
	
	public static void BotaoOperacao_Click(Object botao){
		pontoPermitido = true;
		JButton botaoReceptor = (JButton) botao;
		if(botaoReceptor.getText().equals("C")){
			texto_equacao.setText("");
		}else {
			String comparacao = "+*-/%.";
			String texto_equacao_text = (texto_equacao.getText().length() == 0) ? "+" : texto_equacao.getText();
			String ultimaLetra =  Character.toString(texto_equacao_text.charAt(texto_equacao_text.length()-1));
			String texto = (comparacao.contains(ultimaLetra)) ? texto_equacao.getText() : texto_equacao.getText().concat(botaoReceptor.getText());
			texto_equacao.setText(texto);
		}
		
	}
	public static void BotaoEnter_CLick(Object botao){
		//Limpa o texto e organiza a equacao
		String equacao = texto_equacao.getText();
		if(equacao.length() <= 1){
			return;
		}
		String textoTemp = equacao.substring(equacao.length() -1);
		if("+-/*%".contains(textoTemp)) {
			equacao = equacao.substring(0, equacao.length() - 1);
		}
		//Cria um array com os numeros e funcoes separadas
		equacao = equacao.replaceAll("\\+", ";sum;"); //sum
		equacao = equacao.replaceAll("\\-", ";sub;"); //subtract
		equacao = equacao.replaceAll("\\*", ";mul;"); //multiply
		equacao = equacao.replaceAll("/", ";div;"); //divide
		equacao = equacao.replaceAll("\\%", ";mod;"); //module
		String[] tudo_separado = equacao.split(";");
		LinkedList<String> lista_equacao = new LinkedList<String>(Arrays.asList(tudo_separado));
		//Calcula na ordem das equacoes
		String[] funcoes = {"mod","div","mul","sub","sum"};
		for(int cont_funcoes = 0;cont_funcoes < funcoes.length;cont_funcoes++) {
			//Checka se tem a funcao na equacao. Se tiver, a calcula e retira
			while(lista_equacao.contains(funcoes[cont_funcoes])) {
				//Separa os valores para a operacao
				int operacao = lista_equacao.indexOf(funcoes[cont_funcoes]);
				double numero_um = Double.parseDouble(lista_equacao.get(operacao-1));
				double numero_dois = Double.parseDouble(lista_equacao.get(operacao+1));
				//Faz o calculo
				switch(funcoes[cont_funcoes]) {
				case "mod":
					lista_equacao.set(operacao, Double.toString(numero_um % numero_dois));
					break;
				case "div":
					lista_equacao.set(operacao, Double.toString(numero_um / numero_dois));
					break;
				case "mul":
					lista_equacao.set(operacao, Double.toString(numero_um * numero_dois));
					break;
				case "sub":
					lista_equacao.set(operacao, Double.toString(numero_um - numero_dois));
					break; 
				default:
					lista_equacao.set(operacao, Double.toString(numero_um + numero_dois));
					break;
				}
				//Retira os valores antigos
				lista_equacao.remove(operacao+1);
				lista_equacao.remove(operacao-1);
			}
		}
		texto_equacao.setText(lista_equacao.getFirst());
		pontoPermitido = false;
	}
	
}
