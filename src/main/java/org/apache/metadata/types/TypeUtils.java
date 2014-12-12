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

import org.apache.metadata.MetadataException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeUtils {

    public static void outputVal(String val, Appendable buf, String prefix) throws MetadataException {
        try {
            buf.append(prefix).append(val);
        } catch(IOException ie) {
            throw new MetadataException(ie);
        }
    }

    public static final String NAME_REGEX = "[a-zA-z][a-zA-Z0-9_]*";
    public static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    public static void validateName(String name) throws MetadataException {
        if ( !NAME_PATTERN.matcher(name).matches() ) {
            throw new MetadataException(String.format("Unsupported name for an attribute '%s'", name));
        }
    }

    public static final Pattern ARRAY_TYPE_NAME_PATTERN = Pattern.compile(String.format("array<(%s)>", NAME_REGEX));
    public static final Pattern MAP_TYPE_NAME_PATTERN =
            Pattern.compile(String.format("map<(%s),(%s)>", NAME_REGEX, NAME_REGEX));

    public static String parseAsArrayType(String typeName) {
        Matcher m = ARRAY_TYPE_NAME_PATTERN.matcher(typeName);
        return m.matches() ?  m.group(1) : null;
    }

    public static String[] parseAsMapType(String typeName) {
        Matcher m = MAP_TYPE_NAME_PATTERN.matcher(typeName);
        return m.matches() ?  new String[] {m.group(1), m.group(2)} : null;
    }

}
