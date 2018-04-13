package com.example.jacob.justjava30;

/**
 * Created by jacob on 12/04/2018.
 */

public class Metodos {


    public double Biseccion(double a, double b, double c,
                            double d, double e, double x, double y, double n) {
        double pn = 0, an, bn, fa = 0, fb, fpn;

        for (int i = 1; i <= n; i++) {
            an = x;//asigna limite inferior a punto a
            bn = y;//asigna limite superior a punto b
            pn = an + (bn - an) / 2;//formula de punto intermedio de limites
            fpn = a * Math.pow(pn, 4) + b * Math.pow(pn, 3) + c * Math.pow(pn, 2) + d * x + e;
            fa = a * Math.pow(an, 4) + b * Math.pow(an, 3) + c * Math.pow(an, 2) + d * x + e;
            fb = a * Math.pow(bn, 4) + b * Math.pow(bn, 3) + c * Math.pow(bn, 2) + d * x + e;

            //System.out.println("Pn: "+pn+" Fpn: "+fpn+" Fa: "+fa+" Fb: "+fb);
            if (fa * fb > 0) {
                y = pn;
            }
            if (fa * fb < 0) {
                x = pn;
            }
            if (fa * fb == 0) {
                //System.out.println("No hay Raiz");
            }
        }
        return pn;
    }

    public double ReglaFalsa(double a, double b, double c,
                             double d, double e, double x, double y) {
        double r1, r2, r3, r4, rf;
        r1 = x * ((a * Math.pow(y, 4)) + (b * Math.pow(y, 3)) + (c * Math.pow(y, 2)) + d * y + e);
        r2 = y * ((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + d * x + e);
        r3 = ((a * Math.pow(y, 4)) + (b * Math.pow(y, 3)) + (c * Math.pow(y, 2)) + d * y + e);
        r4 = ((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + d * x + e);

        System.out.println("El resultado est :" + r1);
        System.out.println("El resultado es :" + r2);
        System.out.println("El resultado es :" + r3);
        System.out.println("El resultado es :" + r4);

        rf = (r1 - r2) / (r3 - r4);
        System.out.println("El resultado final es: " + rf);
        return rf;
    }

    public double Secante(double a, double b, double c,
                          double d, double e, double p0, double pi, double n) {
        double r1, r2, r3;
        double rf = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                rf = p0;
                System.out.println("El resultado en p0 es :" + rf);
            }
            if (i == 1) {
                rf = pi;
                System.out.println("El resultado en p1 es :" + rf);

            } else {
                r1 = a * Math.pow(p0, 4) + b * Math.pow(p0, 3) + c * Math.pow(p0, 2) + (d * (p0)) + e;//Funcion evaluada en pn-1

                r2 = (pi) - (p0);                                                         //resta entre puntos

                r3 = a * Math.pow(pi, 4) + b * Math.pow(pi, 3) + c * Math.pow(pi, 2) + (d * (pi)) + e;//Funcion evaluada en pn-2

                rf = (pi) - (r3 * r2) / (r3 - r1);//Formula del metodo secante
                System.out.println("El resultado en p" + (i) + " " + rf);
                p0 = pi;
                r3 = r1;
                pi = rf;
            }
        }
        return rf;
    }

    public double Newton(double x, double y, double a, double b, double c,
                         double d, double e, double p0, double n, double t) {
        double er = 0, pn = p0, i = 1, fx, fy;
        fx = (a * Math.pow(x, 4) + b * Math.pow(x, 3) + c * Math.pow(x, 2) + d * x + e);//formula evaluada en x
        fy = (a * Math.pow(y, 4) + b * Math.pow(y, 3) + c * Math.pow(y, 2) + d * y + e);
        if ((fx * fy) > 0) {
            System.out.print("el intervalo no tiene raiz");
        } else {
            do {
                pn = p0 - (a * Math.pow(p0, 4) + b * Math.pow(p0, 3) + c * Math.pow(p0, 2) + d * p0 + e) / //formula del metodo newton
                        (4 * a * Math.pow(p0, 3) + 3 * b * Math.pow(p0, 2) + 2 * c * p0 + d);

                er = Math.abs(pn - p0);//error relativo
                if (er <= t) {
                    System.out.println(i + " resultado final " + pn + " con error " + er + " tolerancia" + t);//resultado
                    i = n;
                } else {//para comprobar se omite
                    System.out.println(i + " " + pn + " con error " + er);//para comprobar se omite
                }//para comprobar se omite
                p0 = pn;
                i++;
            } while ((i <= n));
        }
        return pn;
    }
}
