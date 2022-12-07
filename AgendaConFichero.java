

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAM2B-11
 */
public class AgendaConFichero{
    
    private static String FicheroTXT = "agenda.txt";
    private ArrayList<String> LineasArrayList = new ArrayList<String>();
    
    public int menu(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Menu");
        System.out.println("1 Dar alta");
        System.out.println("2 Dar baja");
        System.out.println("3 Mostar todo");
        System.out.println("4 Modificar");
        System.out.println("5 Buscar");
        System.out.println("0-Salir");
        System.out.println("Elegir acción:");
        int opcion = 0;
            try{
                opcion = sc.nextInt();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                System.out.println("Has elegido " + opcion);
            }
        
        sc.nextLine();
        
        return opcion;
    }

    public void alta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduce los apellidos:");
        String apellidos = sc.nextLine();
        System.out.println("Introduce la dirección:");
        String direccion = sc.nextLine();
        System.out.println("Introduce el número de teléfono:");
        String telefono = sc.nextLine();
        System.out.println("Introduce la edad:");
        int edad = sc.nextInt();
        File f = new File(FicheroTXT);
        if (f.exists()) {
            try{
                Contacto c = new Contacto(nombre,apellidos,direccion,telefono,edad);
                //Creamos el objeto fichero
                
                //Ahora necesitamos poder escribir en el fichero
                FileWriter fichero = new FileWriter(f,true);
                BufferedWriter bw = new BufferedWriter(fichero);
                //bw.write(c.toCSV());
                bw.write(c.toString());
                bw.write("\n");
                bw.close();
                fichero.close();
            }catch(Exception e){
                System.out.println("Error al escribir en el fichero");
                e.printStackTrace();
            }
        } else {
            try{
                f.createNewFile();
                Contacto c = new Contacto(nombre,apellidos,direccion,telefono,edad);
                //Ahora necesitamos poder escribir en el fichero
                FileWriter fichero = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fichero);
                //bw.write(c.toCSV());
                bw.write(c.toString());
                bw.write("\n");
                bw.close();
                fichero.close();
            }catch(Exception e){
                System.out.println("Error al escribir en el fichero");
                e.printStackTrace();
            }
        }
    }

    public void baja() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> contenido = new ArrayList<String>();
		System.out.print("Introduce apellidos:");
		String apellidos = sc.nextLine();
        boolean encontrado = false;
		try {
			File archivo=new File(FicheroTXT); 
			FileReader fr =new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			boolean borrar=false;
            int contador = 0;
			while((linea=br.readLine())!=null) {
                if (linea.contains(apellidos)) {
                    System.out.println("¿Estas seguro (S/N)?");
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("S")) {
                        encontrado = true;
                    }else{
                        contenido.add(linea);
                    }
                }else{
                    contenido.add(linea);
                }
                
			}
			br.close();
			fr.close();
            if (encontrado) {
                FileWriter fw = new FileWriter(archivo);	            
	            BufferedWriter bWriter = new BufferedWriter(fw);
	            //Escribimos en el archivo con el metodo write
	            int cont=1;
	            for(String con: contenido) {
		            bWriter.write(con);
	                bWriter.write("\n");
	                cont++;
	            }
	            //Cerramos la conexion
                bWriter.close();
	            fw.close();
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    public void modificacion() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> contenido = new ArrayList<String>();
		System.out.print("Introduce apellidos:");
		String apellidos = sc.nextLine();
		try {
			File archivo=new File(FicheroTXT); 
			FileReader fr =new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			boolean cambio=false;
			while((linea=br.readLine())!=null) {
				if(linea.contains(apellidos)) {
					if(linea.contains(apellidos)) {
		            	System.out.println("Estas seguro de modificar: s/n");
		            	String opcionModificar = sc.nextLine();
		            	if(opcionModificar.equalsIgnoreCase("s")) {	            		
		            		cambio =  true;
		            	}else {
		            		contenido.add(linea);
		            	}
		            }else {
		            	contenido.add(linea);
		            }
				}
			}
			br.close();
			fr.close();
			
			if(cambio) {				
	            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
	            FileWriter escribir = new FileWriter(archivo);	            
	            BufferedWriter bw = new BufferedWriter(escribir);
                Contacto c = new Contacto();
	            
                System.out.println("Introduce el número de teléfono:");
                String telefono = sc.nextLine();
                c.setPhone(telefono);
                contenido.add(c.toString());

                int cont=1;
	            for(String con: contenido) {
		            bw.write(con);
	                bw.write("\n");
	                cont++;
	            }

                bw.close();
	            escribir.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    public void busqueda() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca los apellidos de la persona a buscar:");
        String apellidos = sc.nextLine();
        File f = new File(FicheroTXT);
        if (f.exists()) {
            try{
                FileReader fichero = new FileReader(f);
                BufferedReader br = new BufferedReader(fichero);
                String linea;
                while((linea=br.readLine())!=null){
                    LineasArrayList.add(linea);
                }
                for (int i = 0; i < LineasArrayList.size(); i++) {
                    if(LineasArrayList.get(i).contains(apellidos)){
                        System.out.println(LineasArrayList.get(i));
                    }else{
                        System.out.println("Lo sentimos, el contacto que usted está buscando no se encuentra.");
                    }
                }
                br.close();
                fichero.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero no existe");
        }
    }

    public void mostrarTodo() {
        File f = new File(FicheroTXT);
        if (f.exists()) {
            try{ 
                FileReader fichero = new FileReader(f);
                BufferedReader br = new BufferedReader(fichero);
                String linea;
                while((linea = br.readLine()) != null){
                    System.out.println(linea);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero no existe");
        }
    }

    public static void main(String[] args) {
        AgendaConFichero a = new AgendaConFichero();
        int opcion = -1;
        do{
            opcion = a.menu(); 
            switch(opcion){
                case 1:
                    System.out.println("Alta");
                    a.alta();
                    break;
                case 2:
                    System.out.println("Baja");
                    a.baja();
                    break;
                case 3:
                    System.out.println("Mostrar todo");
                    a.mostrarTodo();
                    break;
                case 4:
                    System.out.println("Modificar");
                    a.modificacion();
                    break;
                case 5:
                    System.out.println("Buscar");
                    a.busqueda();
                    break;
                case 0:
                    System.out.println("Salir");
                    break;

                default:
                    System.out.println("Error, no se puede realizar esa operación.");
                    break;
            }
        }while(opcion!=0);
    }
}