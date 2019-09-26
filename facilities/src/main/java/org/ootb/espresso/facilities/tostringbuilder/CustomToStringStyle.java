package org.ootb.espresso.facilities.tostringbuilder;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomToStringStyle extends ToStringStyle implements Serializable {
 
 
    /**
     * 
     */
    private static final long serialVersionUID = 5344295789811593505L;

    public static final ToStringStyle NO_NULL_MULTI_LINE_STYLE = new NoNullMultiLineToStringStyle();
    public static final ToStringStyle NO_NULL_NO_CLASS_NAME_MULTI_LINE_STYLE = new NoNullNoClassNameMultiLineToStringStyle();
    public static final ToStringStyle NO_NULL_NO_CLASS_NAME_NO_HASH_CODE_MULTI_LINE_STYLE = new NoNullNoClassNameNoHashCodeMultiLineToStringStyle();
    
    private static class NoNullMultiLineToStringStyle extends ToStringStyle {

        private static final long serialVersionUID = 1L;

        /**
         * <p>Constructor.</p>
         *
         * <p>Use the static constant rather than instantiating.</p>
         */
        NoNullMultiLineToStringStyle() {
            super();
            this.setContentStart("[");
            this.setFieldSeparator(System.lineSeparator() + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(System.lineSeparator() + "]");
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
            if (value != null) {
                super.append(buffer, fieldName, value, fullDetail);
            }
        }
        
        /**
         * <p>Ensure <code>Singleton</code> after serialization.</p>
         *
         * @return the singleton
         */
        private Object readResolve() {
            return NO_NULL_MULTI_LINE_STYLE;
        }

    }
    
    private static final class NoNullNoClassNameMultiLineToStringStyle extends NoNullMultiLineToStringStyle {
        
        private static final long serialVersionUID = 1L;
        
        /**
         * <p>Constructor.</p>
         *
         * <p>Use the static constant rather than instantiating.</p>
         */
        NoNullNoClassNameMultiLineToStringStyle() {
            this.setUseClassName(false);
            this.setUseIdentityHashCode(true);
        }
        
        
        /**
         * <p>Ensure <code>Singleton</code> after serialization.</p>
         *
         * @return the singleton
         */
        private Object readResolve() {
            return NO_NULL_MULTI_LINE_STYLE;
        }
        
    }
    
    private static final class NoNullNoClassNameNoHashCodeMultiLineToStringStyle extends NoNullMultiLineToStringStyle {
        
        private static final long serialVersionUID = 1L;
        
        /**
         * <p>Constructor.</p>
         *
         * <p>Use the static constant rather than instantiating.</p>
         */
        NoNullNoClassNameNoHashCodeMultiLineToStringStyle() {
            this.setUseClassName(false);
            this.setUseIdentityHashCode(false);
        }
        
        
        /**
         * <p>Ensure <code>Singleton</code> after serialization.</p>
         *
         * @return the singleton
         */
        private Object readResolve() {
            return NO_NULL_MULTI_LINE_STYLE;
        }
        
    }

}