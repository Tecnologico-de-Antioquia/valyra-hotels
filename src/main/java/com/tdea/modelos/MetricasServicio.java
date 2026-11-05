package com.tdea.modelos;

import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.MetodoPago;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class MetricasServicio {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();
    private static ListaHuespedes listaHuespedes = ListaHuespedes.getInstancia();
    private static ListaServicios listaServicios = ListaServicios.getInstancia();
    private static PilaServicios pilaServicios = PilaServicios.getInstancia();
    private static PagoServicio pagoServicio = PagoServicio.getInstancia();
    private static ColaMasaje colaMasaje = ColaMasaje.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private static final String MENOR_CINCO = "Menor de 5 años";
    private static final String MAYOR_SESENTA = "Mayor de 60 años";
    private static final String ENTRE_CINCO_Y_SESENTA = "Entre 5 y 60 años";

    public void mostarMetricas() {
        consola.mostrarTexto("\n---MÉTRICAS DEL HOTEL---\n", CYAN);
        mostrarCantidadHuespedes();
        mostrarCantidadHuespedesPorGenero();
        mostrarIngresosPorGenero();
        mostrarIngresoTotal();
        mostrarIngresosPorMetodoPago();
        mostrarIngresosPorServicio();
        mostrarTotalDescuentos();
        mostrarTotalPorGrupoEtario();
        mostrarHuespedMayorYMenorPago();
        mostrarPorcentajeHuespedesPorEdad();
        mostrarPorcentajeIngresosPorServicio();
        mostrarPorcentajeHuespedesEIngresosPorMetodoPago();
        mostrarPorcentajeHuespedesPorGenero();
        mostrarPorcentajeDineroPorMetodoPago();
        mostrarDineroPorRangoEdad();
        mostrarCantidadColaMasaje();
        mostrarPromedioPersonasMasaje();
        mostrarPromedioColaAtencion();
        consola.mostrarTexto("");
    }

    public void mostrarCantidadHuespedes() {
        consola.mostrarTexto("■ Cantidad de huéspedes que se han hospedado: " + listaHuespedes.contarHuespedes());
    }

    public void mostrarCantidadHuespedesPorGenero() {
        int resultadoH = 0;
        int resultadoM = 0;
        int resultadoI = 0;

        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            if (huesped.getGenero() == Generos.Hombre) {
                resultadoH += 1;
            } else if (huesped.getGenero() == Generos.Mujer) {
                resultadoM += 1;
            } else {
                resultadoI += 1;
            }
            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Cantidad de huéspedes por género: " + "Hombres: " + resultadoH + " - Mujeres: "
                + resultadoM + " - Indeterminado: " + resultadoI);
    }

    public void mostrarIngresosPorGenero() {
        double ingresosH = 0;
        double ingresosM = 0;
        double ingresosI = 0;

        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            if (huesped.getGenero() == Generos.Hombre) {
                ingresosH += huesped.getTotalPagado();
            } else if (huesped.getGenero() == Generos.Mujer) {
                ingresosM += huesped.getTotalPagado();
            } else {
                ingresosI += huesped.getTotalPagado();
            }
            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Ingresos por género: Hombres: " + consola.formatearMoneda(ingresosH)
                + " - Mujeres: " + consola.formatearMoneda(ingresosM)
                + " - Indeterminado: " + consola.formatearMoneda(ingresosI));
    }

    public void mostrarIngresoTotal() {
        consola.mostrarTexto("■ Ingreso total del hotel: " + consola.formatearMoneda(ingresoTotal()));
    }

    public void mostrarIngresosPorMetodoPago() {
        consola.mostrarTexto("■ Ingresos por método de pago:");

        for (MetodoPago metodoPago : MetodoPago.values()) {
            consola.mostrarTexto("   - " + metodoPago + ": " + consola.formatearMoneda(ingresoPorMetodoPago(metodoPago))
                    + " (" + pagoServicio.getCantidad(metodoPago) + " huéspedes)");
        }
    }

    public void mostrarIngresosPorServicio() {
        consola.mostrarTexto("■ Ingresos por servicio:");

        NodoSimple<Servicio> auxiliar = listaServicios.lista;

        if (auxiliar == null) {
            consola.mostrarTexto("   No hay servicios creados");
            return;
        }

        while (auxiliar != null) {
            Servicio servicio = auxiliar.getValor();
            consola.mostrarTexto("   - " + servicio.getNombre() + ": "
                    + consola.formatearMoneda(pilaServicios.totalPorServicio(servicio.getCodigo()))
                    + " (" + pilaServicios.contarPorServicio(servicio.getCodigo()) + " huéspedes)");
            auxiliar = auxiliar.getSiguiente();
        }
    }

    public void mostrarTotalDescuentos() {
        double totalDescuentos = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            totalDescuentos += auxiliar.getValor().getDescuento();
            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Total de descuentos otorgados: " + consola.formatearMoneda(totalDescuentos));
    }

    public void mostrarTotalPorGrupoEtario() {
        consola.mostrarTexto("■ Total generado por grupo de edad (antes de descuento):");
        consola.mostrarTexto("   - " + MENOR_CINCO + ": " + consola.formatearMoneda(subtotalPorGrupo(MENOR_CINCO)));
        consola.mostrarTexto("   - " + MAYOR_SESENTA + ": " + consola.formatearMoneda(subtotalPorGrupo(MAYOR_SESENTA)));
        consola.mostrarTexto("   - " + ENTRE_CINCO_Y_SESENTA + ": "
                + consola.formatearMoneda(subtotalPorGrupo(ENTRE_CINCO_Y_SESENTA)));
    }

    public void mostrarHuespedMayorYMenorPago() {
        Huesped mayor = null;
        Huesped menor = null;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();

            if (huesped.getMetodoPago() != null) {
                if (mayor == null || huesped.getTotalPagado() > mayor.getTotalPagado()) {
                    mayor = huesped;
                }

                if (menor == null || huesped.getTotalPagado() < menor.getTotalPagado()) {
                    menor = huesped;
                }
            }

            auxiliar = auxiliar.getSiguiente();
        }

        if (mayor == null) {
            consola.mostrarTexto("■ Huésped que pagó más y menos: no hay pagos registrados");
            return;
        }

        consola.mostrarTexto("■ Huésped que pagó más: " + mayor.getNombre() + " - "
                + consola.formatearMoneda(mayor.getTotalPagado()));
        consola.mostrarTexto("■ Huésped que pagó menos: " + menor.getNombre() + " - "
                + consola.formatearMoneda(menor.getTotalPagado()));
    }

    public void mostrarPorcentajeHuespedesPorEdad() {
        double total = listaHuespedes.contarHuespedes();

        consola.mostrarTexto("■ Porcentaje de huéspedes por rango de edad:");
        consola.mostrarTexto("   - " + MENOR_CINCO + ": "
                + porcentaje(contarPorGrupo(MENOR_CINCO), total) + "%");
        consola.mostrarTexto("   - " + MAYOR_SESENTA + ": "
                + porcentaje(contarPorGrupo(MAYOR_SESENTA), total) + "%");
        consola.mostrarTexto("   - " + ENTRE_CINCO_Y_SESENTA + ": "
                + porcentaje(contarPorGrupo(ENTRE_CINCO_Y_SESENTA), total) + "%");
    }

    public void mostrarPorcentajeIngresosPorServicio() {
        double totalIngresos = ingresoTotal();

        consola.mostrarTexto("■ Porcentaje de ingresos por servicio:");
        NodoSimple<Servicio> auxiliar = listaServicios.lista;

        while (auxiliar != null) {
            Servicio servicio = auxiliar.getValor();
            consola.mostrarTexto("   - " + servicio.getNombre() + ": "
                    + porcentaje(pilaServicios.totalPorServicio(servicio.getCodigo()), totalIngresos) + "%");
            auxiliar = auxiliar.getSiguiente();
        }
    }

    public void mostrarPorcentajeHuespedesEIngresosPorMetodoPago() {
        double totalHuespedes = listaHuespedes.contarHuespedes();
        double totalIngresos = ingresoTotal();

        consola.mostrarTexto("■ Porcentaje de huéspedes e ingresos por método de pago:");

        for (MetodoPago metodoPago : MetodoPago.values()) {
            consola.mostrarTexto("   - " + metodoPago + ": "
                    + porcentaje(pagoServicio.getCantidad(metodoPago), totalHuespedes)
                    + "% de huéspedes - "
                    + porcentaje(ingresoPorMetodoPago(metodoPago), totalIngresos) + "% de ingresos");
        }
    }

    public void mostrarPorcentajeHuespedesPorGenero() {
        int resultadoH = 0;
        int resultadoM = 0;
        int resultadoI = 0;
        int total = 0;

        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            total += 1;

            if (huesped.getGenero() == Generos.Hombre) {
                resultadoH += 1;
            } else if (huesped.getGenero() == Generos.Mujer) {
                resultadoM += 1;
            } else {
                resultadoI += 1;
            }

            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Porcentaje de huéspedes por género: Hombres: "
                + porcentaje(resultadoH, total) + "% - Mujeres: "
                + porcentaje(resultadoM, total) + "% - Indeterminado: "
                + porcentaje(resultadoI, total) + "%");
    }

    public void mostrarPorcentajeDineroPorMetodoPago() {
        double totalIngresos = ingresoTotal();

        consola.mostrarTexto("■ Porcentaje del dinero recaudado por método de pago:");

        for (MetodoPago metodoPago : MetodoPago.values()) {
            consola.mostrarTexto("   - " + metodoPago + ": "
                    + porcentaje(ingresoPorMetodoPago(metodoPago), totalIngresos) + "%");
        }
    }

    public void mostrarDineroPorRangoEdad() {
        consola.mostrarTexto("■ Dinero recaudado por rango de edad (después de descuento):");
        consola.mostrarTexto("   - " + MENOR_CINCO + ": " + consola.formatearMoneda(totalPagadoPorGrupo(MENOR_CINCO)));
        consola.mostrarTexto("   - " + MAYOR_SESENTA + ": "
                + consola.formatearMoneda(totalPagadoPorGrupo(MAYOR_SESENTA)));
        consola.mostrarTexto("   - " + ENTRE_CINCO_Y_SESENTA + ": "
                + consola.formatearMoneda(totalPagadoPorGrupo(ENTRE_CINCO_Y_SESENTA)));
    }

    public void mostrarCantidadColaMasaje() {
        String enServicio = colaMasaje.hayHuespedEnServicio() ? "1" : "0";
        consola.mostrarTexto("■ Personas actualmente en la cola de masaje: "
                + colaMasaje.getCantidadEnEspera() + " en espera - " + enServicio + " en servicio");
    }

    public void mostrarPromedioPersonasMasaje() {
        int totalHuespedes = listaHuespedes.contarHuespedes();
        int atendidos = colaMasaje.getTotalAtendidos();
        double promedio = totalHuespedes == 0 ? 0 : (double) atendidos / totalHuespedes;

        consola.mostrarTexto("■ Personas que han usado el masaje: " + atendidos
                + " - Promedio por huésped: " + String.format("%.2f", promedio));
    }

    public void mostrarPromedioColaAtencion() {
        consola.mostrarTexto("■ Promedio de personas en la cola de check-in: "
                + String.format("%.2f", colaAtencion.getPromedioCola()));
    }

    private double ingresoTotal() {
        double total = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            total += auxiliar.getValor().getTotalPagado();
            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    private double ingresoPorMetodoPago(MetodoPago metodoPago) {
        double total = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();

            if (huesped.getMetodoPago() == metodoPago) {
                total += huesped.getTotalPagado();
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    private double subtotalPorGrupo(String grupo) {
        double total = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();

            if (grupoEtario(huesped.getEdad()).equals(grupo)) {
                total += huesped.getSubtotal();
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    private double totalPagadoPorGrupo(String grupo) {
        double total = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();

            if (grupoEtario(huesped.getEdad()).equals(grupo)) {
                total += huesped.getTotalPagado();
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    private int contarPorGrupo(String grupo) {
        int cantidad = 0;
        NodoSimple<Huesped> auxiliar = listaHuespedes.lista;

        while (auxiliar != null) {
            if (grupoEtario(auxiliar.getValor().getEdad()).equals(grupo)) {
                cantidad += 1;
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return cantidad;
    }

    private String grupoEtario(Integer edad) {
        if (edad < 5) {
            return MENOR_CINCO;
        } else if (edad > 60) {
            return MAYOR_SESENTA;
        }

        return ENTRE_CINCO_Y_SESENTA;
    }

    private String porcentaje(double parte, double total) {
        if (total == 0) {
            return "0.00";
        }

        return String.format("%.2f", (parte / total) * 100);
    }
}
