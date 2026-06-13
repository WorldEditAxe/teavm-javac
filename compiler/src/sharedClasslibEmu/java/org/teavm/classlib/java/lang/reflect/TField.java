/*
 *  Copyright 2016 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.classlib.java.lang.reflect;

import org.teavm.classlib.PlatformDetector;
import org.teavm.classlib.java.lang.TClass;
import org.teavm.classlib.java.lang.TIllegalAccessException;
import org.teavm.classlib.java.lang.TIllegalArgumentException;
import org.teavm.classlib.java.lang.TObject;
import org.teavm.classlib.java.lang.annotation.TAnnotation;
import org.teavm.interop.Address;
import org.teavm.interop.Unmanaged;
import org.teavm.runtime.GC;
import org.teavm.runtime.reflect.AnnotationInfoUtil;
import org.teavm.runtime.reflect.ClassInfo;
import org.teavm.runtime.reflect.ClassInfoUtil;
import org.teavm.runtime.reflect.FieldInfo;
import org.teavm.runtime.reflect.ModifiersInfo;

public class TField extends TAccessibleObject implements TMember {
    private TClass<?> declaringClass;
    private FieldInfo fieldInfo;
    private TAnnotation[] declaredAnnotations;
    private TType genericType;
    private boolean genericTypeInitialized;

    public TField(TClass<?> declaringClass, FieldInfo fieldInfo) {
        this.declaringClass = declaringClass;
        this.fieldInfo = fieldInfo;
    }

    @Override
    public TClass<?> getDeclaringClass() {
        return declaringClass;
    }

    @Override
    public String getName() {
        return fieldInfo.name().getStringObject();
    }

    @Override
    public int getModifiers() {
        return fieldInfo.modifiers() & ModifiersInfo.JVM_FLAGS_MASK;
    }

    public boolean isEnumConstant() {
        return (fieldInfo.modifiers() & ModifiersInfo.ENUM) != 0;
    }

    @Override
    public boolean isSynthetic() {
        return (fieldInfo.modifiers() & ModifiersInfo.SYNTHETIC) != 0;
    }

    public TClass<?> getType() {
        return (TClass<?>) (Object) ClassInfoUtil.resolve(fieldInfo.type()).classObject();
    }

    public TType getGenericType() {
        if (!genericTypeInitialized) {
            genericTypeInitialized = true;
            var reflectionInfo = fieldInfo.reflection();
            if (reflectionInfo != null && reflectionInfo.genericType() != null) {
                genericType = TGenericTypeFactory.create(declaringClass, reflectionInfo.genericType());
            } else {
                genericType = getType();
            }
        }
        return genericType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TModifier.toString(getModifiers()));
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(getType().getName()).append(' ').append(declaringClass.getName()).append(".").append(getName());
        return sb.toString();
    }

    public Object get(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        checkGetAccess();
        checkInstance(obj);
        return getWithoutCheck(obj);
    }

    public boolean getBoolean(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }
        throw new TIllegalArgumentException();
    }

    public byte getByte(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        throw new TIllegalArgumentException();
    }

    public char getChar(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Character charValue) {
            return charValue;
        }
        throw new TIllegalArgumentException();
    }

    public short getShort(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        if (value instanceof Short shortValue) {
            return shortValue;
        }
        throw new TIllegalArgumentException();
    }

    public int getInt(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        if (value instanceof Short shortValue) {
            return shortValue;
        }
        if (value instanceof Character charValue) {
            return charValue;
        }
        if (value instanceof Integer intValue) {
            return intValue;
        }
        throw new TIllegalArgumentException();
    }

    public long getLong(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        if (value instanceof Short shortValue) {
            return shortValue;
        }
        if (value instanceof Character charValue) {
            return charValue;
        }
        if (value instanceof Integer intValue) {
            return intValue;
        }
        if (value instanceof Long longValue) {
            return longValue;
        }
        throw new TIllegalArgumentException();
    }

    public float getFloat(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        if (value instanceof Short shortValue) {
            return shortValue;
        }
        if (value instanceof Character charValue) {
            return charValue;
        }
        if (value instanceof Integer intValue) {
            return intValue;
        }
        if (value instanceof Long longValue) {
            return longValue;
        }
        if (value instanceof Float floatValue) {
            return floatValue;
        }
        throw new TIllegalArgumentException();
    }

    public double getDouble(Object obj) throws TIllegalArgumentException, TIllegalAccessException {
        var value = get(obj);
        if (value instanceof Byte byteValue) {
            return byteValue;
        }
        if (value instanceof Short shortValue) {
            return shortValue;
        }
        if (value instanceof Character charValue) {
            return charValue;
        }
        if (value instanceof Integer intValue) {
            return intValue;
        }
        if (value instanceof Long longValue) {
            return longValue;
        }
        if (value instanceof Float floatValue) {
            return floatValue;
        }
        if (value instanceof Double doubleValue) {
            return doubleValue;
        }
        throw new TIllegalArgumentException();
    }

    public Object getWithoutCheck(Object obj) {
        if ((fieldInfo.modifiers() & ModifiersInfo.STATIC) != 0) {
            declaringClass.initialize();
        }
        return fieldInfo.read(obj);
    }

    public void set(Object obj, Object value) throws TIllegalArgumentException, TIllegalAccessException {
        checkSetAccess();
        checkInstance(obj);
        setWithoutCheck(obj, value);
    }

    public void setBoolean(Object obj, boolean value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setByte(Object obj, byte value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setChar(Object obj, char value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setShort(Object obj, short value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setInt(Object obj, int value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setLong(Object obj, long value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setFloat(Object obj, float value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    public void setDouble(Object obj, double value) throws TIllegalArgumentException, TIllegalAccessException {
        set(obj, value);
    }

    @Unmanaged
    public void setWithoutCheck(Object obj, Object value) {
        if ((fieldInfo.modifiers() & ModifiersInfo.STATIC) != 0) {
            declaringClass.initialize();
        } else if (PlatformDetector.requiresOwnGC() && fieldInfo.type().arrayDegree() == 0
                && fieldInfo.type().classInfo().primitiveKind() == ClassInfo.PrimitiveKind.NOT) {
            GC.writeBarrier(Address.ofObject(obj).toStructure());
        }
        fieldInfo.write(obj, value);
    }

    private void checkInstance(Object obj) {
        if ((fieldInfo.modifiers() & ModifiersInfo.STATIC) == 0) {
            if (obj == null) {
                throw new NullPointerException();
            }
            if (!declaringClass.isInstance((TObject) obj)) {
                throw new TIllegalArgumentException();
            }
        }
    }

    public void checkGetAccess() throws TIllegalAccessException {
    }

    public void checkSetAccess() throws TIllegalAccessException {
    }

    @Override
    public TAnnotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    @Override
    public TAnnotation[] getDeclaredAnnotations() {
        if (declaredAnnotations == null) {
            var reflectionInfo = fieldInfo.reflection();
            if (reflectionInfo != null) {
                var count = reflectionInfo.annotationCount();
                declaredAnnotations = new TAnnotation[count];
                for (var i = 0; i < count; ++i) {
                    declaredAnnotations[i] = (TAnnotation) AnnotationInfoUtil.createAnnotation(
                            reflectionInfo.annotation(i));
                }
            } else {
                declaredAnnotations = new TAnnotation[0];
            }
        }
        return declaredAnnotations.clone();
    }
}
