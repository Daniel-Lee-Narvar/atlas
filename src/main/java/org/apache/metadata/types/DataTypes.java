/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.metadata.types;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableCollection.Builder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.metadata.storage.IRepository;
import org.apache.metadata.MetadataException;
import org.apache.metadata.MetadataService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

public class DataTypes {

    public static enum TypeCategory {
        PRIMITIVE,
        ARRAY,
        MAP,
        STRUCT,
        TRAIT,
        CLASS;
    };

    static abstract class PrimitiveType<T> extends AbstractDataType<T> {
        @Override
        public TypeCategory getTypeCategory() {
            return TypeCategory.PRIMITIVE;
        }

    }

    public static BooleanType BOOLEAN_TYPE = new BooleanType();
    public static class BooleanType extends PrimitiveType<Boolean> {

        private static final String name = "boolean".intern();

        private BooleanType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Boolean convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Boolean) {
                    return (Boolean)val;
                } else if ( val instanceof  String) {
                    return Boolean.parseBoolean((String)val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).intValue() != 0;
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static ByteType BYTE_TYPE = new ByteType();
    public static class ByteType extends PrimitiveType<Byte> {

        private static final String name = "byte".intern();

        private ByteType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Byte convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Byte) {
                    return (Byte) val;
                } else if ( val instanceof  String) {
                    return Byte.parseByte((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).byteValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static ShortType SHORT_TYPE = new ShortType();
    public static class ShortType extends PrimitiveType<Short> {

        private static final String name = "short".intern();

        private ShortType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Short convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Short) {
                    return (Short) val;
                } else if ( val instanceof  String) {
                    return Short.parseShort((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).shortValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static IntType INT_TYPE = new IntType();
    public static class IntType extends PrimitiveType<Integer> {

        private static final String name = "int".intern();

        private IntType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Integer) {
                    return (Integer) val;
                } else if ( val instanceof  String) {
                    return Integer.parseInt((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).intValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static LongType LONG_TYPE = new LongType();
    public static class LongType extends PrimitiveType<Long> {

        private static final String name = "long".intern();

        private LongType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Long convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Long) {
                    return (Long) val;
                } else if ( val instanceof  String) {
                    return Long.parseLong((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).longValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static FloatType FLOAT_TYPE = new FloatType();
    public static class FloatType extends PrimitiveType<Float> {

        private static final String name = "float".intern();

        private FloatType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Float convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Float) {
                    return (Float) val;
                } else if ( val instanceof  String) {
                    return Float.parseFloat((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).floatValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static DoubleType DOUBLE_TYPE = new DoubleType();
    public static class DoubleType extends PrimitiveType<Double> {

        private static final String name = "double".intern();

        private DoubleType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Double convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Double) {
                    return (Double) val;
                } else if ( val instanceof  String) {
                    return Double.parseDouble((String) val);
                } else if ( val instanceof Number ) {
                    return ((Number)val).doubleValue();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static BigIntegerType BIGINTEGER_TYPE = new BigIntegerType();
    public static class BigIntegerType extends PrimitiveType<BigInteger> {

        private static final String name = "biginteger".intern();

        private BigIntegerType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public BigInteger convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof BigInteger) {
                    return (BigInteger) val;
                } else if ( val instanceof  String) {
                    try {
                        return new BigInteger((String) val);
                    } catch(NumberFormatException ne) {
                        throw new ValueConversionException(this, val, ne);
                    }
                } else if ( val instanceof Number ) {
                    return BigInteger.valueOf(((Number) val).longValue());
                } else if ( val instanceof BigDecimal) {
                    return ((BigDecimal)val).toBigInteger();
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static BigDecimalType BIGDECIMAL_TYPE = new BigDecimalType();
    public static class BigDecimalType extends PrimitiveType<BigDecimal> {

        private static final String name = "bigdecimal".intern();

        private BigDecimalType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public BigDecimal convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof BigDecimal) {
                    return (BigDecimal) val;
                } else if ( val instanceof  String) {
                    try {
                        return new BigDecimal((String) val);
                    } catch(NumberFormatException ne) {
                        throw new ValueConversionException(this, val, ne);
                    }
                } else if ( val instanceof Number ) {
                    return new BigDecimal(((Number)val).doubleValue());
                } else if ( val instanceof BigInteger) {
                    return new BigDecimal((BigInteger)val);
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static DateType DATE_TYPE = new DateType();
    public static class DateType extends PrimitiveType<Date> {

        private static final String name = "date".intern();

        private DateType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Date convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                if ( val instanceof Date) {
                    return (Date) val;
                } else if ( val instanceof  String) {
                    try {
                        return MetadataService.getCurrentRepository().getDateFormat().parse((String)val);
                    } catch(ParseException ne) {
                        throw new ValueConversionException(this, val, ne);
                    }
                } else if ( val instanceof Number ) {
                    return new Date(((Number)val).longValue());
                } else {
                    throw new ValueConversionException(this, val);
                }
            }
            return convertNull(m);
        }
    }

    public static StringType STRING_TYPE = new StringType();
    public static class StringType extends PrimitiveType<String> {

        private static final String name = "string".intern();

        private StringType() {}

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String convert(Object val, Multiplicity m) throws MetadataException {
            if ( val != null ) {
                return val.toString();
            }
            return convertNull(m);
        }
    }

    static String ARRAY_TYPE_PREFIX = "array<";
    static String ARRAY_TYPE_SUFFIX = ">";
    public static class ArrayType extends AbstractDataType<ImmutableCollection<?>> {

        private IDataType elemType;
        private final String nm;

        public ArrayType(IDataType elemType) {
            assert elemType != null;
            this.elemType = elemType;
            this.nm = String.format("%s%s%s", ARRAY_TYPE_PREFIX, elemType.getName(), ARRAY_TYPE_SUFFIX);
        }

        public IDataType getElemType() {
            return elemType;
        }

        protected void setElemType(IDataType elemType) {
            this.elemType = elemType;
        }

        @Override
        public String getName() {
            return nm;
        }

        @Override
        public ImmutableCollection<?> convert(Object val, Multiplicity m) throws MetadataException {
            IRepository r = MetadataService.getCurrentRepository();
            if ( val != null ) {
                Iterator it = null;
                if ( val instanceof Collection ) {
                    it = ((Collection)val).iterator();
                } else if ( val instanceof Iterable ) {
                    it = ((Iterable)val).iterator();
                } else if ( val instanceof Iterator) {
                    it = (Iterator)val;
                }
                if ( it != null ) {
                    ImmutableCollection.Builder<?> b = m.isUnique ? ImmutableSet.builder() : ImmutableList.builder();
                    while (it.hasNext() ) {
                        b.add(elemType.convert(it.next(),
                                r.allowNullsInCollections() ? Multiplicity.OPTIONAL : Multiplicity.REQUIRED));
                    }
                    return b.build();
                }
                else {
                    try {
                        return ImmutableList.of(elemType.convert(val,
                                r.allowNullsInCollections() ? Multiplicity.OPTIONAL : Multiplicity.REQUIRED));
                    } catch(Exception e) {
                        throw new ValueConversionException(this, val, e);
                    }
                }
            }
            if (!m.nullAllowed() ) {
                throw new ValueConversionException.NullConversionException(m);
            }
            return null;
        }

        @Override
        public TypeCategory getTypeCategory() {
            return TypeCategory.ARRAY;
        }
    }

    static String MAP_TYPE_PREFIX = "map<";
    static String MAP_TYPE_SUFFIX = ">";
    public static class MapType extends AbstractDataType<ImmutableMap<?, ?>> {

        private IDataType keyType;
        private IDataType valueType;
        private final String nm;

        public MapType(IDataType keyType, IDataType valueType) {
            assert keyType != null;
            assert valueType != null;
            this.keyType = keyType;
            this.valueType = valueType;
            this.nm = String.format("%s%s,%s%s", MAP_TYPE_PREFIX,
                    keyType.getName(), valueType.getName(), MAP_TYPE_SUFFIX);
        }

        public IDataType getKeyType() {
            return keyType;
        }

        public IDataType getValueType() {
            return valueType;
        }

        protected void setKeyType(IDataType keyType) {
            this.keyType = keyType;
        }

        protected void setValueType(IDataType valueType) {
            this.keyType = valueType;
        }

        @Override
        public String getName() {
            return nm;
        }

        @Override
        public ImmutableMap<?, ?> convert(Object val, Multiplicity m) throws MetadataException {
            IRepository r = MetadataService.getCurrentRepository();
            if ( val != null ) {
                Iterator<Map.Entry> it = null;
                if ( Map.class.isAssignableFrom(val.getClass())) {
                    it = ((Map)val).entrySet().iterator();
                    ImmutableMap.Builder b = ImmutableMap.builder();
                    while (it.hasNext() ) {
                        Map.Entry e = it.next();
                        b.put(keyType.convert(e.getKey(),
                                r.allowNullsInCollections() ? Multiplicity.OPTIONAL : Multiplicity.REQUIRED),
                                valueType.convert(e.getValue(),
                                        r.allowNullsInCollections() ? Multiplicity.OPTIONAL : Multiplicity.REQUIRED));
                    }
                    return b.build();
                }
                else {
                    throw new ValueConversionException(this, val);
                }
            }
            if (!m.nullAllowed() ) {
                throw new ValueConversionException.NullConversionException(m);
            }
            return null;
        }

        @Override
        public TypeCategory getTypeCategory() {
            return TypeCategory.MAP;
        }
    }

}
