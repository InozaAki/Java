import java.util.Scanner;

public class Main{
    public final static void limpiarPantalla(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static class constantes{
        public static final int TOTAL_MESES = 12;
        public static final int PORCENTAJE_IVA = 16;
        public static final int PORCENTAJE_ISR = 11;
        public static final int ISR_RETENIDO = 10;
        public static final int IVA_RETENIDO = 10;
        public static final int C_100 = 100;
    }
    private static class opcionesMenu{
        public static final int ESCOGER_MES = 1;
        public static final int CAPTURA_INGRESOS = 2;
        public static final int CAPTURA_GASTOS = 3;
        public static final int MOSTRAR_INGRESOS = 4;
        public static final int MOSTRAR_GASTOS = 5;
        public static final int CALCULO_IMPUESTOS = 6;
        public static final int SALIR = 7;
    }
    public static void main(String args[]){
        //Variables entrada datos
        Scanner consola = new Scanner(System.in);
        float ingresosMensuales[] = new float[constantes.TOTAL_MESES];
        float gastosMensuales[] = new float[constantes.TOTAL_MESES];
        int opcion, opcionMes, mes = 0;
        //Variables computo
        String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int i;
        float ingreso, gasto;
        float IVA, retencionISR, subtotal, retencionIVA;
        float gananciaBruta, ISR, gastoIVA;
        boolean continuar = true;
        do{
            limpiarPantalla();
            System.out.println("CALCULO DE IMPUESTOS ANUAL\n");
            System.out.println("Menu principal: ");
            System.out.printf("1) Establecer mes para captura (mes actual es %s)\n", meses[mes]);
            System.out.println("2) Captura de ingresos");
            System.out.println("3) Captura de gastos");
            System.out.println("4) Mostrar lista de ingresos anuales");
            System.out.println("5) Mostrar lista de gastos anuales");
            System.out.println("6) Calculo de impuestos anual");
            System.out.println("7) Salir\n");
            System.out.print("Opcion: ");
            opcion = consola.nextInt();
            limpiarPantalla();
            consola.nextLine();
            switch(opcion){
                case opcionesMenu.ESCOGER_MES:
                    System.out.println("Establecer mes para captura");
                    System.out.println("1)  Enero \n2)  Febrero\n3)  Marzo\n4)  Abril\n5)  Mayo\n6)  Junio");
                    System.out.println("7)  Julio\n8)  Agosto\n9)  Septiembre\n10) Octubre\n11) Noviembre\n12) Diciembre");
                    System.out.print("Elige el mes (1 - 12): ");
                    opcionMes = consola.nextInt();
                    consola.nextLine();
                    if(opcionMes > 0 && opcionMes < 13){
                        mes = --opcionMes;
                        System.out.printf("Se ha establecido el mes de captura en %s\n", meses[mes]);
                    }else{ 
                        System.out.println("Mes no valido, se le regresara al menu principal");
                    }
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.CAPTURA_INGRESOS:
                    System.out.println("Captura de ingresos");
                    System.out.printf("Dame el ingreso del mes de %s: ", meses[mes]);
                    ingresosMensuales[mes] = consola.nextFloat();
                    consola.nextLine();
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.CAPTURA_GASTOS:
                    System.out.println("Captura de gastos");
                    System.out.printf("Dame el gasto del mes de %s: ", meses[mes]);
                    gastosMensuales[mes] = consola.nextFloat();
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.MOSTRAR_INGRESOS:
                    System.out.println("Mostrar lista de ingresos anuales");
                    for(i = 0; i < constantes.TOTAL_MESES; i++){
                        System.out.printf("%s = %.2f\n", meses[i], ingresosMensuales[i]);
                    }
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.MOSTRAR_GASTOS:
                    System.out.println("Mostrar lista de gastos anuales");
                    for(i = 0; i < constantes.TOTAL_MESES; i++){
                        System.out.printf("%s = %.2f\n", meses[i], gastosMensuales[i]);
                    }
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.CALCULO_IMPUESTOS:
                    //Calculo de impuestos
                    ingreso = 0;
                    gasto = 0;
                    for(i = 0; i < constantes.TOTAL_MESES; i++){
                        ingreso += ingresosMensuales[i];
                        gasto += gastosMensuales[i];
                    }
                    IVA = (ingreso * constantes.PORCENTAJE_IVA) / constantes.C_100;
                    subtotal = ingreso + IVA;
                    retencionISR = (ingreso * constantes.ISR_RETENIDO) / constantes.C_100;
                    retencionIVA = (ingreso * constantes.IVA_RETENIDO) / constantes.C_100;
                    gananciaBruta = ingreso - gasto;
                    ISR = (gananciaBruta * constantes.PORCENTAJE_ISR) / constantes.C_100;
                    gastoIVA = (gasto * constantes.PORCENTAJE_IVA) / constantes.C_100;
                    // Salida de resultados
                    System.out.printf("CALCULO DE IMPUESTOS\n");
                    System.out.printf("**** Tabla Recibo de Honoriarios ****\n");
                    System.out.printf("Ingresos                    %.2f\n", ingreso);
                    System.out.printf("(+) IVA                     %.2f\n", IVA);
                    System.out.printf("(=) Subtotal                %.2f\n", subtotal);
                    System.out.printf("(-) Retencion ISR           %.2f\n", retencionISR);
                    System.out.printf("(-) Retencion IVA           %.2f\n", retencionIVA);
                    System.out.printf("(=) Total                   %.2f\n", (subtotal-retencionISR) - retencionIVA);
                    System.out.printf("**** Tabla Ganancias ****\n");
                    System.out.printf("Ingresos                    %.2f\n", ingreso);
                    System.out.printf("(-) Gastos                  %.2f\n", gasto);
                    System.out.printf("(=) Ganancia Bruta          %.2f\n", gananciaBruta);
                    System.out.printf("(-) ISR                     %.2f\n", ISR);
                    System.out.printf("(=) Ganancia Neta           %.2f\n", gananciaBruta-ISR);
                    System.out.printf("**** Tabla ISR ****\n");
                    System.out.printf("ISR                         %.2f\n", ISR);
                    System.out.printf("(-) ISR Retenido            %.2f\n", retencionISR);
                    System.out.printf("(=) ISR a Pagar             %.2f\n", ISR - retencionISR);
                    System.out.printf("**** Tabla IVA ****\n");
                    System.out.printf("IVA                         %.2f\n", IVA);
                    System.out.printf("(-) Gastos IVA              %.2f\n", gastoIVA);
                    System.out.printf("(-) Retencion IVA           %.2f\n", retencionIVA);
                    System.out.printf("(=) IVA a Pagar             %.2f\n", (IVA-retencionIVA) - gastoIVA);
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
                    break;
                case opcionesMenu.SALIR:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    System.out.println("Presiona ENTER para continuar...");
                    consola.nextLine();
            }  
        }while(continuar);
        consola.close();
    }
}