/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class Main {

    final static String SEPARATOR = "\t\t\t\t";

    public static void main(String[] args) {
        consultarEmpleadosConSalario();
        consultarEmpleadosConSalarioConJefe();
    }

    private static void consultarEmpleadosConSalario() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.
                executeQuery("SELECT ENAME, SAL FROM dbo.EMP ORDER BY SAL");) {

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void consultarEmpleadosConSalarioConJefe() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.
                executeQuery("SELECT e.ENAME, e.SAL, j.ENAME, j.SAL FROM dbo.EMP e INNER JOIN dbo.EMP j ON e.MGR = j.EMPNO ORDER BY j.ename ");) {

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2) + SEPARATOR + result.getString(3) + SEPARATOR + result.getFloat(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    
}
