package com.misofertas.data.connect;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
		private Connection conexion;
		
		public Connection getConexion() {
			return conexion;
		}
		public void setConexion(Connection conexion) {
			this.conexion = conexion;
		}
		
		public Conexion conectar() {
			
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String BaseDeDatos = "jdbc:oracle:thin:@misofertas.eastus.cloudapp.azure.com:1521:XE";
				
				conexion = DriverManager.getConnection(BaseDeDatos, "portafolio", "portafolio2018");
				
				if(conexion != null) {
					System.out.println("Conexion Exitosa!");
				}
				else {
					System.out.println("Conexion Fallida!");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return this;		
		}
	

}
