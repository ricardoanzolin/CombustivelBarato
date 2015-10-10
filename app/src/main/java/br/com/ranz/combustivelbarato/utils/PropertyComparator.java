package br.com.ranz.combustivelbarato.utils;

import java.util.Comparator;

/**
 * Created by Ricardo on 10/10/2015.
 */
public class PropertyComparator implements Comparator {

    private String[] propertyNames;
    private boolean[] naturalOrder;


    public PropertyComparator(String propertyName, boolean naturalOrder) {
        this( new String[] { propertyName }, naturalOrder );
    }

    public PropertyComparator(String[] propertyNames, boolean naturalOrder) {
        this(propertyNames, new boolean[]{naturalOrder});
    }


    public PropertyComparator(String[] propertyNames, boolean[] naturalOrder) {
        this.propertyNames = propertyNames;
        this.naturalOrder = new boolean[propertyNames.length];

        for (int i=0; i < propertyNames.length; i++) {
            if (i < naturalOrder.length) {
                this.naturalOrder[i] = naturalOrder[i];
            } else {
                this.naturalOrder[i] = true;
            }
        }
    }


    public int compare(Object object1, Object object2 ) {
        try {
            int max = propertyNames.length;

            int comparison = 0;
            int indexComparation = 0;

            for( int ind = 0; ind < max; ind++ ) {
                String propertyName = propertyNames[ind];

                Comparable property1 = getValue(object1.getClass().getDeclaredMethod("get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1)).invoke(object1));
                Comparable property2 = getValue(object2.getClass().getDeclaredMethod("get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1)).invoke(object2));

                if (property1 instanceof String) {
                    // Se a compara��o � feita entre Strings o sistema desconsidera os acentos e a caixa alta e baixa
                    comparison = StringUtils.removerAcentos(((String) property1).toUpperCase()).compareTo(StringUtils.removerAcentos(((String)property2).toUpperCase()));
                } else {
                    comparison = property1.compareTo(property2);
                }

                if ( comparison != 0 ) {
                    indexComparation = ind;
                    break;
                }
            }

            if ( ! naturalOrder[indexComparation] ) {
                comparison = -comparison;
            }

            return comparison;
        } catch ( Exception e ) {
            return 0;
        }
    }

    private Comparable getValue( Object property ) {
        return (Comparable) property;
    }
}
