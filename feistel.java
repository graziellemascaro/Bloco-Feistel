package Feistel;
import java.util.Scanner;

public class feistel {
	
	static String xor(String a, String b) {
		String r[] = new String[4];
		for(int i=0; i<4; i++) {
			if(a.charAt(i)!=b.charAt(i)) r[i]="1";
			else r[i]="0";
		}
		return r[0]+r[1]+r[2]+r[3];
	}
	
	
	static String or(String a, String b) {
		String r[] = new String[4];
		for(int i=0; i<4; i++) {
			if(a.charAt(i)=='0' && b.charAt(i)=='0') r[i]="0";
			else r[i]="1";
		}
		return r[0]+r[1]+r[2]+r[3];
	}
	
	
	public static void main(String[] args) {
		
		/*
		 *** TESTES DAS FUNÇÕES ***
		System.out.println("TESTE XOR");
		System.out.println("0000 1111 = " +xor("0000", "1111"));
		System.out.println("0101 0111 = " +xor("0101", "0111"));
		System.out.println("1010 1111 = " +xor("1010", "1111"));
		System.out.println("0010 0100 = " +xor("0010", "0100"));
		
		System.out.println("TESTE OR");
		System.out.println("0000 1111 = " +or("0000", "1111"));
		System.out.println("0101 0111 = " +or("0101", "0111"));
		System.out.println("1010 1111 = " +or("1010", "1111"));
		System.out.println("0010 0100 = " +or("0010", "0100"));
		*/
		

		String key1 = "1001";
		String key2 = "0011";
		String bloco = null;
		Boolean valido=false;
		System.out.println("Insira o bloco com 8 bits");
		Scanner sc = new Scanner(System.in);
		int bitsValidos=0;
		
		while(valido==false) {
			bloco = sc.nextLine();
			bitsValidos=0;
			if(bloco.length()!=8) {
				System.out.println("Bloco inválido");
			} else {
				for(int i=0; i<8; i++) {
					if(bloco.charAt(i) != '0' && bloco.charAt(i) != '1') {
						System.out.println("Bit "+(i+1)+" inválido");
					} else {
						bitsValidos++;
					}
				}
				if(bitsValidos==8) valido=true;
			}
		}
		
		String left = bloco.substring(0, 4);
		String right = bloco.substring(4, 8);
		
		
		System.out.println();
		System.out.println("ENCRIPTAÇÃO");
		System.out.println("ESTAGIO 1");
		String auxiliar = right;

		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		System.out.print("1 - Função F com chave 1 em RIGHT -> "+right);
		right = or(right, key1);
		System.out.println(" OR "+key1+": "+right);

		System.out.print("2 - XOR RIGHT em LEFT -> "+left);
		left = xor(left, right);
		System.out.println(" XOR "+right+": "+left);

		System.out.println("3 - Invertendo valores: "
				+ "LEFT se torna RIGHT; "
				+ "Valor original de RIGHT se torna LEFT");
		right = left;
		left = auxiliar;
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		
		System.out.println();
		
		System.out.println("ESTAGIO 2");
		auxiliar = right;
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		System.out.print("1 - Função F com chave 2 em RIGHT: "+right);
		right = or(right, key2);
		System.out.println(" OR "+key2+" = "+right);
		
		System.out.print("2 - XOR RIGHT em LEFT: "+left);
		left = xor(left, right);
		System.out.println(" XOR "+right+" = "+left);
		
		System.out.println("3 - Invertendo valores: "
				+ "LEFT se torna RIGHT; "
				+ "Valor original de RIGHT se torna LEFT");
		right = left;
		left = auxiliar;
		
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		
		System.out.println();
		System.out.println(">>>>>> BLOCO CRIPTOGRAFADO: "+ (left+right));
		
		System.out.println();
		System.out.println();
		
		System.out.println("DECRIPTAÇÃO");
		System.out.println("ESTAGIO 1");
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		
		auxiliar = left;
		
		System.out.print("1 - Função F com chave 2 em LEFT -> "+left);
		left = or(left, key2);
		System.out.println(" OR "+key2+": "+left);
		
		System.out.print("XOR em LEFT e RIGHT -> "+right);
		right = xor(right, left);
		System.out.println(" XOR "+left+": "+right);
		
		System.out.println("Invertendo RIGHT e LEFT");
		left = right;
		right = auxiliar;
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);

		System.out.println();
		System.out.println("ESTAGIO 2");
		
		System.out.println("LEFT: " +left+ " / RIGHT: "+right);
		
		auxiliar = left;
		
		System.out.print("1 - Função F em LEFT -> "+left);
		left = or(left, key1);
		System.out.println(" OR "+key1+": "+left);
		
		System.out.print("2 - XOR em LEFT e RIGHT -> "+right);
		right = xor(right, left);
		System.out.println(" XOR "+left+": "+right);
		
		System.out.println("3 - Invertendo RIGHT e LEFT");
		left = right;
		right = auxiliar;
		
		
		System.out.println(">>>>>> BLOCO DECRIPTOGRAFADO: "+(left+right));
	}
}
