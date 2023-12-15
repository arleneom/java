package com.banco;

import java.util.ArrayList;
import java.util.Scanner;

public class Atm {

	private double balanceCredito;
	private double balanceCheques;
	private double balanceDonaciones;
	private double balance = 10_000;
	private ArrayList<AtmLogger> log;
	Scanner scanner = new Scanner(System.in);

	public Atm() {
		this.log = new ArrayList<>();

	}

	// ============== get and set

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	public double getBalanceDonaciones() {
		return this.balanceDonaciones;
	}

	public void setBalanceDonaciones(double balanceDonaciones) {
		this.balanceDonaciones = balanceDonaciones;
	}

	
//======================menu====================================================
	public void menu () {
		System.out.println("Bienvenido a BancaCh34\n");
		
		System.out.println("\t 1: Retirar dinero ");
		System.out.println("\t 2: Hacer depositos ");
		System.out.println("\t 3: Consultar saldo");
		System.out.println("\t 4: Quejas");
		System.out.println("\t 5: Ver ultimos movimientos");
		System.out.println("\t 9: Salir del cajero");
		

	}
	
//====================deposito====================================================

	public void deposit(Scanner scanner) {
		System.out.print("Cuanto desea depositar en su cuenta de cheques: ");
		double amount = scanner.nextDouble();			

	 if (amount % 50 != 0) {
			System.out.println("La cantidad debe ser múltiplo de $50.");

		} else if (amount > 0)  {
			System.out.println("No se permite depósitos igual o menor a cero");
		}
		else {setBalance(getBalance() + amount);
			log.add(AtmLogger.depositRegister(amount));
			System.out.println("Saldo actual: " + this.balance);

	}
//-------------------deposito a tarjeta de credito 
	public void depositCredit(Scanner scanner) {
		System.out.println("Cuanto desea depositar en su tarjeta de credito: ");
		double amount = scanner.nextDouble();

		if (amount > 0) {
			setBalance(getBalance() - amount);
			log.add(AtmLogger.depositCreditRegister(amount));
			System.out.println("Saldo actual: " + this.balance);

		} else {
			System.out.println("No se permite depósitos igual o menor a cero");
		}
	}

//==================retirar===========================================	

	public void withdraw(Scanner scanner) {
		// ----------muestra el balance
		System.out.println("Cantidad disponible a retirar: $" + this.balance);

		System.out.print("Ingrese la cantidad a retirar (múltiplos de $50): ");
		double amount = scanner.nextDouble();
		// -----------cantidad minima 50
		if (amount <= 0) {
			System.out.println("No se puede retirar una cantidad menor o igual a cero");
			// ----------- maximo a retirar
		} else if (amount >= 6000) {
			System.out.println("No se puede retirar montos iguales o superiores a $6,000.00 pesos");
			// --------------multiplos de 50
		} else if (amount % 50 != 0) {
			System.out.println("La cantidad debe ser múltiplo de $50.");
			// -----------
		} else if (amount > getBalance()) {
			System.out.println("Fondos insuficientes");

		} else {
			setBalance(getBalance() - amount);
			log.add(AtmLogger.withdrawRegister(amount));
			System.out.println("Saldo actual: " + this.balance);

		}

	}

//========================================================================================================
	public void printLog() {
		for (int i = 0; i > log.size(); i++) {
			System.out.println(log.get(i));
		}
	}

}